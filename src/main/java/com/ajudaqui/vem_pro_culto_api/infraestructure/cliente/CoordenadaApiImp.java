package com.ajudaqui.vem_pro_culto_api.infraestructure.cliente;

import java.math.BigDecimal;
import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.exception.NotFoundException;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.CoordenadasApi;
import com.ajudaqui.vem_pro_culto_api.domain.dto.CoordenadaDTO;
import com.ajudaqui.vem_pro_culto_api.domain.dto.LocalizacaoDTO;
import com.ajudaqui.vem_pro_culto_api.infraestructure.gateway.CoordenadaFeing;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CoordenadaApiImp implements CoordenadasApi {

  private final CoordenadaFeing coordenadaFeing;
  final String APPLICATION = "vem-pro-culto";
  final String FORMAT = "json";

  @Override
  public CoordenadaDTO buscarCordenadas(String cep) {
    List<LocalizacaoDTO> coordenadas = coordenadaFeing.buscarCordenadas(APPLICATION, cep, FORMAT);

    if (coordenadas.isEmpty())
      throw new NotFoundException(String.format("Cep: %s não localizado", cep));
    LocalizacaoDTO localizacaoDTO = coordenadas.get(0);
    return new CoordenadaDTO(
        localizacaoDTO.getLatitude(), localizacaoDTO.getLongitude());
  }

  @Override
  public String buscarCep(BigDecimal latitude, BigDecimal longitude) {
    LocalizacaoDTO coordenada = coordenadaFeing.buscarCep(
        APPLICATION,
        latitude.toString(),
        longitude.toString(),
        FORMAT);
    return coordenada.getEndereco().getCep();
  }

}
