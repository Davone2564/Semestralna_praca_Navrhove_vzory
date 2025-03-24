package sk.uniza.fri.figurky;

import sk.uniza.fri.sachovnica.Sachovnica;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class Jazdec extends Figurka {
    public Jazdec(int surRiadku, int surStlpca, String farba, char znakFigurky) {
        super(surRiadku, surStlpca, farba, znakFigurky);
    }

    @Override
    public void zvol(Sachovnica paSachovnica) {
        // v cykle postupne prezerame vsetky policka, na ktore ma na sachovnici kon dosah
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    if (paSachovnica.existujePolicko(this.getSurRiadku() - 1, this.getSurStlpca() - 2)) {
                        if (!paSachovnica.jeObsadenePolicko(this.getSurRiadku() - 1, this.getSurStlpca() - 2)) {
                            paSachovnica.oznacPolicko(this.getSurRiadku() - 1, this.getSurStlpca() - 2);
                        } else {
                            if (!paSachovnica.getFarbaFigurky(this.getSurRiadku() - 1, this.getSurStlpca() - 2).equals(this.getFarba())) {
                                paSachovnica.oznacPolicko(this.getSurRiadku() - 1, this.getSurStlpca() - 2);
                            }
                        }
                    }

                    if (paSachovnica.existujePolicko(this.getSurRiadku() + 1, this.getSurStlpca() - 2)) {
                        if (!paSachovnica.jeObsadenePolicko(this.getSurRiadku() + 1, this.getSurStlpca() - 2)) {
                            paSachovnica.oznacPolicko(this.getSurRiadku() + 1, this.getSurStlpca() - 2);
                        } else {
                            if (!paSachovnica.getFarbaFigurky(this.getSurRiadku() + 1, this.getSurStlpca() - 2).equals(this.getFarba())) {
                                paSachovnica.oznacPolicko(this.getSurRiadku() + 1, this.getSurStlpca() - 2);
                            }
                        }
                    }
                    continue;
                case 3:
                    if (paSachovnica.existujePolicko(this.getSurRiadku() - 1, this.getSurStlpca() + 2)) {
                        if (!paSachovnica.jeObsadenePolicko(this.getSurRiadku() - 1, this.getSurStlpca() + 2)) {
                            paSachovnica.oznacPolicko(this.getSurRiadku() - 1, this.getSurStlpca() + 2);
                        } else {
                            if (!paSachovnica.getFarbaFigurky(this.getSurRiadku() - 1, this.getSurStlpca() + 2).equals(this.getFarba())) {
                                paSachovnica.oznacPolicko(this.getSurRiadku() - 1, this.getSurStlpca() + 2);
                            }
                        }
                    }

                    if (paSachovnica.existujePolicko(this.getSurRiadku() + 1, this.getSurStlpca() + 2)) {
                        if (!paSachovnica.jeObsadenePolicko(this.getSurRiadku() + 1, this.getSurStlpca() + 2)) {
                            paSachovnica.oznacPolicko(this.getSurRiadku() + 1, this.getSurStlpca() + 2);
                        } else {
                            if (!paSachovnica.getFarbaFigurky(this.getSurRiadku() + 1, this.getSurStlpca() + 2).equals(this.getFarba())) {
                                paSachovnica.oznacPolicko(this.getSurRiadku() + 1, this.getSurStlpca() + 2);
                            }
                        }
                    }
                    continue;
                case 1:
                    if (paSachovnica.existujePolicko(this.getSurRiadku() - 2, this.getSurStlpca() - 1)) {
                        if (!paSachovnica.jeObsadenePolicko(this.getSurRiadku() - 2, this.getSurStlpca() - 1)) {
                            paSachovnica.oznacPolicko(this.getSurRiadku() - 2, this.getSurStlpca() - 1);
                        } else {
                            if (!paSachovnica.getFarbaFigurky(this.getSurRiadku() - 2, this.getSurStlpca() - 1).equals(this.getFarba())) {
                                paSachovnica.oznacPolicko(this.getSurRiadku() - 2, this.getSurStlpca() - 1);
                            }
                        }
                    }

                    if (paSachovnica.existujePolicko(this.getSurRiadku() + 2, this.getSurStlpca() - 1)) {
                        if (!paSachovnica.jeObsadenePolicko(this.getSurRiadku() + 2, this.getSurStlpca() - 1)) {
                            paSachovnica.oznacPolicko(this.getSurRiadku() + 2, this.getSurStlpca() - 1);
                        } else {
                            if (!paSachovnica.getFarbaFigurky(this.getSurRiadku() + 2, this.getSurStlpca() - 1).equals(this.getFarba())) {
                                paSachovnica.oznacPolicko(this.getSurRiadku() + 2, this.getSurStlpca() - 1);
                            }
                        }
                    }
                    continue;
                case 2:
                    if (paSachovnica.existujePolicko(this.getSurRiadku() - 2, this.getSurStlpca() + 1)) {
                        if (!paSachovnica.jeObsadenePolicko(this.getSurRiadku() - 2, this.getSurStlpca() + 1)) {
                            paSachovnica.oznacPolicko(this.getSurRiadku() - 2, this.getSurStlpca() + 1);
                        } else {
                            if (!paSachovnica.getFarbaFigurky(this.getSurRiadku() - 2, this.getSurStlpca() + 1).equals(this.getFarba())) {
                                paSachovnica.oznacPolicko(this.getSurRiadku() - 2, this.getSurStlpca() + 1);
                            }
                        }
                    }

                    if (paSachovnica.existujePolicko(this.getSurRiadku() + 2, this.getSurStlpca() + 1)) {
                        if (!paSachovnica.jeObsadenePolicko(this.getSurRiadku() + 2, this.getSurStlpca() + 1)) {
                            paSachovnica.oznacPolicko(this.getSurRiadku() + 2, this.getSurStlpca() + 1);
                        } else {
                            if (!paSachovnica.getFarbaFigurky(this.getSurRiadku() + 2, this.getSurStlpca() + 1).equals(this.getFarba())) {
                                paSachovnica.oznacPolicko(this.getSurRiadku() + 2, this.getSurStlpca() + 1);
                            }
                        }
                    }
                    continue;
            }
        }
    }
}