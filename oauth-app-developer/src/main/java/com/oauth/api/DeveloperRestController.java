package com.oauth.api;

import com.oauth.developer.dto.DeveloperDto;
import com.oauth.developer.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/api/developer")
@RequiredArgsConstructor
public class DeveloperRestController {

    private final DeveloperService developerService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    private ResponseEntity signUp(@RequestBody DeveloperDto developerDto) {
        developerDto.setPassword(passwordEncoder.encode(developerDto.getPassword()));
        return ResponseEntity.created(URI.create("/sign-in")).body(developerService.save(developerDto));
    }
}
