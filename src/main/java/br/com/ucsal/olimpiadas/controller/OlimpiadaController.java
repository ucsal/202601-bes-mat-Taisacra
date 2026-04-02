package br.com.ucsal.olimpiadas.controller;

import java.util.List;
import java.util.Scanner;

import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.Resposta;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.service.OlimpiadaService;

public class OlimpiadaController {
	 	private final OlimpiadaService service;
	    private final Scanner in;

	    public OlimpiadaController(OlimpiadaService service) {
	        this.service = service;
	        this.in = new Scanner(System.in);
	    }

	    public void iniciar() {
	        while (true) {
	            System.out.println("\n=== OLIMPÍADA DE QUESTÕES (V1 - SOLID) ===");
	            System.out.println("1) Cadastrar participante");
	            System.out.println("2) Cadastrar prova");
	            System.out.println("3) Cadastrar questão (A–E) em uma prova");
	            System.out.println("4) Aplicar prova (selecionar participante + prova)");
	            System.out.println("5) Listar tentativas (resumo)");
	            System.out.println("0) Sair");
	            System.out.print("> ");

	            try {
	                switch (in.nextLine()) {
	                    case "1" -> CadastrarParticipante();
	                    case "2" -> CadastrarProva();
	                    case "3" -> CadastrarQuestao();
	                    case "4" -> AplicarProva();
	                    case "5" -> ListarTentativas();
	                    case "0" -> {
	                        System.out.println("Tchau!");
	                        return;
	                    }
	                    default -> System.out.println("Opção inválida.");
	                }
	            } catch (Exception e) {
	                System.out.println("Erro: " + e.getMessage());
	            }
	        }
	    }

	    private void CadastrarParticipante() {
	        System.out.print("Nome: ");
	        var nome = in.nextLine();
	        System.out.print("Email (opcional): ");
	        var email = in.nextLine();

	        Participante p = service.cadastrarParticipante(nome, email);
	        System.out.println("Participante cadastrado com sucesso! ID: " + p.getId());
	    }

	    private void CadastrarProva() {
	        System.out.print("Título da prova: ");
	        var titulo = in.nextLine();

	        Prova p = service.cadastrarProva(titulo);
	        System.out.println("Prova criada com sucesso! ID: " + p.getId());
	    }

	    private void CadastrarQuestao() {
	        Long provaId = escolherProva();
	        if (provaId == null) return;

	        System.out.println("Enunciado:");
	        var enunciado = in.nextLine();

	        var alternativas = new String[5];
	        for (int i = 0; i < 5; i++) {
	            char letra = (char) ('A' + i);
	            System.out.print("Alternativa " + letra + ": ");
	            alternativas[i] = letra + ") " + in.nextLine();
	        }

	        System.out.print("Alternativa correta (A–E): ");
	        char correta = in.nextLine().trim().charAt(0);

	        // Opcional: Pedir FEN ou deixar null por padrão para testes simples
	        String fen = "8/8/8/8/8/8/8/8 w - - 0 1"; 

	        Questao q = service.cadastrarQuestao(provaId, enunciado, alternativas, correta, fen);
	        System.out.println("Questão cadastrada com sucesso! ID: " + q.getId());
	    }

	    private void AplicarProva() {
	        Long participanteId = escolherParticipante();
	        if (participanteId == null) return;

	        Long provaId = escolherProva();
	        if (provaId == null) return;

	        Tentativa tentativa = service.registrarTentativa(participanteId, provaId);
	        List<Questao> questoes = service.buscarQuestoesDaProva(provaId);

	        System.out.println("\n--- Início da Prova ---");

	        for (Questao q : questoes) {
	            System.out.println("\nQuestão #" + q.getId());
	            System.out.println(q.getEnunciado());

	            if (q.getFenInicial() != null) {
	                imprimirTabuleiroFen(q.getFenInicial());
	            }

	            for (String alt : q.getAlternativas()) {
	                System.out.println(alt);
	            }

	            System.out.print("Sua resposta (A–E): ");
	            char marcada;
	            try {
	                marcada = Questao.normalizar(in.nextLine().trim().charAt(0));
	            } catch (Exception e) {
	                System.out.println("Resposta inválida (marcando como errada)");
	                marcada = 'X';
	            }

	            boolean isCorreta = q.isRespostaCorreta(marcada);
	            tentativa.adicionarResposta(new Resposta(q.getId(), marcada, isCorreta));
	        }

	        service.salvarTentativaRealizada(tentativa);

	        System.out.println("\n--- Fim da Prova ---");
	        System.out.println("Nota (acertos): " + tentativa.calcularNota() + " / " + tentativa.getRespostas().size());
	    }

	    private void ListarTentativas() {
	        System.out.println("\n--- Tentativas ---");
	        for (Tentativa t : service.listarTentativas()) {
	            System.out.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n",
	                    t.getId(), t.getParticipanteId(), t.getProvaId(),
	                    t.calcularNota(), t.getRespostas().size());
	        }
	    }

	    private Long escolherParticipante() {
	        List<Participante> participantes = service.listarParticipantes();
	        if (participantes.isEmpty()) {
	            System.out.println("Nenhum participante cadastrado.");
	            return null;
	        }
	        System.out.println("\nParticipantes:");
	        participantes.forEach(p -> System.out.printf("  %d) %s%n", p.getId(), p.getNome()));
	        System.out.print("Escolha o id do participante: ");
	        return parseId();
	    }

	    private Long escolherProva() {
	        List<Prova> provas = service.listarProvas();
	        if (provas.isEmpty()) {
	            System.out.println("Nenhuma prova cadastrada.");
	            return null;
	        }
	        System.out.println("\nProvas:");
	        provas.forEach(p -> System.out.printf("  %d) %s%n", p.getId(), p.getTitulo()));
	        System.out.print("Escolha o id da prova: ");
	        return parseId();
	    }

	    private Long parseId() {
	        try {
	            return Long.parseLong(in.nextLine());
	        } catch (NumberFormatException e) {
	            System.out.println("ID inválido.");
	            return null;
	        }
	    }

	    private void imprimirTabuleiroFen(String fen) {
	        String parteTabuleiro = fen.split(" ")[0];
	        String[] ranks = parteTabuleiro.split("/");

	        System.out.println("\n    a b c d e f g h");
	        System.out.println("   -----------------");

	        for (int r = 0; r < 8; r++) {
	            System.out.print((8 - r) + " | ");
	            for (char c : ranks[r].toCharArray()) {
	                if (Character.isDigit(c)) {
	                    int vazios = c - '0';
	                    for (int i = 0; i < vazios; i++) System.out.print(". ");
	                } else {
	                    System.out.print(c + " ");
	                }
	            }
	            System.out.println("| " + (8 - r));
	        }
	        System.out.println("   -----------------");
	        System.out.println("    a b c d e f g h\n");
	    }
}
