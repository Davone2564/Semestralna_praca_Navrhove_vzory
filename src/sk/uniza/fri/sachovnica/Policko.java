package sk.uniza.fri.sachovnica;

import sk.uniza.fri.sachovnica.grafickeObjekty.Stvorec;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class Policko {
    private int surRiadku;
    private int surStlpca;
    private boolean oznacene;
    private boolean zvyraznene;
    private boolean zmenene;

    public Policko(int surRiadku, int surStlpca) {
        this.surRiadku = surRiadku;
        this.surStlpca = surStlpca;
        this.oznacene = false;
        this.zvyraznene = false;
        this.zmenene = true;
    }

    public int getSurRiadku() {
        return this.surRiadku;
    }

    public int getSurStlpca() {
        return this.surStlpca;
    }

    public void oznac() {
        this.oznacene = true;
    }

    public void odznac() {
        this.oznacene = false;
        this.zvyraznene = false;
    }

    public void zvyrazni() {
        this.zvyraznene = true;
    }

    public boolean jeOznacene() {
        return this.oznacene;
    }

    public boolean jeZvyraznene() {
        return this.zvyraznene;
    }

    public boolean jeZmenene() {
        return this.zmenene;
    }

    public void setZmenene(boolean zmenene) {
        this.zmenene = zmenene;
    }
}
