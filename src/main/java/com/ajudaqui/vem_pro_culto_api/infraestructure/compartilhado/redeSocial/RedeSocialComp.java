package com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.redeSocial;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RedeSocialComp {

  @Column(name = "url", nullable = false)
  private String url;

  @Column(name = "tipo", nullable = false, length = 50)
  private String tipo;

}
