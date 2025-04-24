package com.itau.desafio_itau.Repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itau.desafio_itau.Model.Transacao;

@Repository
public class TransacaoRepository implements IRepository {
    private List<Transacao> transacoes = new ArrayList<Transacao>();

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public int obterQuantidadeTransacoes() {
        return transacoes.size();
    }
}
