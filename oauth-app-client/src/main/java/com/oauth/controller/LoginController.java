package com.oauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Description : 컨트롤러
 *
 * @author leejinho
 * @version 1.0
 */
@Slf4j
@Controller
public class LoginController {

    @Value("${spring.security.oauth2.client.registration.gsitm.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.gsitm.redirect-uri}")
    private String redirectUri;

    @Value("${spring.security.oauth2.client.registration.gsitm.response-type}")
    private String responseType;

    @Value("${spring.security.oauth2.client.registration.gsitm.scope}")
    private String scope;

    @Value("${spring.security.oauth2.client.provider.gsitm.authorization-uri}")
    private String authorizationUri;

    @GetMapping("gsitm-login")
    public String mainView() {
        return "login";
    }

    @GetMapping("login")
    public String redirectOAuth2Server(RedirectAttributes redirectAttributes) {
//        String referer = httpServletRequest.getHeader("referer");
//        redirectAttributes.addAttribute("continue", referer);

        redirectAttributes.addAttribute("client_id", clientId);
        redirectAttributes.addAttribute("redirect_uri", redirectUri);
        redirectAttributes.addAttribute("response_type", responseType);
        redirectAttributes.addAttribute("scope", scope);

        return "redirect:"+authorizationUri;
//        return "redirect:http://localhost:8090/oauth/authorize?client_id="+clientId+"&redirect_uri="+redirectUri+"&response_type=code&scope=read";
    }
}
