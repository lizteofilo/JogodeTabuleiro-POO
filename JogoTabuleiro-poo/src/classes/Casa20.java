package classes;

public class Casa20 extends Casa {
    public Casa20(int numero) { super(numero); }
    
    @Override
    public void aplicarEfeito(Jogador jogador, Jogo jogo) {
        System.out.println("-> Casa " + numero + ": Mágica!");
        jogo.trocarComUltimo(jogador);
    }
}