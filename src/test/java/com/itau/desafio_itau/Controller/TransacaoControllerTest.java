package com.itau.desafio_itau.Controller;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itau.desafio_itau.DTO.EstatisticaResponse;
import com.itau.desafio_itau.DTO.TransacaoRequest;
import com.itau.desafio_itau.Model.Transacao;
import com.itau.desafio_itau.Repository.TransacaoRepository;
import com.itau.desafio_itau.Service.TransacaoService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

    @Test
    public void deve_retornar_status_200_quando_delete_chamado() throws Exception {
        //dado: um request DELETE
        mockMvc.perform(
                    delete("/transacao")
                )
                .andExpect(status().isOk());

        verify(service).limparTransacoes();
    }

    @Test
    void deve_retornar_status_200_e_json_correspondente_para_get_estatistica() throws Exception {
        // dado: um DoubleSummaryStatistics com dados conhecidos
        DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
        stats.accept(10.0);
        stats.accept(20.0);
        // agora: count = 2, sum = 30.0, avg = 15.0, min = 10.0, max = 20.0

        //quando o mock do controller chamar o service, o método obterEstatisticas() vai retornar o stats
        given(service.obterEstatisticas()).willReturn(stats);

        // quando: o endpoint GET é chamado
        mockMvc.perform(get("/estatistica")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.count").value(stats.getCount()))
            .andExpect(jsonPath("$.sum").value(stats.getSum()))
            .andExpect(jsonPath("$.avg").value(stats.getAverage()))
            .andExpect(jsonPath("$.min").value(stats.getMin()))
            .andExpect(jsonPath("$.max").value(stats.getMax()));

        // verificar que o controller delegou ao service
        verify(service).obterEstatisticas();
    }
}
