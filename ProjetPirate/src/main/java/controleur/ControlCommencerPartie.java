package controleur;

import entity.De;
import entity.Jeu;
import entity.Joueur;
import entity.Pion;

public class ControlCommencerPartie {

    private Jeu jeu;

    public ControlCommencerPartie(Jeu jeu) {
        this.jeu = jeu;
    }

    public void initialiserJoueurs(String nomJ1, String nomJ2) {
        jeu.setJoueur(0, new Joueur(nomJ1, new Pion()));
        jeu.setJoueur(1, new Joueur(nomJ2, new Pion()));
    }

    public String determinerJoueurQuiCommence() {
        De de = new De();
        int resJ1, resJ2;
        do {
            resJ1 = de.getValeur();
            resJ2 = de.getValeur();
        } while (resJ1 == resJ2);

        if (resJ1 > resJ2) {
            return jeu.getJoueur(0).getNom();
        } else {
            jeu.passerAuJoueurSuivant();
            return jeu.getJoueur(1).getNom();
        }
    }
}
