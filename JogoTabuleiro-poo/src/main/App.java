package main;

import classes.Jogo;

public class App {
    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        
        System.out.println("=== BEM-VINDO AO JOGO DE TABULEIRO ===");
        
        // 1. Configura os jogadores e o modo debug
        jogo.configurarPartida();
        
        // 2. Inicia o ciclo de rodadas até alguém vencer
        jogo.iniciar();
    }
}