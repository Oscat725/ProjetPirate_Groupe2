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
import java.awt.geom.Path2D;
import javax.swing.Timer;

/**
 *
 * @author oscar
 */
public class PanelCarte extends javax.swing.JPanel {

    private boolean enableFlip = true;
    private boolean fliped = false;
    private int value = 1;
    private int effect = 0;

    //atributs pour le flip
    private double flipScale = 1.0;
    private boolean flipping = false;

    public void setFlip(boolean enableFlipBool) {
        this.enableFlip = enableFlipBool;
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
        g2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        int margin = 10;
        int arc = 10;
        int fullX = margin;
        int fullY = margin;
        int fullW = getWidth() - margin * 2;
        int fullH = getHeight() - margin * 2;
        
        int cardW = (int) (fullW * flipScale);
        int cardX = fullX + (fullW - cardW)/2;
        
        cardW = Math.max(cardW, 1);
        
        drawBoarder(g2D, cardX, fullY, cardW, fullH, arc);
        
        if (cardW>20) {
            if (!fliped) {
            drawSkull(g2D, cardX, fullY, cardW, fullH);
        } else {
            switch (effect) {
                case 2:
                    drawHeart(g2D, cardX, fullY, cardW, fullH);
                    break;
                case 1:
                    drawForwardArrow(g2D, cardX, fullY, cardW, fullH);
                    break;
                case 0:
                    drawBackwardArrow(g2D, cardX, fullY, cardW, fullH);
                    break;
                default:
                    throw new AssertionError();
            }
            drawValue(value, g2D, cardX, fullY, cardW, fullH);
        }
        }
        
        
    }

    public void flipCard() {
        if (flipping) {
            return;
        }
        
        if (!enableFlip){
            return;
        }

        flipping = true;

        Timer timer = new Timer(15, null);

        final int[] step = {0};
        int totalSteps = 40;

        timer.addActionListener(e -> {
            step[0]++;

            double progress = step[0] / (double) totalSteps;

            if (progress < 0.5) {
                // First half: shrink card
                flipScale = 1.0 - progress * 2.0;
            } else {
                // Switch face exactly in the middle
                if (!fliped) {
                    fliped = true;
                }

                // Second half: grow card
                flipScale = (progress - 0.5) * 2.0;
            }

            repaint();

            if (step[0] >= totalSteps) {
                flipScale = 1.0;
                flipping = false;
                timer.stop();
                repaint();
            }
        });

        timer.start();
    }

