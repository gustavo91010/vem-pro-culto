package com.ajudaqui.vem_pro_culto_api.web.config;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.exception.BadRequestException;
import com.ajudaqui.vem_pro_culto_api.domain.enums.EPapel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;

@Component
public class JwtUtils {

  @Value("${spring.application.auth_app}")
  private String secret;

  public boolean isModerador(String jwtToken) {
    return getRoles(jwtToken).contains(EPapel.ROLE_MODERATOR.name());
  }

  @SuppressWarnings("unchecked")
  private List<String> getRoles(String jwtToken) {
    return getSecretKeyByJwt(jwtToken)
        .get("roles", List.class);
  }

  public String getAccessToken(String jwtToken) {
    return getSecretKeyByJwt(jwtToken)
        .get("access_token", String.class);
  }

  @SuppressWarnings("deprecation")
  private Claims getSecretKeyByJwt(String jwtToken) {
    try {

      byte[] keyBytes = Decoders.BASE64.decode(secret);
      jwtToken = jwtToken.replace("Bearer ", "").trim();
      return Jwts.parser()
          .setSigningKey(hmacShaKeyFor(keyBytes))
          .build()
          .parseClaimsJws(jwtToken)
          .getBody();
    } catch (Exception e) {
      e.printStackTrace();
      throw new BadRequestException("Não autorizado!");
    }

  }

  public String getEmail(String jwtToken) {
    return getSecretKeyByJwt(jwtToken)
        .get("sub", String.class);
  }

}
