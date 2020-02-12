package com.sk.mall.config;

import com.sk.mall.component.JwtAuthenticationTokenFilter;
import com.sk.mall.component.RestAuthenticationEntryPoint;
import com.sk.mall.component.RestfulAccessDeniedHandler;
import com.sk.mall.dto.AdminUserDetail;
import com.sk.mall.mbg.model.UmsAdmin;
import com.sk.mall.mbg.model.UmsPermission;
import com.sk.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by SongKun on 2020/2/9 10:03 上午
 */
@Configurable
@EnableWebSecurity //说明是Security相关的注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
//三个值取一个就够用。Spring指定的权限控制开关,spring的EL表达式@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")。
// securedEnabled = true 可以在service层和controller使用@Secured注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 继承WebSecurityConfigurerAdapter从而对SpringSecurity进行拓展

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public UserDetailsService userDetailsService() {
       /* return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UmsAdmin admin = umsAdminService.getAdminByUsername(username);
                if (admin != null) {
                    List<UmsPermission> permissionList = umsAdminService.getPermissionList(admin.getId());
                    return new AdminUserDetail(admin, permissionList);
                }
                throw new UsernameNotFoundException("用户名或密码错误");
            }
        };
    }*/
        // 获取登录用户信息
        return username -> {
            UmsAdmin admin = umsAdminService.getAdminByUsername(username);
            if (admin != null) {
                List<UmsPermission> permissionList = umsAdminService.getPermissionList(admin.getId());
                return new AdminUserDetail(admin, permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Autowired
    private UmsAdminService umsAdminService;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    // 配置用户数据来自于数据库
    // @PostConstruct
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());

    }


    // 配置访问路径
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()  // 使用JWT，不需要csrf生成的随机token,必须放在最前面
                .sessionManagement() // 基于token,所以不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
                        "/",
                        "/*.html",
                        "/favicion.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "v2/api-docs/**",
                        "/druid/**"
                )
                .permitAll()
                .antMatchers("/admin/login", "/admin/register")  // 对登录注册运行匿名访问（依然会经过）
                .permitAll()
                .anyRequest()   //除了上面的其他所有请求全部需要鉴权认证
                .authenticated();

        // 禁用缓存
        http.headers().cacheControl().disable();
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }
}
