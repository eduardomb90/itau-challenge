package com.itau.desafio_itau.Service;

import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.itau.desafio_itau.Model.Transacao;
import com.itau.desafio_itau.Repository.IRepository;
import com.itau.desafio_itau.Repository.TransacaoRepository;

public class TransacaoServiceTest {

    private IRepository repoMock;
    private TransacaoService service;

    @BeforeEach
    public void setUp() {
        repoMock = mock(IRepository.class);
        service = new TransacaoService(repoMock);
    }

    @Test
    public void deve_adicionar_transacao() {
        //dado: uma transação
        Transacao transacao = new Transacao(BigDecimal.valueOf(100), OffsetDateTime.now());

        //quando: adiciona a transação
        service.adicionarTransacao(transacao);

        //então: a transação deve ser adicionada
        verify(repoMock, times(1)).adicionarTransacao(transacao);
    }
}
