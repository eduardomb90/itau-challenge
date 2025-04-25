package com.itau.desafio_itau.Model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.itau.desafio_itau.Exception.InvalidDateException;
import com.itau.desafio_itau.Exception.ValueInvalidException;

import jakarta.validation.constraints.*;

public record Transacao (
    BigDecimal valor,
    OffsetDateTime dataHora
) {

        public Transacao {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValueInvalidException("Valor da transação não pode ser nulo ou negativo.");
        }

        if(dataHora == null || dataHora.isAfter(OffsetDateTime.now())){
            throw new InvalidDateException("Data e hora da transação não podem ser nulas ou futuras.");
        }
    }
}
