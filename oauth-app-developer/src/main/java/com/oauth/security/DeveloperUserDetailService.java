package com.oauth.security;

import com.oauth.developer.entity.Developer;
import com.oauth.developer.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class DeveloperUserDetailService implements UserDetailsService {

    private final DeveloperRepository developerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadByUsername: " + username);
        Developer developer = developerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + "이(는) 존재하지 않습니다."));

        return User.builder()
                .username(String.valueOf(developer.getId()))
                .password(developer.getPassword())
                .roles(developer.getRole().name())
                .build();
    }
}
