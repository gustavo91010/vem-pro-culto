package com.ajudaqui.vem_pro_culto_api.infraestructure.cliente;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.application.exception.NotFoundException;
import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.CoordenadasApi;
import com.ajudaqui.vem_pro_culto_api.domain.dto.CoordenadaDTO;
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
  public CoordenadaDTO buscarCep(String cep) {
    List<CoordenadaDTO> coordenadas = coordenadaFeing.buscarCep(APPLICATION, cep, FORMAT);

    if (coordenadas.isEmpty())
      throw new NotFoundException(String.format("Cep: %s não localizado", cep));
    return coordenadas.get(0);
  }

  @Override
  public CoordenadaDTO buscarCordenadas(String latitude, String longitude) {
    return coordenadaFeing.buscarCordenadas(APPLICATION, latitude, longitude, FORMAT);
  }

}
