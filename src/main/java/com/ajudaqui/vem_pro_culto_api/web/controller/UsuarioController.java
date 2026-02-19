package com.ajudaqui.vem_pro_culto_api.web.controller;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  @PostMapping
  public UsuarioResponse registro(@RequestBody UsuarioRequest dto) {
    return usuarioService.registro(dto);
  }

  @GetMapping
  public List<UsuarioResponse> buscarTodos() {
    return usuarioService.buscarTodos();
  }
}
