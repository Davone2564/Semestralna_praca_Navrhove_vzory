package sk.uniza.fri.hra;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class PohybKonzolaObserver implements IObserver {
    @Override
    public void update(String poslednyTah, int cisloTahu) {
        System.out.println(cisloTahu + ". " + poslednyTah);
    }

    @Override
    public void onGameOver() {
        System.out.println("Hra skončila.");
    }
}
