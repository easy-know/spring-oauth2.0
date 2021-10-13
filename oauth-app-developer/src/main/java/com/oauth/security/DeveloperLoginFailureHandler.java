package com.oauth.security;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Slf4j
@Getter @Setter
public class DeveloperLoginFailureHandler implements AuthenticationFailureHandler {

    private final String DEFAULT_FAILURE_URL = "sign-in?error=true";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
        String errorMessage;
        String code;

        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            errorMessage = LoginErrorCode.BAD_CREDENTIAL.getDescription();
            code = LoginErrorCode.BAD_CREDENTIAL.getCode();
        } else if (exception instanceof DisabledException) {
            errorMessage = LoginErrorCode.DISABLE.getDescription();
            code = LoginErrorCode.DISABLE.getCode();
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = LoginErrorCode.CREDENTIAL.getDescription();
            code = LoginErrorCode.CREDENTIAL.getCode();
        } else {
            errorMessage = LoginErrorCode.CREDENTIAL_EXPIRED.getDescription();
            code = LoginErrorCode.CREDENTIAL_EXPIRED.getCode();
        }

        log.info("errorMessage: " + errorMessage);
        log.info("code: " + code);

        request.setAttribute("errorMessage", errorMessage);
//        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
        response.sendRedirect("sign-in?error="+code);
    }
}
