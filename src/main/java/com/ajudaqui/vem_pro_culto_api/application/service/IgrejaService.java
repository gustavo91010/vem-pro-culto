package com.ajudaqui.vem_pro_culto_api.application.service;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.FiltroBuscaIgrejaDTO;
import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;

public interface IgrejaService {

  public Igreja registro(String requestedToken, IgrejaRequest igreja);

  public List<Igreja> buscarTodas(FiltroBuscaIgrejaDTO dto);

  public List<Igreja> buscarPorNomeFantasia(String nomeFantasia);

  public Igreja buscarPorRazaoSocial(String razaoSocial);

  public Igreja buscarPorEmail(String email);

  public Igreja buscarPorId(Long igrejaId);

  public Igreja atualizarIgreja(String authToken, Long igrejaId, IgrejaUpdate igrejaDTO);

  StatusResponse alternarStatus(String authToken, Long igrejaId);
}
