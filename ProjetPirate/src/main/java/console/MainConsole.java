package console;


import entity.Jeu;
import controleur.ControlCommencerPartie;
import controleur.ControleurDe;
import controleur.ControlDeplacer;
import controleur.ControlVerifierFinPartie;
import controleur.ControlJeuPirate;
import controleur.ControlPointDeVie;

public class MainConsole {
	
	
    public static void main(String[] args) {


        Jeu jeu = new Jeu();
        
        // Créer le contrôleur de début de partie
        ControlCommencerPartie controlCommencer = new ControlCommencerPartie(jeu);
        
        // Créer la boundary console
        BoundaryConsole boundary = new BoundaryConsole(controlCommencer);
        
        // ControlJeuPirate est créé d'abord (il implémente IControlJeuPirate)
        ControlJeuPirate controlJeu = new ControlJeuPirate(jeu, boundary);


        boundary.commencerPartie();
        
        // Créer les autres contrôleurs
        ControlDeplacer controlDeplacer = new ControlDeplacer(jeu, boundary, controlJeu);
        ControleurDe controleurDe = new ControleurDe(jeu, boundary, controlJeu);
        ControlVerifierFinPartie controlVerifier = new ControlVerifierFinPartie(jeu, boundary, controlJeu);
        
        
        
     	// ControlJeuPirate reçoit les contrôleurs après
        controlJeu.setControleurs(controleurDe, controlDeplacer, controlVerifier);
        
        // Lancer la boucle de jeu
        boundary.changerJoueurActif(jeu.getJoueurCourant().getNom());
        controlJeu.jouerTour();
    }

}
