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

    }

    public Jeu getJeu() {
        return this.jeu;
    }

    public IBoundary getBoundary() {
        return this.iBoundary;
    }
    
    //-----methodes appellees par les autres controleurs-----
	public void jouerUnTour() {
		// TODO Auto-generated method stub
		
	}
	
    public void apresLancerDe(int sommeDes) {
        controlDeplacer.deplacerPirate(sommeDes);
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
	
	
    
    // Démarre un tour : lance les dés 
    public void jouerTour() {
        controleurDe.lancerDe();
    }


    // Appelé par ControlDeplacer quand l'animation du déplacement est finie
    public void apresDeplacer() {
        // Vérifier le type de case et activer si spéciale
        // ... puis appeler controlVerifierFinPartie
        controlVerifierFinPartie.verifierFinPartie(); //-> Juste pour tester la console mais n'est pas encore fonctionnel
    }

    //
    public void finDeTour() {
        jeu.passerAuJoueurSuivant();
        iBoundary.changerJoueurActif(jeu.getJoueurCourant().getNom());
        // Attend le prochain clic de l'utilisateur
    }

}
