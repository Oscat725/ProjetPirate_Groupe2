package controleur;

import java.lang.instrument.IllegalClassFormatException;

import entity.CaseMystere;
import entity.Joueur;

public class ControlActiverCaseMystere extends ControlActiverCaseSpecial{

	private Joueur joueur;
	private ControlPointDeVie controlVie;
	private ControlDeplacerPirate controlDeplacerPirate;
	
	private void faireAvancerJoueur(int nb) {
		controlDeplacerPirate.deplacerJoueur(nb,joueur);
	}
	
	private void faireReculerJoueur(int nb) {
		controlDeplacerPirate.deplacerJoueur(nb,joueur);
	}
	
	private void modifPointDeVie(int nb) {
		controlVie.gagnerPointsDeVie(nb,joueur);
	}
	
	@Override
	void activerCase() {
		if (caseSpecial instanceof CaseMystere caseMystere ) {
			caseMystere.activerCase();
			switch (caseMystere.effect) {
			case 0: 
				faireAvancerJoueur(caseMystere.getValue());
				break;
			case 1:
				faireReculerJoueur(caseMystere.getValue());
				break;
			case 2:
				if (caseMystere.getValue()>2) {
					throw new IllegalArgumentException("Unexpected value: " + caseMystere.getValue());
				}
				modifPointDeVie(caseMystere.getValue());
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + caseMystere.effect);
			}
		}
		else {
			throw new IllegalClassFormatException("Unexpercted class : "+ caseSpecial.getClass())
		}
		
	}

	public ControlActiverCaseMystere() {
		super();
	}

}
