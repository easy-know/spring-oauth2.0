package com.oauth.oauth2.approvals.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

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
public class OAuthApprovals {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "approvals_id", insertable = false, updatable = false)
    private Long id;
    private String userId;
    private String clientId;
    private String scope;
    private String status;
    private LocalDateTime expiresAt;

    @LastModifiedDate
    @Column(name="MODIFY_DATE_TIME")
    private LocalDateTime modifyDateTime;}
