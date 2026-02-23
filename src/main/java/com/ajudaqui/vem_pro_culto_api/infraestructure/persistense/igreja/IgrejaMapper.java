package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;

import org.springframework.stereotype.Component;

@Component
public class IgrejaMapper {
  // private IgrejaUsuarioMapper mapper;

  public IgrejaEntity toEntity(Igreja igreja) {
  //   Set<IgrejaUsuarioEntity> usuarios = igreja.getUsuarios() == null ? Set.of()
  //       : igreja.getUsuarios().stream()
  //           .map(mapper::toEntity)
  //           .collect(Collectors.toSet());

    return IgrejaEntity.builder()
        .id(igreja.getId())
        .nomeFantasia(igreja.getNomeFantasia())
        .razaoSocial(igreja.getRazaoSocial())
        .email(igreja.getEmail())
        .cnpj(igreja.getCnpj())
        .ativo(igreja.getAtivo())
        // .usuarios(usuarios)
        .endereco(igreja.getEndereco())
        .redesSociais(igreja.getRedesSociais())
        .telefone(igreja.getTelefone())
        .atualizadoEm(igreja.getAtualizadoEm())
        .registradoEm(igreja.getRegistradoEm())
        .build();

  }

  public Igreja toModel(IgrejaEntity entity) {

    // Set<IgrejaUsuario> usuarios = entity.getUsuarios() == null ? Set.of()
    //     : entity.getUsuarios()
    //         .stream()
    //         .map(mapper::toModel)
    //         .collect(Collectors.toSet());

    return Igreja.builder()
        .id(entity.getId())
        .nomeFantasia(entity.getNomeFantasia())
        .razaoSocial(entity.getRazaoSocial())
        .email(entity.getEmail())
        .cnpj(entity.getCnpj())
        .ativo(entity.getAtivo())
        // .usuarios(usuarios)
        .endereco(entity.getEndereco())
        .redesSociais(entity.getRedesSociais())
        .telefone(entity.getTelefone())
        .atualizadoEm(entity.getAtualizadoEm())
        .registradoEm(entity.getRegistradoEm())
        .build();
  }

}
