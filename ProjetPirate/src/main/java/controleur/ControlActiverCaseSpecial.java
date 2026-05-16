package controleur;

import entity.Case;
import entity.Joueur;

public abstract class ControlActiverCaseSpecial {
	Joueur joueur;
	Case caseSpecial;
	
	public ControlActiverCaseSpecial(Joueur joueur, Case caseSpecial) {
		super();
		this.joueur = joueur;
		this.caseSpecial = caseSpecial;
	}
	
	abstract void activerCase();

}
