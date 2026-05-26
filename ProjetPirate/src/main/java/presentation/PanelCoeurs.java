/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author rtiam
 */
// Melanie
public class PanelCoeurs extends JPanel {
    private int pvMax = 5;
    private int pvActuels;
    private Color couleurPlein;
    private Color couleurVide = Color.GRAY;

    public PanelCoeurs(Color couleurPlein) {
        this.pvActuels = pvMax;
        this.couleurPlein =couleurPlein;
        setOpaque(false);
        setPreferredSize(new Dimension(pvMax * 26, 26));
    }

    // mets a jour les coeurs entre 0 et 5
    public void setPv(int pv) {
        this.pvActuels = Math.max(0, Math.min(pvMax, pv));
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int taille = 20;
        int espacement = 4;// entre chaque coeur
        int totalWidth = pvMax * (taille + espacement) - espacement;
        int startX = (getWidth() - totalWidth) / 2;
        int startY = (getHeight() - taille) / 2;

        for (int i = 0; i < pvMax; i++) {
            boolean coeurPlein = i < pvActuels;
            int x = startX + i * (taille + espacement);
            g2d.setColor(coeurPlein ? couleurPlein : couleurVide);
            dessinerCoeur(g2d, x, startY, taille);
        }
    }

    private void dessinerCoeur(Graphics2D g2, int x, int y, int taille) {
        // deux demi-cercles + un triangle
        int w = taille / 2;
        int h = taille / 2;

        // Rempli les deux demi cercle en haut
        g2.fillOval(x, y, w, h);
        g2.fillOval(x + h, y, w, h);

        // Triangle
        int[] xPts = { x, x + taille, x + w };
        int[] yPts = { y + taille / 3, y + taille / 3, y + taille };
        g2.fillPolygon(xPts, yPts, 3);
    }
}
