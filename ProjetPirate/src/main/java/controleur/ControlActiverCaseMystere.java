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
	private int joueurCourant;
	
	public ControlActiverCaseMystere(Joueur[] joueurs, Case caseSpecial,int joueurCourant, ControlPointDeVie controlVie,ControlJeuPirate controlJeuPirate ) {
		super(joueurs, caseSpecial);
		this.joueurCourant = joueurCourant;
		this.controlVie = controlVie;
		this.controlDeplacerPirate = new ControlDeplacer(joueurs);
		this.controlJeuPirate     = controlJeuPirate;
	}
	
	private void faireAvancerJoueur(int nb) {
		controlDeplacerPirate.deplacer(nb, joueurCourant);
	}
	
	private void faireReculerJoueur(int nb) {
		controlDeplacerPirate.deplacer(nb, joueurCourant);
	}
	
	private void modifPointDeVie(int nb) {
		controlVie.gagnerPointsDeVie(nb,joueurs[joueurCourant]);
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
