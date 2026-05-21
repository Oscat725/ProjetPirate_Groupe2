package controleur;

import entity.Joueur;

public class ControlCacherDe {
	private Joueur joueur;
	
	public ControlCacherDe(Joueur joueur) {
		this.joueur = joueur;
	}
	
	public void setAffecteCoco(Joueur joueurAffecte, boolean estAffecte) {
		joueurAffecte.setAffecteCoco(estAffecte);
	}
}