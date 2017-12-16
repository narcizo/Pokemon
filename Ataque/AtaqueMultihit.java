package Ataque;

public class AtaqueMultihit extends Ataque{
    private int min;
    private int max;
    private int id;
    private String nome;
    private String tipo;
    private double ppMax;
    private double ppAtual;
    private double power;
    private double accuracy;
    private String classe;

    public AtaqueMultihit(int linha, String[][] tabelaAtaque) {
        super(linha, tabelaAtaque);
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void efeito(){

    }
}
