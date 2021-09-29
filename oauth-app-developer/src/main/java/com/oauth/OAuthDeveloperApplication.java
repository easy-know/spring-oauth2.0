package com.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@EnableJpaAuditing
@SpringBootApplication
public class OAuthDeveloperApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthDeveloperApplication.class, args);
    }
}
