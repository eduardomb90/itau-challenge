package com.itau.desafio_itau.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.itau.desafio_itau.DTO.TransacaoRequest;
import com.itau.desafio_itau.Model.Transacao;
import com.itau.desafio_itau.Service.TransacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService service;
    
    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarTransacao(@RequestBody @Valid TransacaoRequest dto) {
        var transacao = new Transacao(dto.valor(), dto.data());
        service.adicionarTransacao(transacao);
    }
}
