package controleur;


import boundary.interfaces.IBoundary;
import boundary.interfaces.IControlJeuPirate;
import entity.*;
import interface_noyau_fonctionnel.INoyauFonctionnel;

public class ControlJeuPirate implements INoyauFonctionnel, IControlJeuPirate{

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
    
    public Jeu getJeu() {
        return this.jeu;
    }

    public IBoundary getBoundary() {
        return this.boundary;
    }
    
    //-----methodes appellees par les autres controleurs-----
	public void jouerUnTour() {
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
	
	public ControlJeuPirate(Jeu jeu, IBoundary boundary) {
        this.jeu = jeu;
        this.boundary = boundary;
    }
	
	// Setter pour injecter les contrôleurs après construction
    public void setControleurs(ControleurDe controleurDe,
                               ControlDeplacer controlDeplacer,
                               ControlVerifierFinPartie controlVerifierFinPartie) {
        this.controleurDe = controleurDe;
        this.controlDeplacer = controlDeplacer;
        this.controlVerifierFinPartie = controlVerifierFinPartie;
    }
    
    // Démarre un tour : lance les dés 
    public void jouerTour() {
        controleurDe.lancerDe();
    }

    // Appelé par ControleurDe quand l'animation des dés est finie
    public void apresLancerDe(int sommeDes) {
        controlDeplacer.deplacerPirate(sommeDes);
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
        boundary.changerJoueurActif(jeu.getJoueurCourant().getNom());
        // Attend le prochain clic de l'utilisateur
    }

}
