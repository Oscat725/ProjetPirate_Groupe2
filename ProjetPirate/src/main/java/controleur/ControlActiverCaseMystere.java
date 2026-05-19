package controleur;

import java.lang.instrument.IllegalClassFormatException;

import entity.Case;
import entity.CaseMystere;
import entity.Joueur;
import boundary.interfaces.*;

public class ControlActiverCaseMystere extends ControlActiverCaseSpecial implements ICaseMystere {

	private ControlPointDeVie controlVie;
	private ControlDeplacer controlDeplacerPirate;
	private ControlJeuPirate controlJeuPirate;
	private IBoundary iBoundary;
	private int joueurCourant;

	public ControlActiverCaseMystere(Joueur[] joueurs, int joueurCourant,
			ControlPointDeVie controlVie, ControlJeuPirate controlJeuPirate, IBoundary iBoundary) {
		super(joueurs);
		this.joueurCourant = joueurCourant;
		this.controlVie = controlVie;
		this.controlJeuPirate = controlJeuPirate;
		this.controlDeplacerPirate = new ControlDeplacer(controlJeuPirate.getJeu(), controlJeuPirate.getBoundary(),
				controlJeuPirate);
		this.iBoundary = iBoundary;
	}

	private void faireAvancerJoueur(int nb) {
		controlDeplacerPirate.deplacerPirate(nb);
	}

	private void faireReculerJoueur(int nb) {
		controlDeplacerPirate.deplacerPirate(-nb);
	}

	private void modifPointDeVie(int nb) {
		controlVie.gagnerPointsDeVie(nb, joueurs[joueurCourant]);
	}

	@Override
	void activerCase(Case caseSpecial,int joueurCourant) {
		if (caseSpecial instanceof CaseMystere caseMystere) {
			caseMystere.activerCase();
			switch (caseMystere.effect) {
			case 0: 
				faireAvancerJoueur(caseMystere.getValue());
				iBoundary.afficherEffetCase(
		                "MYSTERE","Le joueur avance de " + caseMystere.getValue() + " cases");
				break;
			case 1:
				faireReculerJoueur(caseMystere.getValue());
				iBoundary.afficherEffetCase(
		                "MYSTERE","Le joueur recule de " + caseMystere.getValue() + " cases");
				break;
			case 2:
				iBoundary.afficherEffetCase(
		                "MYSTERE","Le joueur gagne " + caseMystere.getValue() + " PV");
				modifPointDeVie(caseMystere.getValue());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + caseMystere.effect);
			}
		}
		finCaseMystere();

	}

	// appelé par la boundary après afficherCaseMystere()
	@Override
	public void finCaseMystere() {
		// controlJeuPirate.apresActiverCase();
	}

}