    private void drawValue(int number, Graphics2D g2D, int cardX, int cardY, int cardW, int cardH) {
        switch (number) {
            case 1:
                drawOne(g2D, cardX, cardY, cardW, cardH);
                break;
            case 2:
                drawTwo(g2D, cardX, cardY, cardW, cardH);
                break;
            case 3:
                drawThree(g2D, cardX, cardY, cardW, cardH);
                break;
            case 4:
                drawFour(g2D, cardX, cardY, cardW, cardH);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void drawOne(Graphics2D g2D, int cardX, int cardY, int cardW, int cardH) {
        // Size proportional to the card
        int numberH = (int) (cardH * 0.22);
        int numberW = (int) (cardW * 0.18);

        int centerX = cardX + cardW / 2;
        int centerY = cardY + cardH / 2 + cardH / 4;

        int topY = centerY - numberH / 2;
        int bottomY = centerY + numberH / 2;

        int mainX = centerX + numberW / 6;

        // Stroke thickness proportional to card size
        float strokeSize = Math.max(3f, cardW * 0.035f);

        g2D.setColor(Color.BLACK);
        g2D.setStroke(new BasicStroke(
                strokeSize,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));

        // Main vertical line
        g2D.drawLine(mainX, topY, mainX, bottomY);

        // Small diagonal top part
        g2D.drawLine(mainX, topY, centerX - numberW / 2, topY + numberH / 6);

        // Bottom base
        g2D.drawLine(centerX - numberW / 2, bottomY, centerX + numberW / 2, bottomY);
    }

    private void drawTwo(Graphics2D g2D, int cardX, int cardY, int cardW, int cardH) {
        int numberH = (int) (cardH * 0.22);
        int numberW = (int) (cardW * 0.30);

        int centerX = cardX + cardW / 2;
        int centerY = cardY + cardH / 2 + cardH / 4;

        int xLeft = centerX - numberW / 2;
        int xRight = centerX + numberW / 2;

        int yTop = centerY - numberH / 2;
        int yMiddle = centerY;
        int yBottom = centerY + numberH / 2;

        float strokeSize = Math.max(3f, cardW * 0.035f);

        g2D.setColor(Color.BLACK);
        g2D.setStroke(new BasicStroke(
                strokeSize,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));

        Path2D.Double two = new Path2D.Double();

        // 1) Top curve
        two.moveTo(xLeft, yTop + numberH * 0.12);

        two.curveTo(
                xLeft + numberW * 0.20, yTop - numberH * 0.05,
                xRight, yTop,
                xRight, yTop + numberH * 0.22
        );

        // 2) Curve down to bottom-left
        two.curveTo(
                xRight, yTop + numberH * 0.45,
                xLeft, yMiddle + numberH * 0.20,
                xLeft, yBottom
        );

        // 3) Straight bottom line to bottom-right
        two.lineTo(xRight, yBottom);

        g2D.draw(two);
    }

    private void drawThree(Graphics2D g2D, int cardX, int cardY, int cardW, int cardH) {
        int numberH = (int) (cardH * 0.22);
        int numberW = (int) (cardW * 0.25);

        int centerX = cardX + cardW / 2;
        int centerY = cardY + cardH / 2 + cardH / 4;

        int xLeft = centerX - numberW / 2;
        int xRight = centerX + numberW / 2;

        int yTop = centerY - numberH / 2;
        int yMiddle = centerY;
        int yBottom = centerY + numberH / 2;

        float strokeSize = Math.max(3f, cardW * 0.035f);

        g2D.setColor(Color.BLACK);
        g2D.setStroke(new BasicStroke(
                strokeSize,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));

        // ----- STROKE 1: top inverted C -----
        Path2D.Double top = new Path2D.Double();

        top.moveTo(xLeft, yTop + numberH * 0.12);

        top.curveTo(
                xRight, yTop - numberH * 0.05,
                xRight, yMiddle - numberH * 0.05,
                xLeft + numberW * 0.45, yMiddle
        );

        g2D.draw(top);

        // ----- STROKE 2: bottom inverted C -----
        Path2D.Double bottom = new Path2D.Double();

        bottom.moveTo(xLeft + numberW * 0.45, yMiddle);

        bottom.curveTo(
                xRight, yMiddle + numberH * 0.05,
                xRight, yBottom + numberH * 0.05,
                xLeft, yBottom - numberH * 0.12
        );

        g2D.draw(bottom);
    }

    private void drawFour(Graphics2D g2D, int cardX, int cardY, int cardW, int cardH) {
        int numberH = (int) (cardH * 0.22);
        int numberW = (int) (cardW * 0.16);

        int centerX = cardX + cardW / 2;
        int centerY = cardY + cardH / 2 + cardH / 4;

        int xLeft = centerX - numberW / 2;
        int xRight = centerX + numberW / 2;

        int yTop = centerY - numberH / 2;
        int yMiddle = centerY;
        int yBottom = centerY + numberH / 2;

        float strokeSize = Math.max(3f, cardW * 0.035f);

        g2D.setColor(Color.BLACK);
        g2D.setStroke(new BasicStroke(
                strokeSize,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));

        // 1) Diagonal stroke
        g2D.drawLine(
                xRight,
                yTop,
                xLeft,
                yMiddle
        );

        // 2) Horizontal stroke
        g2D.drawLine(
                xLeft,
                yMiddle,
                xRight,
                yMiddle
        );

        // 3) Vertical stroke
        g2D.drawLine(
                xRight,
                yTop,
                xRight,
                yBottom
        );
    }

    private void drawForwardArrow(Graphics2D g2D, int cardX, int cardY, int cardW, int cardH) {
        // Arrow size proportional to the card
        int arrowW = (int) (cardW * 0.45);
        int arrowH = (int) (cardH * 0.28);

        int centerX = cardX + cardW / 2;
        int centerY = cardY + cardH / 2 - cardH / 8;

        int arrowX = centerX - arrowW / 2;
        int arrowY = centerY - arrowH / 2;

        // Body dimensions
        int bodyW = (int) (arrowW * 0.55);
        int bodyH = (int) (arrowH * 0.45);

        int bodyX = arrowX;
        int bodyY = centerY - bodyH / 2;

        // Head dimensions
        int headX = arrowX + bodyW;
        int headY = arrowY;

        // Green color
        g2D.setColor(new Color(0, 170, 80));

        // Arrow body
        g2D.fillRect(
                bodyX,
                bodyY,
                bodyW,
                bodyH
        );

        // Arrow head
        int[] xPoints = {
            headX,
            arrowX + arrowW,
            headX
        };

        int[] yPoints = {
            headY,
            centerY,
            arrowY + arrowH
        };

        g2D.fillPolygon(xPoints, yPoints, 3);
    }

    private void drawBackwardArrow(Graphics2D g2D, int cardX, int cardY, int cardW, int cardH) {
        // Arrow size proportional to the card
        int arrowW = (int) (cardW * 0.45);
        int arrowH = (int) (cardH * 0.28);

        int centerX = cardX + cardW / 2;
        int centerY = cardY + cardH / 2 - cardH / 8;

        int arrowX = centerX - arrowW / 2;
        int arrowY = centerY - arrowH / 2;

        // Body dimensions
        int bodyW = (int) (arrowW * 0.55);
        int bodyH = (int) (arrowH * 0.45);

        // For backward arrow, body is on the right
        int bodyX = arrowX + (int) (arrowW * 0.45);
        int bodyY = centerY - bodyH / 2;

        // Head is on the left
        int headX = bodyX;
        int headY = arrowY;

        // Dark red color
        g2D.setColor(new Color(130, 0, 0));

        // Arrow body
        g2D.fillRect(
                bodyX,
                bodyY,
                bodyW,
                bodyH
        );

        // Arrow head
        int[] xPoints = {
            headX,
            arrowX,
            headX
        };

        int[] yPoints = {
            headY,
            centerY,
            arrowY + arrowH
        };

        g2D.fillPolygon(xPoints, yPoints, 3);
    }

    private void drawBoarder(Graphics2D g2D, int x, int y, int width, int height, int arc) {
        if (!fliped) {
        g2D.setColor(Color.LIGHT_GRAY);
    } else {
        g2D.setColor(new Color(60, 60, 65));
    }
        g2D.fillRoundRect(x, y, width, height, arc, arc);

        g2D.setColor(Color.DARK_GRAY);
        g2D.setStroke(new BasicStroke(2));
        g2D.drawRoundRect(x, y, width, height, arc, arc);
    }

    private void drawHeart(Graphics2D g2D, int cardX, int cardY, int cardW, int cardH) {
        int heartW = (int) (cardW * 0.32);
        int heartH = (int) (cardH * 0.32);

        int centerX = cardX + cardW / 2;
        int centerY = cardY + cardH / 2 - cardH / 8;

        int x = centerX - heartW / 2;
        int y = centerY - heartH / 2;

        int lobeW = heartW / 2 + heartW / 6;
        int lobeH = heartH / 2;

        g2D.setStroke(new BasicStroke(
                2f,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND
        ));

        g2D.setColor(Color.red);

        //left lobe 
        g2D.fillOval(x - heartW / 12, y, lobeW, lobeH);

        //right lobe
        g2D.fillOval(x + heartW / 2 - heartW / 12, y, lobeW, lobeH);

        //bottom
        int[] px = {
            x - 2 * heartW / 28,
            x + heartW + 2 * heartW / 28,
            (x - 2 * heartW / 18 + x + heartW + 2 * heartW / 18) / 2
        };

        int[] py = {
            y + heartH - 2 * heartH / 3,
            y + heartH - 2 * heartH / 3,
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

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

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

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        if (enableFlip) {
            flipCard();
        }
        this.enableFlip = false;
        repaint();
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
