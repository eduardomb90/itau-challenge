package com.itau.desafio_itau.Repository;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Repository;

import com.itau.desafio_itau.Model.Transacao;

@Repository
public class TransacaoRepository implements IRepository {
    private Queue<Transacao> transacoes = new ConcurrentLinkedQueue<>();

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public int obterQuantidadeTransacoes() {
        return transacoes.size();
    }

    
}
