package controleur;


import boundary.interfaces.IBoundary;
import entity.*;
import interface_noyau_fonctionnel.INoyauFonctionnel;

public class ControlJeuPirate implements INoyauFonctionnel{

    private Jeu jeu;
    private IBoundary boundary;

    // Sous-contrôleurs
    private ControlCommencerPartie controlCommencerPartie;
    private ControleurDe controleurDe;
    private ControlDeplacer controlDeplacer;
    private ControlPointDeVie controlPointDeVie;
    private ControlVerifierFinPartie controlVerifierFinPartie;
    private ControlActiverCaseBombe controlActiverBombe;
    private ControlActiverCaseCoco controlActiverCaseCoco;
    private ControlActiverCaseMystere controlActiverMystere;

    public ControlJeuPirate(IBoundary boundary) {
        this.jeu = new Jeu();
        this.boundary = boundary;

    }

	@Override
	public void jouer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void soumettreNoms(String nomJ1, String nomJ2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmationCommencer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmationTourVu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmationDes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmationDeplacement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmationCaseSpeciale() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmationPV() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void confirmationFinPartie() {
		// TODO Auto-generated method stub
		
	}

}
