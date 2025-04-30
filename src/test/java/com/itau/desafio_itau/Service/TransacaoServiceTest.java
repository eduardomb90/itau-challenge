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

    @Test
    public void deve_limpar_transacoes() {
        //dado: duas transações
        Transacao transacao1 = new Transacao(BigDecimal.valueOf(100), OffsetDateTime.now());
        Transacao transacao2 = new Transacao(BigDecimal.valueOf(200), OffsetDateTime.now());
        
        //quando: adiciona as transações
        service.adicionarTransacao(transacao1);
        service.adicionarTransacao(transacao2);

        //quando: limpa as transações
        service.limparTransacoes();

        //então: a quantidade de transações deve ser 0
        verify(repoMock, times(1)).limparTransacoes();
    }

    @Test
    public void deve_retornar_estatisticas_corretas_para_transacoes_60_segundos() {
        //dado: um repo com três transações:
        // -10s atrás: R$ 10
        // -30s atrás: R$ 20
        // -70s atrás: R$ 100 (deve ser descartada)
        var agora = OffsetDateTime.now();
        var transacao1 = new Transacao(BigDecimal.valueOf(10), agora.minusSeconds(10));
        var transacao2 = new Transacao(BigDecimal.valueOf(20), agora.minusSeconds(30));
        var transacao3 = new Transacao(BigDecimal.valueOf(100), agora.minusSeconds(70));

        var repositorio = new TransacaoRepository();
        var transService = new TransacaoService(repositorio);
        //quando: adiciona as transações
        transService.adicionarTransacao(transacao1);
        transService.adicionarTransacao(transacao2);
        transService.adicionarTransacao(transacao3);

        //quando: obtém as estatísticas
        var estatisticas = transService.obterEstatisticas();

        //então: só o transacao1 e transacao2 devem ser consideradas
        assertEquals(2, estatisticas.getCount());
        assertEquals(30.0, estatisticas.getSum());
        // avg = 30 / 2 = 15.00
        assertEquals(15.0, estatisticas.getAverage());
        assertEquals(10.0, estatisticas.getMin());
        assertEquals(20.0, estatisticas.getMax());
    }
}
