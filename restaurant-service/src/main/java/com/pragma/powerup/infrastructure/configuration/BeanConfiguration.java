package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.spi.ICheckDishRestaurantOwnerPort;
import com.pragma.powerup.domain.spi.ICheckEmpRestaurantOwnerPort;
import com.pragma.powerup.domain.spi.ICheckUserCanOrderPort;
import com.pragma.powerup.domain.spi.IUserContextPort;
import com.pragma.powerup.domain.spi.IUserIdPort;
import com.pragma.powerup.infrastructure.utils.CheckDishRestaurantOwnerAdapter;
import com.pragma.powerup.infrastructure.utils.CheckEmpRestaurantOwnerAdapter;
import com.pragma.powerup.infrastructure.utils.UserIdAdapter;
import com.pragma.powerup.infrastructure.utils.UserMakeNewOrderAdapterPort;
import com.pragma.powerup.infrastructure.utils.UsernameTokenContextAdapter;
import com.pragma.powerup.infrastructure.feign.twilio.service.ITwilioFeignClientService;
import com.pragma.powerup.infrastructure.feign.user.dto.response.UserResponseDto;
import com.pragma.powerup.domain.api.IDishCategoryServicePort;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.api.IRestaurantEmpServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.spi.IDishCategoryPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantEmpPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.usecase.DishCategoryUseCase;
import com.pragma.powerup.domain.usecase.DishUseCase;
import com.pragma.powerup.domain.usecase.OrderUseCase;
import com.pragma.powerup.domain.usecase.RestaurantEmpUseCase;
import com.pragma.powerup.domain.usecase.RestaurantUseCase;
import com.pragma.powerup.infrastructure.feign.user.service.IUserFeignClientService;
import com.pragma.powerup.infrastructure.out.jpa.adapter.DishCategoryJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.OrderJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.RestaurantEmpJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishCategoryEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEmpEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishCategoryRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderDishRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantEmpRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import com.pragma.powerup.infrastructure.security.aut.DetailsUser;
import com.pragma.powerup.infrastructure.security.aut.IDetailsUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IDishCategoryRepository categoryRepository;
    private final IDishCategoryEntityMapper categoryEntityMapper;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final IRestaurantEmpRepository restaurantEmpRepository;
    private final IRestaurantEmpEntityMapper restaurantEmpEntityMapper;
    private final IUserFeignClientService feignClientSpringService;
    private final IDetailsUserMapper detailsUserMapper;
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderDishRepository orderDishRepository;
    private final ITwilioFeignClientService twilioFeignClientService;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper, feignClientSpringService);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(
                orderRepository,
                orderDishRepository,
                restaurantRepository,
                dishRepository,
                restaurantEmpRepository,
                orderEntityMapper,
                feignClientSpringService,
                twilioFeignClientService);
    }

    @Bean
    public ICheckUserCanOrderPort checkUserCanOrder(){
        return new UserMakeNewOrderAdapterPort(orderRepository, feignClientSpringService);
    }

    @Bean
    public IUserIdPort userIdPort(){
        return new UserIdAdapter(feignClientSpringService);
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort(), restaurantEmpPersistencePort(), checkUserCanOrder(), userContextPort(), userIdPort());
    }

    @Bean
    public IDishCategoryServicePort categoryServicePort(){
        return new DishCategoryUseCase(categoryPersistencePort());
    }
    @Bean
    public IDishCategoryPersistencePort categoryPersistencePort(){
        return new DishCategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    IDishPersistencePort dishPersistencePort(){
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IUserContextPort userContextPort(){
        return new UsernameTokenContextAdapter();
    }

    @Bean
    public ICheckDishRestaurantOwnerPort checkDishRestaurantOwnerPort(){
        return new CheckDishRestaurantOwnerAdapter(restaurantRepository, feignClientSpringService);
    }

    @Bean
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort(), userContextPort(), checkDishRestaurantOwnerPort());
    }



    @Bean
    IRestaurantEmpPersistencePort restaurantEmpPersistencePort(){
        return new RestaurantEmpJpaAdapter(
                restaurantEmpRepository,
                restaurantEmpEntityMapper);
    }

    @Bean
    public ICheckEmpRestaurantOwnerPort checkEmpRestaurantOwnerPort(){
        return new CheckEmpRestaurantOwnerAdapter(restaurantRepository, feignClientSpringService);
    }

    @Bean
    public IRestaurantEmpServicePort restaurantEmpServicePort(){
        return new RestaurantEmpUseCase(restaurantEmpPersistencePort(), userContextPort(), checkEmpRestaurantOwnerPort());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return username -> optionalDetailsUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    private Optional<DetailsUser> optionalDetailsUser(String username) {
        UserResponseDto userResponseDto = feignClientSpringService.getUserByEmail(username);
        if (userResponseDto.getId() == null){
            throw new RuntimeException("???");
        }
        DetailsUser user = detailsUserMapper.toUser(userResponseDto);
        user.setRole(userResponseDto.getRole().getName());
        return Optional.of(user);
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}