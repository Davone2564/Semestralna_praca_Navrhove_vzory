package sk.uniza.fri.sachovnica;

import sk.uniza.fri.figurky.Figurka;
import sk.uniza.fri.figurky.Dama;
import sk.uniza.fri.figurky.Jazdec;
import sk.uniza.fri.figurky.Veza;
import sk.uniza.fri.figurky.Kral;
import sk.uniza.fri.figurky.Pesiak;
import sk.uniza.fri.figurky.Strelec;

import java.util.ArrayList;
import java.util.List;

/**
 * Trieda Sachovnica vytvorí maximálne jednu šachovnicu, ktorá sa dá zobraziť na plátne
 * 
 * @author (Dávid Mičo) 
 * @version (08.01.2021)
 */
public class Sachovnica {
    //atribúty triedy
    private static final String FARBA_FIGURIEK1 = "black";
    private static final String FARBA_FIGURIEK2 = "white";
    //atribúty inštancie
    private Policko[][] policka;
    private Figurka[][] figurky;
    private String[] pismena; // pole súradníc stĺpcov pod šachovnicou
    private String[] cisla; // pole súradníc riadkov vľavo od šachovnice
    private final int pocetPolicok = 8; // počet políčok na jednom riadku šachovnice
    
    /**
     * Konštruktor triedy Sachovnica bez parametrov - vytvorí šachovnicu s pevne danou veľkosťou a umiestnením na plátne
     */
    public Sachovnica() {
        this.policka = new Policko[this.pocetPolicok][this.pocetPolicok];
        this.figurky = new Figurka[this.pocetPolicok][this.pocetPolicok];
        // vytvorenie políčok šachovnice
        for (int riadok = 0; riadok < this.policka.length; riadok++) {
            for (int stlpec = 0; stlpec < this.policka[0].length; stlpec++) {
                this.policka[riadok][stlpec] = new Policko(riadok, stlpec);
            }
        }

        this.pismena = new String[8];
        this.cisla = new String[8];
        // vytvorenie pomocného riadku pod políčkami šachovnice, určujúceho súradnice stĺpcov šachovnice 
        this.pismena[0] = "A";
        this.pismena[1] = "B";
        this.pismena[2] = "C";
        this.pismena[3] = "D";
        this.pismena[4] = "E";
        this.pismena[5] = "F";
        this.pismena[6] = "G";
        this.pismena[7] = "H";
        // vytvorenie pomocného stĺpca vľavo od šachovnice, určujúceho súradnice riadkov šachovnice
        for (int i = 8; i > 0; i--) {
            this.cisla[i - 1] = i + "";
        }

        // umiestňovanie figúriek na šachovnicu
        for (int riadok = 0; riadok < this.pocetPolicok; riadok++) {
            for (int stlpec = 0; stlpec < this.pocetPolicok; stlpec++) {
                if (riadok == 0) {
                    if ((stlpec == 0) || (stlpec == 7)) {
                        this.figurky[riadok][stlpec] = new Veza(riadok, stlpec, this.FARBA_FIGURIEK1, 'R');
                    } else if ((stlpec == 1) || (stlpec == 6)) {
                        this.figurky[riadok][stlpec] = new Jazdec(riadok, stlpec, this.FARBA_FIGURIEK1, 'N');
                    } else if ((stlpec == 2) || (stlpec == 5)) {
                        this.figurky[riadok][stlpec] = new Strelec(riadok, stlpec, this.FARBA_FIGURIEK1, 'B');
                    } else if (stlpec == 3) {
                        this.figurky[riadok][stlpec] = new Dama(riadok, stlpec, this.FARBA_FIGURIEK1, 'Q');
                    } else {
                        this.figurky[riadok][stlpec] = new Kral(riadok, stlpec, this.FARBA_FIGURIEK1, 'K');
                    }
                } else if (riadok == 1) {
                    this.figurky[riadok][stlpec] = new Pesiak(riadok, stlpec, this.FARBA_FIGURIEK1, 'P');
                } else if (riadok == 6) {
                    this.figurky[riadok][stlpec] = new Pesiak(riadok, stlpec, this.FARBA_FIGURIEK2, 'P');
                } else if (riadok == 7) {
                    if ((stlpec == 0) || (stlpec == 7)) {
                        this.figurky[riadok][stlpec] = new Veza(riadok, stlpec, this.FARBA_FIGURIEK2, 'R');
                    } else if ((stlpec == 1) || (stlpec == 6)) {
                        this.figurky[riadok][stlpec] = new Jazdec(riadok, stlpec, this.FARBA_FIGURIEK2, 'N');
                    } else if ((stlpec == 2) || (stlpec == 5)) {
                        this.figurky[riadok][stlpec] = new Strelec(riadok, stlpec, this.FARBA_FIGURIEK2, 'B');
                    } else if (stlpec == 3) {
                        this.figurky[riadok][stlpec] = new Dama(riadok, stlpec, this.FARBA_FIGURIEK2, 'Q');
                    } else {
                        this.figurky[riadok][stlpec] = new Kral(riadok, stlpec, this.FARBA_FIGURIEK2, 'K');
                    }
                }
            }
        }
    }
    
    // metódy triedy
    public static String getFarbaFiguriek1() {
        return FARBA_FIGURIEK1;
    }

    public static String getFarbaFiguriek2() {
        return FARBA_FIGURIEK2;
    }

    // metódy inštancie
    public String getPismeno(int index) {
        return this.pismena[index];
    }

    public String getCislo(int index) {
        return this.cisla[index];
    }

