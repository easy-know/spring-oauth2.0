package com.oauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Slf4j
@Controller
public class UserController {

    @GetMapping("user")
    public String userInfoView(@RequestHeader Map<String, Object> requestHeader) {
        log.info(String.valueOf(requestHeader));

        return "user";
    }
}
