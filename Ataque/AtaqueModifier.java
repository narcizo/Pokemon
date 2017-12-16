package Ataque;

public class AtaqueModifier extends Ataque{
    private int mod;
    private int n;
    private int chance;
    private int id;
    private String nome;
    private String tipo;
    private double ppMax;
    private double ppAtual;
    private double power;
    private double accuracy;
    private String classe;

    public AtaqueModifier(int linha, String[][] tabelaAtaque) {
        super(linha, tabelaAtaque);
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public void efeito(){

    }
}
