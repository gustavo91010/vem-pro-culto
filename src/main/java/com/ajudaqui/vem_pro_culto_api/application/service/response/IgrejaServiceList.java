package com.ajudaqui.vem_pro_culto_api.application.service.response;

import java.util.List;

import com.ajudaqui.vem_pro_culto_api.domain.entity.igreja.Igreja;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IgrejaServiceList {

  private long total;
  private List<IgrejaResponse> igrejas;

  public IgrejaServiceList(List<Igreja> igrejas) {
    this.total = igrejas.size();
    this.igrejas = igrejas.stream()
        .map(IgrejaResponse::new)
        .toList();
  }

}
