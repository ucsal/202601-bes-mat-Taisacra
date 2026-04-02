package br.com.ucsal.olimpiadas.repository;

import java.util.List;

import br.com.ucsal.olimpiadas.model.Questao;

public interface QuestaoRepository {
	 void salvar(Questao q);
	 List<Questao> buscarPorProva(long provaId);
}
