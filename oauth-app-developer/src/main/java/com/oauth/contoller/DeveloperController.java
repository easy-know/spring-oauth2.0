package com.oauth.contoller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Slf4j
@Controller
public class DeveloperController {

    @GetMapping("sign-up")
    public String signUp() {
        return "developer/sign-up";
    }

    @RequestMapping(value = "sign-in", method = {RequestMethod.GET, RequestMethod.POST})
    public String signIn(HttpServletRequest request, Model model) {
        return "developer/sign-in";
    }
}
