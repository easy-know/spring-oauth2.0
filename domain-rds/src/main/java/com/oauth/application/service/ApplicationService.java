package com.oauth.application.service;

import com.oauth.application.dto.ApplicationDto;
import com.oauth.application.entity.Application;
import com.oauth.application.repository.ApplicationRepository;
import com.oauth.base.Role;
import com.oauth.developer.repository.DeveloperRepository;
import com.oauth.oauth2.client_details.entity.OAuthClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final DeveloperRepository developerRepository;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30 * 2;        // 1h
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7d

    public List<ApplicationDto> loadAll(Long id) {
        List<Application> applicationList = applicationRepository.findAllByDeveloper_Id(id, Sort.by(Sort.Direction.DESC, "id"));

        List<ApplicationDto> applicationDtoList = applicationList.stream()
                .map(m -> ApplicationDto.builder()
                        .id(m.getId())
                        .name(m.getName())
                        .company(m.getCompany())
                        .restApiKey(m.getOAuthClientDetails().getClientId())
                        .build())
                .collect(Collectors.toList());

        return applicationDtoList;
    }

    public ApplicationDto loadDetail(String name) {
        Application application = applicationRepository.findByName(name).get();

        ApplicationDto applicationDto = ApplicationDto.builder()
                .id(application.getId())
                .name(application.getName())
                .company(application.getCompany())
                .restApiKey(application.getRestApiKey())
                .build();

        return applicationDto;
    }

    @Transactional
    public String save(ApplicationDto applicationDto, String clientSecret, Long id) {
        UUID clientId = UUID.randomUUID();

        OAuthClientDetails oAuthClientDetails = OAuthClientDetails.builder()
                .clientId(clientId.toString())
                .clientSecret(clientSecret)
                .accessTokenValidity(Long.valueOf(ACCESS_TOKEN_EXPIRE_TIME).intValue())
                .refreshTokenValidity(Long.valueOf(REFRESH_TOKEN_EXPIRE_TIME).intValue())
                .authorities(Role.MEMBER.name())
                .authorizedGrantTypes("authorization_code,refresh_token")
                .scope("read, write")
                .build();

        Application application = Application.builder()
                .name(applicationDto.getName())
                .company(applicationDto.getCompany())
                .restApiKey(clientId.toString())
                .oAuthClientDetails(oAuthClientDetails)
                .developer(developerRepository.findById(id).get())
                .build();

        return applicationRepository.save(application).getName();
    }
}
