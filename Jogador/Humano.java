package Jogador;

import Pokemon.Pokemon;
import Enum.Status;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Humano extends Jogador {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public Humano() {
    }

    @Override
    public int escolherComando(List<Pokemon> jogador){
        int a;
        Scanner scan = new Scanner(System.in);
        System.out.println(CYAN + "1 - Atacar\n2 - Trocar Pokemon\n3 - Desistir" + RESET);
        switch (scan.nextInt()){
            case 1:
                return 0;

            case 2:
                return 1;

            case 3:
                for (Pokemon i: jogador) {
                    i.setStatus(Status.FAINTED);
                }
                return 2;

            default:
                System.out.println(RED + "Escolha um comando valido." + RESET);
                escolherComando(jogador);
                break;
        }

        System.out.println("nem");
        return 0;
    }

    @Override
    public void trocarPokemon(List<Pokemon> poke){
        int i;
        Scanner scan = new Scanner(System.in);

        i = 0;
        System.out.println(BLUE + "Qual posiçao deseja trocar?" + RESET);
        for (Pokemon p: poke) {
            System.out.println(YELLOW + p.getNome() + " " + i++ + RESET);
        }
        i = scan.nextInt();
        if(i < 0 || i > poke.size() - 1) {
            System.out.println(RED + "Escolha uma posiçao valida" + RESET);
            trocarPokemon(poke);
        }
        else{
            Collections.swap(poke, 0, i);
        }
    }
}
