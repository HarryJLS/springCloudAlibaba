package com.jlstest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JLS
 * @description:
 * @since 2023-06-28 19:36
 */
@RestController
public class LoginController {

    @RequestMapping("/showLogin")
    public String showLogin() {
        return "login";
    }
}
