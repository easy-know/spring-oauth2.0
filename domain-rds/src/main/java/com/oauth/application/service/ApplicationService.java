package com.oauth.application.service;

import com.oauth.application.dto.ApplicationDto;
import com.oauth.application.entity.Application;
import com.oauth.application.repository.ApplicationRepository;
import com.oauth.base.Role;
import com.oauth.developer.repository.DeveloperRepository;
import com.oauth.oauth2.client_details.entity.OAuthClientDetails;
import com.oauth.util.AES256;
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

    private final AES256 aes256;
    private final ApplicationRepository applicationRepository;
    private final DeveloperRepository developerRepository;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30 * 2;        // 1h
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7d

    public List<ApplicationDto> loadAll(Long id) {
        List<Application> applicationList = applicationRepository.findAllByDeveloper_Id(id, Sort.by(Sort.Direction.DESC, "id"));

        return applicationList.stream()
                .map(m -> ApplicationDto.builder()
                        .id(m.getId())
                        .name(m.getName())
                        .company(m.getCompany())
                        .restApiKey(m.getOAuthClientDetails().getClientId())
                        .build())
                .collect(Collectors.toList());
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

//    @Transactional
//    public String save(ApplicationDto applicationDto, String clientSecret, Long id) {
//        UUID clientId = UUID.randomUUID();
//        AES256 aes256 = new AES256();
//
//        OAuthClientDetails oAuthClientDetails = OAuthClientDetails.builder()
//                .clientId(clientId.toString())
//                .clientSecret(clientSecret)
//                .accessTokenValidity(Long.valueOf(ACCESS_TOKEN_EXPIRE_TIME).intValue())
//                .refreshTokenValidity(Long.valueOf(REFRESH_TOKEN_EXPIRE_TIME).intValue())
//                .authorities(Role.MEMBER.name())
//                .authorizedGrantTypes("authorization_code,refresh_token")
//                .scope("read, write")
//                .build();
//
//        Application application = Application.builder()
//                .name(applicationDto.getName())
//                .company(applicationDto.getCompany())
//                .restApiKey(clientId.toString())
//                .oAuthClientDetails(oAuthClientDetails)
//                .developer(developerRepository.findById(id).get())
//                .build();
//
//        return applicationRepository.save(application).getName();
//    }

    @Transactional
    public String save(ApplicationDto applicationDto, Long id) throws Exception {
        UUID clientUUId = UUID.randomUUID();
        UUID clientSecretUUId = UUID.randomUUID();

        String clientId = clientUUId.toString().replaceAll("-", "");
        String clientSecret = clientSecretUUId.toString().replaceAll("-", "");

        OAuthClientDetails oAuthClientDetails = OAuthClientDetails.builder()
                .clientId(clientId)
                .clientSecret(aes256.encrypt(clientSecret))
                .accessTokenValidity(Long.valueOf(ACCESS_TOKEN_EXPIRE_TIME).intValue())
                .refreshTokenValidity(Long.valueOf(REFRESH_TOKEN_EXPIRE_TIME).intValue())
                .authorities(Role.MEMBER.name())
                .authorizedGrantTypes("authorization_code,refresh_token")
                .scope("read, write")
                .build();

        Application application = Application.builder()
                .name(applicationDto.getName())
                .company(applicationDto.getCompany())
                .restApiKey(clientId)
                .oAuthClientDetails(oAuthClientDetails)
                .developer(developerRepository.findDeveloperById(id))
                .build();

        return applicationRepository.save(application).getName();
    }

    public Long countApplicationByDeveloper(Long developerId) {
        return applicationRepository.countAllByDeveloperId(developerId);
    }
}
