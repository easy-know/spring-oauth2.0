package com.oauth.config;

import com.oauth.security.DeveloperUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@RequiredArgsConstructor
@EnableWebSecurity
public class DeveloperSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DeveloperUserDetailService developerUserDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**", "/js/**", "/images/**")
                .antMatchers("/h2-console/**", "/favicon.ico", "/resources/**", "/error");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()

                .authorizeRequests()
                .antMatchers("/sign-up", "/sign-in", "/api/developer").permitAll()
                .anyRequest().authenticated()
                .and()

                .formLogin()
                .loginPage("/sign-in")
                .usernameParameter("email")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/application")
                .permitAll()
                .and()

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/sign-in")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()

                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(developerUserDetailService).passwordEncoder(passwordEncoder());
    }
}
