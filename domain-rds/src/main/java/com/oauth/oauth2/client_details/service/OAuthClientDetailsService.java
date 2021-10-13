package com.oauth.oauth2.client_details.service;

import com.oauth.oauth2.client_details.dto.OAuthClientDetailsDto;
import com.oauth.oauth2.client_details.entity.OAuthClientDetails;
import com.oauth.oauth2.client_details.repository.OAuthClientDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class OAuthClientDetailsService {

    private final OAuthClientDetailsRepository oAuthClientDetailsRepository;

    public OAuthClientDetailsDto loadDetail(String clientId) {
        OAuthClientDetails oAuthClientDetail = oAuthClientDetailsRepository.findByClientId(clientId);

        return OAuthClientDetailsDto.builder()
                .id(oAuthClientDetail.getId())
                .clientId(oAuthClientDetail.getClientId())
                .clientSecret(oAuthClientDetail.getSecretKey())
                .webServerRedirectUri(oAuthClientDetail.getWebServerRedirectUri())
                .build();
    }

    @Transactional
    public Long saveRedirectUrl(Long id, String redirectUrl) {
        return getOptionalOAuthClientDetail(id, redirectUrl);
    }

    private Long getOptionalOAuthClientDetail(Long id, String redirectUrl) {
        Optional<OAuthClientDetails> oAuthClientDetail = oAuthClientDetailsRepository.findById(id);
        OAuthClientDetails clientDetail = null;

        if (oAuthClientDetail.isPresent()) {
            clientDetail = oAuthClientDetail.get();
            clientDetail.setWebServerRedirectUri(redirectUrl);
        }

        assert clientDetail != null;
        return clientDetail.getId();
    }

    @Transactional
    public Long update(Long id, String redirectUrl) {
        return getOptionalOAuthClientDetail(id, redirectUrl);
    }

    @Transactional
    public void delete(Long id) {
        Optional<OAuthClientDetails> oAuthClientDetails = oAuthClientDetailsRepository.findById(id);
        oAuthClientDetails.ifPresent(o -> o.setWebServerRedirectUri(""));
    }
}
