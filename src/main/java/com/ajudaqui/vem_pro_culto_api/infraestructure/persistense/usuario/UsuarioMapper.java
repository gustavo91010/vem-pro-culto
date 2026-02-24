package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario.IgrejaUsuarioEntity;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario.IgrejaUsuarioMapper;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioMapper {
  private final IgrejaUsuarioMapper mapper;

  public UsuarioEntity toEntity(Usuario model) {

    Set<IgrejaUsuarioEntity> igrejas = isNullOrEmpty(model.getIgrejas())
        ? Set.of()
        : model.getIgrejas().stream()
            .map(mapper::toEntity)
            .collect(Collectors.toSet());

    return UsuarioEntity.builder()
        .id(model.getId())
        .nome(model.getNome())
        .email(model.getEmail())
        .senha(model.getSenha())
        .authToken(model.getAuthToken())
        .ativo(model.getAtivo())
        .igrejas(igrejas)
        .endereco(model.getEndereco())
        .registradoEm(model.getRegistradoEm())
        .atualizadoEm(model.getAtualizadoEm())

        .telefone(
            model.getTelefone() == null
                ? List.of()
                : model.getTelefone())

        .redesSociais(
            model.getRedesSociais() == null ? List.of()
                : model.getRedesSociais())
        .build();
  }

  public Usuario toModel(UsuarioEntity entity) {
    Set<IgrejaUsuario> igrejas = isNullOrEmpty(entity.getIgrejas())
        ? Set.of()
        : entity.getIgrejas().stream()
            .map(mapper::toModel)
            .collect(Collectors.toSet());

    return Usuario.builder()
        .id(entity.getId())
        .nome(entity.getNome())
        .authToken(entity.getAuthToken())
        .ativo(entity.getAtivo())
        .email(entity.getEmail())
        .senha(entity.getSenha())
        .igrejas(igrejas)
        .endereco(entity.getEndereco())
        .telefone(
            entity.getTelefone() == null
                ? List.of()
                : entity.getTelefone())

        .redesSociais(
            entity.getRedesSociais() == null ? List.of()
                : entity.getRedesSociais())
        .registradoEm(entity.getRegistradoEm())
        .atualizadoEm(entity.getAtualizadoEm())
        .build();
  }

  private boolean isNullOrEmpty(Set<?> igrejas) {
    return igrejas == null || igrejas.isEmpty();
  }

}
