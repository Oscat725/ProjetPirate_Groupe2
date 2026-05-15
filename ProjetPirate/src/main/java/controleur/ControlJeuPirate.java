
package controleur;

import entity.Jeu;
import entity.Joueur;
import boundary.interfaces.IBoundary;


//public class ControlJeuPirate {
	//private Joueur joueur;
	//private ControlDeplacer controlDeplacer;
	//private ControlVerifierFinPartie controlVerifierFinPartie;
//	private ControlActiverCase controlActiverCase;

	//public ControlJeuPirate(Joueur joueur, ControlDeplacer controlDeplacer,
			//ControlVerifierFinPartie controlVerifierFinPartie, ControlActiverCase controlActiverCase) {
		//this.joueur = joueur;
		//this.controlDeplacer = controlDeplacer;
		//this.controlVerifierFinPartie = controlVerifierFinPartie;
//		this.controlActiverCase = controlActiverCase;
	
	//}
//}



//Code d'inspiration, pour avoir une idée générale
public class ControlJeuPirate implements IControlJeuPirate{

    private final Jeu jeu;
    private final IBoundary boundary;
    private ControleurDe controleurDe;
    private ControlDeplacer controlDeplacer;
    private ControlVerifierFinPartie controlVerifierFinPartie;
    // + les contrôleurs de cases spéciales

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
