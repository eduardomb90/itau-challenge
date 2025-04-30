package com.itau.desafio_itau.Repository;

import java.util.Queue;

import org.springframework.stereotype.Repository;

import com.itau.desafio_itau.Model.Transacao;

@Repository
public interface IRepository {

    void adicionarTransacao(Transacao transacao);
    int obterQuantidadeTransacoes();
    void limparTransacoes();
    Queue<Transacao> obterTransacoes();
}
