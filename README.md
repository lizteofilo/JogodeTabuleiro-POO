# 🎲 Jogo de Tabuleiro em Java (POO)

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" alt="Java">
  <img src="https://img.shields.io/badge/Paradigma-POO-blue?style=for-the-badge&logo=codeforces" alt="POO">
  <img src="https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge" alt="Status">
</p>

Um jogo de tabuleiro clássico implementado em **Java** utilizando os pilares da **Orientação a Objetos (POO)** — com forte ênfase em **Herança**, **Polimorfismo** e **Encapsulamento**. O projeto simula uma disputa eletrizante para até 6 participantes num tabuleiro de 40 casas recheado de eventos especiais.

---

## 🚀 Funcionalidades e Regras do Jogo

- **Participantes:** Suporte de 2 a 6 jogadores simultâneos. Cada competidor escolhe uma cor única (sem repetições) e começa na **Casa 0**.
- **Tipos de Jogadores:** 
  - 🟢 **Normal:** Lança dois dados padrão de 6 faces de forma totalmente aleatória.
  - 🔵 **Sortudo:** Garante sempre uma soma de dados maior ou igual a 7.
  - 🔴 **Azarado:** Garante sempre uma soma de dados menor ou igual a 6.
- **Regra de Vitória:** Ganha o competidor que alcançar ou ultrapassar a **Casa 40** primeiro.
- **Mecânica de Dados Duplos:** Se um jogador tirar dois valores iguais nos dados, avança a respetiva soma e ganha o direito de **jogar novamente**.
- **Modo Debug:** Permite testar o jogo introduzindo manualmente o número da casa de destino pretendida em vez de lançar os dados de forma aleatória.

---

## 🗺️ Casas Especiais e Polimorfismo

Os efeitos do tabuleiro são geridos de forma estritamente polimórfica (sem recurso a estruturas condicionais `switch` ou `if/else` para identificar o tipo de casa):

| Casas | Tipo | Efeito Dinâmico |
| :---: | :--- | :---|
| **5, 15, 30** | 🍀 *Casas da Sorte* | Avança 3 casas (exceto se o jogador for do tipo Azarado). |
| **10, 25, 38** | ⛓️ *Casas de Prisão* | O competidor fica paralisado e perde a próxima rodada. |
| **13** | 🎁 *Casa Surpresa* | O jogador retira uma carta e muda aleatoriamente de tipo (mantendo a posição e estatísticas). |
| **17, 27** | 🔄 *Casas de Reinício* | O jogador escolhe um adversário para voltar imediatamente à Casa 0. |
| **20, 35** | 🪄 *Casas Mágicas* | O jogador troca de posição com o último colocado no tabuleiro. |

---

## 📂 Estrutura do Projeto

O código está organizado de forma modular e limpa, dividindo as responsabilidades em pacotes profissionais:

```text
JogoTabuleiro-poo/
│
├── src/
│   ├── classes/                 # Pacote com o domínio do negócio e entidades
│   │   ├── Casa.java            # Classe base abstrata para as casas
│   │   ├── Casa5.java           # Casa da Sorte
│   │   ├── Casa10.java          # Casa de Prisão
│   │   ├── Casa13.java          # Casa Surpresa
│   │   ├── Casa17.java          # Casa de Reinício
│   │   ├── Casa20.java          # Casa Mágica
│   │   ├── Jogador.java         # Classe base abstrata para os jogadores
│   │   ├── JogadorNormal.java   # Subclasse de Jogador Normal
│   │   ├── JogadorSortudo.java  # Subclasse de Jogador Sortudo
│   │   ├── JogadorAzarado.java  # Subclasse de Jogador Azarado
│   │   ├── Tabuleiro.java       # Gere o mapa (0-40) e o desenho visual
│   │   └── Jogo.java            # Motor principal / Orquestrador da partida
│   │
│   └── main/                    # Pacote de execução
│       └── App.java             # Ponto de entrada (Main)
│
├── .gitignore
└── README.md

```
### 💡 Destaques Técnicos de POO Aplicados

* **Classes Abstratas e Herança:** `Jogador` e `Casa` definem contratos genéricos de atributos e métodos herdados pelas respetivas subclasses especializadas.
* **Polimorfismo Dinâmico:** O motor do jogo invoca `casaAtual.aplicarEfeito(jogador, jogo)` sem conhecer a implementação concreta da casa, delegando o comportamento à própria subclasse.
* **Encapsulamento Rigoroso:** Atributos protegidos e privados com acessores (`getters` e `setters`) seguros.
* **Sobrescrita de Métodos (`@Override`):** Implementada de forma polimórfica para o comportamento dos dados (`jogarDados()`) e para a comparação de igualdade de instâncias (`equals`).

---

### 🛠️ Como Compilar e Executar

Certifique-se de ter o **Java JDK** (versão 8 ou superior) instalado no seu sistema.

1. Clone o repositório para o seu ambiente local:
   ```bash
   https://github.com/lizteofilo/JogodeTabuleiro-POO.git
   
2. Abra o projeto no seu editor ou IDE preferida (como VS Code, IntelliJ IDEA ou Eclipse).

3. Navegue até ao ficheiro principal e execute-o:
     - Caminho: src/main/App.java
  
### 🖥️️ Pré-visualização do Tabuleiro Dinâmico
A cada jogada, o sistema renderiza de forma gráfica o estado atual de todas las posições na consola:

```text
╔══════════════════════════════════════════════════════════════════════╗
║                       MAPA DO TABULEIRO (0 - 40)                     ║
╚══════════════════════════════════════════════════════════════════════╝
+------+------+------+------+------+------+------+------+
|00    |01    |02    |03    |04    |05    |06    |07    |
|AZU,VER|      |      |      |      |AMA   |      |      |
+------+------+------+------+------+------+------+------+
|08    |09    |10    |11    |12    |13    |14    |15    |
|      |      |BRA   |      |      |      |      |      |
+------+------+------+------+------+------+------+------+
...
📍 Posições atuais: azul (Casa 0) | verde (Casa 0) | amarelo (Casa 5) | branco (Casa 10)
=====================================================================================================================

```
### 👨‍💻 Autoria e Notas de Desenvolvimento

* **Desenvolvido por:** [lizteofilo](https://github.com/lizteofilo)
* **Repositório do Projeto:** [GitHub - JogoTabuleiro-poo](https://github.com/lizteofilo/JogodeTabuleiro-POO)
* **Contexto Académico:** Trabalho prático desenvolvido no âmbito da unidade curricular / módulo de **Programação Orientada a Objetos (POO)**.
* **Objetivo:** Consolidar os conhecimentos de modelação de software, boas práticas de engenharia, organização estruturada de pacotes e aplicação rigorosa de padrões orientados a objetos em Java.
