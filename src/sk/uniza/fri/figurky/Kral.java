package sk.uniza.fri.figurky;

import sk.uniza.fri.sachovnica.Sachovnica;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class Kral extends Figurka {
    public Kral(int surRiadku, int surStlpca, String farba, char znakFigurky) {
        super(surRiadku, surStlpca, farba, znakFigurky);
    }

    @Override
    public void zvol(Sachovnica paSachovnica) {
        // prezerame vsetky body okolo krala
        for (int i = this.getSurRiadku() - 1; i <= this.getSurRiadku() + 1; i++) {
            for (int j = this.getSurStlpca() - 1; j <= this.getSurStlpca() + 1; j++) {
                if ((i != this.getSurRiadku()) && (j != this.getSurStlpca())) {
                    if (paSachovnica.jeObsadenePolicko(i, j)) {
                        if (!paSachovnica.getFarbaFigurky(i, j).equals(this.getFarba())) {
                            paSachovnica.oznacPolicko(i, j);
                        }
                    } else {
                        paSachovnica.oznacPolicko(i, j);
                    }
                }
            }
        }
    }
}
