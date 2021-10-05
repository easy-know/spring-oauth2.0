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
        String errormsg;

        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            errormsg = "아이디나 비밀번호가 맞지 않습니다.";
        } else if (exception instanceof DisabledException) {
            errormsg = "계정이 비활성화 되었습니다.";
        } else if (exception instanceof CredentialsExpiredException) {
            errormsg = "비밀번호 유효 기간이 만료됬습니다.";
        } else {
            errormsg = "로그인에 실패했습니다.";
        }

        log.info("errormsg: " + errormsg);

        request.setAttribute("errorMessage", errormsg);
//        request.getRequestDispatcher(DEFAULT_FAILURE_URL).forward(request, response);
        response.sendRedirect("sign-in");
    }
}
