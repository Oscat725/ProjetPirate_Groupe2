/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author beman
 */
public class PanelDemiCocoDroit extends JPanel{
 
    public PanelDemiCocoDroit() {
        setPreferredSize(new Dimension(200, 200));
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
        double scaleY = getHeight() / 200.0;
        g2.scale(scaleX, scaleY);

        // Coordonnées de référence (pour 200x200)
        int cx = 90;
        int cy = 100;

        // Légère rotation comme dans le dessin original
        g2.rotate(Math.toRadians(-6), cx, cy);

        // --- Coque (demi-ellipse, côté plat à gauche) ---
        Path2D coque = new Path2D.Double();
        coque.moveTo(cx, cy - 80);
        coque.curveTo(cx + 80, cy - 76, cx + 105, cy - 36, cx + 105, cy);
        coque.curveTo(cx + 105, cy + 36, cx + 80, cy + 76, cx, cy + 80);
        coque.closePath();
        g2.setColor(new Color(201, 122, 110));
        g2.fill(coque);

        // --- Chair blanche (bande sur le côté plat) ---
        Path2D chair = new Path2D.Double();
        chair.moveTo(cx, cy - 80);
        chair.curveTo(cx + 6, cy - 78, cx + 8, cy - 44, cx + 8, cy);
        chair.curveTo(cx + 8, cy + 44, cx + 6, cy + 78, cx, cy + 80);
        chair.curveTo(cx - 11, cy + 76, cx - 12, cy + 44, cx - 12, cy);
        chair.curveTo(cx - 12, cy - 44, cx - 11, cy - 76, cx, cy - 80);
        g2.setColor(new Color(242, 235, 227));
        g2.fill(chair);

        // --- Cavité intérieure sombre ---
        Path2D cavite = new Path2D.Double();
        cavite.moveTo(cx, cy - 60);
        cavite.curveTo(cx + 4, cy - 58, cx + 5, cy - 32, cx + 5, cy);
        cavite.curveTo(cx + 5, cy + 32, cx + 4, cy + 58, cx, cy + 60);
        cavite.curveTo(cx - 6, cy + 57, cx - 7, cy + 32, cx - 7, cy);
        cavite.curveTo(cx - 7, cy - 32, cx - 6, cy - 57, cx, cy - 60);
        g2.setColor(new Color(46, 26, 8, 220));
        g2.fill(cavite);

        // --- Hachures sur la coque ---
        g2.setColor(new Color(212, 132, 122, 180));
        g2.setStroke(new BasicStroke(1.1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        int[][] hachuresCoque = {
            {cx + 25, cy - 73, cx + 60, cy - 66},
            {cx + 18, cy - 62, cx + 64, cy - 50},
            {cx + 14, cy - 48, cx + 74, cy - 32},
            {cx + 11, cy - 30, cx + 84, cy - 14},
            {cx + 10, cy - 10, cx + 92, cy},
            {cx + 10, cy + 10, cx + 90, cy + 16},
            {cx + 12, cy + 28, cx + 82, cy + 38},
            {cx + 16, cy + 44, cx + 70, cy + 54},
            {cx + 22, cy + 58, cx + 56, cy + 64},
        };
        for (int[] h : hachuresCoque) {
            g2.drawLine(h[0], h[1], h[2], h[3]);
        }

        // --- Hachures sur la chair ---
        g2.setColor(new Color(180, 160, 140, 130));
        int[][] hachurescChair = {
            {cx - 1, cy - 72, cx - 8,  cy - 52},
            {cx - 1, cy - 46, cx - 9,  cy - 22},
            {cx - 1, cy - 12, cx - 9,  cy + 12},
            {cx - 1, cy + 22, cx - 8,  cy + 46},
            {cx - 1, cy + 54, cx - 8,  cy + 70},
        };
        for (int[] h : hachurescChair) {
            g2.drawLine(h[0], h[1], h[2], h[3]);
        }

        // --- Contour de la coque ---
        g2.setColor(new Color(176, 90, 80));
        g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.draw(coque);

        // --- Contour de la chair (côté gauche) ---
        Path2D contourChair = new Path2D.Double();
        contourChair.moveTo(cx, cy - 80);
        contourChair.curveTo(cx - 12, cy - 77, cx - 13, cy - 44, cx - 13, cy);
        contourChair.curveTo(cx - 13, cy + 44, cx - 12, cy + 77, cx, cy + 80);
        g2.draw(contourChair);

        // --- Ligne plate (bord de coupe) ---
        g2.drawLine(cx, cy - 80, cx, cy + 80);
    }
    
    //test pour le dessin
    public static void main(String[] args) {
        JFrame frame = new JFrame("Coco droite");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PanelDemiCocoDroit());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
