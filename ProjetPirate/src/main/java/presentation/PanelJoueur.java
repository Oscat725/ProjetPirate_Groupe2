/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author rtiam
 */

// Melanie
public class PanelJoueur extends JPanel {

    private Color COULEUR_INACTIF;
    private String nom;
    private Color couleurJoueur;
    private JLabel labelNom;
    private PanelCoeurs coeurs;
    private JLabel indicateur;

    PanelDemiCocoDroit coco;

    // dans le main on récupére le nom du pirate choisit par le joueur
    // La couleur on verra en fonction de si depuis la mainframe on choisit nous
    // même ou si
    public PanelJoueur(String nom, Color couleurJoueur) {
        this.nom = nom;
        this.couleurJoueur = couleurJoueur;
        this.COULEUR_INACTIF = couleurJoueur;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // la bordure est a la couleur du joueur pour separer les 2 joueurs
        setBorder(BorderFactory.createLineBorder(couleurJoueur, 3, true));
        setOpaque(true);

        // ---Nom---

        labelNom = new JLabel(nom);
        labelNom.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelNom.setForeground(couleurJoueur);
        labelNom.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Coeurs PV ---
        coeurs = new PanelCoeurs(couleurJoueur);
        coeurs.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- Coco (à droite des coeurs) ---
        coco = new PanelDemiCocoDroit();
        coco.setPreferredSize(new Dimension(30, 30));
        coco.setVisible(false);

        // Composant fantôme pour équilibrer la gauche et garder les cœurs PARFAITEMENT centrés
        Component fantomeGauche = Box.createRigidArea(new Dimension(30, 30));
        fantomeGauche.setVisible(false);

        // Ligne coeurs + coco : coeurs centré, coco collé à droite
        JPanel ligneCoeurs = new JPanel(new BorderLayout());
        ligneCoeurs.setOpaque(false);
        ligneCoeurs.add(fantomeGauche, BorderLayout.WEST);
        ligneCoeurs.add(coeurs, BorderLayout.CENTER);
        ligneCoeurs.add(coco, BorderLayout.EAST);
        ligneCoeurs.setAlignmentX(Component.CENTER_ALIGNMENT);
        ligneCoeurs.setMaximumSize(new Dimension(200, 40));

        // --- Indicateur joueur actif ---
        indicateur = new JLabel("En jeu");
        indicateur.setFont(new Font("Segoe UI", Font.BOLD, 12));
        indicateur.setForeground(new Color(0, 0, 0, 0)); // Transparent au lieu de setVisible(false)
        indicateur.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(labelNom);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(ligneCoeurs);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(indicateur);
        add(Box.createVerticalGlue());
    }

    public void setCoco(boolean hasCoco) {
        coco.setVisible(hasCoco);
        // Si on affiche le coco, on affiche aussi l'espace fantôme à gauche pour garder le centrage
        BorderLayout layout = (BorderLayout) coco.getParent().getLayout();
        Component fantome = layout.getLayoutComponent(BorderLayout.WEST);
        if (fantome != null)
            fantome.setVisible(hasCoco);
    }

    // met à jour les points de vie affichés (0 à 5)
    public void setPointsDeVie(int pv) {
        coeurs.setPv(pv);
    }

    // Active ou désactive l'indicateur visuel "joueur actif"
    public void setActif(boolean actif) {
        // Au lieu de setVisible qui détruit la symétrie, on rend le texte transparent ou visible
        indicateur.setForeground(actif ? COULEUR_INACTIF : new Color(0, 0, 0, 0));
        setBackground(actif ? new Color(220, 255, 220) : Color.WHITE);
        repaint();
    }

    public String getNom() {
        return nom;
    }

    // Yoakin
    public void setNom(String nouveauNom) {
        this.nom = nouveauNom;
        labelNom.setText(nouveauNom);
    }

}
