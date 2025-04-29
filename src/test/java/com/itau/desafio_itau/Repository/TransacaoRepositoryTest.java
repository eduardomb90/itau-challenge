package com.itau.desafio_itau.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(1, repository.obterQuantidadeTransacoes());
    }

    @Test
    public void deve_limpar_transacoes() {
        //dado: duas transações
        var transacao1 = new Transacao(BigDecimal.valueOf(100), OffsetDateTime.now());
        var transacao2 = new Transacao(BigDecimal.valueOf(200), OffsetDateTime.now());

        //quando: adiciona as transações
        repository.adicionarTransacao(transacao1);
        repository.adicionarTransacao(transacao2);

        //então: a quantidade de transações deve ser 2
        assertEquals(2, repository.obterQuantidadeTransacoes());

        //quando: limpa as transações
        repository.limparTransacoes();

        //então: a quantidade de transações deve ser 0
        assertEquals(0, repository.obterQuantidadeTransacoes());
    }
}
