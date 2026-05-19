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
    
    // On déclare juste les variables ici, sans les initialiser
    private ControlCacherDe controlCacherDe; 
    private ControlActiverCaseBombe controlBombe;
    private ControlActiverCaseCoco controlCoco;
    private ControlActiverCaseMystere controlMystere;

    public ControlJeuPirate(IBoundary iBoundary) {

        this.iBoundary = iBoundary;

        this.jeu = new Jeu();
        this.joueurs = jeu.getJoueurs();

        this.controlCommencerPartie = new ControlCommencerPartie(jeu, iBoundary, this);
        this.controleurDe = new ControleurDe(jeu, iBoundary, this);
        this.controlPointDeVie = new ControlPointDeVie(jeu, iBoundary, this);
        this.controlVerifierFinPartie = new ControlVerifierFinPartie(joueurs);
        this.controlDeplacer = new ControlDeplacer(jeu, iBoundary, this);
        this.controlPirateCourant = new ControlPirateCourant(this.jeu);

        this.controlCacherDe = new ControlCacherDe(null); 
        this.controlBombe = new ControlActiverCaseBombe(joueurs, controleurDe, controlPointDeVie, this, iBoundary);
        this.controlCoco = new ControlActiverCaseCoco(joueurs, null, controlPointDeVie, controlCacherDe, iBoundary);
        this.controlMystere = new ControlActiverCaseMystere(joueurs, 0, controlPointDeVie, this, iBoundary);

        this.controlActiverCase = new ControlActiverCase(controlBombe, controlCoco, controlMystere);
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
        controlActiverCase.activerCase(jeu.getPlateau().getCase(numCase), jeu.getIndiceJoueurCourant());
        
        apresActiverCase();
    }
    
    public void apresActiverCase() {
        controlPointDeVie.calculerPV();
    }
    
    public void apresAfficherPV() {
       //On vérifie si la partie est terminée (victoire ou mort)
        if(controlVerifierFinPartie.verifierFinPartie()) {
            
            // On détermine qui a gagné :
            // Si le joueur courant a atteint la case 30 ou si l'autre est mort, c'est lui le gagnant.
            String gagnant = jeu.getJoueurCourant().getNom();
            
            // Si le joueur courant vient de mourir (ex: case bombe), l'autre gagne.
            if(jeu.getJoueurCourant().getPointDeVie() <= 0) {
                // Si courant = joueur 0, l'autre est joueur 1 (et inversement)
                int autreJoueurIndex = (jeu.getIndiceJoueurCourant() == 0) ? 1 : 0;
                gagnant = jeu.getJoueur(autreJoueurIndex).getNom();
            }
            
            iBoundary.afficherFinDePartie(gagnant, null); // Fin de la boucle
            
        } else {
            // La partie continue : on change de joueur et on relance le tour
            finDeTour(); 
        }
    }
    
	//-----methodes a implementer pour INoyau-----
	
    @Override
    public void jouer() {
        controlCommencerPartie.commencerPartie();
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
        controlPirateCourant.changerJoueur();
        iBoundary.changerJoueurActif(jeu.getJoueurCourant().getNom());
        jouerUnTour(); // Démarre le tour du nouveau joueur
    }

}
