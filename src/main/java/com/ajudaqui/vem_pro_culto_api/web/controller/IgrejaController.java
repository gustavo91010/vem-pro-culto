package com.ajudaqui.vem_pro_culto_api.web.controller;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.IgrejaService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.IgrejaResponse;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/igreja")
public class IgrejaController {

  private IgrejaService igrejaService;

  @PostMapping
  public ResponseEntity<?> registro(
      @RequestHeader("Authorization") String requestedToken,
      @RequestBody IgrejaRequest request) {

    Igreja igreja = igrejaService.registro(requestedToken, request);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  @GetMapping("/todos")
  public ResponseEntity<List<?>> buscarTodos(@RequestHeader("Authorization") String requestedToken) {
    List<IgrejaResponse> igrejas = igrejaService.buscarTodas().stream()
        .map(IgrejaResponse::new)
        .toList();
    return ResponseEntity.ok(igrejas);
  }

  @GetMapping("/nome-fantasia/{nomeFantasia}")
  public ResponseEntity<List<?>> buscarPorNomeFantasia(
      @RequestHeader("Authorization") String requestedToken,
      @RequestParam String nomeFantasia) {

    List<IgrejaResponse> igrejas = igrejaService.buscarPorNomeFantasia(nomeFantasia).stream()
        .map(IgrejaResponse::new)
        .toList();
    return ResponseEntity.ok(igrejas);
  }

  @GetMapping("/razao-social/{razaoSocial}")
  public ResponseEntity<?> buscarPorRazaoSocial(
      @RequestHeader("Authorization") String requestedToken,
      @RequestParam String razaoSocial) {

    Igreja igreja = igrejaService.buscarPorRazaoSocial(razaoSocial);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<?> buscarPorEmail(
      @RequestHeader("Authorization") String requestedToken,
      @RequestParam String email) {

    Igreja igreja = igrejaService.buscarPorEmail(email);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  @GetMapping("/id/{igrejaId}")
  public ResponseEntity<?> buscarPorId(
      @RequestHeader("Authorization") String requestedToken,
      @RequestParam Long igrejaId) {

    Igreja igreja = igrejaService.buscarPorId(igrejaId);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  // @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/igrejaId/{igrejaId}")
  public ResponseEntity<?> atualizarIgreja(
      @RequestHeader("Authorization") String authToken,
      @RequestParam Long igrejaId, @RequestBody IgrejaUpdate igrejaDTO) {

    Igreja igreja = igrejaService.atualizarIgreja(authToken, igrejaId, igrejaDTO);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  // @PreAuthorize("hasRole('ROLE_MODERATOR')")
  @PostMapping("/igrejaId/{igrejaId}")
  public ResponseEntity<?> alternarStatus(
      @RequestHeader("Authorization") String authToken,
      @RequestParam Long igrejaId) {

    return ResponseEntity.ok(igrejaService.alternarStatus(igrejaId));
  }
}
