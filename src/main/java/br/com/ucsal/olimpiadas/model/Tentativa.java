package br.com.ucsal.olimpiadas.model;

import java.util.ArrayList;
import java.util.List;

public class Tentativa {
    private long id;
    private long participanteId;
    private long provaId;
    private final List<Resposta> respostas = new ArrayList<>();

    public Tentativa(long participanteId, long provaId) {
        this.participanteId = participanteId;
        this.provaId = provaId;
    }

    public void adicionarResposta(Resposta resposta) {
        this.respostas.add(resposta);
    }
    
    /* 	o stream transfora a lista em um fluxo de dedos. Funciona como uma esteira por onde os itens da lista resposta vai passar
     	filter vai filtrar as infomações e deixar passar para a próxima etapa se atender a condição.
     	Resposta::isCorreta: Para cada objeto dentro do fluxo, chame o método isCorreta. Se o método retornar TRUE a resposta continua no fluxo de dados, caso não, é descartada.
     	count(): conta quantos itens restaram no fluxo de dados.
     */
    public int calcularNota() {
        return (int) respostas.stream().filter(Resposta::isCorreta).count();
    }

    public long getId() { 
    	return id; 
    }
    public void setId(long id) { 
    	this.id = id; 
    }
    public long getParticipanteId() { 
    	return participanteId; 
    }
    public long getProvaId() { 
    	return provaId; 
    }
    public List<Resposta> getRespostas() { 
    	return respostas; 
    }
}
