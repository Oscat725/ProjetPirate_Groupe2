package controleur;

import boundary.interfaces.IBoundary;
import boundary.interfaces.IControlJeuPirate;
import console.BoundaryConsole;
import entity.*;
import interface_noyau_fonctionnel.INoyauFonctionnel;

public class ControlJeuPirate implements INoyauFonctionnel, IControlJeuPirate {

    private Jeu jeu;
    private Joueur[] joueurs;

    private IBoundary iBoundary;

    // Sous-controleurs
    private ControlCommencerPartie controlCommencerPartie;
    private ControleurDe controleurDe;
    private ControlDeplacer controlDeplacer;
    private ControlPointDeVie controlPointDeVie;
    private ControlVerifierFinPartie controlVerifierFinPartie;
    private ControlActiverCase controlActiverCase;
    private ControlPirateCourant controlPirateCourant;

    public ControlJeuPirate(IBoundary iBoundary) {

        this.iBoundary = iBoundary;

        this.jeu = new Jeu();
        this.joueurs = jeu.getJoueurs();

        this.controlCommencerPartie =
            new ControlCommencerPartie(jeu, iBoundary, this);

        this.controleurDe =
            new ControleurDe(jeu, iBoundary, this);

        this.controlPointDeVie =
            new ControlPointDeVie(jeu, iBoundary, this);

        this.controlVerifierFinPartie =
            new ControlVerifierFinPartie(joueurs);

        this.controlDeplacer =
            new ControlDeplacer(jeu, iBoundary, this);
        
        this.controlActiverCase = 
        	new ControlActiverCase();
        
        this.controlPirateCourant =
        	new ControlPirateCourant(jeu.getJoueur(0), jeu.getJoueur(1));

    }

    public Jeu getJeu() {
        return this.jeu;
    }

    public IBoundary getBoundary() {
        return this.iBoundary;
    }
    
    //-----methodes appellees par les autres controleurs-----
	
    public void apresLancerDe(int sommeDes) {
        controlDeplacer.deplacerPirate(sommeDes);
    }
    
    public void jouerUnTour() {
        controleurDe.lancerDe();
    }
    
    public void apresDeplacer(int numCase) {
        //controlActiverCase.activerCase(jeu.getJoueurCourant(),jeu.getPlateau().getCase(numCase)); //A decommenter
    }
    
    public void apresActiverCase() {
    	controlPointDeVie.calculerPV();
    }
    
    public void apresAfficherPV() {
    	controlPirateCourant.changerJoueur();
    	if(controlVerifierFinPartie.verifierFinPartie()) {
    		System.out.println("Fin Partie"); //a modifier ulterieurement
    	}else {
    		jouerUnTour();
    	}
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
	
	// -----main pour tester le deroulement d'une partie-----
	public static void main(String[] args) {
		IBoundary boundary = new BoundaryConsole(); 

	    ControlJeuPirate controleur =
	            new ControlJeuPirate(boundary);
	    
		controleur.controlCommencerPartie.commencerPartie();
		
	}
	



    //????
    public void finDeTour() {
        jeu.passerAuJoueurSuivant();
        iBoundary.changerJoueurActif(jeu.getJoueurCourant().getNom());
        // Attend le prochain clic de l'utilisateur
    }

}
