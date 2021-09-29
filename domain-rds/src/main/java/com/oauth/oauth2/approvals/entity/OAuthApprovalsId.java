package com.oauth.oauth2.approvals.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */
@Embeddable
public class OAuthApprovalsId implements Serializable {
    @Column
    private String userId;

    @Column
    private String clientId;
}
