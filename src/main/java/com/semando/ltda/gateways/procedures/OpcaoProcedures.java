package com.semando.ltda.gateways.procedures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OpcaoProcedures {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OpcaoProcedures(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirOpcao(Long idPergunta, int idOpcao, String texto, char correta) {
        String sql = "{call inserir_opcao(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idPergunta, idOpcao, texto, correta);
    }

    public void atualizarOpcao(Long idPergunta, int idOpcao, String texto, char correta) {
        String sql = "{call atualizar_opcao(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idPergunta, idOpcao, texto, correta);
    }

    public void deletarOpcao(Long idPergunta, int idOpcao) {
        String sql = "{call deletar_opcao(?, ?)}";
        jdbcTemplate.update(sql, idPergunta, idOpcao);
    }
}
