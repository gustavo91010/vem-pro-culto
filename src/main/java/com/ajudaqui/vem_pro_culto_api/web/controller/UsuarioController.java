package com.ajudaqui.vem_pro_culto_api.web.controller;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  @PostMapping
  public ResponseEntity<UsuarioResponse> registro(@RequestBody UsuarioRequest dto) {
    return ResponseEntity.ok(usuarioService.registro(dto));
  }

  @GetMapping("/{authToken}")
  public ResponseEntity<UsuarioResponse> findByAuthToken(String authToken) {
    return ResponseEntity.ok(usuarioService.findByAuthToken(authToken));
  }

  @GetMapping("/todos")
  public ResponseEntity<List<UsuarioResponse>> buscarTodos() {
    return ResponseEntity.ok(usuarioService.buscarTodos());
  }

  @PutMapping("/atualizar/{usuarioId}")
  public ResponseEntity<UsuarioResponse> atualizar(
      @PathVariable Long usuarioId,
      @RequestBody UsuarioUpdate usuario) {

    return ResponseEntity.ok()
        .header("X-Mensagem", "Usuário atualizado com sucesso")
        .body(usuarioService.update(usuarioId, usuario));
  }

  @PatchMapping("/alternarStatus/{usuarioId}")
  public ResponseEntity<String> desativarConta(@PathVariable Long usuarioId) {
    usuarioService.alternarStatus(usuarioId);

    return ResponseEntity
        .ok()
        .header("mensagem", "Usuário desativado com sucesso.").build();
  }
}
