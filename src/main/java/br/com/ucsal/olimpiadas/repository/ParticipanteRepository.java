package br.com.ucsal.olimpiadas.repository;

import java.util.List;
import java.util.Optional;

import br.com.ucsal.olimpiadas.model.Participante;

public interface ParticipanteRepository {
    void salvar(Participante p);
    List<Participante> buscarTodos();
    Optional<Participante> buscarPorId(long id);
}
