package classes;

public class JogadorSortudo extends Jogador{

    public JogadorSortudo(int id, String cor) {
        super(id, cor);
    }

    @Override
    public int jogarDados() {
        do {
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
        } while (dado1 + dado2 < 7);
        incrementarJogadas();
        return dado1 + dado2;
    }
}