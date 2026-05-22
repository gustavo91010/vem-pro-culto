package com.ajudaqui.vem_pro_culto_api.web.config;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

import com.ajudaqui.vem_pro_culto_api.application.exception.BadRequestException;
import com.ajudaqui.vem_pro_culto_api.domain.enums.EPapel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  @Value("${spring.application.auth_app}")
  private String secret;

  public boolean isAdmin(String jwtToken) {
    if (jwtToken == null) return false;
    return getRoles(jwtToken).contains(EPapel.ROLE_ADMIN.name());
  }

  public boolean isModerador(String jwtToken) {
    if (jwtToken == null) return false;
    return getRoles(jwtToken).contains(EPapel.ROLE_MODERATOR.name());
  }

  @SuppressWarnings("unchecked")
  private List<String> getRoles(String jwtToken) {
    return getSecretKeyByJwt(jwtToken).get("roles", List.class);
  }

  public String getAccessToken(String jwtToken) {
    return getSecretKeyByJwt(jwtToken).get("access_token", String.class);
  }

  @SuppressWarnings("deprecation")
  private Claims getSecretKeyByJwt(String jwtToken) {
    jwtToken = jwtToken.replace("Bearer ", "").trim();
    try {
      byte[] keyBytes = Decoders.BASE64.decode(secret);
      return Jwts.parser()
          .verifyWith(hmacShaKeyFor(keyBytes))
          .build()
          .parseSignedClaims(jwtToken)
          .getPayload();
    } catch (Exception e) {
      System.out.println("Erro ao validar token: " + e.getMessage());
      throw new BadRequestException("Não autorizado! Detalhe: " + e.getMessage());
    }
  }

  public String getEmail(String jwtToken) {
    return getSecretKeyByJwt(jwtToken).get("sub", String.class);
  }
}
