package controleur;

import entity.Case;
import entity.Joueur;

public abstract class ControlActiverCaseSpecial {
	Joueur[] joueurs;
	
	protected ControlActiverCaseSpecial(Joueur[] joueurs) {
		super();
		this.joueurs = joueurs;
	}
	
	abstract void activerCase(Case caseCourante, int joueurCourant);

}
