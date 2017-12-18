package Main;

import Jogador.Jogador;
import Jogador.Humano;
import Jogador.Maquina;
import Pokemon.Pokemon;
import Ataque.Ataque;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";


    private boolean morto1;
    private boolean morto2;
    private double versao = 0.1;
    private Scanner input = new Scanner(System.in);
    private List<Integer> inputJogador;
    private Jogador jogador1;
    private Jogador jogador2;


    Main(){
        this.morto1 = false;
        this.morto2 = false;
        int p1, p2;
        Batalha treta = new Batalha();
        Scanner scan = new Scanner(System.in);


        treta.carregarTabelas("src/TabelaDeEspecies.txt", 1);
        treta.carregarTabelas("src/TabelaDeAtaques.txt", 2);
        inputJogador = treta.carregarJogador("src/Jogador1.txt");

        System.out.println(BLUE + "Simulador Pokemon " + this.versao + " Escolha seu time de 1 a 151:" + RESET);
        System.out.println("Modo de Jogo:\nPvP(0)\nPvM(1)\nMvM(2)");
        escolheJogador(scan.nextInt());

        escolhePokemon1(treta);
        escolhePokemon2(treta);

        treta.printaListaPokemon();

        do {
            treta.getListPokemon1().get(0).showStatus(treta.getListPokemon1(), 1);
            treta.getListPokemon2().get(0).showStatus(treta.getListPokemon2(), 2);
            p1 = jogador1.escolherComando(treta.getListPokemon1());
            if(p1 == 2){
                System.out.println(RED + "Jogador 1 Desistiu." + RESET);
                terminaJogo(2);
            }
            p2 = jogador2.escolherComando(treta.getListPokemon2());
            if(p2 == 2){
                System.out.println(RED + "Jogador 2 Desistiu." + RESET);
                terminaJogo(1);
            }
            if(p1 == 1){
                jogador1.trocarPokemon(treta.getListPokemon1());
            }
            if(p2 == 1){
                jogador1.trocarPokemon(treta.getListPokemon2());
            }
            if(p1 == 0){
                System.out.println(PURPLE+"Ataques Disponiveis"+RESET);
                for (Pokemon p: treta.getListPokemon1()) {
                    System.out.println(BLACK + ">"+ RESET + p.getNome());
                    for (Ataque a: p.getAtaque()) {
                        System.out.println(YELLOW + ">>>" + RESET + a.getNome() + "["+a.getPpAtual()+"]");
                    }
                }
            }
        }while(true);

    }

    private void terminaJogo(int jogador){
        System.out.println(GREEN + "Paraben Jogador " + jogador + ", voce ganhou o jogo!!" + RESET);
        System.exit(0);
    }

    private int escolheJogador(int i){

        switch (i){
            case 0:
                jogador1 = new Humano();
                jogador2 = new Humano();
                return 1;
            case 1:
                jogador1 = new Humano();
                jogador2 = new Maquina();
                return 2;
            case 2:
                jogador1 = new Maquina();
                jogador2 = new Maquina();
                return 3;
            default:
                System.out.println("Escolha uma entrada valida");
                escolheJogador(i);
        }
        return -1;
    }

    private void escolhePokemon1(Batalha treta){
        int a;

        System.out.println(GREEN + "Time 1:" + RESET);
        do {
            System.out.println("Digite o id do Pokemon: ");
            a = input.nextInt();
            treta.inicializarJogadores(a + 1, 1);
        }while((a > 0 && treta.getListPokemon1().size() < 6) || !(treta.getListPokemon1().size() > 0));
        System.out.println();
        System.out.println(BLUE + "O time um e formado dos seguintes Pokemons: " + RESET);
        for(int i = 0; i < treta.getListPokemon1().size(); i ++)
            System.out.println(treta.getListPokemon1().get(i).getNome());
        System.out.println(CYAN + "Esta satisfeito com esse time?" + RESET);
        if(input.nextInt() == 0) {
            for (int i = treta.getListPokemon1().size() - 1; i >=0; i--)
                treta.getListPokemon1().remove(i);
            escolhePokemon1(treta);
        }
    }

    private void escolhePokemon2(Batalha treta){
        int a;

        System.out.println(GREEN + "Time 2:" + RESET);
        do {
            System.out.println("Digite o id do Pokemon: ");
            a = input.nextInt();
            treta.inicializarJogadores(a + 1, 2);
        }while((a > 0 && treta.getListPokemon2().size() < 6) || !(treta.getListPokemon2().size() > 0));
        System.out.println();
        System.out.println(BLUE + "O time dois e formado dos seguintes Pokemons: " + RESET);
        for(int i = 0; i < treta.getListPokemon2().size(); i ++)
            System.out.println(treta.getListPokemon2().get(i).getNome());
        System.out.println(CYAN + "Esta satisfeito com esse time?" + RESET);
        if(input.nextInt() == 0) {
            for (int i = treta.getListPokemon2().size() - 1; i >= 0; i--)
                treta.getListPokemon2().remove(i);
            escolhePokemon2(treta);
        }
    }

}
