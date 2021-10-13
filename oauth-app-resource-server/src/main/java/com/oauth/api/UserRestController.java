package com.oauth.api;

import com.oauth.user.entity.User;
import com.oauth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class UserRestController {

    private final UserRepository userRepository;

    @GetMapping("users")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

}
