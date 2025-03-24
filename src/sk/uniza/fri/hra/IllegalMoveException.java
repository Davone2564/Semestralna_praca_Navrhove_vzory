package sk.uniza.fri.hra;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class IllegalMoveException extends IllegalArgumentException {
    public IllegalMoveException() {
        super("Na toto políčka sa nemôžeš posunúť");
    }
}
