package com.semando.ltda.gateways.requests;
import com.semando.ltda.domains.enums.Dificuldade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Classe que representa a requisição de um nível.
 * Usada para criar ou atualizar os dados de um nível.
 */
@Data
public class LevelRequest {
    @NotBlank
    private String titulo;

    private String descricao;

    @NotNull
    private Dificuldade dificuldade;
}
