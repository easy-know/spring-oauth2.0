package com.oauth.config;

import com.oauth.security.LoginFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@Configuration
public class OAuth2SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers("/css/**", "/js/**", "/images/**", "/favicon.ico")
                .antMatchers("/h2-console/**", "/swagger-ui/**");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()

                .authorizeRequests().antMatchers("/oauth/**", "/oauth/token", "/oauth2/**", "/h2-console/*").permitAll()
                .and()

                .formLogin()
                .loginPage("/gsitm/login")
                .loginProcessingUrl("/authenticate")
                .failureHandler(new LoginFailureHandler())
                .permitAll()
                .and()

                .httpBasic();
    }
}