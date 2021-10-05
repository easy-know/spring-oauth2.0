package com.oauth.oauth2.client_details.service;

import com.oauth.oauth2.client_details.dto.OAuthClientDetailsDto;
import com.oauth.oauth2.client_details.entity.OAuthClientDetails;
import com.oauth.oauth2.client_details.repository.OAuthClientDetailsRepository;
import com.oauth.util.AES256;
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

    private final AES256 aes256;
    private final OAuthClientDetailsRepository oAuthClientDetailsRepository;

    public OAuthClientDetailsDto loadDetail(String clientId) throws Exception {
        OAuthClientDetails oAuthClientDetail = oAuthClientDetailsRepository.findByClientId(clientId);

        return OAuthClientDetailsDto.builder()
                .id(oAuthClientDetail.getId())
                .clientId(oAuthClientDetail.getClientId())
                .clientSecret(aes256.decrypt(oAuthClientDetail.getClientSecret()))
                .webServerRedirectUri(oAuthClientDetail.getWebServerRedirectUri())
                .build();
    }

    @Transactional
    public Long saveRedirectUrl(Long id, String redirectUrl) {
        Optional<OAuthClientDetails> oAuthClientDetail = oAuthClientDetailsRepository.findById(id);
        OAuthClientDetails clientDetail = null;

        if (oAuthClientDetail.isPresent()) {
            clientDetail = oAuthClientDetail.get();
            clientDetail.setWebServerRedirectUri(redirectUrl);
        }

        return clientDetail.getId();
    }

    @Transactional
    public Long update(Long id, String redirectUrl) {
        Optional<OAuthClientDetails> oAuthClientDetail = oAuthClientDetailsRepository.findById(id);
        OAuthClientDetails clientDetail = null;

        if (oAuthClientDetail.isPresent()) {
            clientDetail = oAuthClientDetail.get();
            clientDetail.setWebServerRedirectUri(redirectUrl);
        }

        return clientDetail.getId();
    }

    @Transactional
    public void delete(Long id) {
        oAuthClientDetailsRepository.deleteById(id);
    }
}
