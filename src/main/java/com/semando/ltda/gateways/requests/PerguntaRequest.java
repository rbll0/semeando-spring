package com.semando.ltda.gateways.requests;

import com.semando.ltda.domains.enums.TipoPergunta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Classe que representa a requisição de uma pergunta.
 * Usada para criar ou atualizar os dados de uma pergunta.
 */
@Data
public class PerguntaRequest {
    @NotNull
    private Long idLevel;

    @NotBlank
    private String texto;

    @NotNull
    private TipoPergunta tipoPergunta;
}
