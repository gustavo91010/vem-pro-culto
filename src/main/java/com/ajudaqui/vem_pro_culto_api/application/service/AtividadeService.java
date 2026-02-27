package com.ajudaqui.vem_pro_culto_api.application.service;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.AtividadeDTO;
import com.ajudaqui.vem_pro_culto_api.domain.entity.atividade.Atividade;

public interface AtividadeService {

    Atividade registro(String authToken, AtividadeDTO dto);

    List<Atividade> buscarAtividades(Long igrejaId, String dataInicio, String dataFim);

    void excluir(String authToken, Long igrejaId, Long atividadeId);

    Atividade buscarPorId(Long atividadeId);

  
}
