package com.ajudaqui.vem_pro_culto_api.application.service;

import java.time.LocalDate;
import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.AtividadeDTO;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;

public interface AtividadeService {

    Atividade registro(String authToken, AtividadeDTO dto);

    List<Atividade> buscarAtividades(Long igrejaId, LocalDate dataInicio, LocalDate dataFim);

    void excluir(String authToken, Long igrejaId, Long atividadeId);

    Atividade buscarPorId(Long atividadeId);

  
}
