package Main;

import Jogador.Jogador;
import Jogador.Humano;
import Jogador.Maquina;
import Pokemon.Pokemon;
import Ataque.Ataque;
import Enum.Status;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Collections;
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
        boolean oi = true;
        int p1, p2, input;
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
            for (Pokemon p: treta.getListPokemon2()) {
                if(!p.getStatus().toString().equals("FAINTED"))
                    morto2 = false;
                else
                    morto2 = true;
            }
            oi = true;
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
                System.out.println(YELLOW+"Jogador 1"+ RESET);
                jogador1.trocarPokemon(treta.getListPokemon1());
            }

            if(p2 == 1){
                System.out.println(YELLOW+"Jogador 2" + RESET);
                jogador1.trocarPokemon(treta.getListPokemon2());
            }

            if(p1 == 0 && p2 == 0){
                if(treta.getListPokemon1().get(0).getSpd() >= treta.getListPokemon2().get(0).getSpd()){
                    System.out.println("spd1 > spd2");
                    atacaJogador1(treta);
                    atacaJogador2(treta);
                }else{
                    System.out.println("spd1 < spd2");
                    atacaJogador1(treta);
                    atacaJogador2(treta);
                }
            }
            else if(p1 == 0){
                atacaJogador1(treta);
            }
            else if(p2 == 0){
                atacaJogador2(treta);
            }


        }while(true);

    }

    private void atacaJogador1(Batalha treta){
        Scanner scan = new Scanner(System.in);
        int input;

        System.out.println(YELLOW+"Jogador 1"+ RESET);
        if(treta.getListPokemon1().get(0).getAtaque().size() == 0)
            System.out.println(RED + treta.getListPokemon1().get(0).getNome() + " nao tem ataques!");
            //while(oi){
        else {
            System.out.println(PURPLE + "Ataques Disponiveis" + RESET);
            System.out.println(BLACK + ">" + RESET + treta.getListPokemon1().get(0).getNome());
            for (Ataque a : treta.getListPokemon1().get(0).getAtaque())
                System.out.println(YELLOW + ">>>" + RESET + a.getNome() + "[" + a.getPpAtual() + "]");
            System.out.println();
            do {
                input = scan.nextInt();
            } while (input < 0 || input > treta.getListPokemon1().get(0).getAtaque().size() - 1);
            if (treta.getListPokemon1().get(0).getAtaque().get(input).getPpAtual() - 1 < 0) {
                System.out.println(PURPLE + "Ataques Disponiveis" + RESET);
                System.out.println(BLACK + ">" + RESET + treta.getListPokemon1().get(0).getNome());
                for (Ataque a : treta.getListPokemon1().get(0).getAtaque())
                    System.out.println(YELLOW + ">>>" + RESET + a.getNome() + "[" + a.getPpAtual() + "]");
                System.out.println();
                System.out.println(RED + "Voce nao pode usar esse ataque! Insira um novo ataque\n" + RESET);
                treta.getListPokemon1().get(0).getAtaque().remove(input);
                do {
                    input = scan.nextInt();
                    if(treta.getListPokemon1().get(0).getAtaque().size() == 0){
                        System.out.println("O Pokemon nao tem mais ataques");
                        break;
                    }
                } while (input < 0 || input > treta.getListPokemon1().get(0).getAtaque().size() - 1);
            }
            //}
            else {
                treta.getListPokemon1().get(0).getAtaque().get(input).setPpAtual(treta.getListPokemon1().get(0).getAtaque().get(input).getPpAtual() - 1);
                if (treta.getListPokemon1().get(0).getAtaque().get(input).calculoAcerto(treta.getListPokemon1().get(0), treta.getListPokemon2().get(0), input)) {
                    treta.getListPokemon2().get(0).setHpAtual(treta.getListPokemon2().get(0).getHpAtual() - treta.getListPokemon1().get(0).getAtaque().get(input).calculoDano(treta.getListPokemon1().get(0), treta.getListPokemon2().get(0), input));
                }
                if (treta.getListPokemon2().get(0).getHpAtual() <= 0){
                    System.out.println(RED + "O Pokemon " + treta.getListPokemon2().get(0).getNome() + " desmaiou e nao podera ser usado em combate." + RESET);
                    treta.getListPokemon2().get(0).setStatus(Status.FAINTED);
                    treta.getListPokemon2().get(0).setHpAtual(0);
                    swap(treta.getListPokemon2());
                }
                morto2 = true;
                for (Pokemon p: treta.getListPokemon2()) {
                    if(!p.getStatus().toString().equals("FAINTED"))
                        morto2 = false;
                }
                if(morto2) {
                    System.out.println(RED+"O jogador 2 nao possuiu pokemons aptos para a batalha."+RESET);
                    terminaJogo(1);
                }
            }
        }

    }

    private void atacaJogador2(Batalha treta){
        Scanner scan = new Scanner(System.in);
        int input;

        System.out.println(YELLOW+"Jogador 1"+ RESET);
        if(treta.getListPokemon2().get(0).getAtaque().size() == 0)
            System.out.println(RED + treta.getListPokemon2().get(0).getNome() + " nao tem ataques!");
            //while(oi){
        else {
            System.out.println(PURPLE + "Ataques Disponiveis" + RESET);
            System.out.println(BLACK + ">" + RESET + treta.getListPokemon2().get(0).getNome());
            for (Ataque a : treta.getListPokemon2().get(0).getAtaque())
                System.out.println(YELLOW + ">>>" + RESET + a.getNome() + "[" + a.getPpAtual() + "]");
            System.out.println();
            do {
                input = scan.nextInt();
            } while (input < 0 || input > treta.getListPokemon2().get(0).getAtaque().size() - 1);
            if (treta.getListPokemon2().get(0).getAtaque().get(input).getPpAtual() - 1 < 0) {
                System.out.println(PURPLE + "Ataques Disponiveis" + RESET);
                System.out.println(BLACK + ">" + RESET + treta.getListPokemon2().get(0).getNome());
                for (Ataque a : treta.getListPokemon2().get(0).getAtaque())
                    System.out.println(YELLOW + ">>>" + RESET + a.getNome() + "[" + a.getPpAtual() + "]");
                System.out.println();
                System.out.println(RED + "Voce nao pode usar esse ataque! Insira um novo ataque\n" + RESET);
                treta.getListPokemon2().get(0).getAtaque().remove(input);
                do {
                    input = scan.nextInt();
                    if(treta.getListPokemon2().get(0).getAtaque().size() == 0){
                        System.out.println("O Pokemon nao tem mais ataques");
                        break;
                    }
                } while (input < 0 || input > treta.getListPokemon2().get(0).getAtaque().size() - 1);
            }
            //}
            else {
                treta.getListPokemon2().get(0).getAtaque().get(input).setPpAtual(treta.getListPokemon2().get(0).getAtaque().get(input).getPpAtual() - 1);
                if (treta.getListPokemon2().get(0).getAtaque().get(input).calculoAcerto(treta.getListPokemon2().get(0), treta.getListPokemon1().get(0), input)) {
                    treta.getListPokemon1().get(0).setHpAtual(treta.getListPokemon1().get(0).getHpAtual() - treta.getListPokemon2().get(0).getAtaque().get(input).calculoDano(treta.getListPokemon2().get(0), treta.getListPokemon1().get(0), input));
                }
                if (treta.getListPokemon1().get(0).getHpAtual() <= 0){
                    System.out.println(RED + "O Pokemon " + treta.getListPokemon1().get(0).getNome() + " desmaiou e nao podera ser usado em combate." + RESET);
                    treta.getListPokemon1().get(0).setStatus(Status.FAINTED);
                    treta.getListPokemon1().get(0).setHpAtual(0);
                    swap(treta.getListPokemon1());
                }
                morto1 = true;
                for (Pokemon p: treta.getListPokemon1()) {
                    if(!p.getStatus().toString().equals("FAINTED"))
                        morto1 = false;
                }
                if(morto1) {
                    System.out.println(RED+"O jogador 1 nao possuiu pokemons aptos para a batalha."+RESET);
                    terminaJogo(2);
                }
            }
        }
    }

    private void terminaJogo(int jogador){
        System.out.println(GREEN + "Paraben Jogador " + jogador + ", voce ganhou o jogo!!" + RESET);
        System.exit(0);
    }

    private int escolheJogador(int i){
    Scanner scan = new Scanner(System.in);
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
                escolheJogador(scan.nextInt());
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

    private void swap(List<Pokemon> poke){
        for(int i = 1; i < poke.size(); i++){
            Collections.swap(poke, i, i-1);
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
