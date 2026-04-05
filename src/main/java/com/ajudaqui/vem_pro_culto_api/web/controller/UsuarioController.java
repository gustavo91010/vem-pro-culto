package com.ajudaqui.vem_pro_culto_api.web.controller;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.UsuarioService;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.response.ResponseMessage;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;
import com.ajudaqui.vem_pro_culto_api.web.config.JwtUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

  private final JwtUtils jwtUtils;
  private final UsuarioService usuarioService;

  @PostMapping({ "/register" })
  public ResponseEntity<UsuarioResponse> registro(
      @RequestBody UsuarioRequest dto) {
    return ResponseEntity.ok(usuarioService.registro(dto));
  }

  @GetMapping("/relacao-igreja")
  public ResponseEntity<?> igrejas(
      @RequestHeader("Authorization") String jwtToken) {

    String authToken = jwtUtils.getAccessToken(jwtToken);
    return ResponseEntity.ok(usuarioService.relacaoIgreja(authToken));
  }

  /**
   * Endpoint complexo: Usado para buscar o usuario logado.
   * Mudança para RequestHeader para seguir o padrao JWT do front.
   */
  @GetMapping("/me")
  public ResponseEntity<UsuarioResponse> getUsuarioLogado(
      @RequestHeader("Authorization") String jwtToken) {
    String authToken = jwtUtils.getAccessToken(jwtToken);
    System.out.println(usuarioService.me(authToken));
    return ResponseEntity.ok(usuarioService.me(authToken));
  }

  @GetMapping("/todos")
  public ResponseEntity<List<UsuarioResponse>> buscarTodos() {
    return ResponseEntity.ok(usuarioService.buscarTodos());
  }

  @PutMapping("/atualizar")
  public ResponseEntity<ResponseMessage> atualizar(
      @RequestHeader("Authorization") String jwtToken,
      @RequestBody UsuarioUpdate usuario) {
    String authToken = jwtUtils.getAccessToken(jwtToken);
    usuarioService.update(authToken, usuario);
    return ResponseEntity.ok(new ResponseMessage("Usuário atualizado com sucesso."));
  }

  @PatchMapping("/alternar-status")
  public ResponseEntity<ResponseMessage> desativarConta(
      @RequestHeader("Authorization") String jwtToken) {
    String authToken = jwtUtils.getAccessToken(jwtToken);
    usuarioService.alternarStatus(authToken);
    return ResponseEntity.ok(new ResponseMessage("Usuário atualizado com sucesso."));
  }

  // Mantendo o antigo temporariamente para nao quebrar o front antes da atualizacao
  @Deprecated
  @GetMapping("/{authToken}")
  public ResponseEntity<UsuarioResponse> findByAuthToken(@PathVariable String authToken) {
    return ResponseEntity.ok(new UsuarioResponse(usuarioService.findByAuthToken(authToken)));
  }
}
