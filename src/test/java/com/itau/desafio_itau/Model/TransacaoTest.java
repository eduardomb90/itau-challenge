package com.itau.desafio_itau.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;

import com.itau.desafio_itau.Exception.ValueInvalidException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


public class TransacaoTest {

    private static Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void transacao_nao_deve_ser_nula() {
        Transacao transacao = new Transacao(BigDecimal.valueOf(100), OffsetDateTime.now());
        assertNotNull(transacao);
    }

    @Test
    public void nao_deve_validar_transacao_sem_valor() {
        assertThrows(ValueInvalidException.class, () -> {
            Transacao transacao = new Transacao(null, OffsetDateTime.now());
        });
    }

    @Test
    public void nao_deve_validar_transacao_com_data_futura() {
        //dado: dataHora 1 minuto no futuro
        OffsetDateTime dataFutura = OffsetDateTime.now().plusMinutes(1);
        Transacao transacao = new Transacao(BigDecimal.valueOf(100), dataFutura);

        //quando: validamos a transação
        Set<ConstraintViolation<Transacao>> violations = validator.validate(transacao);

        //então: deve haver violação de PastOrPresent
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                                .anyMatch(v -> v.getPropertyPath().toString().equals("dataHora")
                                && v.getMessage().equals("Data e hora da transação não podem ser futuras.")));

    }

    @Test
    public void nao_deve_validar_transacao_com_data_nula() {
        //dado: dataHora nula
        Transacao transacao = new Transacao(BigDecimal.valueOf(100), null);

        //quando: validamos a transação
        Set<ConstraintViolation<Transacao>> violations = validator.validate(transacao);

        //então: deve haver violação de NotNull
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                                .anyMatch(v -> v.getPropertyPath().toString().equals("dataHora")
                                && v.getMessage().equals("Data e hora da transação não podem ser nulos.")));
    }
}