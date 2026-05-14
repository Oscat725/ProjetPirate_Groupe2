
public class MainConsole {

	public static void main(String[] args) {
	    Jeu jeu = new Jeu();
	    // Créer les contrôleurs...
	    BoundaryConsole boundary = new BoundaryConsole(adaptateur);
	    boundary.lancerPartie(); 
	    // boucle synchrone Scanner
	}
}
