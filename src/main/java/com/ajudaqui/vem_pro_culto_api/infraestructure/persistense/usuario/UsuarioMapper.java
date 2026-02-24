package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario;

import java.util.List;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UsuarioMapper {

  public UsuarioEntity toEntity(Usuario usuario) {

    return UsuarioEntity.builder()
        .id(usuario.getId())
        .nome(usuario.getNome())
        .email(usuario.getEmail())
        .senha(usuario.getSenha())
        .authToken(usuario.getAuthToken())
        .ativo(usuario.getAtivo())
        .endereco(usuario.getEndereco())
        .registradoEm(usuario.getRegistradoEm())
        .atualizadoEm(usuario.getAtualizadoEm())

        .telefone(
            usuario.getTelefone() == null
                ? List.of()
                : usuario.getTelefone())

        .redesSociais(
            usuario.getRedesSociais() == null ? List.of()
                : usuario.getRedesSociais())
        .build();
  }

  public Usuario toModel(UsuarioEntity entity) {
    return Usuario.builder()
        .id(entity.getId())
        .nome(entity.getNome())
        .authToken(entity.getAuthToken())
        .ativo(entity.getAtivo())
        .email(entity.getEmail())
        .senha(entity.getSenha())

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

}
