package br.com.ucsal.olimpiadas.repository;

import java.util.List;
import java.util.Optional;

import br.com.ucsal.olimpiadas.model.Prova;

public interface ProvaRepository {
    void salvar(Prova p);
    List<Prova> buscarTodas();
    Optional<Prova> buscarPorId(long id);
}
