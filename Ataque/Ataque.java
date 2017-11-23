package Ataque;

public class Ataque {
    private int id;
    private String nome;
    private double ppMax;
    private double ppAtual;
    private double power;
    private double accuracy;

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
