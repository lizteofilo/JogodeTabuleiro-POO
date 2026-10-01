package classes;

public class Casa17 extends Casa {
    public Casa17(int numero) { super(numero); }
    
    @Override
    public void aplicarEfeito(Jogador jogador, Jogo jogo) {
        System.out.println("-> Casa " + numero + ": Reinício!");
        jogo.mandarParaInicio(jogador);
    }
}