package controleur;

import java.lang.instrument.IllegalClassFormatException;

import entity.Case;
import entity.CaseMystere;
import entity.Joueur;
import boundary.interfaces.*;

public class ControlActiverCaseMystere extends ControlActiverCaseSpecial implements ICaseMystere{

	
	private ControlPointDeVie controlVie;
	private ControlDeplacer controlDeplacerPirate;
	private ControlJeuPirate  controlJeuPirate;
	
	public ControlActiverCaseMystere(Joueur joueur, Case caseSpecial, ControlPointDeVie controlVie,ControlJeuPirate controlJeuPirate ) {
		super(joueur, caseSpecial);
		this.controlVie = controlVie;
		this.controlDeplacerPirate = new ControlDeplacer(joueur);
		this.controlJeuPirate     = controlJeuPirate;
	}
	
	private void faireAvancerJoueur(int nb) {
		controlDeplacerPirate.deplacer(nb);
	}
	
	private void faireReculerJoueur(int nb) {
		controlDeplacerPirate.deplacer(nb);
	}
	
	private void modifPointDeVie(int nb) {
		controlVie.gagnerPointsDeVie(nb,joueur);
	}
	
	@Override
	void activerCase() {
		if (caseSpecial instanceof CaseMystere caseMystere ) {
//			caseMystere.activerCase();
//			switch (caseMystere.effect) {
//			case 0: 
//				faireAvancerJoueur(caseMystere.getValue());
//				break;
//			case 1:
//				faireReculerJoueur(caseMystere.getValue());
//				break;
//			case 2:
//				if (caseMystere.getValue()>2) {
//					throw new IllegalArgumentException("Unexpected value: " + caseMystere.getValue());
//				}
//				modifPointDeVie(caseMystere.getValue());
//				break;
//			default:
//				throw new IllegalArgumentException("Unexpected value: " + caseMystere.effect);
//			}
		}
		
	}
	
	//appelé par la boundary après afficherCaseMystere()
    @Override
    public void finCaseMystere() {
        //controlJeuPirate.apresActiverCase();
    }

}
