package com.hbw.x.oa;

import com.hbw.x.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException {
            // 从请求头中获取token
            String header = request.getHeader("Authorization");
//            if (StringUtils.isEmpty(header) || !header.startsWith("Bearer ")) {
//                  throw new RuntimeException("Invalid JWT token");
//            }
            if (header != null ) {
                  String t = header;
                  try {
                        Claims claims = JwtUtil.parseToken(t);
                        if (claims.getExpiration().before(new Date())) { // 判断token是否过期
                              throw new RuntimeException("Token expired");
                        }
                        // 生成Authentication对象，保存到SecurityContextHolder中
                        Authentication authentication = new JwtAuthenticationToken(claims);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                  } catch (ExpiredJwtException e) {
                       logger.error("oa token err",e);
                        throw new RuntimeException("Token expired");
                  }
            }
            filterChain.doFilter(request, response);
      }
}
