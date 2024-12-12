package com.hbw.x.oa;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

      private final Object principal;

      public JwtAuthenticationToken(Object principal) {
            super(null);
            this.principal = principal;
            setAuthenticated(true);
      }

      @Override
      public Object getCredentials() {
            return null;
      }

      @Override
      public Object getPrincipal() {
            return principal;
      }
}
