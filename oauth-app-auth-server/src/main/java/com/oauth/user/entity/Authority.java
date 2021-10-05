package com.oauth.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@AllArgsConstructor
@Getter
public enum Authority {
    ADMIN("ROLE_USER");

    private String value;
}