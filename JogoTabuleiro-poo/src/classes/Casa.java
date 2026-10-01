package classes;

public class Casa {
    protected int numero;

    public Casa(int numero) {
        this.numero = numero;
    }

    public void aplicarEfeito(Jogador jogador, Jogo jogo) {
        // Casas normais não têm efeito
    }
}