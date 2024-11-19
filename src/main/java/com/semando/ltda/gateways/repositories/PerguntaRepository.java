package com.semando.ltda.gateways.repositories;

import com.semando.ltda.domains.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    Optional<Pergunta> findTopByLevel_IdLevelAndTextoOrderByIdPerguntaDesc(Long idLevel, String texto);

    List<Pergunta> findByLevel_IdLevel(Long idLevel);
}
