package com.itau.desafio_itau.DTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.*;

public record TransacaoRequest(
    @NotNull(message = "Valor da transação não pode ser nulo.")
    @PositiveOrZero(message = "Valor da transação não pode ser negativo.")
    BigDecimal valor,

    @NotNull(message = "Data e hora da transação não podem ser nulos.")
    @PastOrPresent(message = "Data e hora da transação não podem ser futuras.")
    OffsetDateTime data
) {}
