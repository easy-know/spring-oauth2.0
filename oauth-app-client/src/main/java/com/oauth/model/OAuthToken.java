package com.oauth.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Getter @Setter
public class OAuthToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
}
