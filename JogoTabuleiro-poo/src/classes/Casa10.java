package classes;

public class Casa10 extends Casa {
    public Casa10(int numero) { super(numero); }
    
    @Override
    public void aplicarEfeito(Jogador jogador, Jogo jogo) {
        System.out.println("-> Casa " + numero + ": Prisão! " + jogador.getCor() + " não joga na próxima rodada.");
        jogador.setVaiJogar(false); 
    }
}