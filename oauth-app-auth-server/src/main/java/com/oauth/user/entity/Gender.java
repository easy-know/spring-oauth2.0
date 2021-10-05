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
public enum Gender {
    MAN("남자", "0"),
    WOMAN("여자", "1");

    private String korean;
    private String code;
}
