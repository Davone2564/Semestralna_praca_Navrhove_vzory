package sk.uniza.fri.sachovnica.grafickeObjekty;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Graphics;
import java.io.File;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Cudzia trieda. 
 *
 * @author: Bruce Quig
 * @author: Michael Kolling (mik)
 *
 * @version: 1.6.1 (shapes)
 */
public class Platno {
    // Note: The implementation of this class (specifically the handling of
    // shape identity and colors) is slightly more complex than necessary. This
    // is done on purpose to keep the interface and instance fields of the
    // shape objects in this project clean and simple for educational purposes.

    private static Platno platnoSingleton;

    /**
     * Factory method to get the canvas singleton object.
     */
    public static Platno dajPlatno() {
        if (Platno.platnoSingleton == null) {
            Platno.platnoSingleton = new Platno("BlueJ Shapes Demo", 2000, 2000,
                                         Color.white);
        }
        Platno.platnoSingleton.setVisible(true);
        return Platno.platnoSingleton;
    }

    //  ----- instance part -----

    private JFrame frame;
    private CanvasPane canvas;
    private Graphics2D graphic;
    private Color pozadie;
    private Image canvasImage;
    private List<Object> objekty;
    private ArrayList<Znak> znaky = new ArrayList<Znak>();
    private ArrayList<Obrazok> obrazky = new ArrayList<>();
    private HashMap<Object, PopisTvaru> tvary;
    
