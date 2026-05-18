package controleur;

import entity.Case;
import entity.Joueur;

public abstract class ControlActiverCaseSpecial {
	Joueur[] joueurs;
	Case caseSpecial;
	
	protected ControlActiverCaseSpecial(Joueur[] joueurs, Case caseSpecial) {
		super();
		this.joueurs = joueurs;
		this.caseSpecial = caseSpecial;
	}
	
	abstract void activerCase();

}
