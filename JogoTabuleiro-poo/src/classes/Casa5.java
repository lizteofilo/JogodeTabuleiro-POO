package classes;

public class Casa5 extends Casa {
    public Casa5(int numero) { super(numero); }
    
    @Override
    public void aplicarEfeito(Jogador jogador, Jogo jogo) {
        System.out.print("-> Casa " + numero + ": Sorte! ");
        if (!(jogador instanceof JogadorAzarado)) {
            System.out.println(jogador.getCor() + " avança 3 casas!");
            jogador.setPosicao(jogador.getPosicao() + 3);
        } else {
            System.out.println("O jogador " + jogador.getCor() + " é azarado, o efeito falhou.");
        }
    }
}