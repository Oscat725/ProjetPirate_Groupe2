package console;

import entity.Jeu;
import controleur.*;

public class MainConsole {
    
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        
        //initialisation temporaire pour lier les objets
        ControlJeuPirate controlJeu = new ControlJeuPirate(null);
        ControlCommencerPartie controlCommencer = new ControlCommencerPartie(jeu, null, controlJeu);
        
        //création de  boundary
        BoundaryConsole boundary = new BoundaryConsole();
        
        
        //instanciation des controleur avec la vraie boundary
        controlJeu = new ControlJeuPirate(boundary);
        ControlDeplacer controlDeplacer = new ControlDeplacer(jeu, boundary, controlJeu);
        ControleurDe controleurDe = new ControleurDe(jeu, boundary, controlJeu);
        ControlVerifierFinPartie controlVerifier = new ControlVerifierFinPartie(jeu.getJoueurs());
        
        // test
        controlJeu.jouer();
    }
}