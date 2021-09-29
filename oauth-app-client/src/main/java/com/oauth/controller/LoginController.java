package com.oauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Description : 컨트롤러
 *
 * @author leejinho
 * @version 1.0
 */
@Slf4j
@Controller
public class LoginController {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.redirect-uri}")
    private String redirectUri;

    @GetMapping("gsitm-login")
    public String mainView() {
        return "login";
    }

    @GetMapping("login")
    public String redirectLoginGsitm(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        String referer = httpServletRequest.getHeader("referer");
        log.info(referer);

        redirectAttributes.addAttribute("continue", referer);

        return "redirect:http://localhost:8090/oauth/authorize?client_id="+clientId+"&redirect_uri="+redirectUri+"&response_type=code&scope=read";
    }
}
