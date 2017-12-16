package Ataque;

public class AtaqueCharge extends Ataque{
    private int id;
    private String nome;
    private String tipo;
    private double ppMax;
    private double ppAtual;
    private double power;
    private double accuracy;
    private String classe;

    public AtaqueCharge(int linha, String[][] tabelaAtaque) {
        super(linha, tabelaAtaque);
    }

    public void efeito(){

    }
}
