package sk.uniza.fri.figurky;

import sk.uniza.fri.sachovnica.Sachovnica;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class Veza extends Figurka {
    public Veza(int surRiadku, int surStlpca, String farba, char znakFigurky) {
        super(surRiadku, surStlpca, farba, znakFigurky);
    }

    @Override
    public void zvol(Sachovnica paSachovnica) {
        // v cykle prezerame policka smerom hore od veze
        for (int i = this.getSurRiadku() - 1; i > -1; i--) {
            if (paSachovnica.jeObsadenePolicko(i, this.getSurStlpca())) {
                if (!paSachovnica.getFarbaFigurky(i, this.getSurStlpca()).equals(this.getFarba())) {
                    paSachovnica.oznacPolicko(i, this.getSurStlpca());
                }
                break;
            } else {
                paSachovnica.oznacPolicko(i, this.getSurStlpca());
            }
        }

        // v cykle prezerame policka smerom dole od veze
        for (int i = this.getSurRiadku() + 1; i < 8; i++) {
            if (paSachovnica.jeObsadenePolicko(i, this.getSurStlpca())) {
                if (!paSachovnica.getFarbaFigurky(i, this.getSurStlpca()).equals(this.getFarba())) {
                    paSachovnica.oznacPolicko(i, this.getSurStlpca());
                }
                break;
            } else {
                paSachovnica.oznacPolicko(i, this.getSurStlpca());
            }
        }

        // v cykle prezerame policka smerom dolava od veze
        for (int i = this.getSurStlpca() - 1; i > -1; i--) {
            if (paSachovnica.jeObsadenePolicko(this.getSurRiadku(), i)) {
                if (!paSachovnica.getFarbaFigurky(this.getSurRiadku(), i).equals(this.getFarba())) {
                    paSachovnica.oznacPolicko(this.getSurRiadku(), i);
                }
                break;
            } else {
                paSachovnica.oznacPolicko(this.getSurRiadku(), i);
            }
        }

        // v cykle prezerame policka smerom doprava od veze
        for (int i = this.getSurStlpca() + 1; i < 8; i++) {
            if (paSachovnica.jeObsadenePolicko(this.getSurRiadku(), i)) {
                if (!paSachovnica.getFarbaFigurky(this.getSurRiadku(), i).equals(this.getFarba())) {
                    paSachovnica.oznacPolicko(this.getSurRiadku(), i);
                }
                break;
            } else {
                paSachovnica.oznacPolicko(this.getSurRiadku(), i);
            }
        }
    }
}
