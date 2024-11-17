package com.semando.ltda.domains.enums;

/**
 * Enumeração que representa os tipos de perguntas disponíveis no quiz.
 * <p>
 * Essa enum permite definir o formato da pergunta, adaptando a experiência de aprendizado
 * de acordo com o tipo de questão que melhor se ajusta ao conteúdo.
 * </p>
 * Os tipos de perguntas são:
 * <ul>
 *     <li><b>MULTIPLA_ESCOLHA</b> - Pergunta com múltiplas alternativas, onde apenas uma é correta.</li>
 *     <li><b>VERDADEIRO_FALSO</b> - Pergunta com apenas duas opções, verdadeiro ou falso.</li>
 * </ul>
 */

public enum TipoPergunta {
    MULTIPLA_ESCOLHA, VERDADEIRO_FALSO
}
