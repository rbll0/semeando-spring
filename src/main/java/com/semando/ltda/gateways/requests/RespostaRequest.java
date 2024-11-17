package com.semando.ltda.gateways.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * Classe que representa a requisição de uma resposta de usuário.
 * Usada para criar ou atualizar os dados de uma resposta.
 */
@Data
public class RespostaRequest {
    @NotNull
    private Long idUsuario;

    @NotNull
    private Long idPergunta;

    @NotNull
    private Long idOpcaoEscolhida;
}
