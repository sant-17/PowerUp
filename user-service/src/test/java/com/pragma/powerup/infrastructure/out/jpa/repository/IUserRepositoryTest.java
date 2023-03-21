package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IUserRepositoryTest {

    @Autowired
    IUserRepository userRepository;

    @Test
    void itShouldCheckIfEmailExists() {
        //given
        String email = "user@email.com";

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        userRepository.save(userEntity);

        //when
        boolean expected = userRepository.selectExistsEmail(email);

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfEmailDoesNotExists(){
        //given
        String email = "user@email.com";

        //when
        boolean expected = userRepository.selectExistsEmail(email);

        //then
        assertThat(expected).isFalse();
    }
}