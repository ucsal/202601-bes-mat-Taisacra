package br.com.ucsal.olimpiadas.repository;

import java.util.List;

import br.com.ucsal.olimpiadas.model.Tentativa;

public interface TentativaRepository {
	void salvar(Tentativa t);
    List<Tentativa> buscarTodas();
}
