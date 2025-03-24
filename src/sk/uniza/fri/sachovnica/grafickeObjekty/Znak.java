package sk.uniza.fri.sachovnica.grafickeObjekty;

import sk.uniza.fri.sachovnica.grafickeObjekty.Platno;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

/**
 * Trieda sk.uniza.fri.sachovnica.grafickeObjekty.Znak vytvorí Ľubovoľný Unicode znak, ktorý sa dá nakresliť na plátno.
 * 
 * @author (Dávid Mičo)
 * 
 * @version (08.01.2021)
 */
public class Znak {
    // atribúty inštancie
    private int lavyDolnyX;
    private int lavyDolnyY; // súradnice na plátne
    private String znak;
    private boolean jeViditelny;
    
    /**
     * Konštruktor triedy sk.uniza.fri.sachovnica.grafickeObjekty.Znak s parametrami - vytvorí znak s pevne zadaným rozmerom na daných súradniciach <br>
     * @param znak Unicode znak ktorý chceme vytvoriť
     * @param paLavyDolnyX X-ová súradnica ľavého dolného rohu znaku
     * @param paLavyDolnyY Y-ová súradnica ľavého dolného rohu znaku
     */
    public Znak(String znak, int paLavyDolnyX, int paLavyDolnyY) {
        // inicializácia atribútov inštancie
        this.lavyDolnyX = paLavyDolnyX;
        this.lavyDolnyY = paLavyDolnyY;
        this.znak = znak;
        this.jeViditelny = false;
    }  
    
    // metódy inštancie
    /**
     * Metóda zobrazí sk.uniza.fri.sachovnica.grafickeObjekty.Znak na plátne
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }        
    
    /**
     * Metóda nakreslí znak na príslušnú grafiku(Plátno)
     * @param graphic príslušná grafika na ktorú chceme nakresliť sk.uniza.fri.sachovnica.grafickeObjekty.Znak
     */
    public void draw(Graphics2D graphic) {
        Font f = new Font("TimesRoman", Font.BOLD, 72);
        graphic.setFont(f);
        graphic.setColor(Color.darkGray);
        graphic.drawString(this.znak , this.lavyDolnyX, this.lavyDolnyY);
    }  
    
    /**
     * Metóda nakreslí znak na Plátno
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this);
        }    
    }      
}    
