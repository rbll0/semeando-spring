package com.semando.ltda.gateways.repositories;

import com.semando.ltda.domains.Opcao;
import com.semando.ltda.domains.OpcaoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpcaoRepository extends JpaRepository<Opcao, OpcaoId> {
    // Método customizado para buscar opções por pergunta (idPergunta)
    List<Opcao> findByPerguntaId(Long perguntaId);

}
