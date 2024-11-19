package com.semando.ltda.gateways.procedures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RespostaProcedures {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RespostaProcedures(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirResposta(Long idUsuario, Long idPergunta, int idOpcaoEscolhida) {
        String sql = "{call inserir_resposta(?, ?, ?)}";
        jdbcTemplate.update(sql, idUsuario, idPergunta, idOpcaoEscolhida);
    }

    public void atualizarResposta(Long idResposta, Long idUsuario, Long idPergunta, int idOpcaoEscolhida) {
        String sql = "{call atualizar_resposta(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idResposta, idUsuario, idPergunta, idOpcaoEscolhida);
    }

    public void deletarResposta(Long idResposta) {
        String sql = "{call deletar_resposta(?)}";
        jdbcTemplate.update(sql, idResposta);
    }
}
