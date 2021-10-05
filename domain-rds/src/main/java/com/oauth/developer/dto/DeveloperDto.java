package com.oauth.developer.dto;

import com.oauth.application.entity.Application;
import com.oauth.base.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Getter
@Setter
public class DeveloperDto {
    private Long id;

    @Email(message = "이메일 양식을 지켜주세요")
    @NotNull(message = "이메일을 입력해주세요")
    private String email;

    @NotNull(message = "비밀번호를 입력해주세요")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotNull(message = "비밀번호를 입력해주세요")
    private String passwordCheck;

    private Role role;

    private List<Application> applicationList;

    public Boolean isEqualsToPasswordCheck(String password, String passwordCheck) {
        if (password.equals(passwordCheck)) {
            return true;
        }
        return false;
    }
}
