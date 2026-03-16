package com.ajudaqui.vem_pro_culto_api.application.service.imp;

import com.ajudaqui.vem_pro_culto_api.application.service.AuthService;
import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthServiceImp implements AuthService {

  @Value("${spring.application.auth_app}")
  private String authInternal;
  private final UsuarioService usuarioService;

  @Override
  public UsuarioResponse register(String authApp, UsuarioRequest dto) {

    if (!authInternal.equals(authApp))
      throw new IllegalArgumentException("solicitação não autorizada!");

    return usuarioService.registro(dto);
  };
}
