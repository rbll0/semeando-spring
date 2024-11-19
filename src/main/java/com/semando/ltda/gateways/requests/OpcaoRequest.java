package com.semando.ltda.gateways.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Classe que representa a requisição de uma opção de resposta.
 * Usada para criar ou atualizar os dados de uma opção.
 */
@Data
public class OpcaoRequest {
    @NotNull
    private Long idPergunta;

    @NotNull
    private Integer idOpcao;

    @NotBlank
    private String texto;

    @NotNull
    private Boolean correta;
}
