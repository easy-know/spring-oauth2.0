package com.oauth.oauth2.approvals.service;

import com.oauth.oauth2.approvals.repository.OAuth2ApprovalsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OAuth2ApprovalsService {
    private final OAuth2ApprovalsRepository oAuth2ApprovalsRepository;
}
