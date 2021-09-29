package com.oauth.repository;

import com.oauth.user.entity.User;
import com.oauth.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.Collections;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserRepositoryTest {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Test
    public void insert() {
        userRepository.save(User.builder()
                .uid("test@gmail.com")
                .password(passwordEncoder.encode("test"))
                .name("이진호")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }
}
