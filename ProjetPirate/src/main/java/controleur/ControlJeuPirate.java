package controleur;


import boundary.interfaces.IBoundary;
import entity.*;
import interface_noyau_fonctionnel.INoyauFonctionnel;

public class ControlJeuPirate implements INoyauFonctionnel{

	private Jeu jeu = new Jeu();
	private Joueur[] joueurs = jeu.getJoueurs();
	
    private IBoundary boundary;

    // Sous-controleurs
    private ControlCommencerPartie controlCommencerPartie = new ControlCommencerPartie(jeu, boundary, this);
    private ControleurDe controleurDe = new ControleurDe(jeu, boundary, this);
    private ControlDeplacer controlDeplacer = new ControlDeplacer(joueurs);
    private ControlPointDeVie controlPointDeVie = new ControlPointDeVie(jeu, boundary, this);
    private ControlVerifierFinPartie controlVerifierFinPartie = new ControlVerifierFinPartie(joueurs);
    private ControlActiverCaseBombe controlActiverBombe = new ControlActiverCaseBombe(null, null, controleurDe, controlPointDeVie, null, boundary);
    private ControlActiverCaseCoco controlActiverCaseCoco = new ControlActiverCaseCoco(null, null, controlPointDeVie, null);
    private ControlActiverCaseMystere controlActiverMystere = new ControlActiverCaseMystere(null, null, controlPointDeVie, null);

    public ControlJeuPirate(IBoundary boundary) {
        this.boundary = boundary;

    }
    //-----methodes appellees par les autres controleurs-----
	public void jouerUnTour() {
		// TODO Auto-generated method stub
		
	}
	
	public void apresLancerDe(int i) {
		// TODO Auto-generated method stub
		
	}

	//-----methodes a implementer pour INoyau-----
	
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
	
	public static void main(String[] args) {
		
	}

}
