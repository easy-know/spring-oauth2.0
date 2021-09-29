package com.oauth.developer.dto;

import com.oauth.application.entity.Application;
import com.oauth.base.Role;
import lombok.Getter;
import lombok.Setter;

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

    private String email;

    private String password;

    private Role role;

    private List<Application> applicationList;
}
