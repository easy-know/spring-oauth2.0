package com.oauth.base;

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
public enum Role {
    MEMBER("ROLE_MEMBER"),
    ADMIN("ROLE_ADMIN");

    private String value;
}

