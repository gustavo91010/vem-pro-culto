package com.ajudaqui.vem_pro_culto_api.web.controller;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.response.ResponseMessage;
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
  public ResponseEntity<UsuarioResponse> findByAuthToken(@PathVariable String authToken) {
    return ResponseEntity.ok(usuarioService.findByAuthToken(authToken));
  }

  @GetMapping("/todos")
  public ResponseEntity<List<UsuarioResponse>> buscarTodos() {
    return ResponseEntity.ok(usuarioService.buscarTodos());
  }

  @PutMapping("/atualizar/{authToken}")
  public ResponseEntity<ResponseMessage> atualizar(
      @PathVariable String authToken,
      @RequestBody UsuarioUpdate usuario) {
    usuarioService.update(authToken, usuario);
    return ResponseEntity.ok(new ResponseMessage("Usuário atualizado com sucesso."));

  }

  @PatchMapping("/alternar-status/{authToken}")
  public ResponseEntity<ResponseMessage> desativarConta(@PathVariable String authToken) {
    usuarioService.alternarStatus(authToken);

    return ResponseEntity.ok(new ResponseMessage("Usuário atualizado com sucesso."));
  }
}
