package com.oauth.oauth2.approvals.repository;

import com.oauth.oauth2.approvals.entity.OAuthApprovals;
import com.oauth.oauth2.approvals.entity.OAuthApprovalsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */
@Repository
public interface OAuth2ApprovalsRepository extends JpaRepository<OAuthApprovals, OAuthApprovalsId> {
}
