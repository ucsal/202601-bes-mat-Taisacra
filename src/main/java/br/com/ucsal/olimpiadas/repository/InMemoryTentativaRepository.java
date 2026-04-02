package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Tentativa;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTentativaRepository implements TentativaRepository {
    private long proximoId = 1;
    private final List<Tentativa> tentativas = new ArrayList<>();

    @Override
    public void salvar(Tentativa t) {
        t.setId(proximoId++);
        tentativas.add(t);
    }

    @Override
    public List<Tentativa> buscarTodas() {
        return new ArrayList<>(tentativas);
    }
}