package controleur;

import boundary.interfaces.IActiverCase;
import boundary.interfaces.IBoundary;
import entity.Case;
import entity.Joueur;
import entity.CaseCoco;

public class ControlActiverCaseCoco extends ControlActiverCaseSpecial implements IActiverCase {

	private final ControlPointDeVie controlVie;
	private final IBoundary iBoundary;
	private int derniersDegats;
	private int joueurCourantIndex;

	public ControlActiverCaseCoco(Joueur[] joueurs, Case caseSpecial, ControlPointDeVie controlVie,
			ControlCacherDe controlDe, IBoundary iBoundary) {
		super(joueurs);
		this.controlVie = controlVie;
		this.iBoundary = iBoundary;
	}

	@Override
	public void activerCase(Case caseCourant, int joueurCourant) {

		this.joueurCourantIndex = joueurCourant;
		derniersDegats = ((CaseCoco) caseCourant).getDegats();
		joueurs[joueurCourantIndex].setHasCoco(true);
		iBoundary.afficherEffetCase("CHUTE DE NOIX DE COCO", "Une noix de coco tombe : -" + derniersDegats + " PV",
				this,0,derniersDegats,joueurCourant);
	}

	@Override
	public void finActiverCase() {
		controlVie.perdrePointsDeVie(derniersDegats, joueurs[joueurCourantIndex]);
	}

	public int getDerniersDegats() {
		return derniersDegats;
	}
}
