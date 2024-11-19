package com.semando.ltda.gateways.repositories;

import com.semando.ltda.domains.Opcao;
import com.semando.ltda.domains.OpcaoId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpcaoRepository extends JpaRepository<Opcao, OpcaoId> {
    List<Opcao> findByPergunta_IdPergunta(Long idPergunta);

    // Busca opções associadas a uma pergunta específica com suporte a paginação
    Page<Opcao> findByPergunta_IdPergunta(Long idPergunta, Pageable pageable);
}
