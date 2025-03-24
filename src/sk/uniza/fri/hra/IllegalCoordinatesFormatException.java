package sk.uniza.fri.hra;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class IllegalCoordinatesFormatException extends IllegalArgumentException {
    public IllegalCoordinatesFormatException() {
        System.err.println("Zadal si súradnice v nesprávnom formáte");
    }
}
