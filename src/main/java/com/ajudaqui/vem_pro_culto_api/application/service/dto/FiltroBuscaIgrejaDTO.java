package com.ajudaqui.vem_pro_culto_api.application.service.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FiltroBuscaIgrejaDTO {
  private String razaoSocial;
  private String nomeFantasia;
  private String cnpj;
  private String logradouro;
  private String bairro;
  private String rua;
  private String estado;
  private String cep;
  private String cidade;

  public Map<String, String> toFilterMap() {

    Map<String, String> filters = new HashMap<>();

    if (razaoSocial != null)
      filters.put("razaoSocial", razaoSocial);
    if (nomeFantasia != null)
      filters.put("nomeFantasia", nomeFantasia);
    if (cnpj != null)
      filters.put("cnpj", cnpj);
    if (logradouro != null)
      filters.put("logradouro", logradouro);
    if (bairro != null)
      filters.put("bairro", bairro);
    if (rua != null)
      filters.put("rua", rua);
    if (estado != null)
      filters.put("estado", estado);
    if (cep != null)
      filters.put("cep", cep);
    if (cidade != null)
      filters.put("cidade", cidade);

    return filters;
  }
}
