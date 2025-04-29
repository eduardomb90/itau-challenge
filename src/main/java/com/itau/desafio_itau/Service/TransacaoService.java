package com.itau.desafio_itau.Service;

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

    

}
