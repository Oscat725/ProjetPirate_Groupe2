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
	public void activerCase(Case caseBombe,int joueurCourant) {
		if (caseBombe instanceof CaseBombe caseB) {
			// La bombe fait entre 1 et 5 de dégâts ( modif 0)
			int valeur = controlDe.lancerDesModif(1, 1, 5); 
			caseB.activerBombe(valeur);
			
			//affichage de l'effet 
			iBoundary.afficherEffetCase("Bombe", "Le pirate subit "+ valeur + " degats");			
			// On utilise la "valeur" directement 
			controlVie.perdrePointsDeVie(valeur, joueurs[joueurCourant]);
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
