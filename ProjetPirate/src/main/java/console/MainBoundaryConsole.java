package console;

import controleur.*;

public class MainBoundaryConsole {
	public static void main(String[] args) {
            BoundaryConsole boundary = new BoundaryConsole();
	  
            ControlJeuPirate controlJeu = new ControlJeuPirate(boundary);
	
            controlJeu.jouer();
	}
}
