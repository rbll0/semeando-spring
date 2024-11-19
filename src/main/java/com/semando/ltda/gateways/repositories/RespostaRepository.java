package com.semando.ltda.gateways.repositories;

import com.semando.ltda.domains.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    // Método customizado para buscar respostas por usuário (idUsuario)
    List<Resposta> findByUsuario_IdUsuario(Long idUsuario);

    Optional<Resposta> findTopByUsuario_IdUsuarioAndPergunta_IdPerguntaOrderByIdRespostaDesc(Long idUsuario, Long idPergunta);

}
