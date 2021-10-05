package com.oauth.exception;

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
public enum ErrorCode {
    NOT_NULL("ERROR_CODE_0001","필수값이 누락되었습니다"),
    MIN_VALUE("ERROR_CODE_0002", "최소값보다 커야 합니다."),
    EXIST_VALUE("ERROR_CODE_0003", "이미 존재하는 이메일입니다."),
    INVALID_INPUT("ERROR_CODE_0004", "이미 존재하는 이메일입니다.");

    private String code;
    private String description;

}
