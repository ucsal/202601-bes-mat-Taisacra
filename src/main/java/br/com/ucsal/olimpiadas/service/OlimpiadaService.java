package br.com.ucsal.olimpiadas.service;

import java.util.List;

import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;

public class OlimpiadaService {
	 private final ParticipanteRepository participanteRepo;
	    private final ProvaRepository provaRepo;
	    private final QuestaoRepository questaoRepo;
	    private final TentativaRepository tentativaRepo;

	    // Injeção das 4 dependências independentes pelo construtor (DIP)
	    public OlimpiadaService(ParticipanteRepository participanteRepo,
	                            ProvaRepository provaRepo,
	                            QuestaoRepository questaoRepo,
	                            TentativaRepository tentativaRepo) {
	        this.participanteRepo = participanteRepo;
	        this.provaRepo = provaRepo;
	        this.questaoRepo = questaoRepo;
	        this.tentativaRepo = tentativaRepo;
	    }

	    public Participante cadastrarParticipante(String nome, String email) {
	        if (nome == null || nome.isBlank()) {
	            throw new IllegalArgumentException("Nome inválido");
	        }
	        Participante p = new Participante(nome, email);
	        participanteRepo.salvar(p);
	        return p;
	    }

	    public Prova cadastrarProva(String titulo) {
	        if (titulo == null || titulo.isBlank()) {
	            throw new IllegalArgumentException("Título inválido");
	        }
	        Prova p = new Prova(titulo);
	        provaRepo.salvar(p);
	        return p;
	    }

	    public Questao cadastrarQuestao(long provaId, String enunciado, String[] alternativas, char alternativaCorreta, String fen) {
	        if (provaRepo.buscarPorId(provaId).isEmpty()) {
	            throw new IllegalArgumentException("Prova não encontrada");
	        }

	        Questao q = new Questao();
	        q.setProvaId(provaId);
	        q.setEnunciado(enunciado);
	        q.setAlternativas(alternativas);
	        q.setAlternativaCorreta(alternativaCorreta);
	        q.setFenInicial(fen);

	        questaoRepo.salvar(q);
	        return q;
	    }

	    public Tentativa registrarTentativa(long participanteId, long provaId) {
	        if (participanteRepo.buscarPorId(participanteId).isEmpty()) {
	            throw new IllegalArgumentException("Participante não encontrado");
	        }
	        if (provaRepo.buscarPorId(provaId).isEmpty()) {
	            throw new IllegalArgumentException("Prova não encontrada");
	        }

	        List<Questao> questoes = questaoRepo.buscarPorProva(provaId);
	        if (questoes.isEmpty()) {
	            throw new IllegalStateException("Esta prova não possui questões cadastradas");
	        }

	        return new Tentativa(participanteId, provaId);
	    }

	    public void salvarTentativaRealizada(Tentativa tentativa) {
	        tentativaRepo.salvar(tentativa);
	    }

	    public List<Questao> buscarQuestoesDaProva(long provaId) {
	        return questaoRepo.buscarPorProva(provaId);
	    }

	    public List<Participante> listarParticipantes() {
	        return participanteRepo.buscarTodos();
	    }

	    public List<Prova> listarProvas() {
	        return provaRepo.buscarTodas();
	    }

	    public List<Tentativa> listarTentativas() {
	        return tentativaRepo.buscarTodas();
	    }
}
