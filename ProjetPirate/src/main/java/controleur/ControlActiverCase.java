package controleur;

import entity.Case;

public class ControlActiverCase {
	private Case caseCourante;
	private ControlActiverCaseSpecial controlActiverCaseSpecial;
	
	public Case getCase() {
		return caseCourante;

	}
	
	public boolean isCaseSpecial() {
		return caseCourante.getEstCaseSpecial();
	}
	
	public void activerCase(){
		if (isCaseSpecial()) {
			controlActiverCaseSpecial.activerCase();
		}
	}
	
}
