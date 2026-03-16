package com.ajudaqui.vem_pro_culto_api.web.controller;

import com.ajudaqui.vem_pro_culto_api.application.service.AuthService;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;

import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<UsuarioResponse> register(
      @RequestHeader("Authorization") String authApp,
      @RequestBody UsuarioRequest dto) {

    UsuarioResponse ususario = authService.register(authApp, dto);
    System.out.println(ususario.toString());
    return ResponseEntity.ok(ususario);
  }
}
