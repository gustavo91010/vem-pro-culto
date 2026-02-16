package com.ajudaqui.vem_pro_culto_api.application.service;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.response.AtividadeResponse;
import com.ajudaqui.vem_pro_culto_api.domain.Atividade;

public interface AtividadeService {
  public Atividade cadastrar(Atividade atividade);

  public List<AtividadeResponse> buscarTodas();

}
