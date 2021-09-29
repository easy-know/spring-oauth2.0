package com.oauth.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description :
 *
 * @author leejinho
 * @version 1.0
 */

@Controller
public class ApplicationController {

    @GetMapping("/application")
    public String viewApplicationList() {
        return "application/list";
    }

    @GetMapping("/application/{name}")
    public String viewApplicationDetail(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        return "application/detail";
    }
}
