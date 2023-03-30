package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.exception.InvalidCodeException;
import com.pragma.powerup.domain.exception.NewOrderStatusNotValidException;
import com.pragma.powerup.domain.exception.OrderCantBeCancelledException;
import com.pragma.powerup.domain.exception.OrderWithWrongClientException;
import com.pragma.powerup.domain.exception.UserCantOrderException;
import com.pragma.powerup.domain.exception.UserFromDifferentRestaurantException;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantEmpModel;
import com.pragma.powerup.domain.spi.ICheckUserCanOrderPort;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantEmpPersistencePort;
import com.pragma.powerup.domain.spi.IUserContextPort;
import com.pragma.powerup.domain.spi.IUserIdPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantEmpPersistencePort employeePersistencePort;
    private final ICheckUserCanOrderPort checkUserCanOrder;
    private final IUserContextPort userContextPort;
    private final IUserIdPort userIdPort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort, IRestaurantEmpPersistencePort employeePersistencePort, ICheckUserCanOrderPort checkUserCanOrder, IUserContextPort userContextPort, IUserIdPort userIdPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.employeePersistencePort = employeePersistencePort;
        this.checkUserCanOrder = checkUserCanOrder;
        this.userContextPort = userContextPort;
        this.userIdPort = userIdPort;
    }

    @Override
    public void saveOrder(OrderModel orderModel) {
        String usernameContext = userContextPort.getUserContext();
        if (!checkUserCanOrder.canUserMakeNewOrder(usernameContext)){
            throw new UserCantOrderException();
        }
        orderModel.setClient(userIdPort.getUserIdByEmail(usernameContext));
        orderModel.setDate(LocalDateTime.now());
        orderModel.setStatus("PENDIENTE");
        orderPersistencePort.saveOrder(orderModel);
    }

    @Override
    public List<OrderModel> getOrdersByStatus(Integer pageNumber, Integer pageSize, String status) {
        return orderPersistencePort.getOrdersByStatus(pageNumber, pageSize, status);
    }

    @Override
    public void setChef(Long id, OrderModel orderModel) {
        String usernameContext = userContextPort.getUserContext();
        Long employeeId = userIdPort.getUserIdByEmail(usernameContext);
        RestaurantEmpModel restaurantEmpModel = employeePersistencePort.getEmployeeById(employeeId);

        OrderModel orderModelById = orderPersistencePort.getOrderById(id);
        Long orderRestaurantId = orderModelById.getRestaurant().getId();
        String actualOrderStatus = orderModelById.getStatus();

        if (!Objects.equals(restaurantEmpModel.getRestaurant(), orderRestaurantId)){
            throw new UserFromDifferentRestaurantException();
        }
        if (!Objects.equals(actualOrderStatus, "PENDIENTE")){
            throw new NewOrderStatusNotValidException();
        }

        orderPersistencePort.setChef(id, orderModel);
    }

    @Override
    public void setOrderStatusReady(Long id) {
        String usernameContext = userContextPort.getUserContext();
        Long employeeId = userIdPort.getUserIdByEmail(usernameContext);
        RestaurantEmpModel restaurantEmpModel = employeePersistencePort.getEmployeeById(employeeId);

        OrderModel orderModelById = orderPersistencePort.getOrderById(id);
        Long orderRestaurantId = orderModelById.getRestaurant().getId();
        String actualOrderStatus = orderModelById.getStatus();

        if (!Objects.equals(restaurantEmpModel.getRestaurant(), orderRestaurantId)){
            throw new UserFromDifferentRestaurantException();
        }
        if (!Objects.equals(actualOrderStatus, "EN_PREPARACIÓN")){
            throw new NewOrderStatusNotValidException();
        }
        orderPersistencePort.setOrderStatusReady(id);
    }

    @Override
    public void setOrderStatusDelivered(Long id, Integer code) {
        String usernameContext = userContextPort.getUserContext();
        Long employeeId = userIdPort.getUserIdByEmail(usernameContext);
        RestaurantEmpModel restaurantEmpModel = employeePersistencePort.getEmployeeById(employeeId);

        OrderModel orderModelById = orderPersistencePort.getOrderById(id);
        Long orderRestaurantId = orderModelById.getRestaurant().getId();
        String actualOrderStatus = orderModelById.getStatus();
        Integer actualOrderCode = orderModelById.getCode();

        if (!Objects.equals(restaurantEmpModel.getRestaurant(), orderRestaurantId)){
            throw new UserFromDifferentRestaurantException();
        }
        if (!Objects.equals(actualOrderStatus, "LISTO")){
            throw new NewOrderStatusNotValidException();
        }
        if (!Objects.equals(actualOrderCode, code)){
            throw new InvalidCodeException();
        }
        orderPersistencePort.setOrderStatusDelivered(id, code);
    }

    @Override
    public void cancelOrder(Long id) {
        String usernameContext = userContextPort.getUserContext();
        Long actualUser = userIdPort.getUserIdByEmail(usernameContext);

        OrderModel orderModelById = orderPersistencePort.getOrderById(id);
        Long orderClient = orderModelById.getClient();
        String actualOrderStatus = orderModelById.getStatus();

        if (!Objects.equals(actualOrderStatus, "PENDIENTE")){
            throw new OrderCantBeCancelledException();
        }
        if (!Objects.equals(orderClient, actualUser)){
            throw new OrderWithWrongClientException();
        }
        orderPersistencePort.cancelOrder(id);
    }
}
