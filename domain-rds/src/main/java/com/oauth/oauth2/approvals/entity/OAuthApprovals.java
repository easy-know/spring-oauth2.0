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
    @EmbeddedId
    private OAuthApprovalsId oAuthApprovalsId;

    @Column(name = "SCOPE")
    private String scope;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "EXPIRESAT")
    private LocalDateTime expiresAt;

    @LastModifiedDate
    @Column(name="LASTMODIFIEDAT")
    private LocalDateTime modifyDateTime;
}
