/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

/**
 *
 * @author rtiam
 */

//Melanie
public class PanelJoueur extends JPanel {

    private Color COULEUR_INACTIF;
    private String nom;
    private Color couleurJoueur;
    private JLabel labelNom;
    private PanelCoeurs coeurs;
    private JLabel indicateur;
 
    //dans le main on récupére le nom du pirate choisit par le joueur
    //La couleur on verra en fonction de si depuis la mainframe on choisit nous même ou si 
    public PanelJoueur(String nom, Color couleurJoueur) {
        this.nom = nom;
        this.couleurJoueur = couleurJoueur;
        this.COULEUR_INACTIF = couleurJoueur;
 
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //la bordure est a la couleur du joueur pour separer les 2 joueurs
        setBorder(BorderFactory.createLineBorder(couleurJoueur, 3, true));
        setOpaque(true);
 
        //---Nom---
        labelNom = new JLabel(nom);
        labelNom.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelNom.setForeground(couleurJoueur);
        labelNom.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        //---Coeurs PV---
        coeurs = new PanelCoeurs(couleurJoueur);
        coeurs.setAlignmentX(Component.CENTER_ALIGNMENT);
 
        //--- Indicateur joueur actif ---
        indicateur = new JLabel("En jeu");
        indicateur.setFont(new Font("Segoe UI", Font.BOLD, 12));
        indicateur.setForeground(COULEUR_INACTIF);
        indicateur.setVisible(false);
        indicateur.setAlignmentX(Component.CENTER_ALIGNMENT);
        

        add(labelNom);
        add(coeurs);
        add(indicateur);
    }
 
    //met à jour les points de vie affichés (0 à 5)
    public void setPointsDeVie(int pv) {
        coeurs.setPv(pv);
    }
 
    // Active ou désactive l'indicateur visuel "joueur actif"
    public void setActif(boolean actif) {
        indicateur.setVisible(actif);
        setBackground(actif ? new Color(220, 255, 220) : Color.WHITE);
        repaint();
    }
 
    public String getNom() {
        return nom;
    }
    
    //Yoakin
    public void setNom(String nouveauNom) {
        this.nom = nouveauNom;
        labelNom.setText(nouveauNom);
    }
    
    
}


