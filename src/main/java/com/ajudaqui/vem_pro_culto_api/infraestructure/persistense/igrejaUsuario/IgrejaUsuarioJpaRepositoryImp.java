package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario;

import java.util.List;
import java.util.UUID;

import com.ajudaqui.vem_pro_culto_api.domain.dto.RelacaoComIgrejaDTO;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuarioRepository;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IgrejaUsuarioJpaRepositoryImp implements IgrejaUsuarioRepository {
  private final IgrejaUsuarioJpaRepository repository;
  private final IgrejaUsuarioMapper mapper;

  public List<RelacaoComIgrejaDTO> relacaoComIgrejas(UUID authToken) {

    return repository.relacaoComIgrejas(authToken);
  }

  public IgrejaUsuario save(IgrejaUsuario model) {

    var igrejaUsuario = repository.save(mapper.toEntity(model));

    return mapper.toModel(igrejaUsuario);
  }

  @Override
  public int removerVinculo(Long usuarioId, Long igrejaId, String papel) {
    return repository.removerVinculo(usuarioId, igrejaId, papel);
  }
}
