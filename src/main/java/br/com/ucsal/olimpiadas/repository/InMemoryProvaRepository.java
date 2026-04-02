package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Prova;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryProvaRepository implements ProvaRepository {
    private long proximoId = 1;
    private final List<Prova> provas = new ArrayList<>();

    @Override
    public void salvar(Prova p) {
        p.setId(proximoId++);
        provas.add(p);
    }

    @Override
    public List<Prova> buscarTodas() {
        return new ArrayList<>(provas);
    }

    @Override
    public Optional<Prova> buscarPorId(long id) {
        return provas.stream().filter(p -> p.getId() == id).findFirst();
    }
}