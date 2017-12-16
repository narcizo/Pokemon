package Ataque;

public class AtaqueFixo extends Ataque{
    private int val;
    private int id;
    private String nome;
    private String tipo;
    private double ppMax;
    private double ppAtual;
    private double power;
    private double accuracy;
    private String classe;

    public AtaqueFixo(int linha, String[][] tabelaAtaque) {
        super(linha, tabelaAtaque);
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void efeito(){

    }
}
