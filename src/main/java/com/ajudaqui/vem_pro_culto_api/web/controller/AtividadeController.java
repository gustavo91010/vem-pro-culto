package com.ajudaqui.vem_pro_culto_api.web.controller;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.AtividadeService;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.AtividadeDTO;
import com.ajudaqui.vem_pro_culto_api.application.service.response.AtividadeResponse;
import com.ajudaqui.vem_pro_culto_api.application.service.response.AtividadeResponseList;
import com.ajudaqui.vem_pro_culto_api.application.service.response.ResponseMessage;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;
import com.ajudaqui.vem_pro_culto_api.web.config.JwtUtils;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/atividade")
public class AtividadeController {

  private final AtividadeService atividadeService;
  private final JwtUtils jwtUtils;

  @CrossOrigin
  @PostMapping
  public ResponseEntity<AtividadeResponse> registro(
      @RequestHeader("Authorization") String jwtToken,
      @RequestBody AtividadeDTO dto) {

    String authToken = jwtUtils.getAccessToken(jwtToken);
    Atividade atividade = atividadeService.registro(authToken, dto);
    return ResponseEntity.ok(new AtividadeResponse(atividade));
  }

  @GetMapping("/id/{atividadeId}")
  public ResponseEntity<AtividadeResponse> buscarPorId(
      @RequestHeader(value = "Authorization", required = false) String jwtToken,
      @PathVariable Long atividadeId) {
    // Busca publica, token opcional para log se necessário futuramente
    Atividade atividades = atividadeService.buscarPorId(atividadeId);
    return ResponseEntity.ok(new AtividadeResponse(atividades));
  }

  @GetMapping("/todos")
  public ResponseEntity<AtividadeResponseList> buscarTodos(
      @RequestHeader(value = "Authorization", required = false) String jwtToken,
      @RequestParam(value = "igrejaId") Long igrejaId,
      @RequestParam(value = "dataInicio") @DateTimeFormat(pattern = "dd-MM-yyyy") String dataInicio,
      @RequestParam(value = "dataFim", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String dataFim) {
    List<Atividade> atividades = atividadeService.buscarAtividades(igrejaId, dataInicio, dataFim);
    return ResponseEntity.ok(new AtividadeResponseList(atividades));
  }

  @GetMapping("/listar")
  public ResponseEntity<AtividadeResponseList> listarTodas() {
    List<Atividade> atividades = atividadeService.listarTodas();
    return ResponseEntity.ok(new AtividadeResponseList(atividades));
  }

  @DeleteMapping()
  public ResponseEntity<ResponseMessage> excluir(
      @RequestHeader("Authorization") String jwtToken,
      @RequestParam(value = "igrejaId") Long igrejaId,
      @RequestParam(value = "atividadeId") Long atividadeId) {

    String authToken = jwtUtils.getAccessToken(jwtToken);
    atividadeService.excluir(authToken, igrejaId, atividadeId);
    return ResponseEntity.ok(new ResponseMessage("Atividae excluida com sucesso."));
  }

}
