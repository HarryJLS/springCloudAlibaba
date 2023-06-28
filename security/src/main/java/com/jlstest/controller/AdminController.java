package com.jlstest.controller;

import com.example.common.response.BaseController;
import com.example.common.response.JlsTestResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JLS
 * @description:
 * @since 2023-06-27 11:02
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @GetMapping("/demo")
    @ResponseBody
    public JlsTestResponse<Object> demo() {
        String username = getUsername();
        return sendSuccessMsg("spring security demo" + username);
    }

    /**
     * 根据token获取当前登录的用户信息
     */
    private String getUsername() {
        // 获取当前登录的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

}
