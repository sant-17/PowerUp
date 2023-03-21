package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserJpaAdapterTest {

    @Mock private IUserRepository userRepository;
    private UserJpaAdapter userJpaAdapter;
    private IUserEntityMapper userEntityMapper;

    @BeforeEach
    void setUp() {
        userJpaAdapter = new UserJpaAdapter(userRepository,userEntityMapper);
    }

    @Test
    void getAllUsers() {
        assertThatThrownBy(() -> userJpaAdapter.getAllUsers())
                .isInstanceOf(NoDataFoundException.class);
    }

    @Test
    void saveUserAsOwner() {
        //given
        RoleModel roleModel = new RoleModel(1L, "Rol", "Rol");

        UserModel userModel = new UserModel(
                1L,
                "Santiago",
                "García",
                1036684021L,
                "+573006132466",
                "santiago@email.com",
                "s3cr3t",
                roleModel
        );

        //when
        UserModel newUserModel = userJpaAdapter.saveUserAsOwner(userModel);

        //then
        ArgumentCaptor<UserEntity> userModelArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);

        verify(userRepository).save(userModelArgumentCaptor.capture());

        UserEntity capturedUser = userModelArgumentCaptor.getValue();

        assertThat(capturedUser).isNotEqualTo(userModel);
    }

    @Test
    @Disabled
    void getUserById() {
    }
}