package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import java.util.Set;
import java.util.stream.Collectors;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario.IgrejaUsuarioEntity;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario.IgrejaUsuarioMapper;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class IgrejaMapper {

  private final IgrejaUsuarioMapper mapper;

  public IgrejaEntity toEntity(Igreja igreja) {
    Set<IgrejaUsuarioEntity> usuarios = isNullOrEmpty(igreja.getUsuarios())
        ? Set.of()
        : igreja.getUsuarios().stream()
            .map(mapper::toEntity)
            .collect(Collectors.toSet());

    return IgrejaEntity.builder()
        .id(igreja.getId())
        .nomeFantasia(igreja.getNomeFantasia())
        .razaoSocial(igreja.getRazaoSocial())
        .email(igreja.getEmail())
        .cnpj(igreja.getCnpj())
        .ativo(igreja.getAtivo())
        .usuarios(usuarios)
        .endereco(igreja.getEndereco())
        .redesSociais(igreja.getRedesSociais())
        .telefone(igreja.getTelefone())
        .atualizadoEm(igreja.getAtualizadoEm())
        .registradoEm(igreja.getRegistradoEm())
        .build();

  }

  private boolean isNullOrEmpty(Set<?> usuarios) {
    return usuarios == null || usuarios.isEmpty();
  }

  public Igreja toModel(IgrejaEntity entity) {

    Set<IgrejaUsuario> usuarios = isNullOrEmpty(entity.getUsuarios())
        ? Set.of()
        : entity.getUsuarios().stream()
            .map(mapper::toModel)
            .collect(Collectors.toSet());

    return Igreja.builder()
        .id(entity.getId())
        .nomeFantasia(entity.getNomeFantasia())
        .razaoSocial(entity.getRazaoSocial())
        .email(entity.getEmail())
        .usuarios(usuarios)
        .cnpj(entity.getCnpj())
        .ativo(entity.getAtivo())
        .usuarios(usuarios)
        .endereco(entity.getEndereco())
        .redesSociais(entity.getRedesSociais())
        .telefone(entity.getTelefone())
        .atualizadoEm(entity.getAtualizadoEm())
        .registradoEm(entity.getRegistradoEm())
        .build();
  }

}
