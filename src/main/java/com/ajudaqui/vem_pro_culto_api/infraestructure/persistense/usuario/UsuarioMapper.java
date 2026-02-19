package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario;

import java.util.List;
import com.ajudaqui.vem_pro_culto_api.domain.entity.usuario.Usuario;
import com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.endereco.EnderecoMapper;
import com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.redeSocial.RedeSocialMapper;
import com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.telefone.TelefoneMapper;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class UsuarioMapper {
  private EnderecoMapper enderecoMapper;
  private TelefoneMapper telefoneMapper;
  private RedeSocialMapper redeSocialMapper;

  public UsuarioEntity toEntity(Usuario usuario) {

    return UsuarioEntity.builder()
        .nome(usuario.getNome())
        .email(usuario.getEmail())
        .senha(usuario.getSenha())
        .authToken(usuario.getAuthToken())
        .ativo(usuario.getAtivo())
        .telefone(

            usuario.getTelefone() == null
                ? List.of()
                : usuario.getTelefone().stream()
                    .map(telefoneMapper::toEntity).toList())

        .endereco(usuario.getEndereco() == null
            ? null
            : enderecoMapper.toEntity(usuario.getEndereco()))
        .redesSociais(

            usuario.getRedesSociais() == null ? List.of()
                : usuario.getRedesSociais().stream()
                    .map(redeSocialMapper::toEntity).toList())
        .registradoEm(usuario.getRegistradoEm())
        .atualizadoEm(usuario.getAtualizadoEm())
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
        .telefone(entity.getTelefone() == null
            ? List.of()
            : entity.getTelefone().stream()
                .map(telefoneMapper::toModel).toList())

        .endereco(entity.getEndereco() == null
            ? null
            : enderecoMapper.toModel(entity.getEndereco()))
        .redesSociais(
            entity.getRedesSociais() == null
                ? List.of()
                : entity.getRedesSociais().stream()
                    .map(redeSocialMapper::toModel).toList())
        .registradoEm(entity.getRegistradoEm())
        .atualizadoEm(entity.getAtualizadoEm())
        .build();
  }

}
