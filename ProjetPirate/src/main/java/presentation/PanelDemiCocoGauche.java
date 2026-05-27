/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author beman
 */
public class PanelDemiCocoGauche extends JPanel{

    public PanelDemiCocoGauche() {
        setPreferredSize(new Dimension(200, 220));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // Adaptation à la taille réelle du panel
        double scaleX = getWidth() / 200.0;
        double scaleY = getHeight() / 220.0;
        g2.scale(scaleX, scaleY);

        // Coordonnées de référence (pour 200x220)
        int cx = 120;
        int cy = 110;

        // --- Coque (demi-ellipse, côté plat à droite) ---
        Path2D coque = new Path2D.Double();
        coque.moveTo(cx, cy - 90);
        coque.curveTo(cx - 90, cy - 85, cx - 115, cy - 40, cx - 115, cy);
        coque.curveTo(cx - 115, cy + 40, cx - 90, cy + 85, cx, cy + 90);
        coque.closePath();
        g2.setColor(new Color(201, 122, 110));
        g2.fill(coque);

        // --- Chair blanche (bande sur le côté plat) ---
        Path2D chair = new Path2D.Double();
        chair.moveTo(cx, cy - 90);
        chair.curveTo(cx - 6, cy - 88, cx - 8, cy - 50, cx - 8, cy);
        chair.curveTo(cx - 8, cy + 50, cx - 6, cy + 88, cx, cy + 90);
        chair.curveTo(cx + 12, cy + 85, cx + 13, cy + 48, cx + 13, cy);
        chair.curveTo(cx + 13, cy - 48, cx + 12, cy - 85, cx, cy - 90);
        g2.setColor(new Color(242, 235, 227));
        g2.fill(chair);

        // --- Cavité intérieure sombre ---
        Path2D cavite = new Path2D.Double();
        cavite.moveTo(cx, cy - 68);
        cavite.curveTo(cx - 4, cy - 65, cx - 5, cy - 35, cx - 5, cy);
        cavite.curveTo(cx - 5, cy + 35, cx - 4, cy + 65, cx, cy + 68);
        cavite.curveTo(cx + 6, cy + 63, cx + 7, cy + 35, cx + 7, cy);
        cavite.curveTo(cx + 7, cy - 35, cx + 6, cy - 63, cx, cy - 68);
        g2.setColor(new Color(46, 26, 8, 220));
        g2.fill(cavite);

        // --- Hachures sur la coque ---
        g2.setColor(new Color(212, 132, 122, 180));
        g2.setStroke(new BasicStroke(1.1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int[][] hachuresCoque = {
            {cx - 30, cy - 82, cx - 65, cy - 75},
            {cx - 20, cy - 70, cx - 70, cy - 58},
            {cx - 15, cy - 55, cx - 80, cy - 38},
            {cx - 12, cy - 38, cx - 90, cy - 18},
            {cx - 10, cy - 20, cx - 100, cy - 4},
            {cx - 10, cy + 2,  cx - 100, cy + 10},
            {cx - 12, cy + 22, cx - 92,  cy + 30},
            {cx - 16, cy + 40, cx - 82,  cy + 50},
            {cx - 22, cy + 56, cx - 68,  cy + 62},
            {cx - 32, cy + 70, cx - 55,  cy + 74},
        };
        for (int[] h : hachuresCoque) {
            g2.drawLine(h[0], h[1], h[2], h[3]);
        }

        // --- Hachures sur la chair ---
        g2.setColor(new Color(180, 160, 140, 130));
        int[][] hachurescChair = {
            {cx + 2, cy - 80, cx + 9,  cy - 60},
            {cx + 2, cy - 55, cx + 10, cy - 30},
            {cx + 2, cy - 20, cx + 10, cy + 5},
            {cx + 2, cy + 15, cx + 10, cy + 40},
            {cx + 2, cy + 48, cx + 9,  cy + 68},
        };
        for (int[] h : hachurescChair) {
            g2.drawLine(h[0], h[1], h[2], h[3]);
        }

        // --- Contour de la coque ---
        g2.setColor(new Color(176, 90, 80));
        g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(coque);

        // --- Contour de la chair (côté droit) ---
        Path2D contourChair = new Path2D.Double();
        contourChair.moveTo(cx, cy - 90);
        contourChair.curveTo(cx + 13, cy - 85, cx + 14, cy - 48, cx + 14, cy);
        contourChair.curveTo(cx + 14, cy + 48, cx + 13, cy + 85, cx, cy + 90);
        g2.draw(contourChair);

        // --- Ligne plate (bord de coupe) ---
        g2.drawLine(cx, cy - 90, cx, cy + 90);
    }

    //test pour le dessin
    public static void main(String[] args) {
        JFrame frame = new JFrame("Coco gauche");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PanelDemiCocoGauche());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    } 
}
