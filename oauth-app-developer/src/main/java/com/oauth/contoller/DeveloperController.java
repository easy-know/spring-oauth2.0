package com.oauth.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Controller
public class DeveloperController {
    @GetMapping("sign-up")
    public String signUp() {
        return "developer/sign-up";
    }

    @GetMapping("sign-in")
    public String signIn() {
        return "developer/sign-in";
    }
}
