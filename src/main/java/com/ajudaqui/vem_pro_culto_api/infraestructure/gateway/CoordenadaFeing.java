package com.ajudaqui.vem_pro_culto_api.infraestructure.gateway;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.dto.CoordenadaDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nominatim.org", url = "https://nominatim.openstreetmap.org")
public interface CoordenadaFeing {

  @GetMapping("/search")
  public List<CoordenadaDTO> buscarCordenadas(@RequestParam("q") String cep, @RequestParam("format") String format);

  @GetMapping("/reverse")
  public List<CoordenadaDTO> buscarCep(
      @RequestParam("lat") String latitude,
      @RequestParam("lon") String longitude,
      @RequestParam("format") String format);

}
