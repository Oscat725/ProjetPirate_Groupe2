/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controleur;

import entity.Jeu;

// Nolawi
public class ControleurDe implements ILancerDe {

    private Jeu jeu;

    public ControleurDe(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public int[] lancerDe() {
        return jeu.getJoueurCourant().lancerDe();
    }
}


