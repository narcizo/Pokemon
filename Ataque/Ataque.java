package Ataque;

public class Ataque {
    private int id;
    private String nome;
    private String tipo;
    private double ppMax;
    private double ppAtual;
    private double power;
    private double accuracy;
    private String classe;

    public Ataque(int linha, String[][] tabelaAtaque) {
        this.id = Integer.parseInt(tabelaAtaque[linha][0]);
        this.nome = tabelaAtaque[linha][1];
        this.tipo = tabelaAtaque[linha][2];
        this.ppMax = Integer.parseInt(tabelaAtaque[linha][3]);
        this.ppAtual = this.ppMax;
        this.power = Integer.parseInt(tabelaAtaque[linha][4]);
        this.accuracy = Integer.parseInt(tabelaAtaque[linha][5]);
        this.classe = tabelaAtaque[linha][6];
    }

    public void efeito(){

    }

    public boolean calculoCritico(){
        return true;
    }

    public boolean calculoAcerto(){
        return true;
    }

    public double calculoDano(){
        return 0;
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