    /**
     * Create a Canvas.
     * @param titulok  title to appear in Canvas Frame
     * @param sirka  the desired width for the canvas
     * @param vyska  the desired height for the canvas
     * @param pozadie  the desired background colour of the canvas
     */
    private Platno(String titulok, int sirka, int vyska, Color pozadie) {
        this.frame = new JFrame();
        this.canvas = new CanvasPane();
        JToolBar toolbar = new JToolBar("Nástroje");
        JButton saveImage = new JButton("Ulož");
        saveImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFileChooser fc = new JFileChooser() {
                    @Override
                    public void approveSelection() {
                        File f = this.getSelectedFile();
                        if (f.exists() && this.getDialogType() == SAVE_DIALOG) {
                            int result = JOptionPane.showConfirmDialog(this, "The file exists, overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
                            switch (result) {
                                case JOptionPane.YES_OPTION:
                                    super.approveSelection();
                                    return;
                                case JOptionPane.NO_OPTION:
                                    return;
                                case JOptionPane.CLOSED_OPTION:
                                    return;
                                case JOptionPane.CANCEL_OPTION:
                                    this.cancelSelection();
                                    return;
                            }
                        }
                        super.approveSelection();
                    }        
                };
                fc.setFileFilter(new FileNameExtensionFilter("Images", "png"));
                
                int returnVal = fc.showSaveDialog(Platno.this.frame);
                
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selected = fc.getSelectedFile();
                        if (!selected.getName().endsWith(".png")) {
                            selected = new File(selected.getAbsolutePath() + ".png");
                        }
                        BufferedImage image = new BufferedImage(sirka, vyska, BufferedImage.TYPE_INT_RGB);
                        Graphics2D graphics2D = image.createGraphics();
                        Platno.this.canvas.paint(graphics2D);
                        ImageIO.write(image, "png", selected);
                    } catch (Exception exception) {
                        //code
                    } 
                }
            }
        });
        toolbar.add(saveImage);
        this.frame.add(toolbar, BorderLayout.PAGE_START);
        this.frame.add(this.canvas, BorderLayout.CENTER);
        this.frame.setTitle(titulok);
        this.canvas.setPreferredSize(new Dimension(sirka, vyska));
        this.pozadie = pozadie;
        this.frame.pack();
        this.objekty = new ArrayList<Object>();
        this.tvary = new HashMap<Object, PopisTvaru>();
    }

    /**
     * Set the canvas visibility and brings canvas to the front of screen
     * when made visible. This method can also be used to bring an already
     * visible canvas to the front of other windows.
     * @param visible  boolean value representing the desired visibility of
     * the canvas (true or false) 
     */
    public void setVisible(boolean visible) {
        if (this.graphic == null) {
            // first time: instantiate the offscreen image and fill it with
            // the background colour
            Dimension size = this.canvas.getSize();
            this.canvasImage = this.canvas.createImage(size.width, size.height);
            this.graphic = (Graphics2D)this.canvasImage.getGraphics();
            this.graphic.setColor(this.pozadie);
            this.graphic.fillRect(0, 0, size.width, size.height);
            this.graphic.setColor(Color.black);
        }
        this.frame.setVisible(visible);
    }

    /**
     * Draw a given shape onto the canvas.
     * @param  objekt  an object to define identity for this shape
     * @param  farba           the color of the shape
     * @param  tvar            the shape object to be drawn on the canvas
     */
     // Note: this is a slightly backwards way of maintaining the shape
     // objects. It is carefully designed to keep the visible shape interfaces
     // in this project clean and simple for educational purposes.
    public void draw(Object objekt, String farba, Shape tvar) {
        this.objekty.remove(objekt);   // just in case it was already there
        this.objekty.add(objekt);      // add at the end
        this.tvary.put(objekt, new PopisTvaru(tvar, farba));

        Platno.this.setForegroundColor("black");
        this.tvary.get(tvar).draw(this.graphic);

        this.canvas.repaint();
    }
    
    public void draw(Znak znak) {
        this.znaky.remove(znak);
        this.znaky.add(znak);

        Platno.this.setForegroundColor("black");
        znak.draw(this.graphic);
        this.canvas.repaint();
    }

    public void draw(Obrazok obrazok) {
        this.obrazky.remove(obrazok);
        this.obrazky.add(obrazok);

        Platno.this.setForegroundColor("black");
        this.graphic.drawImage(obrazok.getImage(), obrazok.getSuradnicaX(), obrazok.getSuradnicaY(), obrazok.getSirka(), obrazok.getVyska(), null);
        this.canvas.repaint();
    }
 
    /**
     * Erase a given shape's from the screen.
     * @param  objekt  the shape object to be erased
     */
    public void erase(Object objekt) {
        this.objekty.remove(objekt);   // just in case it was already there
        this.tvary.remove(objekt);
        this.obrazky.remove(objekt);
        this.redraw();
    }

    /**
     * Set the foreground colour of the Canvas.
     * @param  farba   the new colour for the foreground of the Canvas
     */
    public void setForegroundColor(String farba) {
        if (farba.equals("red")) {
            this.graphic.setColor(Color.red);
        } else if (farba.equals("black")) {
            this.graphic.setColor(Color.black);
        } else if (farba.equals("blue")) {
            this.graphic.setColor(Color.blue);
        } else if (farba.equals("yellow")) {
            this.graphic.setColor(Color.yellow);
        } else if (farba.equals("green")) {
            this.graphic.setColor(Color.green);
        } else if (farba.equals("magenta")) {
            this.graphic.setColor(Color.magenta);
        } else if (farba.equals("white")) {
            this.graphic.setColor(Color.white);
        } else if (farba.equals("gray")) {
            this.graphic.setColor(Color.gray);
        } else if (farba.equals("lightGray")) {
            this.graphic.setColor(Color.LIGHT_GRAY);
        } else {
            this.graphic.setColor(Color.black);
        }
    }

    /**
     * Wait for a specified number of milliseconds before finishing.
     * This provides an easy way to specify a small delay which can be
     * used when producing animations.
     * @param  milisekundy  the number
     */
    public void wait(int milisekundy) {
        try {
            Thread.sleep(milisekundy);
        } catch (Exception e) {
            System.out.println("Cakanie sa nepodarilo");
        }
    }

    /**
     * * Redraw all shapes currently on the Canvas.
     */
    private void redraw() {
        Platno.this.setForegroundColor("black");
        for (Znak znak : this.znaky) {
            znak.draw(this.graphic);
        }
        
        for (Object tvar : this.objekty ) {
            this.tvary.get(tvar).draw(this.graphic);
        }

        for (Obrazok obrazok: this.obrazky) {
            this.graphic.drawImage(obrazok.getImage(), obrazok.getSuradnicaX(), obrazok.getSuradnicaY(), obrazok.getSirka(), obrazok.getVyska(), null);
        }

        this.canvas.repaint();
    }
       
    /**
     * Erase the whole canvas. (Does not repaint.)
     */
    public void erase() {
        Color original = this.graphic.getColor();
        this.graphic.setColor(this.pozadie);
        Dimension size = this.canvas.getSize();
        this.graphic.fill(new Rectangle(0, 0, size.width, size.height));
        this.graphic.setColor(original);

        // Vymazať všetky objekty
        this.objekty.clear();
        this.tvary.clear();
        this.obrazky.clear();
        this.znaky.clear();
    }



    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class CanvasPane extends JPanel {
        public void paint(Graphics graphic) {
            graphic.drawImage(Platno.this.canvasImage, 0, 0, null);
        }
    }
    
    /************************************************************************
     * Inner class CanvasPane - the actual canvas component contained in the
     * Canvas frame. This is essentially a JPanel with added capability to
     * refresh the image drawn on it.
     */
    private class PopisTvaru {
        private Shape tvar;
        private String farba;

        private PopisTvaru(Shape tvar, String farba) {
            this.tvar = tvar;
            this.farba = farba;
        }

        public void draw(Graphics2D graphic) {
            Platno.this.setForegroundColor(this.farba);
            graphic.fill(this.tvar);
        }
    }
}
