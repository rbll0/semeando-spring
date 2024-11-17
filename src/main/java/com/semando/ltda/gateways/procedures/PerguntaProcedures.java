package com.semando.ltda.gateways.procedures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PerguntaProcedures {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PerguntaProcedures(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirPergunta(Long idLevel, String texto, String tipoPergunta) {
        String sql = "{call inserir_pergunta(?, ?, ?)}";
        jdbcTemplate.update(sql, idLevel, texto, tipoPergunta);
    }

    public void atualizarPergunta(Long idPergunta, Long idLevel, String texto, String tipoPergunta) {
        String sql = "{call atualizar_pergunta(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idPergunta, idLevel, texto, tipoPergunta);
    }

    public void deletarPergunta(Long idPergunta) {
        String sql = "{call deletar_pergunta(?)}";
        jdbcTemplate.update(sql, idPergunta);
    }
}
