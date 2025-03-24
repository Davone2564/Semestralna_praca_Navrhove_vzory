package sk.uniza.fri.hra;

import sk.uniza.fri.figurky.Figurka;
import sk.uniza.fri.sachovnica.Policko;
import sk.uniza.fri.sachovnica.Sachovnica;
import sk.uniza.fri.sachovnica.grafickeObjekty.Obrazok;
import sk.uniza.fri.sachovnica.grafickeObjekty.Platno;
import sk.uniza.fri.sachovnica.grafickeObjekty.Znak;

import javax.swing.*;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.List;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class SwingRenderer implements IRenderer {
    private int rozmerPolicka; // finálny atribút triedy ktorý určuje rozmer jedného políčka šachovnice
    private int odsadenieSachovnice;
    private Platno platno;
    private HashMap<Character, String> figurkaObrazky; // Mapovanie znakov figúriek na cesty k obrázkom
    private boolean vykreslenySuradnicovySystem;

    public SwingRenderer() {
        this.platno = Platno.dajPlatno();
        this.rozmerPolicka = 75;
        this.odsadenieSachovnice = this.rozmerPolicka * 6;
        this.figurkaObrazky = new HashMap<>();
        this.vykreslenySuradnicovySystem = false;

        //Inicializácia cesty k obrázkom figúrok
        //biele figurky
        this.figurkaObrazky.put('K', "King_White.png");
        this.figurkaObrazky.put('Q', "Queen_White.png");
        this.figurkaObrazky.put('R', "Rook_White.png");
        this.figurkaObrazky.put('B', "Bishop_White.png");
        this.figurkaObrazky.put('N', "Knight_White.png");
        this.figurkaObrazky.put('P', "Pawn_White.png");

        //cierne figurky
        this.figurkaObrazky.put('k', "King_Black.png");
        this.figurkaObrazky.put('q', "Queen_Black.png");
        this.figurkaObrazky.put('r', "Rook_Black.png");
        this.figurkaObrazky.put('b', "Bishop_Black.png");
        this.figurkaObrazky.put('n', "Knight_Black.png");
        this.figurkaObrazky.put('p', "Pawn_Black.png");

        this.platno.setVisible(true);
    }
    @Override
    public String getVstup(String sprava) {
        String vstup = JOptionPane.showInputDialog(null, sprava);
        return vstup;
    }

    @Override
    public int getMoznost(String sprava, String[] moznosti) {
        int vyber = JOptionPane.showOptionDialog(null,
                sprava,
                "Pokračovanie",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                moznosti,
                moznosti[0]);
        return vyber;
    }

    @Override
    public void vypisSpravu(String sprava) {
        JOptionPane.showMessageDialog(null, sprava);
    }

    @Override
    public void vykresliSachovnicu(Sachovnica sachovnica) {
        if (!this.vykreslenySuradnicovySystem) {
            //vykreslenie pomocného riadku pod políčkami šachovnice, určujúceho súradnice stĺpcov šachovnice
            for (int i = 0; i < 8; i++) {
                this.platno.draw(new Znak(sachovnica.getPismeno(i), i * this.rozmerPolicka + (this.rozmerPolicka + 15) + 5 * this.rozmerPolicka, this.rozmerPolicka * 9 - 10));
            }

            // vykreslenie pomocného stĺpca vľavo od šachovnice, určujúceho súradnice riadkov šachovnice
            for (int i = 7; i > -1; i--) {
                this.platno.draw(new Znak(sachovnica.getCislo(i), 20 + 5 * this.rozmerPolicka, (8 - i) * this.rozmerPolicka - 15));
            }

            this.vykreslenySuradnicovySystem = true;
        }

        List<Policko> zmenenePolicka = sachovnica.getZmenenePolicka();

        for (Policko policko : zmenenePolicka) {
            int riadok = policko.getSurRiadku(); // Predpokladáme, že Policko má getRiadok()
            int stlpec = policko.getSurStlpca(); // Predpokladáme, že Policko má getStlpec()

            int x = stlpec * this.rozmerPolicka + this.odsadenieSachovnice;
            int y = riadok * this.rozmerPolicka;

            String farbaPolicka = (riadok + stlpec) % 2 == 0 ? "white" : "lightGray";
            this.platno.draw(new Rectangle(x, y, this.rozmerPolicka, this.rozmerPolicka), farbaPolicka, new Rectangle(x, y, this.rozmerPolicka, this.rozmerPolicka));

            if (policko.jeOznacene()) {
                this.platno.draw(new Rectangle(x, y, this.rozmerPolicka, this.rozmerPolicka), "green", new Rectangle(x, y, this.rozmerPolicka, this.rozmerPolicka));
            } else if (policko.jeZvyraznene()) {
                this.platno.draw(new Rectangle(x, y, this.rozmerPolicka, this.rozmerPolicka), "magenta", new Rectangle(x, y, this.rozmerPolicka, this.rozmerPolicka));
            }

            //Vykreslenie figúrok (ak sú prítomné na políčku)
            Figurka figurka = sachovnica.getFigurka(riadok, stlpec);
            if (figurka != null) {
                this.vykresliFigurku(figurka, riadok, stlpec, false);
            } else {
                if (!policko.jeZvyraznene() && !policko.jeOznacene()) {
                    this.vykresliFigurku(figurka, riadok, stlpec, true);
                }
            }
        }
    }

    private void vykresliFigurku(Figurka figurka, int riadok, int stlpec, boolean vymazat) {
        int x = stlpec * this.rozmerPolicka + this.rozmerPolicka / 10 + this.odsadenieSachovnice;
        int y = riadok * this.rozmerPolicka + this.rozmerPolicka / 10;

        if (vymazat) {
            // Vyčisti políčko (prekresli základnú farbu)
            String farbaPolicka = (riadok + stlpec) % 2 == 0 ? "white" : "lightGray";
            this.platno.draw(new Rectangle(x - this.rozmerPolicka / 10, y - this.rozmerPolicka / 10,
                    this.rozmerPolicka, this.rozmerPolicka), farbaPolicka,
                    new Rectangle(x - this.rozmerPolicka / 10, y - this.rozmerPolicka / 10,
                            this.rozmerPolicka, this.rozmerPolicka));
            return; // Ak vymazávame, ďalej nevykresľujeme figúrku
        }

        // Nájsť cestu k obrázku figúrky podľa jej znaku
        String obrazokCesta;
        //ak je figurka cierna, znak dame na male pismeno(oznacuju sa tak cierne figurky)
        if (figurka.getFarba().equals("black")) {
            obrazokCesta = this.figurkaObrazky.get(Character.toLowerCase(figurka.getZnakFigurky()));
        } else {
            obrazokCesta = this.figurkaObrazky.get(figurka.getZnakFigurky());
        }

        if (obrazokCesta != null) {
            // Vykresliť figúrku ako obrázok
            Obrazok obrazok = new Obrazok(obrazokCesta, x, y, this.rozmerPolicka / 5 * 4, this.rozmerPolicka / 5 * 4);
            if (!vymazat) {
                this.platno.draw(obrazok);
            } else {
                this.platno.erase(obrazok);
            }
        }
    }
}
