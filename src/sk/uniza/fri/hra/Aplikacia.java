package sk.uniza.fri.hra;

import javax.swing.*;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class Aplikacia {
    public static void main(String[] args) {
        // Výber renderera
        IRenderer renderer = null;
        String[] options = {"Konzola", "JavaSwing"};
        int volba = JOptionPane.showOptionDialog(null, "Vyberte spôsob vykreslenia:", "Renderer",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (volba == 0) {
            renderer = new KonzolaRenderer();
        } else if (volba == 1) {
            renderer = new SwingRenderer();
        } else {
            System.exit(0);
        }
        Hra hra = new Hra(renderer);
        hra.hraj();
    }
}
