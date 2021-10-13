package com.oauth.contoller;

import com.oauth.security.LoginErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String signIn(@RequestParam(value = "error", required = false) String code, Model model) {

        if (code != null) {
            model.addAttribute("errorMessage", LoginErrorCode.getDescription(code));
        }

        return "developer/sign-in";
    }
}
