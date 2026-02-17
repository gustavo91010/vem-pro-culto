package com.ajudaqui.vem_pro_culto_api.application.service;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaDTO;
import com.ajudaqui.vem_pro_culto_api.application.service.response.IgrejaResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.Igreja;


public interface IgrejaService {

  public Igreja registro(Igreja igreja);
  public List<IgrejaResponse> buscarTodas();
  public List<IgrejaResponse> buscarPorNomeFantasia(String nomeFantasia);
  public IgrejaResponse buscarPorId(Long id);
  public Igreja atualizarIgreja(IgrejaDTO igrejaDTO);
  public Boolean desativarIgreja(Long id);
}
