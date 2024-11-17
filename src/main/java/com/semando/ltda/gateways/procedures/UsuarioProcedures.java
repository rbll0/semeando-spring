package com.semando.ltda.gateways.procedures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UsuarioProcedures {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioProcedures(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirUsuario(String nomeUsuario, String email, String ranking, String bio) {
        String sql = "{call inserir_usuario(?, ?, ?, ?)}";
        jdbcTemplate.update(sql, nomeUsuario, email, ranking, bio);
    }

    public void atualizarUsuario(Long idUsuario, String nomeUsuario, String email, String ranking, String bio) {
        String sql = "{call atualizar_usuario(?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, idUsuario, nomeUsuario, email, ranking, bio);
    }

    public void deletarUsuario(Long idUsuario) {
        String sql = "{call deletar_usuario(?)}";
        jdbcTemplate.update(sql, idUsuario);
    }
}
