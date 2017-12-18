package Ataque;

import Enum.Tipo;
import Pokemon.Pokemon;
import com.sun.org.apache.regexp.internal.RE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Ataque {
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    private int id;
    private String nome;
    private Tipo tipo;
    private double ppMax;
    private double ppAtual;
    private double power;
    private double accuracy;
    private String classe;


    public Ataque(int linha, String[][] tabelaAtaque) {
        this.id = Integer.parseInt(tabelaAtaque[linha][0]);
        this.nome = tabelaAtaque[linha][1];
        this.tipo = Tipo.valueOf(tabelaAtaque[linha][2]);
        this.ppMax = Integer.parseInt(tabelaAtaque[linha][3]);
        this.ppAtual = this.ppMax;
        this.power = Integer.parseInt(tabelaAtaque[linha][4]);
        this.accuracy = Integer.parseInt(tabelaAtaque[linha][5]);
        this.classe = tabelaAtaque[linha][6];
    }

    public void efeito(){

    }

    public boolean calculoCritico(double spd){
        Random rand = new Random();
        if(rand.nextInt(100) < spd/5) {
            System.out.println(RED + "\nCritical Strike!!" + RESET);
            return true;
        }
        else
            return false;
    }

    public boolean calculoAcerto(Pokemon atacante, Pokemon defensor, int i){
        double prob;
        Random rand = new Random();

        prob = atacante.getAtaque().get(i).getAccuracy() * (tabelaModifier(atacante.getModifierAccuracy()) / tabelaModifier(defensor.getModifierEvasion()));

        if (rand.nextInt(100) < prob){
            System.out.println(YELLOW + "Acertou!" + RESET);
            return true;
        }else {
            System.out.println(RED + "Errou!" + RESET);
            return false;
        }
    }

    public double calculoDano(Pokemon atacante, Pokemon defensor, int i){
        double a = 0, d = 0, dano = 0;
        int level = atacante.getLevel();
        Random rand = new Random();
        double power = atacante.getAtaque().get(i).getPower();
        switch (atacante.getAtaque().get(i).getTipo().toString()){
            case "Normal":
                a = atacante.getAtk();
                d = defensor.getDef();
                break;
            case "Fighting":
                a = atacante.getAtk();
                d = defensor.getDef();
                break;
            case "Flying":
                a = atacante.getAtk();
                d = defensor.getDef();
                break;
            case "Poison":
                a = atacante.getAtk();
                d = defensor.getDef();
                break;
            case "Ground":
                a = atacante.getAtk();
                d = defensor.getDef();
                break;
            case "Rock":
                a = atacante.getAtk();
                d = defensor.getDef();
                break;
            case "Bug":
                a = atacante.getAtk();
                d = defensor.getDef();
                break;
            case "Ghost":
                a = atacante.getAtk();
                d = defensor.getDef();
                break;
            case "Fire":
                a = atacante.getSpe();
                d = defensor.getSpe();
                break;
            case "Water":
                a = atacante.getSpe();
                d = defensor.getSpe();
                break;
            case "Electric":
                a = atacante.getSpe();
                d = defensor.getSpe();
                break;
            case "Grass":
                a = atacante.getSpe();
                d = defensor.getSpe();
                break;
            case "Ice":
                a = atacante.getSpe();
                d = defensor.getSpe();
                break;
            case "Psychic":
                a = atacante.getSpe();
                d = defensor.getSpe();
                break;
            case "Dragon":
                a = atacante.getSpe();
                d = defensor.getSpe();
                break;
        }
        if(calculoCritico(atacante.getSpd())) level *= 2;

        if(atacante.getStatus().toString().equals("Burned")) a /= 2;

        if(a < 0) a = 0;
        if(d < 0) d = 0;
        if(a > 255) a = 255;
        if(d > 255) d = 255;

        dano = (level *  a * power / d / 50) + 2;
        if(atacante.getAtaque().get(i).getTipo().toString().equals(atacante.getEspecie().getTipo1().toString()) ||
                atacante.getAtaque().get(i).getTipo().toString().equals(atacante.getEspecie().getTipo2().toString()))
            dano *= 1.5;

        dano *= danoTipo(atacante.getEspecie().getTipo1().toString(), defensor.getEspecie().getTipo1().toString());

        //System.out.println("dano: " + dano);
        dano = (dano*(rand.nextInt(38) + 217))/ 255;

        //System.out.println("level " + level);
        //System.out.println("a " + a);
        //System.out.println("power " + power);
        //System.out.println("d " +d);
        //System.out.println();
        System.out.println(PURPLE + "Dano: " + dano + RESET);
        System.out.println();
        System.out.println();
        return dano;
    }

    private double tabelaModifier(int modifier){
        Map<Integer, Double> pos = new HashMap<>();

        pos.put(-6, 0.33);
        pos.put(-5, 0.37);
        pos.put(-4, 0.43);
        pos.put(-3, 0.50);
        pos.put(-2, 0.60);
        pos.put(-1, 0.75);
        pos.put( 0, 1.0);
        pos.put( 1, 1.33);
        pos.put( 2, 1.66);
        pos.put( 3, 2.0);
        pos.put( 4, 2.33);
        pos.put( 5, 2.66);
        pos.put( 6, 3.0);

        return pos.get(modifier);
    }
    private double danoTipo(String tipo1, String tipo2){
        Map<String, Integer> pos = new HashMap<>();
        double[][] multDano = {{1, 1, 1, 1, 1, 0.5, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                               {2, 1, 0.5, 0.5, 1, 2, 0.5, 0, 1, 1, 1, 1, 0.5, 2, 1},
                               {1, 2, 1, 1, 1, 0.5, 2, 1, 1, 1, 2, 0.5, 1, 1, 1},
                               {1, 1, 1, 0.5, 0.5, 0.5, 2, 0.5, 1, 1, 2, 1, 1, 1, 1},
                               {1, 1, 0, 2, 1, 2, 0.5, 1, 2, 1, 0.5, 2, 1, 1, 1},
                               {1, 0.5, 2, 1, 0.5, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1},
                               {1, 0.5, 0.5, 2, 1, 1, 1, 0.5, 0.5, 1, 2, 1, 2, 1, 1},
                               {0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0, 1, 1},
                               {1, 1, 1, 1, 1, 0.5, 2, 1, 0.5, 0.5, 2, 1, 1, 2, 0.5},
                               {1, 1, 1, 1, 2, 2, 1, 1, 2, 0.5, 0.5, 1, 1, 1, 0.5},
                               {1, 1, 0.5, 0.5, 2, 2, 0.5, 1, 0.5, 2, 0.5, 1, 1, 1, 0.5},
                               {1, 1, 2, 1, 0, 1, 1, 1, 1, 2, 0.5, 0.5, 1, 1, 0.5},
                               {1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 0.5, 1, 1},
                               {1, 1, 2, 1, 2, 1, 1, 1, 1, 0.5, 2, 1, 1, 0.5, 2},
                               {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2}
        };

        pos.put("Normal", 0);
        pos.put("Fighting", 1);
        pos.put("Flying", 2);
        pos.put("Poison", 3);
        pos.put("Ground", 4);
        pos.put("Rock", 5);
        pos.put("Bug", 6);
        pos.put("Ghost", 7);
        pos.put("Fire" , 8);
        pos.put("Water", 9);
        pos.put("Grass", 10);
        pos.put("Electric", 11);
        pos.put("Psychic", 12);
        pos.put("Ice", 13);
        pos.put("Dragon", 14);

        //System.out.println("tipo 1: " + tipo1);
        //System.out.println("tipo 2: " + tipo2);
        return multDano[pos.get(tipo1)][pos.get(tipo2)];
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPpMax() {
        return ppMax;
    }

    public void setPpMax(double ppMax) {
        this.ppMax = ppMax;
    }

    public double getPpAtual() {
        return ppAtual;
    }

    public void setPpAtual(double ppAtual) {
        this.ppAtual = ppAtual;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
