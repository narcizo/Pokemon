package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Pokemon.Pokemon;
import Ataque.Ataque;
import Ataque.AtaqueHP;
import Ataque.AtaqueFixo;
import Ataque.AtaqueModifier;
import Ataque.AtaqueMultihit;
import Ataque.AtaqueStatus;
import Ataque.AtaqueCharge;

public class Batalha {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    private BufferedReader conteudoCsv = null;
    private String linha;
    private String separador = "\t";
    private String separadorJogador = " ";
    private String [][] tabelaEspecie = new String[153][9];
    private String [][] tabelaAtaque = new String[167][8];
    private List<Integer> inputJogador = new ArrayList<Integer>();

    private List<Pokemon> listPokemon1 = new ArrayList<Pokemon>();
    private List<Pokemon> listPokemon2 = new ArrayList<Pokemon>();

    Ataque ataque;

    public void carregarTabelas(String arq, int chave){
        int i = 0, j = 0;

        try {
            conteudoCsv = new BufferedReader(new FileReader(arq));//abre arquivo e cria objeto
            while ((linha = conteudoCsv.readLine()) != null) {
                String[] camposLidos = linha.split(separador);
                for (String s : camposLidos) {
                    if (chave == 1)
                        tabelaEspecie[i][j++] = s;
                    else if (chave == 2)
                        tabelaAtaque[i][j++] = s;
                }
                j = 0;
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(RED + "Arquivo nao encontrado: \n" + RESET + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indice fora do limite: \n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de IO: \n" + e.getMessage());
        } finally {
            if (conteudoCsv != null){
                try{
                    conteudoCsv.close();
                }catch(IOException e){
                    System.out.println("Erro de IO (close");
                }
            }

        }

    }

    public List<Integer> carregarJogador(String arq){
        int i = 0, j = 0;

        try {
            conteudoCsv = new BufferedReader(new FileReader(arq));//abre arquivo e cria objeto
            while ((linha = conteudoCsv.readLine()) != null) {
                String[] camposLidos = linha.split(separadorJogador);
                for (String s : camposLidos) {
                    inputJogador.add(Integer.parseInt(s));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(RED + "Arquivo nao encontrado: \n" + RESET + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Indice fora do limite: \n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de IO: \n" + e.getMessage());
        } finally {
            if (conteudoCsv != null){
                try{
                    conteudoCsv.close();
                }catch(IOException e){
                    System.out.println("Erro de IO (close");
                }
            }

        }
        return inputJogador;
    }

    public void inicializarJogadores(int i, int chave){
        Scanner input = new Scanner(System.in);
        int a, cont = -1;
        Pokemon poke;

        if(i>1 && i<153) {
            System.out.println(PURPLE + "seu pokemon e: " + tabelaEspecie[i][1] + RESET);
            do{
                System.out.println("Escolha um level entre 1 e 100");
                a = input.nextInt();
            }while(a <= 0 || a > 100);

            poke = new Pokemon(tabelaEspecie, i, a);

            do{
                System.out.println("Escolha os ataques entre 0 a 165");
                a = input.nextInt();
                if(a>0 && a <= 165)
                    poke.setAtaque(criaAtaque(a));
                else
                    System.out.println(RED + "Ataque invalido." + RESET);
            }while(a >= 0 && ++cont < 3);

            if(chave == 1)
                listPokemon1.add(poke);
            else
                listPokemon2.add(poke);

        }
        else
            System.out.println(RED + "Escolha um Pokemon valido." + RESET);
    }

    public Ataque criaAtaque(int i){
            Ataque ataque = null;
            switch (tabelaAtaque[i][6]){
                case "comum":
                    ataque = new Ataque(i, tabelaAtaque);
                    break;
                case "hp":
                    ataque = new AtaqueHP(i, tabelaAtaque);
                    break;
                case "multihit":
                    ataque = new AtaqueMultihit(i, tabelaAtaque);
                    break;
                case "modifier":
                    ataque = new AtaqueModifier(i, tabelaAtaque);
                    break;
                case "fixo":
                    ataque = new AtaqueFixo(i, tabelaAtaque);
                    break;
                case "status":
                    ataque = new AtaqueStatus(i, tabelaAtaque);
                    break;
                case "charge":
                    ataque = new AtaqueCharge(i, tabelaAtaque);
                    break;
        }

        return ataque;
    }


    public void executarTurno(){

    }

    public void printaAtaque(){
        for(int i = 0; i < 167; i++){
            for (int j = 0; j < 8; j++)
                System.out.printf(tabelaAtaque[i][j] + "\t");
            System.out.println();
        }
        System.out.println();
    }

    public void printaEspecie(){
        for(int i = 0; i < 153; i++){
            for (int j = 0; j < 9; j++)
                System.out.printf(tabelaEspecie[i][j] + "\t");
            System.out.println();
        }
        System.out.println();
    }

    public List<Pokemon> getListPokemon1() {
        return listPokemon1;
    }

    public List<Pokemon> getListPokemon2() {
        return listPokemon2;
    }

    public void printaListaPokemon(){
        System.out.println(YELLOW + "Jogador 1" + RESET);
        for(int i = 0; i < this.listPokemon1.size(); i++){
            System.out.println(">Nome: "+ listPokemon1.get(i).getNome());
            System.out.println(">Habilidades: " + listPokemon1.get(i).getAtaque());
            for (Ataque j: listPokemon1.get(i).getAtaque()) {
                System.out.println(">>>Id: " + j.getId());
                System.out.println(">>>Nome : " + j.getNome());
                System.out.println(">>>PPMax: " + j.getPpMax());
                System.out.println(">>>Power: " + j.getPower());
            }
            System.out.println(">Atk: "+ listPokemon1.get(i).getAtk());
            System.out.println(">Def: "+ listPokemon1.get(i).getDef());
            System.out.println(">Hp atual:"+ listPokemon1.get(i).getHpAtual());
            System.out.println(">Hp max: >"+ listPokemon1.get(i).getHpMax());
            System.out.println(">Level: "+ listPokemon1.get(i).getLevel());
            System.out.println(">Accuracy Modifier: "+ listPokemon1.get(i).getModifierAccuracy());
            System.out.println(">Atk Modifier: "+ listPokemon1.get(i).getModifierAtk());
            System.out.println(">Def Modifier: "+ listPokemon1.get(i).getModifierDef());
            System.out.println(">Evasion Modifier: "+ listPokemon1.get(i).getModifierEvasion());
            System.out.println(">Spd Modifier: "+ listPokemon1.get(i).getModifierSpd());
            System.out.println(">Spe Modifier: "+ listPokemon1.get(i).getModifierSpe());
            System.out.println(">Spd: "+ listPokemon1.get(i).getSpd());
            System.out.println(">Spe: "+ listPokemon1.get(i).getSpe());
            System.out.println(">Status: "+ listPokemon1.get(i).getStatus());
            System.out.println();
        }
        System.out.println();
        System.out.println(YELLOW + "Jogador 2" + RESET);
        for(int i = 0; i < this.listPokemon2.size(); i++){
            System.out.println(">Nome: "+ listPokemon2.get(i).getNome());
            System.out.println(">Habilidades: " + listPokemon2.get(i).getAtaque());
            for (Ataque j: listPokemon2.get(i).getAtaque()) {
                System.out.println(">>>Id: " + j.getId());
                System.out.println(">>>Nome : " + j.getNome());
                System.out.println(">>>PPMax: " + j.getPpMax());
                System.out.println(">>>Power: " + j.getPower());
            }
            System.out.println(">Atk: "+ listPokemon2.get(i).getAtk());
            System.out.println(">Def: "+ listPokemon2.get(i).getDef());
            System.out.println(">Hp atual:"+ listPokemon2.get(i).getHpAtual());
            System.out.println(">Hp max: >"+ listPokemon2.get(i).getHpMax());
            System.out.println(">Level: "+ listPokemon2.get(i).getLevel());
            System.out.println(">Accuracy Modifier: "+ listPokemon2.get(i).getModifierAccuracy());
            System.out.println(">Atk Modifier: "+ listPokemon2.get(i).getModifierAtk());
            System.out.println(">Def Modifier: "+ listPokemon2.get(i).getModifierDef());
            System.out.println(">Evasion Modifier: "+ listPokemon2.get(i).getModifierEvasion());
            System.out.println(">Spd Modifier: "+ listPokemon2.get(i).getModifierSpd());
            System.out.println(">Spe Modifier: "+ listPokemon2.get(i).getModifierSpe());
            System.out.println(">Spd: "+ listPokemon2.get(i).getSpd());
            System.out.println(">Spe: "+ listPokemon2.get(i).getSpe());
            System.out.println(">Status: "+ listPokemon2.get(i).getStatus());
            System.out.println();
        }
        System.out.println();
    }

}
