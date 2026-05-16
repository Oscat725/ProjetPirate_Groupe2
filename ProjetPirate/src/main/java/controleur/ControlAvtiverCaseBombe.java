package controleur;

import java.lang.instrument.IllegalClassFormatException;

import entity.Case;
import entity.CaseBombe;
import entity.Joueur;


public class ControlAvtiverCaseBombe extends ControlActiverCaseSpecial {

	private ControlPointDeVie controlVie;
	private ControleurDe controlDe;



	public ControlAvtiverCaseBombe(Joueur joueur, Case caseSpecial,ControleurDe controleurDe,ControlPointDeVie controlVie) {
		super(joueur, caseSpecial);
		this.controlDe = controleurDe;
		this.controlVie = controlVie;
	}

	@Override
	void activerCase() {
		if (caseSpecial instanceof CaseBombe caseBombe  ) {
//			caseBombe.activerBombe(controlDe.lancerDe());
//			controlVie.perdrePointsDeVie(CaseBombe.getValue(), joueur);;
			//TODO Completer le fonctions necessaires de Case Bombe
		}
		
	}
	
}
