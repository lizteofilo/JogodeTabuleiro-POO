package classes;

public class JogadorAzarado extends Jogador{

    public JogadorAzarado(int id, String cor) {
        super(id, cor);
    }

    @Override
    public int jogarDados() {
        do {
            dado1 = random.nextInt(6) + 1;
            dado2 = random.nextInt(6) + 1;
        } while (dado1 + dado2 > 6);
        incrementarJogadas();
        return dado1 + dado2;
    }
}