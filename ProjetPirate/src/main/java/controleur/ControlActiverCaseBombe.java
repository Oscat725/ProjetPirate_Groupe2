package controleur;

import java.lang.instrument.IllegalClassFormatException;

import boundary.interfaces.IBoundary;
import boundary.interfaces.ICaseBombe;
import entity.Case;
import entity.CaseBombe;
import entity.Joueur;


public class ControlActiverCaseBombe extends ControlActiverCaseSpecial implements ICaseBombe{
	private ControlJeuPirate controlJeuPirate;
	private ControlPointDeVie controlVie;
	private ControleurDe controlDe;
	private IBoundary iBoundary;



	public ControlActiverCaseBombe(Joueur joueur, Case caseSpecial,ControleurDe controleurDe,ControlPointDeVie controlVie, ControlJeuPirate controlJeuPirate, IBoundary iBoundary) {
		super(joueur, caseSpecial);
		this.controlDe = controleurDe;
		this.controlVie = controlVie;
		this.controlJeuPirate = controlJeuPirate;
		this.iBoundary = iBoundary;
	}

	@Override
	void activerCase() {
		if (caseSpecial instanceof CaseBombe caseBombe  ) {
//			caseBombe.activerBombe(controlDe.lancerDe());
//			controlVie.perdrePointsDeVie(CaseBombe.getValue(), joueur);;
			//TODO Completer le fonctions necessaires de Case Bombe
		}
		
	}

	@Override
	public void afficherBombe() {
		activerCase(); //pourait renvoyer degats
		//iBoundary.degatsBombe(degats, this);
		
	}

	@Override
	public void finCaseBombe() {
		//controlJeuPirate.finCaseBombe();
		
	}
	
	
}
