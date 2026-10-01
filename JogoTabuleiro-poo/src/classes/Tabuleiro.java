package classes;

import java.util.List;
import java.util.ArrayList;

public class Tabuleiro {
    private Casa[] casas;
    public static final int TAMANHO = 40;

    public Tabuleiro() {
        casas = new Casa[TAMANHO + 1];
        for (int i = 0; i <= TAMANHO; i++) {
            if (i == 10 || i == 25 || i == 38) casas[i] = new Casa10(i);
            else if (i == 13) casas[i] = new Casa13(i);
            else if (i == 5 || i == 15 || i == 30) casas[i] = new Casa5(i);
            else if (i == 17 || i == 27) casas[i] = new Casa17(i);
            else if (i == 20 || i == 35) casas[i] = new Casa20(i);
            else casas[i] = new Casa(i);
        }
    }

    public Casa getCasa(int posicao) {
        return posicao >= TAMANHO ? casas[TAMANHO] : casas[posicao];
    }

 
    public void desenharTabuleiro(List<Jogador> jogadores) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                       MAPA DO TABULEIRO (0 - 40)                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");

        for (int linha = 0; linha <= 4; linha++) {
            StringBuilder linhaCima = new StringBuilder();
            StringBuilder linhaMeio = new StringBuilder();
            StringBuilder linhaBaixo = new StringBuilder();

            int inicio = linha * 8; 
            int fim = Math.min(inicio + 7, TAMANHO);

            for (int i = inicio; i <= fim; i++) {
                linhaCima.append("+------");
                
                String numCasa = String.format("%02d", i);
                linhaMeio.append("|").append(numCasa);

                List<String> coresNaCasa = new ArrayList<>();
                for (Jogador j : jogadores) {
                    if (j.getPosicao() == i) {
                        String corAbreviada = j.getCor().length() > 3 ? j.getCor().substring(0, 3).toUpperCase() : j.getCor().toUpperCase();
                        coresNaCasa.add(corAbreviada);
                    }
                }

                if (!coresNaCasa.isEmpty()) {
                    String peoes = String.join(",", coresNaCasa);
                    if (peoes.length() > 4) peoes = peoes.substring(0, 4);
                    linhaBaixo.append("|").append(peoes);
                } else {
                    linhaBaixo.append("|    ");
                }
            }

            linhaCima.append("+");
            linhaMeio.append("|");
            linhaBaixo.append("|");

            System.out.println(linhaCima.toString());
            System.out.println(linhaMeio.toString());
            System.out.println(linhaBaixo.toString());
        }
        System.out.println("+------+------+------+------+------+------+------+------+");
        
        System.out.print("📍 Posições atuais: ");
        List<String> posTextuais = new ArrayList<>();
        for (Jogador j : jogadores) {
            posTextuais.add(j.getCor() + " (Casa " + j.getPosicao() + ")");
        }
        System.out.println(String.join(" | ", posTextuais));
        System.out.println("========================================================================");
    }
}