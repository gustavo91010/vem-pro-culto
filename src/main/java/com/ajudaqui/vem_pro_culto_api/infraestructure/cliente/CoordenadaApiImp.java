package com.ajudaqui.vem_pro_culto_api.infraestructure.cliente;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.compartilhado.CoordenadasApi;
import com.ajudaqui.vem_pro_culto_api.domain.dto.CoordenadaDTO;
import com.ajudaqui.vem_pro_culto_api.infraestructure.gateway.CoordenadaFeing;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CoordenadaApiImp implements CoordenadasApi {

  private final CoordenadaFeing coordenadaFeing;

  @Override
  public CoordenadaDTO buscarCordenadas(String cep) {
    // coordenadaFeing.bus
    List<CoordenadaDTO> lalal = coordenadaFeing.buscarCordenadas(cep, "json");
    System.out.println(lalal);
    return null;
  }

  @Override
  public CoordenadaDTO buscarCep(String latitude, String longitude) {
    List<CoordenadaDTO> buscarCep = coordenadaFeing.buscarCep(latitude, longitude, "json");
    System.out.println(buscarCep);
    return null;
  }

}
