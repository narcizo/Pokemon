package Pokemon;

import java.util.ArrayList;
import java.util.List;
import Ataque.Ataque;
import Enum.Status;


import static sun.swing.MenuItemLayoutHelper.max;

public class Pokemon {
    private String nome;
    private int level;
    private double hpAtual;
    private double hpMax;
    private double atk;
    private double def;
    private double spe;
    private double spd;
    private int modifierAccuracy;
    private int modifierEvasion;
    private int modifierAtk;
    private int modifierDef;
    private int modifierSpe;
    private int modifierSpd;
    private boolean confusion;
    private boolean flinch;
    List<Ataque> ataque = new ArrayList<Ataque>();
    Status status;
    Especie especie;

    public Pokemon(String[][] tabelaEspecie, int i, int level){
        especie = new Especie(tabelaEspecie, i);
        this.level = level;
        this.hpMax = especie.calcularHP(level);
        this.hpAtual = this.hpMax;
        this.atk = especie.calcularAtributos(level, "atk");
        this.def = especie.calcularAtributos(level, "def");
        this.spe = especie.calcularAtributos(level, "spe");
        this.spd = especie.calcularAtributos(level, "spd");
        this.nome = tabelaEspecie[i][1];
        this.modifierAccuracy = 0;
        this.modifierEvasion = 0;
        this.modifierAtk = 0;
        this.modifierDef = 0;
        this.modifierSpe = 0;
        this.modifierSpd = 0;
        this.status = Status.valueOf("OK");
        this.confusion = false;
        this.flinch = false;
    }

    public double valorAtributo(int modifier, String chave){
        int ret = 0;

        switch (chave){
            case "atk":
                ret = modifierAtk;
                break;
            case "def":
                ret = modifierDef;
                break;
            case "spe":
                ret = modifierSpe;
                break;
            case "spd":
                ret = modifierSpd;
                break;
        }
        return ret *( max(2,2+modifier)/max(max(2,2-modifier)));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLevel() {
        return level;

    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getHpAtual() {
        return hpAtual;
    }

    public void setHpAtual(double hpAtual) {
        this.hpAtual = hpAtual;
    }

    public double getHpMax() {
        return hpMax;
    }

    public void setHpMax(double hpMax) {
        this.hpMax = hpMax;
    }

    public double getAtk() {
        return atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public double getDef() {
        return def;
    }

    public void setDef(double def) {
        this.def = def;
    }

    public double getSpe() {
        return spe;
    }

    public void setSpe(double spe) {
        this.spe = spe;
    }

    public double getSpd() {
        return spd;
    }

    public void setSpd(double spd) {
        this.spd = spd;
    }

    public int getModifierAccuracy() {
        return modifierAccuracy;
    }

    public void setModifierAccuracy(int modifierAccuracy) {
        this.modifierAccuracy = modifierAccuracy;
    }

    public int getModifierAtk() {
        return modifierAtk;
    }

    public void setModifierAtk(int modifierAtk) {
        this.modifierAtk = modifierAtk;
    }

    public int getModifierDef() {
        return modifierDef;
    }

    public void setModifierDef(int modifierDef) {
        this.modifierDef = modifierDef;
    }

    public int getModifierSpe() {
        return modifierSpe;
    }

    public void setModifierSpe(int modifierSpe) {
        this.modifierSpe = modifierSpe;
    }

    public int getModifierSpd() {
        return modifierSpd;
    }

    public void setModifierSpd(int modifierSpd) {
        this.modifierSpd = modifierSpd;
    }

    public boolean isConfusion() {
        return confusion;
    }

    public void setConfusion(boolean confusion) {
        this.confusion = confusion;
    }

    public boolean isFlinch() {
        return flinch;
    }

    public void setFlinch(boolean flinch) {
        this.flinch = flinch;
    }

    public int getModifierEvasion() {
        return modifierEvasion;
    }

    public void setModifierEvasion(int modifierEvasion) {
        this.modifierEvasion = modifierEvasion;
    }

    public List<Ataque> getAtaque() {
        return ataque;
    }

    public Ataque getAtaque1(int id){
        return ataque.get(id);
    }

    public void setAtaque(Ataque ataque) {
        this.ataque.add(ataque);
        for (Ataque a: this.ataque) {
            //System.out.println(a.getNome());
        }
    }

    public void setAtaque(List<Ataque> ataque) {
        this.ataque = ataque;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Enum getStatus() {
        return status;
    }

}
