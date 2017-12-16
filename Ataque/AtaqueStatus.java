package Ataque;

public class AtaqueStatus extends Ataque{
    private int status;
    private int chance;
    private int id;
    private String nome;
    private String tipo;
    private double ppMax;
    private double ppAtual;
    private double power;
    private double accuracy;
    private String classe;

    public AtaqueStatus(int linha, String[][] tabelaAtaque) {
        super(linha, tabelaAtaque);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
