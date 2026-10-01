package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private List<Jogador> jogadores = new ArrayList<>();
    private Tabuleiro tabuleiro = new Tabuleiro();
    private Scanner scanner = new Scanner(System.in);
    private boolean debug = false;
    private Jogador vencedor = null;

    public void configurarPartida() {
        System.out.print("Ativar modo Debug? (1-Sim / 0-Não): ");
        debug = scanner.nextInt() == 1;
        
        int num = 0;
        while (num < 2 || num > 6) {
            System.out.print("Quantos jogadores (2 a 6)? ");
            num = scanner.nextInt();
        }
        
        boolean temTiposDiferentes = false;
        String primeiroTipo = "";

        for (int i = 1; i <= num; i++) {
            String cor;
            boolean corDuplicada;
            
            
            do {
                corDuplicada = false;
                System.out.print("Cor/Nome do Jogador " + i + ": ");
                cor = scanner.next();
                
                for (Jogador existente : jogadores) {
                    if (existente.getCor().equalsIgnoreCase(cor)) {
                        System.out.println("⚠️️ Esta cor já foi escolhida! Escolha uma cor diferente.");
                        corDuplicada = true;
                        break;
                    }
                }
            } while (corDuplicada);

            System.out.print("Tipo do Jogador (1-Normal, 2-Sortudo, 3-Azarado): ");
            int tipo = scanner.nextInt();
            
            Jogador j;
            if (tipo == 2) j = new JogadorSortudo(i, cor);
            else if (tipo == 3) j = new JogadorAzarado(i, cor);
            else j = new JogadorNormal(i, cor);

            jogadores.add(j);

            String tipoAtual = j.getClass().getSimpleName();
            if (i == 1) {
                primeiroTipo = tipoAtual;
            } else if (!tipoAtual.equals(primeiroTipo)) {
                temTiposDiferentes = true;
            }
        }

        
        if (!temTiposDiferentes && num > 1) {
            System.out.println("\n[Aviso] Todos os jogadores eram do mesmo tipo. A alterar o último jogador para garantir tipos diferentes...");
            Jogador ultimo = jogadores.get(num - 1);
            if (ultimo instanceof JogadorNormal) {
                jogadores.set(num - 1, new JogadorSortudo(ultimo.getId(), ultimo.getCor()));
            } else {
                jogadores.set(num - 1, new JogadorNormal(ultimo.getId(), ultimo.getCor()));
            }
        }
    }

    public void iniciar() {
        int rodada = 1;
        while (vencedor == null) {
            System.out.println("\n================ RODADA " + rodada + " ================");

            for (Jogador j : jogadores) {
                if (vencedor != null) break;
                
                if (!j.getPulaRodada()) {
                    System.out.println("\n-> Vez de " + j.getCor() + ", mas ele está paralisado nesta rodada!");
                    j.setVaiJogar(true); 
                    continue;
                }
                
                jogarTurno(j);
            }
            rodada++;
        }
        
        System.out.println("\n🏆 ================= FIM DO JOGO ================= 🏆");
        System.out.println("O Vencedor foi o Jogador: " + vencedor.getCor() + " (" + vencedor.getClass().getSimpleName() + ")!");
        System.out.println("\nEstatísticas Finais de Todos os Competidores:");
        for (Jogador j : jogadores) {
            System.out.println("- Jogador " + j.getCor() + " | Tipo: " + j.getClass().getSimpleName() + " | Posição final: Casa " + j.getPosicao() + " | Total de jogadas: " + j.getQuantJogadas());
        }
    }

    private void mostrarPlacarRodada() {
        tabuleiro.desenharTabuleiro(jogadores);
    }

    private void jogarTurno(Jogador j) {
        boolean jogarNovamente;
        do {
            jogarNovamente = false;
            
            
            mostrarPlacarRodada();

            System.out.println("\n--------------------------------------------------");
            System.out.println("É a vez de jogar: " + j.getCor() + " (Tipo: " + j.getClass().getSimpleName() + ")");
            
            int avanco;
            
            if (debug) {
                System.out.print("[MODO DEBUG] Insira o número da casa exata para onde " + j.getCor() + " deve ir: ");
                int destino = scanner.nextInt();
                avanco = destino - j.getPosicao();
                j.incrementarJogadas();
                
                System.out.print("[MODO DEBUG] Simular dados iguais/duplos (jogar novamente)? (1-Sim / 0-Não): ");
                if (scanner.nextInt() == 1) {
                    jogarNovamente = true;
                    System.out.println("🎲 Dados iguais simulados! Jogará novamente.");
                }
            } else {
                avanco = j.jogarDados();
                System.out.println("🎲 " + j.getCor() + " lançou os dados -> Dado 1: " + j.getDado1() + " | Dado 2: " + j.getDado2());
                System.out.println("Soma total dos dados: " + avanco);
                
                if (j.getDado1() == j.getDado2()) {
                    jogarNovamente = true;
                    System.out.println("✨ DADOS IGUAIS! " + j.getCor() + " ganhou o direito de jogar novamente!");
                }
            }
            
            j.setPosicao(j.getPosicao() + avanco);
            verificarVitoria(j);
            if (vencedor != null) return;
            
            System.out.println(j.getCor() + " moveu-se para a casa " + j.getPosicao());

            // Polimorfismo puro aplicado nas casas
            Casa casaAtual = tabuleiro.getCasa(j.getPosicao());
            casaAtual.aplicarEfeito(j, this);
            
            verificarVitoria(j);
            if (vencedor != null) return;

            if (!j.getPulaRodada()) {
                jogarNovamente = false;
            }

        } while (jogarNovamente && vencedor == null);
    }

    private void verificarVitoria(Jogador j) {
        if (j.getPosicao() >= Tabuleiro.TAMANHO) {
            j.setPosicao(Tabuleiro.TAMANHO);
            vencedor = j;
        }
    }

    public void mudarTipoJogador(Jogador j) {
        int r = new Random().nextInt(3);
        Jogador novo;
        if (r == 0) novo = new JogadorNormal(j.getId(), j.getCor());
        else if (r == 1) novo = new JogadorSortudo(j.getId(), j.getCor());
        else novo = new JogadorAzarado(j.getId(), j.getCor());
        
        novo.setPosicao(j.getPosicao());
        for(int k = 0; k < j.getQuantJogadas(); k++) novo.incrementarJogadas();
        novo.setVaiJogar(j.getPulaRodada());
        
        jogadores.set(jogadores.indexOf(j), novo);
        System.out.println("✨ Carta Surpresa tirada! O jogador " + j.getCor() + " mudou de tipo para: " + novo.getClass().getSimpleName() + "!");
    }

    public void mandarParaInicio(Jogador atual) {
        System.out.println("\nEscolha um competidor adversário para voltar para o início (Casa 0):");
        for (Jogador j : jogadores) {
            if (!j.equals(atual)) {
                System.out.println("ID: " + j.getId() + " | Cor: " + j.getCor() + " (Casa " + j.getPosicao() + ")");
            }
        }
        System.out.print("Digite o ID do jogador alvo: ");
        int alvoId = scanner.nextInt();
        
        for (Jogador j : jogadores) {
            if (j.getId() == alvoId) {
                j.setPosicao(0);
                System.out.println("💥 Bum! " + j.getCor() + " foi enviado de volta para a Casa 0!");
            }
        }
    }

    public void trocarComUltimo(Jogador atual) {
        Jogador ultimo = jogadores.get(0);
        for (Jogador j : jogadores) {
            if (j.getPosicao() < ultimo.getPosicao()) {
                ultimo = j;
            }
        }

        if (ultimo.equals(atual) || ultimo.getPosicao() == atual.getPosicao()) {
            System.out.println("🔄 Casa Mágica: " + atual.getCor() + " já é o último ou está empatado, logo nada acontece.");
        } else {
            System.out.println("✨ Casa Mágica! Troca de posições entre " + atual.getCor() + " (Casa " + atual.getPosicao() + ") e o último colocado " + ultimo.getCor() + " (Casa " + ultimo.getPosicao() + ").");
            int tempPos = atual.getPosicao();
            atual.setPosicao(ultimo.getPosicao());
            ultimo.setPosicao(tempPos);
        }
    }
}