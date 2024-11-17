package com.semando.ltda.gateways.procedures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LevelProcedures {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LevelProcedures(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirLevel(String titulo, String descricao, String dificuldade) {
        String sql = "{call inserir_level(?, ?, ?)}";
        jdbcTemplate.update(sql, titulo, descricao, dificuldade);
    }

    public void atualizarLevel(Long idLevel, String titulo, String descricao, String dificuldade) {
        String sql = "{call atualizar_level(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idLevel, titulo, descricao, dificuldade);
    }

    public void deletarLevel(Long idLevel) {
        String sql = "{call deletar_level(?)}";
        jdbcTemplate.update(sql, idLevel);
    }
}
