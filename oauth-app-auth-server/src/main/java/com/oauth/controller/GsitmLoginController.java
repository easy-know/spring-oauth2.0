package com.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Controller
public class GsitmLoginController {
    @GetMapping("/gsitm/login")
    public String viewLogin() {
        return "login/gsitm-login";
    }
}
