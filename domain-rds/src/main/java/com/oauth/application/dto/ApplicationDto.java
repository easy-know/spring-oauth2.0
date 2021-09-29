package com.oauth.application.dto;

import com.oauth.developer.entity.Developer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {
    private Long id;
    private String name;
    private String company;
    private String restApiKey;
    private Developer developer;
}
