package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Questao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryQuestaoRepository implements QuestaoRepository {
    private long proximoId = 1;
    private final List<Questao> questoes = new ArrayList<>();

    @Override
    public void salvar(Questao q) {
        q.setId(proximoId++);
        questoes.add(q);
    }

    @Override
    public List<Questao> buscarPorProva(long provaId) {
        return questoes.stream()
                .filter(q -> q.getProvaId() == provaId)
                .collect(Collectors.toList());
    }
}