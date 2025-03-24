package sk.uniza.fri.figurky;

import sk.uniza.fri.sachovnica.Sachovnica;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class Dama extends Figurka {
    public Dama(int surRiadku, int surStlpca, String farba, char znakFigurky) {
        super(surRiadku, surStlpca, farba, znakFigurky);
    }

    @Override
    public void zvol(Sachovnica paSachovnica) {
        // najprv kralovnej dame schopnosti veze
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

        // teraz jej pridelime schopnosti strelca
        // budeme potrebovat sucet suradnic aktualnej polohy na sachovnici
        int sucetSuradnic = this.getSurRiadku() + this.getSurStlpca();
        // v cykle prezerame diagonalu smerom vlavo hore od strelca
        int pocitadlo = 1;
        outerloop:
        for (int i = this.getSurRiadku() - 1; i < -1; i--) {
            for (int j = this.getSurStlpca() - 1; j < -1; j--) {
                if (i + j == sucetSuradnic - pocitadlo * 2) {
                    if (paSachovnica.jeObsadenePolicko(i, j)) {
                        if (!paSachovnica.getFarbaFigurky(i, j).equals(this.getFarba())) {
                            paSachovnica.oznacPolicko(i, j);
                        }
                        break outerloop;
                    } else {
                        paSachovnica.oznacPolicko(i, j);
                    }
                }
            }
            pocitadlo++;
        }

        // v cykle prezerame diagonalu smerom vpravo dole od strelca
        pocitadlo = 1;
        outerloop:
        for (int i = this.getSurRiadku() + 1; i < 8; i++) {
            for (int j = this.getSurStlpca() + 1; j < 8; j++) {
                if (i + j == sucetSuradnic + pocitadlo * 2) {
                    if (paSachovnica.jeObsadenePolicko(i, j)) {
                        if (!paSachovnica.getFarbaFigurky(i, j).equals(this.getFarba())) {
                            paSachovnica.oznacPolicko(i, j);
                        }
                        break outerloop;
                    } else {
                        paSachovnica.oznacPolicko(i, j);
                    }
                }
            }
            pocitadlo++;
        }

        // v cykle prezerame diagonalu smerom vpravo hore od strelca
        outerloop:
        for (int i = this.getSurRiadku() - 1; i > -1; i--) {
            for (int j = this.getSurStlpca() + 1; j < 8; j++) {
                if (i + j == sucetSuradnic) {
                    if (paSachovnica.jeObsadenePolicko(i, j)) {
                        if (!paSachovnica.getFarbaFigurky(i, j).equals(this.getFarba())) {
                            paSachovnica.oznacPolicko(i, j);
                        }
                        break outerloop;
                    } else {
                        paSachovnica.oznacPolicko(i, j);
                    }
                }
            }
        }

        // v cykle prezerame diagonalu smerom vlavo dole od strelca
        outerloop:
        for (int i = this.getSurRiadku() + 1; i < 8; i++) {
            for (int j = this.getSurStlpca() - 1; j > -1; j--) {
                if (i + j == sucetSuradnic) {
                    if (paSachovnica.jeObsadenePolicko(i, j)) {
                        if (!paSachovnica.getFarbaFigurky(i, j).equals(this.getFarba())) {
                            paSachovnica.oznacPolicko(i, j);
                        }
                        break outerloop;
                    } else {
                        paSachovnica.oznacPolicko(i, j);
                    }
                }
            }
        }
    }
}
