package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PanelEffetBombe extends JPanel {

    private JDialog dialogueParent;
    private JButton boutonContinuer;
    private Timer timer;
    private String messageDegats;
    
    //nos variables
    private int bombeY = -50;
    private boolean enExplosion = false;
    private int rayonExplosion = 0;

    public PanelEffetBombe(JDialog dialogueParent, String messageDegats) {
        this.dialogueParent = dialogueParent;
        this.messageDegats = messageDegats;
        setPreferredSize(new Dimension(400, 400));
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Fond neutre

        // notre bouton continuer doit être caché au debut et apparait à la fin
        boutonContinuer = new JButton("Continuer");
        boutonContinuer.setVisible(false);
        boutonContinuer.addActionListener(e -> dialogueParent.dispose());
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.add(boutonContinuer);
        add(bottomPanel, BorderLayout.SOUTH);

        // boucle anim
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!enExplosion) {
                    bombeY = bombeY + 10; // vitesse de chute
                    // bombe au milieu
                    if (bombeY >= getHeight() / 2 - 25) {
                        enExplosion = true; 
                       
                    }
                } else {
                    rayonExplosion += 15; // vitesse de l'explosion du feu
                    //recouvre l'ecran en entier
                    if (rayonExplosion > Math.max(getWidth(), getHeight()) * 1.5) {
                        timer.stop(); //fin anim
                        boutonContinuer.setVisible(true); // afdfichage du bouton
                    }
                }
                repaint(); //on redessine pour chaque frame
            }
        });
        jouerSonExplosion();
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //( anti aliasaing qui permet d'avoir une plus belle image ( les ronds seront p^lus fluide ) 
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centreX = getWidth() / 2;
        int centreY = getHeight() / 2;

        if (!enExplosion) {
            // bombe noire
            g2d.setColor(Color.BLACK);
            g2d.fillOval(centreX - 25, bombeY, 50, 50);
            
            // mèche grise
            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(4));
            g2d.drawLine(centreX, bombeY, centreX + 15, bombeY - 15);
            
            // étincelle
            g2d.setColor(Color.ORANGE);
            g2d.fillOval(centreX + 10, bombeY - 20, 10, 10);
            
        } else {
            // Exploision : superposition des cercles
            g2d.setColor(new Color(255, 50, 50)); // rouge
            g2d.fillOval(centreX - (rayonExplosion / 2), centreY - (rayonExplosion / 2), rayonExplosion, rayonExplosion);
            
            g2d.setColor(Color.ORANGE); // orange
            int rayonInterieur = (int) (rayonExplosion * 0.7);
            g2d.fillOval(centreX - (rayonInterieur / 2), centreY - (rayonInterieur / 2), rayonInterieur, rayonInterieur);

            g2d.setColor(Color.YELLOW); // jaune
            int rayonCoeur = (int) (rayonExplosion * 0.4);
            g2d.fillOval(centreX - (rayonCoeur / 2), centreY - (rayonCoeur / 2), rayonCoeur, rayonCoeur);
            
            if (rayonExplosion > 100) {
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 24));
                String msg = "BOUM !";
                FontMetrics fm = g2d.getFontMetrics();
                g2d.drawString(messageDegats, centreX - (fm.stringWidth(messageDegats) / 2), centreY);
            }
        }
    }
    
    private void jouerSonExplosion() {
        try {
            java.net.URL url = getClass().getResource("/boum.wav");
            if (url != null) {
                javax.sound.sampled.AudioInputStream audioIn = javax.sound.sampled.AudioSystem.getAudioInputStream(url);
                javax.sound.sampled.Clip clip = javax.sound.sampled.AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            }
        } catch (Exception e) {
            System.err.println("Erreur de son : " + e.getMessage());
        }
    }
}