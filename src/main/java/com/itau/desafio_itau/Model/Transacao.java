package com.itau.desafio_itau.Model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.itau.desafio_itau.Exception.ValueInvalidException;

import jakarta.validation.constraints.*;

public record Transacao (

    //A validação do valor está sendo feita por do construtor e por uma exception customizada.
    BigDecimal valor,

    //A validação da data e hora está sendo feita por uma annotation do jakarta, ou Bean Validation.
    @NotNull(message = "Data e hora da transação não podem ser nulos.")
    @PastOrPresent(message = "Data e hora da transação não podem ser futuras.")
    OffsetDateTime dataHora) {

        public Transacao {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValueInvalidException("Valor da transação não pode ser nulo ou negativo.");
        }
    }
}
