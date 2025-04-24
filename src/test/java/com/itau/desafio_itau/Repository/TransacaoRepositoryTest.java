package com.itau.desafio_itau.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.itau.desafio_itau.Model.Transacao;

public class TransacaoRepositoryTest {
    
    private TransacaoRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new TransacaoRepository();
    }

    @Test
    public void deve_salvar_transacao() {
        //dado: uma transação
        Transacao transacao = new Transacao(BigDecimal.valueOf(100), OffsetDateTime.now());

        //quando: salva a transação
        repository.adicionarTransacao(transacao);

        //então: a transação deve ser salva
        Assertions.assertEquals(1, repository.obterQuantidadeTransacoes());
    }
}
