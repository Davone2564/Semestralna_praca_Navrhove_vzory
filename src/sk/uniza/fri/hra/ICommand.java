package sk.uniza.fri.hra;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public interface ICommand {
    void execute();
    void unexecute();
}
