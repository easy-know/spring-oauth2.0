package com.oauth.oauth2.client_details.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oauth.application.entity.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthClientDetails {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "oauth_client_details_id", insertable = false, updatable = false)
    private Long id;
    private String clientId;               //   varchar(256) not null
    private String resourceIds;            //   varchar(256) null,
    private String clientSecret;           //   varchar(256) null,
    private String scope;                  //   varchar(256) null,
    private String authorizedGrantTypes;   //   varchar(256) null,
    private String webServerRedirectUri;   //   varchar(256) null,
    private String authorities;            //   varchar(256) null,
    private Integer accessTokenValidity;   //   int null,
    private Integer refreshTokenValidity;  //   int null,
    private String additionalInformation;  //   varchar(4096) null,
    private String autoapprove;            //   varchar(256) null
    private String secretKey;

    @OneToOne(mappedBy = "oAuthClientDetails", fetch = FetchType.LAZY)
    @JsonIgnore
    private Application application;
}
