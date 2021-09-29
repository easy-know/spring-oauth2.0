package com.oauth.application.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oauth.base.BaseEntity;
import com.oauth.developer.entity.Developer;
import com.oauth.oauth2.client_details.entity.OAuthClientDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Application extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "application_id", insertable = false, updatable = false)
    private Long id;

    private String name;

    private String company;

    private String restApiKey;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "oauth_client_details_id")
    @JsonIgnore
    private OAuthClientDetails oAuthClientDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id")
    private Developer developer;
}
