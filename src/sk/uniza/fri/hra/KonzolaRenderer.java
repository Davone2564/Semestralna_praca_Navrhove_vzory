package sk.uniza.fri.hra;

import sk.uniza.fri.figurky.Figurka;
import sk.uniza.fri.sachovnica.Sachovnica;
import sk.uniza.fri.sachovnica.Policko;

import java.util.Scanner;

/**
 * Konzolový renderer na zobrazovanie šachovnice a interakciu s hráčom.
 */
public class KonzolaRenderer implements IRenderer {
    private Scanner scanner;

    public KonzolaRenderer() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getVstup(String sprava) {
        System.out.println(sprava);
        return this.scanner.nextLine();
    }

    @Override
    public int getMoznost(String sprava, String[] moznosti) {
        System.out.println(sprava);
        for (int i = 0; i < moznosti.length; i++) {
            System.out.println((i + 1) + ". " + moznosti[i]);
        }

        int vyber = -1;
        while (vyber < 1 || vyber > moznosti.length) {
            System.out.print("Vyber možnosť (1 - " + moznosti.length + "): ");
            try {
                vyber = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Zadaj platné číslo.");
            }
        }
        return vyber - 1;
    }

    @Override
    public void vypisSpravu(String sprava) {
        System.out.println(sprava);
    }

    @Override
    public void vykresliSachovnicu(Sachovnica sachovnica) {
        StringBuilder builder = new StringBuilder();
        builder.append("   +-----+-----+-----+-----+-----+-----+-----+-----+\n");

        for (int riadok = 0; riadok < 8; riadok++) {
            builder.append(" ").append(8 - riadok).append(" |");

            for (int stlpec = 0; stlpec < 8; stlpec++) {
                Policko policko = sachovnica.getPolicko(riadok, stlpec);
                Figurka figurka = sachovnica.getFigurka(riadok, stlpec);

                if (figurka != null) {
                    if (figurka.getFarba().equals("white")) {
                        builder.append("  ").append(figurka.getZnakFigurky());
                    } else {
                        builder.append("  ").append(Character.toLowerCase(figurka.getZnakFigurky()));
                    }
                    if (policko.jeOznacene()) {
                        builder.append(". |");
                    } else {
                        builder.append("  |");
                    }
                } else if (policko.jeOznacene()) {
                    builder.append("  .  |"); // Označené políčko
                } else {
                    builder.append("     |"); // Prázdne políčko
                }
            }
            builder.append(" \n");
            builder.append("   +-----+-----+-----+-----+-----+-----+-----+-----+\n");
        }

        builder.append("      A     B     C     D     E     F     G     H\n");
        System.out.print(builder.toString());
    }
}
