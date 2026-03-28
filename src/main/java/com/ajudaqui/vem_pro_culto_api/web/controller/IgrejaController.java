package com.ajudaqui.vem_pro_culto_api.web.controller;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.IgrejaService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.FiltroBuscaIgrejaDTO;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.IgrejaResponse;
import com.ajudaqui.vem_pro_culto_api.application.service.response.IgrejaServiceList;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.web.config.JwtUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/igreja")
public class IgrejaController {

  private final IgrejaService igrejaService;
  private final JwtUtils jwtUtils;

  @PostMapping
  public ResponseEntity<?> registro(
      @RequestHeader("Authorization") String jwtToken,
      @RequestBody IgrejaRequest request) {

    jwtToken = jwtUtils.getAccessToken(jwtToken);
    Igreja igreja = igrejaService.registro(jwtToken, request);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  @GetMapping("/do-usuario")
  public ResponseEntity<IgrejaServiceList> minhas(
      @RequestHeader("Authorization") String authToken) {
    authToken = jwtUtils.getAccessToken(authToken);
    List<Igreja> igrejas = igrejaService.listarIgrejasDoUsuario(authToken);
    return ResponseEntity.ok(new IgrejaServiceList(igrejas));
  }

  @GetMapping("/todos")
  public ResponseEntity<IgrejaServiceList> buscarTodos(
      @ModelAttribute FiltroBuscaIgrejaDTO dto,
      @RequestParam(required = false) Boolean isActive) {
    var igrejas = igrejaService.buscarTodas(dto, isActive);
    return ResponseEntity.ok(new IgrejaServiceList(igrejas));
  }

  @GetMapping("/razao-social/{razaoSocial}")
  public ResponseEntity<?> buscarPorRazaoSocial(
      @RequestParam String razaoSocial) {

    Igreja igreja = igrejaService.buscarPorRazaoSocial(razaoSocial);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<?> buscarPorEmail(
      @RequestParam String email) {

    Igreja igreja = igrejaService.buscarPorEmail(email);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  @GetMapping("/id/{igrejaId}")
  public ResponseEntity<?> buscarPorId(
      @PathVariable Long igrejaId,
      @RequestParam(required = false, defaultValue = "true") Boolean isActive) {

    Igreja igreja = igrejaService.buscarPorId(igrejaId, isActive);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  @PutMapping("/atualizar/{igrejaId}")
  public ResponseEntity<?> atualizarIgreja(
      @RequestHeader("Authorization") String authToken,
      @PathVariable("igrejaId") Long igrejaId,
      @RequestBody IgrejaUpdate igrejaDTO) {

    Igreja igreja = igrejaService.atualizarIgreja(authToken, igrejaId, igrejaDTO);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  @PatchMapping("/alternar-status/{igrejaId}")
  public ResponseEntity<?> alternarStatus(
      @RequestHeader("Authorization") String authToken,
      @PathVariable("igrejaId") Long igrejaId) {

    return ResponseEntity.ok(igrejaService.alternarStatus(authToken, igrejaId));
  }
}
