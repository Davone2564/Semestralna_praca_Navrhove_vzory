package sk.uniza.fri.hra;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class PohybSuborObserver implements IObserver {
    private FileWriter fw;
    public PohybSuborObserver(String nazovSuboru) {
        try {
            this.fw = new FileWriter(nazovSuboru);
        } catch (IOException e) {
            System.out.println("Nastala chyba pri zapisani do suboru");
            e.printStackTrace();
        }
    }
    @Override
    public void update(String poslednyTah, int cisloTahu) {
        try {
            this.fw.write(cisloTahu + ". " + poslednyTah + "\n");
        } catch (IOException e) {
            System.out.println("Nastala chyba pri zapisani do suboru");
            e.printStackTrace();
        }
    }

    @Override
    public void onGameOver() {
        try {
            this.fw.write("Hra skončila.\n");
            this.fw.close();
        } catch (IOException e) {
            System.out.println("Nastala chyba pri zapisani do suboru");
            e.printStackTrace();
        }
    }
}
