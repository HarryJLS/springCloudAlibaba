//package com.jlstest.config;
//
//import com.jlstest.service.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.annotation.Resource;
//
///**
// * @author JLS
// * @description:
// * @since 2023-06-28 20:02
// */
////@Configuration
//public class CsrfSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Resource
//    private UserService userService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//
//        // return NoOpPasswordEncoder.getInstance(); // 不加密方式
//        return new BCryptPasswordEncoder();
//    }
//
//    /**
//     * 认证管理器相关配置
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.formLogin()
//                .loginPage("/showLogin") // 指定登录页面的请求路径
//                .loginProcessingUrl("/login") // 指定自定义form表单请求的路径
//                .defaultSuccessUrl("/main.html"); // 指定登录成功后的跳转路径
//
//        http.authorizeRequests()
//                .antMatchers("/showLogin").permitAll()
//                .anyRequest().authenticated();
//
//    }
//
//}
