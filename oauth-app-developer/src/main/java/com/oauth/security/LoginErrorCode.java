package com.oauth.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Getter
@AllArgsConstructor
public enum LoginErrorCode {
    BAD_CREDENTIAL("LOGIN_ERROR_CODE_0001","아이디나 비밀번호가 맞지 않습니다."),
    DISABLE("LOGIN_ERROR_CODE_0002", "계정이 비활성화 되었습니다."),
    CREDENTIAL("LOGIN_ERROR_CODE_0003", "비밀번호 유효 기간이 만료됬습니다."),
    CREDENTIAL_EXPIRED("LOGIN_ERROR_CODE_0004", "로그인에 실패했습니다.");

    private String code;
    private String description;

    public static String getDescription(String code) {
        String message;

        switch (code){
            case "LOGIN_ERROR_CODE_0001":
                message = LoginErrorCode.BAD_CREDENTIAL.getDescription();
                break;
            case "LOGIN_ERROR_CODE_0002":
                message = LoginErrorCode.DISABLE.getDescription();
                break;
            case "LOGIN_ERROR_CODE_0003":
                message = LoginErrorCode.CREDENTIAL.getDescription();
                break;
            case "LOGIN_ERROR_CODE_0004":
                message = LoginErrorCode.CREDENTIAL_EXPIRED.getDescription();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + code);
        }

        return message;
    }
}
