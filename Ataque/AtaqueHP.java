package Ataque;

public class AtaqueHP extends Ataque{
    private int valor;
    private int porcentagem;
    private int id;
    private String nome;
    private String tipo;
    private double ppMax;
    private double ppAtual;
    private double power;
    private double accuracy;
    private String classe;

    public AtaqueHP(int linha, String[][] tabelaAtaque) {super(linha, tabelaAtaque);
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

    public void efeito(){

    }
}
