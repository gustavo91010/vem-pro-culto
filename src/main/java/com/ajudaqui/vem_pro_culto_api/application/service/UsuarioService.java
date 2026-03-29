package com.ajudaqui.vem_pro_culto_api.application.service;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioRequest;
import com.ajudaqui.vem_pro_culto_api.application.service.request.UsuarioUpdate;
import com.ajudaqui.vem_pro_culto_api.application.service.response.StatusResponse;
import com.ajudaqui.vem_pro_culto_api.application.service.response.UsuarioResponse;
import com.ajudaqui.vem_pro_culto_api.domain.dto.RelacaoComIgrejaDTO;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

public interface UsuarioService {

  public UsuarioResponse registro( UsuarioRequest request);

  List<UsuarioResponse> buscarTodos();

  public Usuario findByAuthToken(String authToken);

  public Usuario findById(Long usuarioId);

  public UsuarioResponse update(String authToken, UsuarioUpdate usuario);

  public StatusResponse alternarStatus(String authToken);

  public List<RelacaoComIgrejaDTO> relacaoIgreja(String authToken);

}
