package controleur;

import boundary.interfaces.IBoundary;
import boundary.interfaces.IControlCacherDe;
import entity.Joueur;
import entity.Jeu;

public class ControlCacherDe implements IControlCacherDe {
	private Joueur[] joueurs;
	private IBoundary iBoundary;
	private Jeu jeu;
	private ControlJeuPirate controlJeuPirate;

	public ControlCacherDe(Joueur[] joueur, IBoundary iBoundary, Jeu jeu, ControlJeuPirate controlJeuPirate) {
		this.joueurs = joueur;
		this.iBoundary = iBoundary;
		this.jeu = jeu;
		this.controlJeuPirate = controlJeuPirate;
	}

	public void setAffecteCoco(Joueur joueurAffecte, boolean estAffecte) {
		joueurAffecte.setAffecteCoco(estAffecte);
	}

	@Override
	public void demanderUtilisationCoco() {
		System.out.println("YESS");
		if (jeu.getJoueurCourant().hasCoco()) {
			System.out.println("entered");
			iBoundary.demanderUtilisationCoco(this);
		} else {
			controlJeuPirate.apresDemandeCoco();
		}
	}

	@Override
	public void finDemandeCoco(String reponse) {
		if (reponse.equalsIgnoreCase("oui")) {
			joueurs[(jeu.getIndiceJoueurCourant() + 1) % 2].setAffecteCoco(true);
			joueurs[jeu.getIndiceJoueurCourant()].setHasCoco(false);
		}
		controlJeuPirate.apresDemandeCoco();
	}
}