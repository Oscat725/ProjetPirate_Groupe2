/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author rtiam
 */
//Melanie
public class PanelDe extends JPanel {

    private int valeur;
    private final Random random;
    private final Timer timerAnimation;
    private int nombreToursAnimation;
    private int valeurFinale;
    private Runnable onAnimationFinie;

    public PanelDe() {
        this.valeur = 1;
        this.random = new Random();//generateur aleatoire
        setPreferredSize(new Dimension(80, 80));
        //un timer pour l'animation du de
        timerAnimation = new Timer(100, e -> animerDe());
    }

    public void setValeur(int valeur) {
        if (valeur<1 || valeur>6) {
            throw new IllegalArgumentException("Valeur invalide");
        }
        this.valeur = valeur;
        repaint();
    }

    public int getValeur() {
        return valeur;
    }

    public void setOnAnimationFinie(Runnable callback) {
        this.onAnimationFinie = callback;
    }

    public void lancerAnimation(int resFinal) {
        if (resFinal<1 || resFinal>6) {
            throw new IllegalArgumentException("Résultat final invalide");
        }
        timerAnimation.stop();
        this.valeurFinale = resFinal;
        this.nombreToursAnimation = 0;
        timerAnimation.start();
    }
    //l'animation que le timer appelle tous les 100ms
    private void animerDe() {
        nombreToursAnimation++;
        if (nombreToursAnimation < 10) {
            this.valeur = random.nextInt(6) + 1;
        } else {
            this.valeur = valeurFinale;
            timerAnimation.stop();
            if (onAnimationFinie != null)
                onAnimationFinie.run();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        //rendre les formes plus lisses
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        //dessin du carree
        int taille = Math.min(getWidth(),getHeight())-10;
        int x = (getWidth()-taille)/2;
        int y = (getHeight()-taille) /2;
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(x, y, taille, taille, 20, 20);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));//epaisseur
        g2d.drawRoundRect(x, y, taille, taille, 20, 20);

        //points
        int rayon = taille/8;

        int gauche = x+  taille/4;
        int centreX = x + taille/2;
        int droite = x +3*taille/4;

        int haut = y + taille/4;
        int centreY = y + taille/2;
        int bas = y + 3* taille/4;

        switch (valeur) {
            case 1:
                dessinerPoint(g2d,centreX,centreY,rayon);
                break;
            case 2:
                dessinerPoint(g2d,gauche,haut,rayon);
                dessinerPoint(g2d,droite,bas,rayon);
                break;
            case 3:
                dessinerPoint(g2d,gauche,haut,rayon);
                dessinerPoint(g2d,centreX,centreY,rayon);
                dessinerPoint(g2d,droite,bas,rayon);
                break;
            case 4:
                dessinerPoint(g2d,gauche,haut,rayon);
                dessinerPoint(g2d,droite,haut,rayon);
                dessinerPoint(g2d,gauche,bas,rayon);
                dessinerPoint(g2d,droite,bas,rayon);
                break;
            case 5:
                dessinerPoint(g2d,gauche,haut,rayon);
                dessinerPoint(g2d,droite,haut,rayon);
                dessinerPoint(g2d,centreX,centreY, rayon);
                dessinerPoint(g2d,gauche,bas,rayon);
                dessinerPoint(g2d,droite,bas,rayon);
                break;
            case 6:
                dessinerPoint(g2d,gauche,haut,rayon);
                dessinerPoint(g2d,droite,haut,rayon);
                dessinerPoint(g2d,gauche,centreY,rayon);
                dessinerPoint(g2d,droite,centreY,rayon);
                dessinerPoint(g2d,gauche,bas,rayon);
                dessinerPoint(g2d,droite,bas,rayon);
                break;
            default:
                break;
        }
    }

    private void dessinerPoint(Graphics2D g2d, int x, int y, int rayon) {
        g2d.fillOval(x-rayon, y-rayon, 2*rayon, 2*rayon);
    }
}