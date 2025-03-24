package sk.uniza.fri.sachovnica.grafickeObjekty;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 2. 5. 2021 - 21:49
 *
 * @author Dávid
 */
public class Obrazok {
    private Image img;
    private int suradnicaX;
    private int suradnicaY;
    private int sirka;
    private int vyska;
    private boolean jeViditelny;

    public Obrazok(String nazovCesty, int suradnicaX, int suradnicaY, int sirka, int vyska) {
        try {
            BufferedImage bimg = ImageIO.read(new File(nazovCesty));
            this.img = (Image)bimg;
            this.suradnicaX = suradnicaX;
            this.suradnicaY = suradnicaY;
            this.sirka = sirka;
            this.vyska = vyska;
        } catch (IOException ioe) {
            System.err.println("Pri nacitavani obrazku zo suboru doslo k chybe");
        }
    }

    public Image getImage() {
        return this.img;
    }

    public int getSuradnicaX() {
        return this.suradnicaX;
    }

    public int getSuradnicaY() {
        return this.suradnicaY;
    }

    public int getSirka() {
        return this.sirka;
    }

    public int getVyska() {
        return this.vyska;
    }

    public void setSuradnicaX(int suradnica) {
        this.suradnicaX = suradnica;
    }

    public void setSuradnicaY(int suradnica) {
        this.suradnicaY = suradnica;
    }

    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }

    public void skry() {
        this.jeViditelny = false;
        this.zmaz();
    }

    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this);
        }
    }

    private void zmaz() {
        if (!this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}