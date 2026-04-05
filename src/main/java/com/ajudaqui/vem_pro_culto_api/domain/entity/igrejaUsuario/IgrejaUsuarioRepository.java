package com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario;

import java.util.List;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.domain.dto.RelacaoComIgrejaDTO;

public interface IgrejaUsuarioRepository {

  public IgrejaUsuario save(IgrejaUsuario igrejaUsuario);

  public List<RelacaoComIgrejaDTO> relacaoComIgrejas(UUID authToken);

  public int removerVinculo(Long usuarioId, Long igrejaId, String papel);
}
