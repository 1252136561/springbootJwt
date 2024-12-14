package com.hbw.x.config;


import com.hbw.x.oa.JwtAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

      @Override
      protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    // 设置会话管理策略为无状态（STATELESS）
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .httpBasic().disable() // 禁用HTTP Basic认证
                    // 添加自定义的JwtAuthenticationFilter
                    .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/refresh","/wxlogin").permitAll() // 开发微信登录接口，不需要认证
                    .anyRequest().authenticated();
      }
}
