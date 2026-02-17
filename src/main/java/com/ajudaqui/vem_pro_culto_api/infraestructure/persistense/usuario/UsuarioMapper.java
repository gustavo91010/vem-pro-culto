package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario;

import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

  public UsuarioEntity toEntity(Usuario usuario) {

    return UsuarioEntity.builder()
        .nome(usuario.getNome())
        .email(usuario.getEmail())
        .senha(usuario.getSenha())
        // .telefone(usuario.getTelefone())
        // .endereco(usuario.getEndereco()
        // .redesSociais(usuario.getRedesSociais())
        .registradoEm(usuario.getRegistradoEm())
        .atualizadoEm(usuario.getAtualizadoEm())
        .build();
  }

  public Usuario toModel(UsuarioEntity entity) {
    return Usuario.builder()
        .id(entity.getId())
        .nome(entity.getNome())
        .email(entity.getEmail())
        .senha(entity.getSenha())
        .registradoEm(entity.getRegistradoEm())
        .atualizadoEm(entity.getAtualizadoEm())
        .build();
  }

}
