package Pokemon;

public class Especie {
    private int id;
    private String nome;
    private double baseHp;
    private double baseAtk;
    private double baseDef;
    private double baseSpe;
    private double baseSpd;

    public double calcularAtributos(){
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

    public double getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(double baseHp) {
        this.baseHp = baseHp;
    }

    public double getBaseAtk() {
        return baseAtk;
    }

    public void setBaseAtk(double baseAtk) {
        this.baseAtk = baseAtk;
    }

    public double getBaseDef() {
        return baseDef;
    }

    public void setBaseDef(double baseDef) {
        this.baseDef = baseDef;
    }

    public double getBaseSpe() {
        return baseSpe;
    }

    public void setBaseSpe(double baseSpe) {
        this.baseSpe = baseSpe;
    }

    public double getBaseSpd() {
        return baseSpd;
    }

    public void setBaseSpd(double baseSpd) {
        this.baseSpd = baseSpd;
    }
}
