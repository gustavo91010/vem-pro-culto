package com.ajudaqui.vem_pro_culto_api.web.controller;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.IgrejaService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.FiltroBuscaIgrejaDTO;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.IgrejaResponse;
import com.ajudaqui.vem_pro_culto_api.application.service.response.IgrejaServiceList;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/igreja")
public class IgrejaController {

  private final IgrejaService igrejaService;

  @PostMapping
  public ResponseEntity<?> registro(
      @RequestHeader("Authorization") String requestedToken,
      @RequestBody IgrejaRequest request) {

    Igreja igreja = igrejaService.registro(requestedToken, request);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  @GetMapping("/todos")
  public ResponseEntity<IgrejaServiceList> buscarTodos(
      @RequestHeader("Authorization") String requestedToken,
      @RequestBody FiltroBuscaIgrejaDTO dto) {
    var igrejas = igrejaService.buscarTodas(dto);
    return ResponseEntity.ok(new IgrejaServiceList(igrejas));
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

  // A role de quem cadastrou a aigreja
  // @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/atualizar/{igrejaId}")
  public ResponseEntity<?> atualizarIgreja(
      @RequestHeader("Authorization") String authToken,
      @PathVariable("igrejaId") Long igrejaId,
      @RequestBody IgrejaUpdate igrejaDTO) {

    Igreja igreja = igrejaService.atualizarIgreja(authToken, igrejaId, igrejaDTO);
    return ResponseEntity.ok(new IgrejaResponse(igreja));
  }

  // A role do dono da aplicacao
  // @PreAuthorize("hasRole('ROLE_MODERATOR')")
  @PatchMapping("/alternar-status/{igrejaId}")
  public ResponseEntity<?> alternarStatus(
      @RequestHeader("Authorization") String authToken,
      @PathVariable("igrejaId") Long igrejaId) {

    return ResponseEntity.ok(igrejaService.alternarStatus(authToken, igrejaId));
  }
}
