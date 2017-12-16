package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import Pokemon.Pokemon;
import Ataque.Ataque;
import Ataque.AtaqueHP;
import Ataque.AtaqueFixo;
import Ataque.AtaqueModifier;
import Ataque.AtaqueMultihit;
import Ataque.AtaqueStatus;
import Ataque.AtaqueCharge;

public class Batalha {
    private BufferedReader conteudoCsv = null;
    private String linha;
    private String separador = "\t";
    private String [][] tabelaEspecie = new String[153][9];
    private String [][] tabelaAtaque = new String[167][8];

    private List<Pokemon> jogador1 = new ArrayList<Pokemon>();
    private List<Pokemon> jogador2 = new ArrayList<Pokemon>();

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
            System.out.println("Arquivo nao encontrado: \n" + e.getMessage());
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

    public void inicializarJogadores(int i, int chave){
        Scanner input = new Scanner(System.in);
        int a, cont = -1;
        Pokemon poke;

        if(i>1 && i<153) {
            System.out.println("seu pokemon e: " + tabelaEspecie[i][1]);
            do{
                System.out.println("Escolha um level entre 1 e 100");
                a = input.nextInt();
            }while(a < 0 || a > 100);

            poke = new Pokemon(tabelaEspecie[i][4], tabelaEspecie[i][5], tabelaEspecie[i][6], tabelaEspecie[i][7], tabelaEspecie[i][8], tabelaEspecie[i][1], a);

            do{
                System.out.println("Escolha os ataques entre 0 a 165");
                a = input.nextInt();
                if(a>0 && a <= 165)
                    poke.setAtaque(criaAtaque(a));
                else
                    System.out.println("Ataque invalido.");
            }while(a >= 0 && ++cont < 3);

            if(chave == 1)
                jogador1.add(poke);
            else
                jogador2.add(poke);

        }
        else
            System.out.println("Escolha um Pokemon valido.");
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

    public List<Pokemon> getJogador1() {
        return jogador1;
    }

    public List<Pokemon> getJogador2() {
        return jogador2;
    }

    public void printaListaPokemon(){
        System.out.println("Jogador 1");
        for(int i = 0; i < this.jogador1.size(); i++){
            System.out.println(">Nome: "+jogador1.get(i).getNome());
            System.out.println(">Tipo: "+jogador1.get(i).getTipo());
            System.out.println(">Habilidades: " + jogador1.get(i).getAtaque());
            for (Ataque j: jogador1.get(i).getAtaque()) {
                System.out.println(">>>Id: " + j.getId());
                System.out.println(">>>Nome : " + j.getNome());
                System.out.println(">>>PPMax: " + j.getPpMax());
                System.out.println(">>>Power: " + j.getPower());
            }
            /*for (int j = 0; i < this.jogador1.get(i).getAtaque().size(); j++){
                System.out.println(">>>Id: " + jogador1.get(i).getAtaque().get(j).getNome());
                System.out.println(">>>Nome : " + jogador1.get(i).getAtaque().get(j).getNome());
                System.out.println(">>>PPMax: " + jogador1.get(i).getAtaque().get(j).getPpMax());
                System.out.println(">>>Power: " + jogador1.get(i).getAtaque().get(j).getPower());
            }*/
            System.out.println(">Atk: "+jogador1.get(i).getAtk());
            System.out.println(">Def: "+jogador1.get(i).getDef());
            System.out.println(">Hp atual:"+jogador1.get(i).getHpAtual());
            System.out.println(">Hp max: >"+jogador1.get(i).getHpMax());
            System.out.println(">Level: "+jogador1.get(i).getLevel());
            System.out.println(">Accuracy Modifier: "+jogador1.get(i).getModifierAccuracy());
            System.out.println(">Atk Modifier: "+jogador1.get(i).getModifierAtk());
            System.out.println(">Def Modifier: "+jogador1.get(i).getModifierDef());
            System.out.println(">Evasion Modifier: "+jogador1.get(i).getModifierEvasion());
            System.out.println(">Spd Modifier: "+jogador1.get(i).getModifierSpd());
            System.out.println(">Spe Modifier: "+jogador1.get(i).getModifierSpe());
            System.out.println(">Spd: "+jogador1.get(i).getSpd());
            System.out.println(">Spe: "+jogador1.get(i).getSpe());
            System.out.println(">Status: "+jogador1.get(i).getStatus());
            System.out.println();
        }
        System.out.println();
        System.out.println("Jogador 2");
        for(int i = 0; i < this.jogador2.size(); i++){
            System.out.println(">Nome: "+jogador2.get(i).getNome());
            System.out.println(">Tipo: "+jogador2.get(i).getTipo());
            System.out.println(">Habilidades: " + jogador2.get(i).getAtaque());
            for (Ataque j: jogador2.get(i).getAtaque()) {
                System.out.println(">>>Id: " + j.getId());
                System.out.println(">>>Nome : " + j.getNome());
                System.out.println(">>>PPMax: " + j.getPpMax());
                System.out.println(">>>Power: " + j.getPower());
            }
            /*for (int j = 0; i < this.jogador2.get(i).getAtaque().size(); j++){
                System.out.println(">>>Id: " + jogador2.get(i).getAtaque().get(j).getNome());
                System.out.println(">>>Nome : " + jogador2.get(i).getAtaque().get(j).getNome());
                System.out.println(">>>PPMax: " + jogador2.get(i).getAtaque().get(j).getPpMax());
                System.out.println(">>>Power: " + jogador2.get(i).getAtaque().get(j).getPower());
            }*/
            System.out.println(">Atk: "+jogador2.get(i).getAtk());
            System.out.println(">Def: "+jogador2.get(i).getDef());
            System.out.println(">Hp atual:"+jogador2.get(i).getHpAtual());
            System.out.println(">Hp max: >"+jogador2.get(i).getHpMax());
            System.out.println(">Level: "+jogador2.get(i).getLevel());
            System.out.println(">Accuracy Modifier: "+jogador2.get(i).getModifierAccuracy());
            System.out.println(">Atk Modifier: "+jogador2.get(i).getModifierAtk());
            System.out.println(">Def Modifier: "+jogador2.get(i).getModifierDef());
            System.out.println(">Evasion Modifier: "+jogador2.get(i).getModifierEvasion());
            System.out.println(">Spd Modifier: "+jogador2.get(i).getModifierSpd());
            System.out.println(">Spe Modifier: "+jogador2.get(i).getModifierSpe());
            System.out.println(">Spd: "+jogador2.get(i).getSpd());
            System.out.println(">Spe: "+jogador2.get(i).getSpe());
            System.out.println(">Status: "+jogador2.get(i).getStatus());
        }
        System.out.println();
    }

}
