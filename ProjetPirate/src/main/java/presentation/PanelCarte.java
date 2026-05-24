/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

/**
 *
 * @author oscar
 */
public class PanelCarte extends javax.swing.JPanel {
    
    private boolean enableFlip = true;
    private boolean fliped = true;
    private int value = 0;
    private int effect = 2;
    
    public void setFlip(boolean set) {
        enableFlip = set;
    }

    /**
     * Creates new form PanelCarte
     */
    public PanelCarte() {
        initComponents();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        Graphics2D g2D = (Graphics2D) g;
        int margin = 10;
        int arc = 10;
        int x = margin;
        int y = margin;
        int width = getWidth() - margin * 2;
        int height = getHeight() - margin * 2;
        drawBoarder(g2D, x, y, width, height, arc);
        if (!fliped) {
            drawSkull(g2D, x, y, width, height);
        } else {
            switch (effect) {
                case 2:
                    drawHeart(g2D, x, y, width, height);
                    break;
                case 1:
                    //drawFowardArrow();
                case 0:
                    //drawBackwardsArrow();
                default:
                    throw new AssertionError();
            }
            //drawValue(value);
        }
    }
    
    private void drawBoarder(Graphics2D g2D, int x, int y, int width, int height, int arc) {
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(Color.LIGHT_GRAY);
        g2D.fillRoundRect(x, y, width, height, arc, arc);
        
        g2D.setColor(Color.DARK_GRAY);
        g2D.setStroke(new BasicStroke(2));
        g2D.drawRoundRect(x, y, width, height, arc, arc);
    }
    
    private void drawHeart(Graphics2D g2D, int cardX, int cardY, int cardW, int cardH) {
        int heartW = (int) (cardW * 0.32);
        int heartH = (int) (cardH * 0.32);
        
        int centerX = cardX + cardW / 2;
        int centerY = cardY + cardH / 2;
        
        int x = centerX - heartW / 2;
        int y = centerY - heartH / 2;
        
        int lobeW = heartW / 2;
        int lobeH = heartH / 2;
        
        g2D.setColor(Color.red);

        //left lobe 
        g2D.fillOval(x, y, lobeW, lobeH);

        //right lobe
        g2D.fillOval(x + lobeW, y, lobeW, lobeH);

        //bottom
        int[] px = {
            x,
            x + heartW,
            x + heartW / 2
        };
        
        int[] py = {
            y + lobeH / 2,
            y + lobeH / 2,
            y + heartH
        };
        
      
        g2D.fillPolygon(px, py, 3);
    }
    
  
    private void drawSkull(Graphics2D g2, int cardX, int cardY, int cardW, int cardH) {
        
        Color skullColor = new Color(245, 245, 245);
        Color holeColor = new Color(35, 30, 35);
        Color cutColor = new Color(200, 200, 200); // light gray for teeth cutouts

        // Skull size proportional to the card
        int skullW = (int) (cardW * 0.42);
        int skullH = (int) (cardH * 0.38);
        
        int skullX = cardX + (cardW - skullW) / 2;
        int skullY = cardY + (cardH - skullH) / 2 - (int) (cardH * 0.03);

        // ----- HEAD -----
        int headH = (int) (skullH * 0.72);
        
        g2.setColor(skullColor);
        g2.fillOval(skullX, skullY, skullW, headH);

        // ----- JAW -----
        int jawW = (int) (skullW * 0.62);
        int jawH = (int) (skullH * 0.23);
        int jawX = skullX + (skullW - jawW) / 2;
        int jawY = skullY + headH - (int) (jawH * 0.35);
        
        g2.fillRoundRect(jawX, jawY, jawW, jawH, jawH / 2, jawH / 2);

        // ----- SIDE BARS / CHEEKS -----
        int cheekW = (int) (skullW * 0.10);
        int cheekH = (int) (skullH * 0.18);

        // move them closer to the center
        int leftCheekX = skullX + (int) (skullW * 0.18);
        int rightCheekX = skullX + skullW - cheekW - (int) (skullW * 0.18);
        int cheekY = jawY - (int) (cheekH * 0.20);
        
        g2.fillRoundRect(leftCheekX, cheekY, cheekW, cheekH, cheekW, cheekW);
        g2.fillRoundRect(rightCheekX, cheekY, cheekW, cheekH, cheekW, cheekW);

        // ----- EYES -----
        g2.setColor(holeColor);
        
        int eyeSize = (int) (skullW * 0.26);
        int eyeY = skullY + (int) (headH * 0.42);
        
        int leftEyeX = skullX + (int) (skullW * 0.22);
        int rightEyeX = skullX + skullW - eyeSize - (int) (skullW * 0.22);
        
        g2.fillOval(leftEyeX, eyeY, eyeSize, eyeSize);
        g2.fillOval(rightEyeX, eyeY, eyeSize, eyeSize);

        // ----- NOSE : sharper inverted heart -----
        int noseW = (int) (skullW * 0.14);
        int noseH = (int) (skullH * 0.16);
        
        int noseCenterX = skullX + skullW / 2;
        int noseTopY = skullY + (int) (headH * 0.68);
        
        int lobeW = noseW / 2;
        int lobeH = (int) (noseH * 0.50);
        int lobeY = noseTopY + (int) (noseH * 0.50);

// bottom lobes
        g2.fillOval(noseCenterX - lobeW, lobeY, lobeW, lobeH);
        g2.fillOval(noseCenterX, lobeY, lobeW, lobeH);

// top point
        int[] px = {
            noseCenterX,
            noseCenterX - noseW / 2,
            noseCenterX + noseW / 2
        };
        
        int[] py = {
            noseTopY,
            lobeY + lobeH / 3,
            lobeY + lobeH / 3
        };
        
        g2.fillPolygon(px, py, 3);

        // ----- TEETH AS CUTOUTS -----
        g2.setColor(cutColor);
        
        int toothGapW = (int) (jawW * 0.08);
        int toothGapH = (int) (jawH * 0.60);
        int gapY = jawY + jawH - toothGapH;
        
        int gap1X = jawX + jawW / 4 - toothGapW / 2;
        int gap2X = jawX + jawW / 2 - toothGapW / 2;
        int gap3X = jawX + (3 * jawW / 4) - toothGapW / 2;
        
        g2.fillRoundRect(gap1X, gapY, toothGapW, toothGapH, 3, 3);
        g2.fillRoundRect(gap2X, gapY, toothGapW, toothGapH, 3, 3);
        g2.fillRoundRect(gap3X, gapY, toothGapW, toothGapH, 3, 3);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
