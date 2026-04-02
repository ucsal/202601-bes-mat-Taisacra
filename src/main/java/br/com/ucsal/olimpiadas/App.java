package br.com.ucsal.olimpiadas; 

import br.com.ucsal.olimpiadas.controller.OlimpiadaController;
import br.com.ucsal.olimpiadas.repository.InMemoryParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.InMemoryProvaRepository;
import br.com.ucsal.olimpiadas.repository.InMemoryQuestaoRepository;
import br.com.ucsal.olimpiadas.repository.InMemoryTentativaRepository;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;
import br.com.ucsal.olimpiadas.service.OlimpiadaService;

public class App {

    public static void main(String[] args) {
        // Vai instanciar cada repositório de forma independente
        ParticipanteRepository participanteRepo = new InMemoryParticipanteRepository();
        ProvaRepository provaRepo = new InMemoryProvaRepository();
        QuestaoRepository questaoRepo = new InMemoryQuestaoRepository();
        TentativaRepository tentativaRepo = new InMemoryTentativaRepository();

        // Injeta os 4 repositórios no serviço
        OlimpiadaService service = new OlimpiadaService(
                participanteRepo,
                provaRepo,
                questaoRepo,
                tentativaRepo
        );

        // Setup de dados iniciais (Seed)
        seed(service);

        // Injeta o serviço no Controller e inicia a aplicação (Atenção ao nome: ConsoleController)
        OlimpiadaController controller = new OlimpiadaController(service);
        controller.iniciar();
    }

    private static void seed(OlimpiadaService service) {
        var prova = service.cadastrarProva("Olimpíada 2026 • Nível 1 • Prova A");

        String enunciado = "Questão 1 — Mate em 1.\nÉ a vez das brancas.\nEncontre o lance que dá mate imediatamente.\n";
        String fen = "6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1";
        String[] alternativas = {"A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#"};

        service.cadastrarQuestao(prova.getId(), enunciado, alternativas, 'C', fen);
    }
}