package classes;
import java.util.Random;
public abstract class Jogador{
    protected int id;
    protected String cor;
    protected int posicao;
    protected boolean vezDeJogar;
    protected int quantJogadas;
    protected int dado1;
    protected int dado2;
    protected Random random;

    
    public Jogador(int id, String cor){
        this.id = id;
        this.cor = cor;
        this.posicao = 0;
        this.vezDeJogar = true;
        this.quantJogadas = 0;
        this.random = new Random();
    }

    public int getId() { return id; }

    public String getCor(){ return cor; }

    public int getPosicao(){ return posicao; }

    public boolean getPulaRodada(){ return vezDeJogar; }

    public int getQuantJogadas(){ return quantJogadas; }

    public int getDado1(){ return dado1; }

    public int getDado2(){ return dado2; }

    public void incrementarJogadas(){ this.quantJogadas++; }

    public void setPosicao(int posicao) { this.posicao = posicao; }

    public void setVaiJogar(boolean vaiJogar) { this.vezDeJogar = vaiJogar; }
    
    public abstract int jogarDados();

    @Override
    public boolean equals(Object obj) {
    if (obj == this)
        return true;
    if (!(obj instanceof Jogador))
        return false;
    return ((Jogador) obj).id == this.id;
}


}