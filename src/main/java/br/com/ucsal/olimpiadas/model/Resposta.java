package br.com.ucsal.olimpiadas.model;

public class Resposta {
    private long questaoId;
    private char alternativaMarcada;
    private boolean correta;

    public Resposta(long questaoId, char alternativaMarcada, boolean correta) {
        this.questaoId = questaoId;
        this.alternativaMarcada = alternativaMarcada;
        this.correta = correta;
    }

    public long getQuestaoId() { 
    	return questaoId; 
    }
    public char getAlternativaMarcada() { 
    	return alternativaMarcada; 
    }
    public boolean isCorreta() { 
    	return correta; 
    }
}
