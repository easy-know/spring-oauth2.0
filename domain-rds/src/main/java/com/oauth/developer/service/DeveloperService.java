package com.oauth.developer.service;

import com.oauth.base.Role;
import com.oauth.developer.dto.DeveloperDto;
import com.oauth.developer.entity.Developer;
import com.oauth.developer.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.Optional;

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

        if (developerRepository.existsByEmail(developerDto.getEmail())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        Developer developer = Developer.builder()
                .email(developerDto.getEmail())
                .password(developerDto.getPassword())
                .role(Role.MEMBER)
                .build();

        return developerRepository.save(developer).getEmail();
    }

    @Transactional
    public void update(DeveloperDto developerDto) {
        Optional<Developer> optionalDeveloper = developerRepository.findByEmail(developerDto.getEmail());
        optionalDeveloper.ifPresent(developer -> {
            developer.changePassword(developerDto.getPassword());
        });
    }
}
