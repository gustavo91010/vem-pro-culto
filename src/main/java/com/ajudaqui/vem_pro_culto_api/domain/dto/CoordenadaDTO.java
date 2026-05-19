package com.ajudaqui.vem_pro_culto_api.domain.dto;

import java.math.BigDecimal;

public record CoordenadaDTO(
    BigDecimal latitude,
    BigDecimal longitude) {
}
