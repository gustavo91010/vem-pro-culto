package com.ajudaqui.vem_pro_culto_api.web.controller;

import com.ajudaqui.vem_pro_culto_api.application.service.AuthService;
import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public void register(
      @RequestHeader("Authorization") String authApp,
      @RequestBody UsuarioRequest dto) {

    authService.register(authApp, dto);
  }
}
