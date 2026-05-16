package controleur;

import entity.Joueur;

public class ControlPirateCourant {
	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurcourant;
	private int nbTour;
	
	public Joueur getJoueurcourant() {
		return joueurcourant;
	}
	
	
	
	public ControlPirateCourant(Joueur joueur1, Joueur joueur2) {
		super();
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
		this.nbTour = 1;
		this.joueurcourant = joueur1;
	}



	public int getNbTour() {
		return nbTour;
	}



	public Joueur changerJoueur() {
		if (joueurcourant.equals(joueur1)) {
			joueurcourant = joueur2;
		}
		else {
			joueurcourant = joueur1;
		}
		nbTour++;
		return joueurcourant;
	}

}
