package controleur;

import entity.Case;

public class ControlActiverCase {
	private ControlActiverCaseSpecial[] controlCasesSpeciales;

	public ControlActiverCase(ControlActiverCaseSpecial[] controlCasesSpeciales) {
		super();
		this.controlCasesSpeciales = controlCasesSpeciales;
	}

	public ControlActiverCase(ControlActiverCaseBombe controlCaseBombe, ControlActiverCaseCoco controlCaseCoco,
			ControlActiverCaseMystere controlCaseMystere) {
		this.controlCasesSpeciales = new ControlActiverCaseSpecial[3];
		controlCasesSpeciales[0] = controlCaseBombe;
		controlCasesSpeciales[1] = controlCaseCoco;
		controlCasesSpeciales[2] = controlCaseMystere;

	}

	public boolean isCaseSpecial(Case caseCourante) {
		return caseCourante.getEstCaseSpecial();
	}

	public void activerCase(Case caseCourante, int joueurCourant) {
		if (isCaseSpecial(caseCourante)) {
			if (caseCourante.isCaseBombe()) {
				controlCasesSpeciales[0].activerCase(caseCourante, joueurCourant);
			}
			if (caseCourante.isCaseCoco()) {
				controlCasesSpeciales[1].activerCase(caseCourante, joueurCourant);
			}
			if (caseCourante.isCaseMystere()) {
				controlCasesSpeciales[2].activerCase(caseCourante, joueurCourant);
			}
		}
	}
}
