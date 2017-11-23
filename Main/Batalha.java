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

public class Batalha {
    private BufferedReader conteudoCsv = null;
    private String linha;
    private String separador = "\t";
    private String [][] tabelaEspecie = new String[153][9];
    private String [][] tabelaAtaque = new String[167][8];

    private List<Pokemon> jogador1 = new ArrayList<Pokemon>();
    private List<Pokemon> jogador2 = new ArrayList<Pokemon>();

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
            //if (conteudoCsv);
            //
        }

    }

    public void inicializarJogadores(int i, int chave){
        Scanner input = new Scanner(System.in);
        int a;

        if(i>1 && i<153) {
            System.out.println("seu pokemon e: " + tabelaEspecie[i][1]);
            do{
                System.out.println("Escolha um level entre 1 e 100");
                a = input.nextInt();
            }while(a < 0 || a > 100);
            criaAtaque(i);
            Pokemon poke = new Pokemon(tabelaEspecie[i][4], tabelaEspecie[i][5], tabelaEspecie[i][6], tabelaEspecie[i][7], tabelaEspecie[i][8], tabelaEspecie[i][1], a);
            if(chave == 1)
                jogador1.add(poke);
            else
                jogador2.add(poke);

        }
        else
            System.out.println("Escolha um Pokemon valido.");
    }

    public void criaAtaque(int i){

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

}
