package com.oauth.oauth2.client_details.repository;

import com.oauth.oauth2.client_details.entity.OAuthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */
@Repository
public interface OAuthClientDetailsRepository extends JpaRepository<OAuthClientDetails, Long> {
    OAuthClientDetails findByClientId(String clientId);
}
