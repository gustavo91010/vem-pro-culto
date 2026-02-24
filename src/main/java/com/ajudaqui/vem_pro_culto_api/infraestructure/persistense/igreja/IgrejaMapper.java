package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igreja;

import java.util.Set;
import java.util.stream.Collectors;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;
import com.ajudaqui.vem_pro_culto_api.domain.entity.igrejaUsuario.IgrejaUsuario;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario.IgrejaUsuarioEntity;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.igrejaUsuario.IgrejaUsuarioMapper;
import com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.usuario.UsuarioMapper;

import org.springframework.stereotype.Component;

@Component
public class IgrejaMapper {

  private IgrejaUsuarioMapper mapper;
  // private  UsuarioMapper usuarioMapper;

  public IgrejaEntity toEntity(Igreja igreja) {
Set<IgrejaUsuarioEntity> usuarios = igreja.getUsuarios().stream()
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

  private Set<IgrejaUsuarioEntity> usuariosToEntity(Set<IgrejaUsuario> usuarios) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'usuariosToEntity'");
  }

  public Igreja toModel(IgrejaEntity entity) {

    return Igreja.builder()
        .id(entity.getId())
        .nomeFantasia(entity.getNomeFantasia())
        .razaoSocial(entity.getRazaoSocial())
        .email(entity.getEmail())
        .usuarios(usuariosToModel(entity.getUsuarios()))
        .cnpj(entity.getCnpj())
        .ativo(entity.getAtivo())
        .endereco(entity.getEndereco())
        .redesSociais(entity.getRedesSociais())
        .telefone(entity.getTelefone())
        .atualizadoEm(entity.getAtualizadoEm())
        .registradoEm(entity.getRegistradoEm())
        .build();
  }

  private Set<IgrejaUsuario> usuariosToModel(Set<IgrejaUsuarioEntity> usuarios) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'usuariosToModel'");
  }

}
