package Main;

import Jogador.Jogador;
import Pokemon.Pokemon;
import Jogador.Humano;
import Jogador.Maquina;
import com.sun.org.apache.regexp.internal.RE;

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


    private double versao = 0.1;
    private Scanner input = new Scanner(System.in);
    Jogador jogador1;
    Jogador jogador2;


    Main(){
        int a;
        Batalha treta = new Batalha();


        treta.carregarTabelas("src/TabelaDeEspecies.txt", 1);
        treta.carregarTabelas("src/TabelaDeAtaques.txt", 2);
        System.out.println(BLUE + "Simulador Pokemon " + this.versao + " Escolha seu time de 1 a 151:" + RESET);
        a = escolheJogador();

        escolhePokemon1(treta);
        escolhePokemon2(treta);

        treta.printaListaPokemon();

        jogador1.escolherComando();
        jogador2.escolherComando();
    }

    private int escolheJogador(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Modo de Jogo:\nPvP(0)\nPvM(1)\nMvM(2)");

        switch (scan.nextInt()){
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
                escolheJogador();
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
        }while((a > 0 && treta.getJogador1().size() < 6) || !(treta.getJogador1().size() > 0));
        System.out.println();
        System.out.println(BLUE + "O time um e formado dos seguintes Pokemons: " + RESET);
        for(int i = 0; i < treta.getJogador1().size(); i ++)
            System.out.println(treta.getJogador1().get(i).getNome());
        System.out.println(CYAN + "Esta satisfeito com esse time?" + RESET);
        if(input.nextInt() == 0)
            escolhePokemon1(treta);
    }

    private void escolhePokemon2(Batalha treta){
        int a;

        System.out.println(GREEN + "Time 2:" + RESET);
        do {
            System.out.println("Digite o id do Pokemon: ");
            a = input.nextInt();
            treta.inicializarJogadores(a + 1, 2);
        }while((a > 0 && treta.getJogador2().size() < 6) && !(treta.getJogador2().size() > 0));
        System.out.println();
        System.out.println(BLUE + "O time dois e formado dos seguintes Pokemons: " + RESET);
        for(int i = 0; i < treta.getJogador2().size(); i ++)
            System.out.println(treta.getJogador2().get(i).getNome());
        System.out.println(CYAN + "Esta satisfeito com esse time?" + RESET);
        if(input.nextInt() == 0)
            escolhePokemon2(treta);
    }
}
