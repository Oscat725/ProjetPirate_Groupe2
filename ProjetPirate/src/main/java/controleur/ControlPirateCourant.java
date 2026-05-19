package controleur;

import entity.Jeu;
import entity.Joueur;

public class ControlPirateCourant {
	private Jeu jeu;
	private int nbTour;
	
	public ControlPirateCourant(Jeu jeu) {
		this.jeu = jeu;
		this.nbTour = 1;
	}

	public Joueur getJoueurcourant() {
		return jeu.getJoueurCourant();
	}

	public int getNbTour() {
		return nbTour;
	}

	public Joueur changerJoueur() {
		nbTour++;
		return jeu.getJoueurCourant();
	}
}