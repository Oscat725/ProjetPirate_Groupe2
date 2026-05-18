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



	public ControlActiverCaseBombe(Joueur[] joueurs,ControleurDe controleurDe,ControlPointDeVie controlVie, ControlJeuPirate controlJeuPirate, IBoundary iBoundary) {
		super(joueurs);
		this.controlDe = controleurDe;
		this.controlVie = controlVie;
		this.controlJeuPirate = controlJeuPirate;
		this.iBoundary = iBoundary;
	}

	@Override
	void activerCase(Case caseBombe,int joueurCourant) {
		if (caseBombe instanceof CaseBombe caseB) {
			int valeur = controlDe.lancerDesModif(1, 5, 0);
			caseB.activerBombe(valeur);
			controlVie.perdrePointsDeVie(CaseBombe.getValue(), joueurs[joueurCourant]);
		}
			
		
		finCaseBombe();
	}

	@Override
	public void afficherBombe() {
//		activerCase(int joueurCourant); //pourait renvoyer degats
		//iBoundary.degatsBombe(degats, this);
		
	}

	@Override
	public void finCaseBombe() {
		//controlJeuPirate.finCaseBombe();
		
	}
	
	
}
