package com.semando.ltda.gateways.requests;

import com.semando.ltda.domains.enums.Ranking;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Classe que representa a requisição de um usuário.
 * Usada para criar ou atualizar os dados de um usuário.
 */
@Data
public class UsuarioRequest {

    @NotBlank
    private String nomeUsuario;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Ranking ranking;

    private int streak;

    private String bio;
}
