package console;

import entity.Jeu;
import controleur.*;

public class MainConsole {
    
    public static void main(String[] args) {
        BoundaryConsole boundary = new BoundaryConsole();
        
        ControlJeuPirate controlJeu = new ControlJeuPirate(boundary);
 
        controlJeu.jouer();
    }
}