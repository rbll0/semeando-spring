package com.semando.ltda.gateways.repositories;

import com.semando.ltda.domains.Level;
import com.semando.ltda.domains.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {
}
