package sk.uniza.fri.hra;

import sk.uniza.fri.figurky.*;
import sk.uniza.fri.sachovnica.Sachovnica;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class Hra {
    private StavHry stav;
    private Sachovnica sachovnica;
    private IRenderer renderer;
    private Figurka zvolFigurka;
    private boolean jeZvolFigurka;
    private String hracNaTahu;
    private Stack<ICommand> undoStack;
    private Stack<ICommand> redoStack;
    private List<IObserver> observers;
    private String poslednyTah;
    private int cisloTahu;

    public Hra(IRenderer renderer) {
        this.sachovnica = new Sachovnica();
        this.renderer = renderer;
        this.stav = StavHry.NEROZHODNUTY;
        this.zvolFigurka = null;
        this.jeZvolFigurka = false;
        this.hracNaTahu = "white";
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.observers = new ArrayList<>();
        this.addObserver(new PohybKonzolaObserver());
        this.addObserver(new PohybSuborObserver("ZapisPartie.txt"));
        this.poslednyTah = "";
        this.cisloTahu = 1;
    }

    /**
     * Metóda pretransformuje súradnice zadané do dialógového okna na súradnice matice, ktorá predstavuje políčka na šachovnici <br>
     * @param surStlpca súradnica stĺpca šachovnice ktorú zadal užívateľ do dialógového okna
     * @param surRiadku súradnica riadku šachovnice ktorú zadal užívateľ do dialógového okna
     * @return vracia pole, v ktorom sa nachádzajú pretransformované súradnice
     */
    private int[] urcSuradniceMatice(String surStlpca, String surRiadku) {
        int riadokMatice;
        int stlpecMatice;
        // premieňa súradnice riadku šachovnice
        if (Character.isDigit(surRiadku.charAt(0))) {
            if ((Integer.parseInt(surRiadku) > 0) && (Integer.parseInt(surRiadku) < 9)) {
                riadokMatice = 8 - Integer.parseInt(surRiadku);
            } else {
                throw new IllegalCoordinatesFormatException();
            }
        } else {
            throw new IllegalCoordinatesFormatException();
        }
        // premieňa súradnice stĺpca šachovnice
        switch (surStlpca.toUpperCase()) {
            case "A":
                stlpecMatice = 0;
                break;
            case "B":
                stlpecMatice = 1;
                break;
            case "C":
                stlpecMatice = 2;
                break;
            case "D":
                stlpecMatice = 3;
                break;
            case "E":
                stlpecMatice = 4;
                break;
            case "F":
                stlpecMatice = 5;
                break;
            case "G":
                stlpecMatice = 6;
                break;
            case "H":
                stlpecMatice = 7;
                break;
            default:
                throw new IllegalCoordinatesFormatException();
        }
        int[] suradnice = {riadokMatice, stlpecMatice};
        return suradnice;
    }

    public StavHry getStav() {
        return this.stav;
    }

    /**
     * Metóda riadi priebeh celej hry
     */
    public void hraj() {

        this.renderer.vykresliSachovnicu(this.sachovnica);
        this.sachovnica.resetZmenene();

        this.renderer.vypisSpravu("Vitaj pri hre Šach. Keď budeš zadávať súradnice políčka, tak napíš najprv písmeno stĺpca a potom číslo riadku. ");
        String[] options = { "OK", "Undo", "Redo" };
        while (this.getStav() == StavHry.NEROZHODNUTY) {
            if (this.cisloTahu % 2 == 1 && this.cisloTahu != 1 && !this.poslednyTah.equals("")) {
                this.notifyObservers();
                this.poslednyTah = "";
            }

            if (this.hracNaTahu.equals("white")) {
                int vyber = this.renderer.getMoznost("Na rade je biely tím", options);
                if (vyber == 0) {
                    this.hracNaTahu = "white";
                    this.tahHraca();
                } else if (vyber == 1) {
                    if (!this.undoStack.isEmpty()) {
                        this.undo();
                    }
                } else if (vyber == 2) {
                    if (!this.redoStack.isEmpty()) {
                        this.redo();
                    }
                } else {
                    this.stav = StavHry.VYHRA_CIERNY;
                    this.koniecHry();
                }
            } else {
                int vyber = this.renderer.getMoznost("Na rade je čierny tím", options);
                if (vyber == 0) {
                    this.hracNaTahu = "black";
                    this.tahHraca();
                } else if (vyber == 1) {
                    if (!this.undoStack.isEmpty()) {
                        this.undo();
                    }
                } else if (vyber == 2) {
                    if (!this.redoStack.isEmpty()) {
                        this.redo();
                    }
                } else {
                    this.stav = StavHry.VYHRA_BIELY;
                    this.koniecHry();
                }
            }
        }
    }

    /**
     * Metóda vyzve hráča na ťah v hre
     * atribut hracNaTahu určuje farbu figúriek, s ktorými hrá hráč, ktorý je práve na ťahu
     */
    public void tahHraca() {
        String odpoved;
        int[] suradnice;

        while (!this.jeZvolFigurka) {
            try {
                odpoved = this.renderer.getVstup("Napis suradnice policka na ktorom je figurka, ktoru chces zvolit:");
                suradnice = this.urcSuradniceMatice(odpoved.substring(0, 1), odpoved.substring(1));
                this.zvolFigurka = this.sachovnica.zvolFigurku(suradnice[0], suradnice[1]);

                if ((this.sachovnica.jeOznaceneAsponJednoPolicko()) && (this.zvolFigurka.getFarba().equals(this.hracNaTahu))) {
                    this.jeZvolFigurka = true;
                    this.sachovnica.getPolicko(suradnice[0], suradnice[1]).setZmenene(true);
                    this.renderer.vykresliSachovnicu(this.sachovnica);
                    this.sachovnica.resetZmenene();
                } else {
                    this.zvolFigurka = null;
                    this.sachovnica.odznacPolicka();
                    this.sachovnica.resetZmenene();
                    this.renderer.vypisSpravu("Figúrku s týmito súradnicami nie je možné zvoliť.");
                }
            } catch (IllegalCoordinatesFormatException ife) {
                JOptionPane.showMessageDialog(null, "Zadal si súradnice v nesprávnom formáte");
                continue;
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(null, "Zadal si neplatné súradnice políčka šachovnice");
                continue;
            }
        }

        while (this.jeZvolFigurka) {
            try {
                odpoved = this.renderer.getVstup("Zadaj suradnice policka, na ktore chces danu figurku posunut");
                suradnice = this.urcSuradniceMatice(odpoved.substring(0, 1), odpoved.substring(1));

                this.sachovnica.getPolicko(this.zvolFigurka.getSurRiadku(), this.zvolFigurka.getSurStlpca()).setZmenene(true);
                this.sachovnica.getPolicko(suradnice[0], suradnice[1]).setZmenene(true);

                ICommand pohybCommand = new PohybCommand(this.zvolFigurka,
                        this.sachovnica.getPolicko(this.zvolFigurka.getSurRiadku(), this.zvolFigurka.getSurStlpca()),
                        this.sachovnica.getPolicko(suradnice[0], suradnice[1]),
                        this.sachovnica);
                this.poslednyTah += pohybCommand.toString() + " ";
                this.cisloTahu++;
                this.vykonajCommand(pohybCommand);
                this.renderer.vykresliSachovnicu(this.sachovnica);
                this.sachovnica.resetZmenene();
            } catch (IllegalCoordinatesFormatException icfe) {
                JOptionPane.showMessageDialog(null, "Zadal si súradnice políčka v nesprávnom formáte");
            } catch (IllegalMoveException ime) {
                JOptionPane.showMessageDialog(null, "Na toto políčko sa nemôžeš posunúť");
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(null, "Zadal si súradnice políčka mimo šachovnice");
            } finally {
                continue;
            }
        }
    }

    public void vykonajCommand(ICommand command) {
        command.execute();
        this.undoStack.push(command);
        this.redoStack.clear();
        this.zvolFigurka = null;
        this.jeZvolFigurka = false;
        if (this.hracNaTahu.equals("black")) {
            this.hracNaTahu = "white";
        } else {
            this.hracNaTahu = "black";
        }
    }

    public void undo() {
        if (!this.undoStack.empty()) {
            ICommand command = this.undoStack.pop();
            command.unexecute();
            this.renderer.vykresliSachovnicu(this.sachovnica);
            this.redoStack.push(command);
            this.zvolFigurka = null;
            this.jeZvolFigurka = false;
            this.cisloTahu--;
            if (this.hracNaTahu.equals("black")) {
                this.hracNaTahu = "white";
                this.poslednyTah = "";
            } else {
                this.hracNaTahu = "black";
                this.poslednyTah = this.undoStack.peek().toString() + " ";
            }
        }
    }

    public void redo() {
        if (!this.redoStack.empty()) {
            ICommand command = this.redoStack.pop();
            int[] suradnice = this.urcSuradniceMatice(command.toString().substring(command.toString().length() - 2, command.toString().length() - 1),
                    command.toString().substring(command.toString().length() - 1));
            this.sachovnica.oznacPolicko(suradnice[0], suradnice[1]);
            command.execute();
            this.renderer.vykresliSachovnicu(this.sachovnica);
            this.undoStack.push(command);
            this.zvolFigurka = null;
            this.jeZvolFigurka = false;
            this.cisloTahu++;
            if (this.hracNaTahu.equals("black")) {
                this.hracNaTahu = "white";
                this.poslednyTah = "";
            } else {
                this.hracNaTahu = "black";
                this.poslednyTah = this.undoStack.peek().toString() + " ";
            }
        }
    }

    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        //ak hra skoncila, posleme kazdemu observerovi o tom spravu
        if (this.stav == StavHry.VYHRA_BIELY || this.stav == StavHry.VYHRA_CIERNY) {
            for (IObserver observer : this.observers) {
                observer.onGameOver();
            }
        } else {
            for (IObserver observer : this.observers) {
                observer.update(this.poslednyTah, this.cisloTahu / 2);
            }
        }
    }

    public void koniecHry() {
        this.notifyObservers();
        System.exit(0);
    }
}
