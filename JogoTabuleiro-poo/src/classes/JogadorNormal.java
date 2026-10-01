package classes;

public class JogadorNormal extends Jogador{

    public JogadorNormal(int id, String cor) {
        super(id, cor);
    }

    @Override
    public int jogarDados() {
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
            incrementarJogadas();
            return dado1 + dado2;
    }
}