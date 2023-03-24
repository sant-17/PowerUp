package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.api.IDishCategoryServicePort;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IRestaurantEmpServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.spi.IDishCategoryPersistencePort;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantEmpPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.domain.usecase.DishCategoryUseCase;
import com.pragma.powerup.domain.usecase.DishUseCase;
import com.pragma.powerup.domain.usecase.RestaurantEmpUseCase;
import com.pragma.powerup.domain.usecase.RestaurantUseCase;
import com.pragma.powerup.infrastructure.feign.IUserClientFeign;
import com.pragma.powerup.infrastructure.out.jpa.adapter.DishCategoryJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.DishJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.RestaurantEmpJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishCategoryEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEmpEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishCategoryRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
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
    private final IUserClientFeign userClientFeign;

    private final IDetailsUserMapper detailsUserMapper;

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper, userClientFeign);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
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
    public IDishServicePort dishServicePort(){
        return new DishUseCase(dishPersistencePort());
    }

    @Bean
    IRestaurantEmpPersistencePort restaurantEmpPersistencePort(){
        return new RestaurantEmpJpaAdapter(restaurantEmpRepository, restaurantEmpEntityMapper);
    }

    @Bean
    public IRestaurantEmpServicePort restaurantEmpServicePort(){
        return new RestaurantEmpUseCase(restaurantEmpPersistencePort());
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
        UserResponseDto userResponseDto = userClientFeign.getUserByEmail(username);
        userResponseDto = userClientFeign.getUserByEmail(username);

        //Optional<UserEntity> userEntity = repository.findByEmail(username);
        //if(userEntity.isEmpty()){
        //throw new RuntimeException();
        //}
        //catch (Exception e){
        //throw new RuntimeException();
        //}
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