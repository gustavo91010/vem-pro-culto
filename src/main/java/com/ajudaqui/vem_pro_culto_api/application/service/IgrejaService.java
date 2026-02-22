package com.ajudaqui.vem_pro_culto_api.application.service;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.dto.IgrejaUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.request.IgrejaRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.response.IgrejaResponse;

public interface IgrejaService {

  public IgrejaResponse registro(String requestedToken, IgrejaRequest igreja);

  public List<IgrejaResponse> buscarTodas();

  public List<IgrejaResponse> buscarPorNomeFantasia(String nomeFantasia);

  public IgrejaResponse buscarPorRazaoSocial(String razaoSocial);

  public IgrejaResponse buscarPorEmail(String email);

  public IgrejaResponse buscarPorId(Long igrejaId);

  public IgrejaResponse atualizarIgreja(IgrejaUpdate igrejaDTO);

  public Boolean desativarIgreja(String requestedToken, Long igrejaId);
}
