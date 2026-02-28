package com.ajudaqui.vem_pro_culto_api.infraestructure.persistense.atividade;

import java.time.LocalDateTime;

import com.ajudaqui.vem_pro_culto_api.domain.enums.EAtividadeTipo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atividade")
public class AtividadeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "igreja_id", nullable = false)
  private Long igrejaId;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo", nullable = false, length = 30)
  private EAtividadeTipo tipo;

  @Column(name = "descricao", nullable = true, length = 200)
  private String descricao;

  @Column(name = "horario", nullable = false)
  private LocalDateTime horario;

}
