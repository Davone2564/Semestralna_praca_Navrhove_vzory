package sk.uniza.fri.hra;

import sk.uniza.fri.figurky.Figurka;
import sk.uniza.fri.sachovnica.Sachovnica;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public interface IRenderer {
    String getVstup(String sprava);
    int getMoznost(String sprava, String[] moznosti);
    void vypisSpravu(String sprava);
    void vykresliSachovnicu(Sachovnica sachovnica);
}
