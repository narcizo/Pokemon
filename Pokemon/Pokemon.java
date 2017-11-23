package Pokemon;

public class Pokemon {
    private int level;
    private double hpAtual;
    private double hpMax;
    private double atk;
    private double def;
    private double spe;
    private double spd;
    private int modifierAccuracy;
    private int modifierAtk;
    private int modifierDef;
    private int modifierSpe;
    private int modifierSpd;
    private boolean confusion;
    private boolean flinch;

    public double valorAtributo(){
        return 0;
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
}
