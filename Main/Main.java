package Main;

import Jogador.Jogador;
import Pokemon.Pokemon;
import Jogador.Humano;
import Jogador.Maquina;

import java.util.Scanner;

public class Main {
    private double versao = 0.1;
    private Scanner input = new Scanner(System.in);
    Jogador jogador1;
    Jogador jogador2;


    Main(){

        Batalha treta = new Batalha();

        treta.carregarTabelas("src/TabelaDeEspecies.txt", 1);
        treta.carregarTabelas("src/TabelaDeAtaques.txt", 2);
        System.out.println("Simulador Pokemon " + this.versao + " Escolha seu time de 1 a 151:");
        System.out.println("Time 1:");

        escolhePokemon1(treta);
        escolhePokemon2(treta);

        escolheJogador();

        treta.printaListaPokemon();
        //treta.printaAtaque();
        //System.out.println();
        //treta.printaEspecie();
    }

    private void escolheJogador(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Modo de Jogo:\nPvP(0)\nPvM(1)\nMvM(2)");

        switch (scan.nextInt()){
            case 0:
                jogador1 = new Humano();
                jogador2 = new Humano();
                break;
            case 1:
                jogador1 = new Humano();
                jogador2 = new Maquina();
                break;
            case 2:
                jogador1 = new Maquina();
                jogador2 = new Maquina();
                break;
            default:
                System.out.println("Escolha uma entrada valida");
                escolheJogador();
        }
    }

    private void escolhePokemon1(Batalha treta){
        int a;
        do {
            System.out.println("Digite o id do Pokemon: ");
            a = input.nextInt();
            treta.inicializarJogadores(a + 1, 1);
        }while((a > 0 && treta.getJogador1().size() < 6) || !(treta.getJogador1().size() > 0));
        System.out.println();
        System.out.println("O time um e formado dos seguintes Pokemons: ");
        for(int i = 0; i < treta.getJogador1().size(); i ++)
            System.out.println(treta.getJogador1().get(i).getNome());
        System.out.println("Esta satisfeito com esse time?");
        if(input.nextInt() == 0)
            escolhePokemon1(treta);
    }

    private void escolhePokemon2(Batalha treta){
        int a;

        System.out.println("Time 2:");
        do {
            System.out.println("Digite o id do Pokemon: ");
            a = input.nextInt();
            treta.inicializarJogadores(a + 1, 2);
        }while((a > 0 && treta.getJogador2().size() < 6) && !(treta.getJogador2().size() > 0));
        System.out.println();
        System.out.println("O time dois e formado dos seguintes Pokemons: ");
        for(int i = 0; i < treta.getJogador2().size(); i ++)
            System.out.println(treta.getJogador2().get(i).getNome());
        System.out.println("Esta satisfeito com esse time?");
        if(input.nextInt() == 0)
            escolhePokemon2(treta);
    }
}
