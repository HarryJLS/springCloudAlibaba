package com.jlstest.service.Impl;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author JLS
 * @description:
 * @since 2023-06-27 20:02
 */
//@Component
public class MyUserDetailsService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /** 这里两种写法皆可 详情可去看User类的源码 **/
//        String password = passwordEncoder.encode("123456");
//        return new User("jls", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        String password = passwordEncoder.encode("123456");
        UserDetails userDetails = User.withUsername("jls")
                .password(password)
                .authorities("admin").build();

        return userDetails;
    }
}
