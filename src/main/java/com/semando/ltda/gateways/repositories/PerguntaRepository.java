package com.semando.ltda.gateways.repositories;

import com.semando.ltda.domains.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
    // Método customizado para buscar perguntas por nível (idLevel)
    List<Pergunta> findByLevelIdLevel(Long idLevel);

}
