package com.itau.desafio_itau.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;

import org.springframework.stereotype.Service;

import com.itau.desafio_itau.Model.Transacao;
import com.itau.desafio_itau.Repository.IRepository;

@Service
public class TransacaoService {

    private IRepository repository;

    public TransacaoService(IRepository repository) {
        this.repository = repository;
    }

    public void adicionarTransacao(Transacao transacao) {
        repository.adicionarTransacao(transacao);
    }

    public int obterQuantidadeTransacoes() {
        return repository.obterQuantidadeTransacoes();
    }

    public void limparTransacoes() {
        repository.limparTransacoes();
    }

    public DoubleSummaryStatistics obterEstatisticas() {
        var transacoes = repository.obterTransacoes();
        return transacoes.stream()
            .filter(t -> t.dataHora().isAfter(OffsetDateTime.now().minusSeconds(60)))
            .map(Transacao::valor) //.map(t -> t.valor()) ... mesma coisa
            .mapToDouble(BigDecimal::doubleValue) // .mapToDouble(bd -> bd.doubleValue()) ... mesma coisa
            .summaryStatistics();
    }

}
