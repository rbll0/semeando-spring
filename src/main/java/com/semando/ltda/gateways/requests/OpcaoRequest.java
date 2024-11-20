package com.semando.ltda.gateways.requests;

import com.semando.ltda.domains.OpcaoId;
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
    private OpcaoId id;

    @NotBlank
    private String texto;

    @NotNull
    private Boolean correta;

}
