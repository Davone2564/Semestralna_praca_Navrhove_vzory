package sk.uniza.fri.hra;

import sk.uniza.fri.figurky.Figurka;
import sk.uniza.fri.sachovnica.Policko;
import sk.uniza.fri.sachovnica.Sachovnica;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class PohybCommand implements ICommand {
    private Figurka figurka;
    private Policko startovaciePolicko;
    private Policko cielovePolicko;
    private Sachovnica sachovnica;

    public PohybCommand(Figurka figurka, Policko startovaciePolicko, Policko cielovePolicko, Sachovnica sachovnica) {
        this.figurka = figurka;
        this.startovaciePolicko = startovaciePolicko;
        this.cielovePolicko = cielovePolicko;
        this.sachovnica = sachovnica;
    }

    public void execute() {
        this.sachovnica.posunFigurku(this.figurka, this.cielovePolicko);
        this.startovaciePolicko.setZmenene(true);
        this.cielovePolicko.setZmenene(true);
    }

    public void unexecute() {
        this.sachovnica.oznacPolicko(this.startovaciePolicko.getSurRiadku(), this.startovaciePolicko.getSurStlpca());
        this.sachovnica.posunFigurku(this.figurka, this.startovaciePolicko);
        this.startovaciePolicko.setZmenene(true);
        this.cielovePolicko.setZmenene(true);
    }

    public String toString() {
        //ak sa hybalo s pesiakom(pawn) tak nezapiseme ziadny znak figurky, lebo sa to tak zvykne
        if (this.figurka.getZnakFigurky() == 'P') {
            return Character.toString(this.cielovePolicko.getSurStlpca() + 97) + (8 - this.cielovePolicko.getSurRiadku());
        } else {
            return this.figurka.getZnakFigurky() + Character.toString(this.cielovePolicko.getSurStlpca() + 97) + (8 - this.cielovePolicko.getSurRiadku());
        }
    }
}
