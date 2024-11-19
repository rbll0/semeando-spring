package com.semando.ltda.gateways.repositories;

import com.semando.ltda.domains.Level;
import com.semando.ltda.domains.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Long> {

    Optional<Level> findTopByTituloOrderByIdLevelDesc(String titulo);

}
