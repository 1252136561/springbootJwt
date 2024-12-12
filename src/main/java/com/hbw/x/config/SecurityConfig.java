package com.hbw.x.config;


import com.hbw.x.oa.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

      @Bean
      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .csrf().disable() // 禁用CSRF保护，根据您的需求决定是否启用
                    // 设置会话管理策略为无状态（STATELESS）
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    // 禁用HTTP Basic认证
                    .httpBasic().disable()
                    // 添加自定义的JwtAuthenticationFilter
                    .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                    // 配置请求授权规则
                    .authorizeHttpRequests((authorize) -> authorize
                            .requestMatchers("/wxlogin").permitAll() // 开发微信登录接口，不需要认证
                            .anyRequest().authenticated() // 其他所有请求都需要认证
                    )
                    .build(); // 构建并返回SecurityFilterChain对象
      }
}