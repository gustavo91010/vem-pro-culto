package com.ajudaqui.vem_pro_culto_api.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtils {

  @Value("${spring.application.auth_app}")
  private String secret;

  public String getAccessToken(String jwtToken) {
    return getSecretKeyByJwt(jwtToken)
        .get("access_token", String.class);
  }

  @SuppressWarnings("deprecation")
  private Claims getSecretKeyByJwt(String jwtToken) {
    return Jwts.parser()
        .setSigningKey(secret)
        .build()
        .parseClaimsJws(jwtToken)
        .getBody();

  }

}
