package com.jlstest.service;

import com.jlstest.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author JLS
 * @description:
 * @since 2023-06-28 14:14
 */
public interface UserService extends UserDetailsService {

    User getUserByUsername(String username);

}
