package com.ajudaqui.vem_pro_culto_api.domain;

import java.time.LocalDateTime;

import com.ajudaqui.vem_pro_culto_api.domain.enums.EAtividadeTipo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atividade")
public class Atividade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "nome", nullable = false, length = 100)
  private String nome;

  @Column(name = "descricao", nullable = false, length = 256)
  private String descricao;

  @Column(name = "data_hora", nullable = false)
  private LocalDateTime dataHora;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private EAtividadeTipo tipo;

  @ManyToOne
  @JoinColumn(name = "igreja_id")
  private Igreja igreja;
}
