package controleur;

import java.lang.instrument.IllegalClassFormatException;

import entity.CaseBombe;


public class ControlAvtiverCaseBombe extends ControlActiverCaseSpecial {

	private ConrolPointsDeVie controlVie;
	private ControlLanceDe controlDe;
	
	
	@Override
	void activerCase() {
		if (caseSpecial instanceof CaseBombe caseBombe  ) {
			caseBombe.activerBombe(controlDe.lancerDe(null));
			controlVie.predrePointsDeVie(caseBombe.getDamage());
		}
		else {
			throw new IllegalClassFormatException("Unexpercted class : "+ caseSpecial.getClass())
		}
		
	}
	
}
