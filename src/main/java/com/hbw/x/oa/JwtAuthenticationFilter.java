package com.hbw.x.oa;

import com.hbw.x.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            // 从请求头中获取token
            String header = request.getHeader("Authorization");
//            if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
//                  throw new RuntimeException("Invalid JWT token");
//            }
            if (header != null ) {
                  String token = header;
                  try {
                        Claims claims = JwtUtil.parseToken(token);
                        if (claims.getExpiration().before(new Date())) { // 判断token是否过期
                              throw new RuntimeException("Token expired");
                        }
                        // 生成Authentication对象，保存到SecurityContextHolder中
                        Authentication authentication = new JwtAuthenticationToken(claims);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                  } catch (ExpiredJwtException e) {
                        throw new RuntimeException("Token expired");
                  }
            }
            filterChain.doFilter(request, response);
      }
}
