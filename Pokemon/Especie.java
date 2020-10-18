package Pokemon;
import Enum.Tipo;

public class Especie {
    private int id;
    private String nome;
    private Tipo tipo;
    private Tipo tipo2;
    private double baseHp;
    private double baseAtk;
    private double baseDef;
    private double baseSpe;
    private double baseSpd;
    private double olhaSo;

    public Especie(String[][] tabelaEspecie, int i){
        this.id = Integer.parseInt(tabelaEspecie[i][0]);
        this.nome = tabelaEspecie[i][1];
        this.tipo = Tipo.valueOf(tabelaEspecie[i][2]);
        if(tabelaEspecie[i][3].equals("")) this.tipo2 = Tipo.valueOf("None");
        else this.tipo2 = Tipo.valueOf(tabelaEspecie[i][3]);
        this.baseHp = Integer.parseInt(tabelaEspecie[i][4]);
        this.baseAtk = Integer.parseInt(tabelaEspecie[i][5]);
        this.baseDef = Integer.parseInt(tabelaEspecie[i][6]);
        this.baseSpe = Integer.parseInt(tabelaEspecie[i][7]);
        this.baseSpd = Integer.parseInt(tabelaEspecie[i][8]);
        this.olhaSo = 1231;
    }

    public double calcularHP(int level){
        return (2 * this.baseHp * level)/(100 + level + 10);
    }

    public double calcularAtributos(int level, String chave){
        double ret = 0;
        switch (chave){
            case "atk":
                ret = baseAtk;
                break;
            case "def":
                ret = baseDef;
                break;
            case "spe":
                ret = baseSpe;
                break;
            case "spd":
                ret = baseSpd;
                break;
        }
        return (2 * ret * level)/ 105;
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

    public Tipo getTipo1() {
        return tipo;
    }

    public void setTipo1(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipo2() {
        return tipo2;
    }

    public void setTipo2(Tipo tipo2) {
        this.tipo2 = tipo2;
    }
}
