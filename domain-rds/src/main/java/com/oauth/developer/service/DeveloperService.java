package com.oauth.developer.service;

import com.oauth.base.Role;
import com.oauth.developer.dto.DeveloperDto;
import com.oauth.developer.entity.Developer;
import com.oauth.developer.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    @Transactional
    public String save(DeveloperDto developerDto) {

        Developer developer = Developer.builder()
                .email(developerDto.getEmail())
                .password(developerDto.getPassword())
                .role(Role.MEMBER)
                .build();

        return developerRepository.save(developer).getEmail();
    }
}
