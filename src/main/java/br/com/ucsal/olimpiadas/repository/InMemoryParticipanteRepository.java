package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Participante;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryParticipanteRepository implements ParticipanteRepository {
    private long proximoId = 1;
    private final List<Participante> participantes = new ArrayList<>();

    @Override
    public void salvar(Participante p) {
        p.setId(proximoId++);
        participantes.add(p);
    }

    @Override
    public List<Participante> buscarTodos() {
        return new ArrayList<>(participantes);
    }

    @Override
    public Optional<Participante> buscarPorId(long id) {
        return participantes.stream().filter(p -> p.getId() == id).findFirst();
    }
}