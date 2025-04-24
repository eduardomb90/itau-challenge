package com.itau.desafio_itau.Repository;

import org.springframework.stereotype.Repository;

import com.itau.desafio_itau.Model.Transacao;

@Repository
public interface IRepository {

    void adicionarTransacao(Transacao transacao);
    int obterQuantidadeTransacoes();

}
