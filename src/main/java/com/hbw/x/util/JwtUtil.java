package com.hbw.x.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

      private static String SECRET_KEY;
      private static long EXPIRATION_TIME;

      @Value("${jwt.secretKey}")
      public void setSecretKey(String secretKey) {
            SECRET_KEY = secretKey;
      }

      @Value("${jwt.expirationTime}")
      public void setExpirationTime(long expirationTime) {
            EXPIRATION_TIME = expirationTime;
      }
      // 生成token
      public static String generateToken(String subject) {
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME); // 1小时后过期，3600000
            return Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
      }
      // 解析token
      public static Claims parseToken(String token) {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
      }
}
