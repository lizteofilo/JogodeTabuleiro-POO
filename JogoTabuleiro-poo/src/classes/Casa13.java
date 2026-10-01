package classes;

public class Casa13 extends Casa {
    public Casa13(int numero) { super(numero); }
    
    @Override
    public void aplicarEfeito(Jogador jogador, Jogo jogo) {
        System.out.println("-> Casa " + numero + ": Surpresa!");
        jogo.mudarTipoJogador(jogador);
    }
}