    public Figurka getFigurka(int riadok, int stlpec) {
        return this.figurky[riadok][stlpec];
    }

    public Policko getPolicko(int paSurRiadku, int paSurStlpca) {
        if ((paSurRiadku >= 0) && (paSurRiadku < this.pocetPolicok) && (paSurStlpca >= 0) && (paSurStlpca < this.pocetPolicok)) {
            return this.policka[paSurRiadku][paSurStlpca];
        } else {
            throw new IllegalArgumentException("Neplatné súradnice políčka");
        }
    }

    public String getFarbaFigurky(int paSurRiadku, int paSurStlpca) {
        return this.figurky[paSurRiadku][paSurStlpca].getFarba();
    }

    /**
     * Metóda zistí či je označené(môžeme sa na neho posunúť) aspoň jedno políčko na šachovnici
     * @return
     */
    public boolean jeOznaceneAsponJednoPolicko() {
        for (int riadok = 0; riadok < this.pocetPolicok; riadok++) {
            for (int stlpec = 0; stlpec < this.pocetPolicok; stlpec++) {
                if (this.policka[riadok][stlpec].jeOznacene()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean jeObsadenePolicko(int paRiadok, int paStlpec) {
        return this.figurky[paRiadok][paStlpec] != null;
    }
    
    /**
     * Metóda označí dané políčko (môže na neho figúrka preskočiť) na šachovnici
     * @param paRiadok číslo riadku políčka ktoré chceme označiť
     * @param paStlpec číslo stĺpca políčka ktoré chceme označiť
     */
    public void oznacPolicko(int paRiadok, int paStlpec) {
        this.policka[paRiadok][paStlpec].oznac();
        this.policka[paRiadok][paStlpec].setZmenene(true);
    }

    /**
     * Metóda zvolí figúrku na konkrétnom riadku a stĺpci
     * @param paSurRiadku súradnica riadku figúrky, ktorú ideme zvoliť
     * @param paSurStlpca súradnica stĺpca figúrky, ktorú ideme zvoliť
     * @return
     */
    public Figurka zvolFigurku(int paSurRiadku, int paSurStlpca) {
        if ((paSurRiadku >= 0) && (paSurRiadku < this.pocetPolicok) && (paSurStlpca >= 0) && (paSurStlpca < this.pocetPolicok)) {
            if (this.figurky[paSurRiadku][paSurStlpca] != null) {
                this.figurky[paSurRiadku][paSurStlpca].zvol(this);
                this.policka[paSurRiadku][paSurStlpca].setZmenene(true);
                this.policka[paSurRiadku][paSurStlpca].zvyrazni();
                return this.figurky[paSurRiadku][paSurStlpca];
            } else {
                throw new IllegalArgumentException("Na tomto políčku sa nenachádza figúrka");
            }
        } else {
            throw new IllegalArgumentException("Neplatné súradnice figúrky");
        }
    }

    /**
     * Metóda posunie figúrku na konkrétne políčko(ak sa dá)
     * @param figurka figúrka, ktorú ideme posúvať
     * @param policko políčko, na ktoré chceme posunúť figúrku
     */
    public void posunFigurku(Figurka figurka, Policko policko) {
        this.figurky[figurka.getSurRiadku()][figurka.getSurStlpca()] = null;
        figurka.posun(policko, this);

        this.figurky[policko.getSurRiadku()][policko.getSurStlpca()] = figurka;
        this.odznacPolicka();
    }
    
    /**
     * Metóda zvýrazní dané políčko (nachádza sa na ňom figúrka, ktorú sme si zvolili) na šachovnici
     * @param paRiadok číslo riadku políčka ktoré chceme zvýrazniť
     * @param paStlpec číslo stĺpca políčka ktoré chceme zvýrazniť
     */
    public void zvyrazniPolicko(int paRiadok, int paStlpec) {
        this.policka[paRiadok][paStlpec].zvyrazni();
    }
    
    /**
     * Metóda odznačí všetky políčka na šachovnici
     */
    public void odznacPolicka() {
        for (int riadok = 0; riadok < this.policka.length; riadok++) {
            for (int stlpec = 0; stlpec < this.policka[riadok].length; stlpec++) { 
                if ((this.policka[riadok][stlpec].jeOznacene()) || (this.policka[riadok][stlpec].jeZvyraznene())) {
                    this.policka[riadok][stlpec].odznac();
                    this.policka[riadok][stlpec].setZmenene(true);
                }
            }
        }
    }   
    
    /**
     * Metóda zistí či je na šachovnici políčko s danými súradnicami
     * @param paRiadok číslo riadku políčka
     * @param paStlpec číslo stĺpca políčka
     * @return vráti true ak existuje políčko s danými súradnicami
     */
    public boolean existujePolicko(int paRiadok, int paStlpec) {
        return (paRiadok < this.policka.length) && (paRiadok >= 0) && (paStlpec < this.policka[0].length) && (paStlpec >= 0);
    }

    public List<Policko> getZmenenePolicka() {
        List<Policko> zmenenePolicka = new ArrayList<Policko>();
        for (int riadok = 0; riadok < 8; riadok++) {
            for (int stlpec = 0; stlpec < 8; stlpec++) {
                if (this.policka[riadok][stlpec].jeZmenene()) {
                    zmenenePolicka.add(this.policka[riadok][stlpec]);
                }
            }
        }
        return zmenenePolicka;
    }

    public void resetZmenene() {
        for (int riadok = 0; riadok < 8; riadok++) {
            for (int stlpec = 0; stlpec < 8; stlpec++) {
                this.policka[riadok][stlpec].setZmenene(false);
            }
        }
    }
}
