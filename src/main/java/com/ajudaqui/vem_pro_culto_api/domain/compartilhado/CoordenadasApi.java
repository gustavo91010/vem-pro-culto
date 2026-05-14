package com.ajudaqui.vem_pro_culto_api.domain.compartilhado;

import com.ajudaqui.vem_pro_culto_api.domain.dto.CoordenadaDTO;

public interface CoordenadasApi {

  public CoordenadaDTO buscarCep(String cep);
  public CoordenadaDTO buscarCordenadas(String latitude, String longitude);
  
}
