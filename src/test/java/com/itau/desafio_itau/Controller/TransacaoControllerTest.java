package com.itau.desafio_itau.Controller;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.desafio_itau.DTO.TransacaoRequest;
import com.itau.desafio_itau.Service.TransacaoService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TransacaoController.class)
public class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TransacaoService service;

    @Test
    public void deve_retornar_status_201_quando_post_valido() throws Exception {
        //dado: um request POST com um corpo válido
        var dto = new TransacaoRequest(BigDecimal.valueOf(100), OffsetDateTime.now());

       //quando: o endpoint POST é chamado com o corpo do dto
        mockMvc.perform(
                    post("/transacao")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isCreated());

        verify(service).adicionarTransacao(any());
    }

    @Test
    public void deve_retornar_status_422_quando_post_invalido() throws Exception {
        //dado: um request POST com um corpo inválido
        var dto = new TransacaoRequest(BigDecimal.valueOf(100), null);

        //quando: o endpoint POST é chamado com o corpo do dto
        mockMvc.perform(
                    post("/transacao")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void deve_retornar_status_400_quando_json_invalido() throws Exception {
        //dado: um request POST com um corpo inválido
        String invalidJson = "{ \"valor\": 100, \"dataHora\": ";

        //quando: o endpoint POST é chamado
        mockMvc.perform(
                    post("/transacao")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(invalidJson)
                )
                .andExpect(status().isBadRequest());
    }

    
}
