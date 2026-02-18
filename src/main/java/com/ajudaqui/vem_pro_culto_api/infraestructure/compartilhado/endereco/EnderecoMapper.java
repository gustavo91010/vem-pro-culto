package com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.endereco;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.Endereco;

import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper {

  public EnderecoComp toEntity(Endereco endereco) {
    return EnderecoComp.builder()
        .logradouro(endereco.getLogradouro())
        .numero(endereco.getNumero())
        .complemento(endereco.getComplemento())
        .bairro(endereco.getBairro())
        .cidade(endereco.getCidade())
        .estado(endereco.getEstado())
        .cep(endereco.getCep())
        .pais(endereco.getPais())
        .latitude(endereco.getLatitude())
        .longitude(endereco.getLongitude())
        .build();
  }

  public Endereco toModel(EnderecoComp entity) {

    return Endereco.builder()
        .logradouro(entity.getLogradouro())
        .numero(entity.getNumero())
        .complemento(entity.getComplemento())
        .bairro(entity.getBairro())
        .cidade(entity.getCidade())
        .estado(entity.getEstado())
        .cep(entity.getCep())
        .pais(entity.getPais())
        .latitude(entity.getLatitude())
        .longitude(entity.getLongitude())
        .build();
  }
}
