package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.exception.NoRestaurantFoundException;
import com.pragma.powerup.infrastructure.exception.DishFromDifferentRestaurantException;
import com.pragma.powerup.infrastructure.exception.NoDishFoundException;
import com.pragma.powerup.infrastructure.exception.NoUserFoundException;
import com.pragma.powerup.infrastructure.feign.twilio.dto.request.SMSRequestDto;
import com.pragma.powerup.infrastructure.feign.twilio.service.ITwilioFeignClientService;
import com.pragma.powerup.infrastructure.feign.user.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.OrderDishesModel;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoOrderFoundException;
import com.pragma.powerup.infrastructure.feign.user.service.IUserFeignClientService;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishesEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmpEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantEmpRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IDishRepository dishRepository;
    private final IRestaurantEmpRepository restaurantEmpRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IUserFeignClientService feignClientSpringService;
    private final ITwilioFeignClientService twilioFeignClientService;

    @Override
    public OrderModel saveOrder(OrderModel orderModel) {
        OrderEntity orderEntity = orderRepository.save(orderEntityMapper.toEntity(orderModel));
        List<OrderDishesModel> orderDishesModels = orderModel.getDishes();

        for (OrderDishesModel order : orderDishesModels){
            DishEntity dishEntity = dishRepository.findById(order.getDish())
                            .orElseThrow(NoDishFoundException::new);
            if (dishEntity.getRestaurant().getId() != orderModel.getRestaurant().getId()){
                throw new DishFromDifferentRestaurantException();
            }

            orderDishRepository.save(new OrderDishesEntity(
                    orderEntity,
                    dishEntity,
                    order.getQuantity()
            ));
        }
        return orderEntityMapper.toOrderModel(orderEntity);
    }

    @Override
    public List<OrderModel> getOrdersByStatus(Integer pageNumber, Integer pageSize, String status) {
        UserResponseDto userResponseDto = feignClientSpringService.getUserByEmail(usernameToken());

        RestaurantEmpEntity restaurantEmpEntity = restaurantEmpRepository
                .findById(userResponseDto.getId()).orElseThrow(NoUserFoundException::new);

        RestaurantEntity restaurant = restaurantRepository
                .findById(restaurantEmpEntity.getRestaurant()).orElseThrow(NoRestaurantFoundException::new);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return orderRepository.findByRestaurantAndStatus(restaurant, status, pageable)
                .stream()
                .map(orderEntityMapper::toOrderModel)
                .collect(Collectors.toList());
    }

    @Override
    public OrderModel setChef(Long id) {
        UserResponseDto userResponseDto = feignClientSpringService.getUserByEmail(usernameToken());
        RestaurantEmpEntity employee = restaurantEmpRepository.findById(userResponseDto.getId())
                .orElseThrow(NoUserFoundException::new);

        OrderEntity orderEntity = orderRepository
                .findById(id)
                .orElseThrow(NoOrderFoundException::new);

        orderEntity.setChef(employee);
        orderEntity.setStatus("EN_PREPARACION");

        orderRepository.save(orderEntity);
        return orderEntityMapper.toOrderModel(orderEntity);
    }

    @Override
    public OrderModel setOrderStatusReady(Long id) {
        OrderEntity orderEntity = orderRepository
                .findById(id)
                .orElseThrow(NoOrderFoundException::new);

        orderEntity.setStatus("LISTO");

        Integer randomCode = (int) Math.floor(Math.random() *(999999 - 100000 + 1) + 100000);
        orderEntity.setCode(randomCode);

        UserResponseDto userResponseDto = feignClientSpringService.getUserById(orderEntity.getClient());
        twilioFeignClientService.sendMessage(new SMSRequestDto(userResponseDto.getPhoneNumber(), Integer.toString(randomCode)));

        orderRepository.save(orderEntity);
        return orderEntityMapper.toOrderModel(orderEntity);
    }

    @Override
    public OrderModel setOrderStatusDelivered(Long id, Integer code) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(NoOrderFoundException::new);
        orderEntity.setStatus("ENTREGADO");
        orderRepository.save(orderEntity);
        return orderEntityMapper.toOrderModel(orderEntity);
    }

    @Override
    public OrderModel cancelOrder(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(NoOrderFoundException::new);
        orderEntity.setStatus("CANCELADO");

        orderRepository.save(orderEntity);
        return orderEntityMapper.toOrderModel(orderEntity);
    }

    @Override
    public OrderModel getOrderById(Long id) {
        return orderEntityMapper.toOrderModel(orderRepository.findById(id)
                .orElseThrow(NoOrderFoundException::new));
    }

    public static String usernameToken(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails){
            userDetails = (UserDetails) principal;
        }
        return userDetails.getUsername();
    }

}
