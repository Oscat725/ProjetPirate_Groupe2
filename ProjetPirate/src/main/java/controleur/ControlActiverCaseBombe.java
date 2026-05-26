package controleur;

import boundary.interfaces.IActiverCase;
import boundary.interfaces.IBoundary;
import entity.Case;
import entity.CaseBombe;
import entity.Joueur;

public class ControlActiverCaseBombe extends ControlActiverCaseSpecial implements IActiverCase {

	private final ControlPointDeVie controlVie;
	private final ControleurDe controlDe;
	private final IBoundary iBoundary;
	private int derniersDegats;
	private int joueurCourantIndex;

	public ControlActiverCaseBombe(Joueur[] joueurs, ControleurDe controleurDe, ControlPointDeVie controlVie,
			IBoundary iBoundary) {
		super(joueurs);
		this.controlDe = controleurDe;
		this.controlVie = controlVie;
		this.iBoundary = iBoundary;
	}

	@Override
	void activerCase(Case caseBombe, int joueurCourant) {
		if (caseBombe instanceof CaseBombe) {
			this.joueurCourantIndex = joueurCourant;
			derniersDegats = controlDe.lancerDesModif(1, 1, 5);
			iBoundary.afficherEffetCase("BOMBE", "Le pirate subit " + derniersDegats + " degats", this,0,derniersDegats);
		}
	}

	@Override
	public void finActiverCase() {
		controlVie.perdrePointsDeVie(derniersDegats, joueurs[joueurCourantIndex]);
	}

}
