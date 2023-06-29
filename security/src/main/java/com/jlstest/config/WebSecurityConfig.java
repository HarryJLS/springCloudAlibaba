package com.jlstest.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.jlstest.service.Impl.MyAuthenticationSuccessHandler;
import com.jlstest.service.Impl.MyExpiredSessionStrategy;
import com.jlstest.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


/**
 * @author JLS
 * @description:
 * @since 2023-06-28 10:26
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;

    @Resource
    public DataSource dataSource;


    /**
     * 指定密码加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {

        // return NoOpPasswordEncoder.getInstance(); // 不加密方式
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器相关配置
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // session失效管理
        http.sessionManagement()
//                .invalidSessionUrl("/session/invalid"); // session失效后跳转的路径
                .maximumSessions(6)// 设置最大session数量，超过后会调用MyExpiredSessionStrategy
//                .maxSessionsPreventsLogin(true) // 当session数量达到最大值后，阻止后面的用户登录 可能存在问题，当session超时的时候，自己无法再次登录
                .expiredSessionStrategy(new MyExpiredSessionStrategy());

        // 认证配置
        http.formLogin()  //表单提交
                .loginPage("/login.html") //自定义登录页面 认证成功会会隐藏的生成个token
                .loginProcessingUrl("/user/login")  //登录访问路径，必须和表单提交接口一样
                .defaultSuccessUrl("/admin/demo")   //认证成功之后跳转的路径 转发
                .failureForwardUrl("/fail.html")  //认证失败之后跳转的路径 转发
//                .successForwardUrl()// 认证成功，跳转（重定向）页面 （依托于浏览器）
//                .successHandler(new MyAuthenticationSuccessHandler("/main.html")) // 自定义认证成功后的处理 重定向处理
//                .failureHandler()
        ;


                // 授权认证
        http
                .authorizeRequests()
                //设置哪些路径可以直接访问，不需要认证
                .antMatchers("/login.html", "/fail.html", "/session/invalid").permitAll()
                .anyRequest().authenticated();  //需要认证

        http.csrf().disable(); //关闭csrf防护 csrf跨站点请求  可以通过token来避免跨站点请求的攻击   csrf跨站请求伪造


//        //记住我
//        http.rememberMe()
//                .tokenRepository(persistentTokenRepository())//设置持久化仓库
//                .tokenValiditySeconds(3600) //超时时间,单位s 默认两周
//                .userDetailsService(userService);  //设置自定义登录逻辑
    }


    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        //设置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

}
