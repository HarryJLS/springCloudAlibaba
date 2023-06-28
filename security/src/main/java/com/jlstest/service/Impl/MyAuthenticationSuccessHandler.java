package com.jlstest.service.Impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JLS
 * @description:
 * @since 2023-06-28 16:00
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String redirectUrl;

    public MyAuthenticationSuccessHandler(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     *                       the authentication process.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.sendRedirect(redirectUrl);
    }
}
