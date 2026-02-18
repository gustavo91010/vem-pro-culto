package com.ajudaqui.vem_pro_culto_api.infraestructure.compartilhado.telefone;


import com.ajudaqui.vem_pro_culto_api.domain.enums.ETipoTelefone;

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
public class TelefoneComp {

    @Column(nullable = false, length = 20)
    private String numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ETipoTelefone tipo; 

}
