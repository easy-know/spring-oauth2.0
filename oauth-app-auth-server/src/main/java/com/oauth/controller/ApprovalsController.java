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
@SessionAttributes("authorizationRequest")
public class ApprovalsController {

    @GetMapping("/oauth/confirm_access")
    public String viewApprovals() {
        return "login/approvals";
    }

}
