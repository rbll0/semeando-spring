package com.semando.ltda.gateways.repositories;

import com.semando.ltda.domains.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    // Método customizado para buscar respostas por usuário (idUsuario)
    List<Resposta> findByUsuarioId(Long usuarioId);

    // Método customizado para buscar respostas por pergunta (idPergunta)
    List<Resposta> findByPerguntaId(Long perguntaId);
}
