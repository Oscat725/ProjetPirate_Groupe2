package frontiere;

import java.util.Scanner;
import controleur.ControlCommencerPartie;

public class BoundaryCommencerPartie {

    private ControlCommencerPartie controlCommencerPartie;
    private Scanner scanner = new Scanner(System.in);

    public BoundaryCommencerPartie(ControlCommencerPartie controlCommencerPartie) {
        this.controlCommencerPartie = controlCommencerPartie;
    }

    public void commencerPartie() {
        System.out.println("=== Isla de la Muerte ===\n");

        System.out.print("Nom du joueur 1 (Rouge) : ");
        String nom1 = scanner.nextLine();

        System.out.print("Nom du joueur 2 (Bleu)  : ");
        String nom2 = scanner.nextLine();

        controlCommencerPartie.initialiserJoueurs(nom1, nom2);

        System.out.println("\nLancer des dés pour déterminer qui commence...");
        String premier = controlCommencerPartie.determinerJoueurQuiCommence();
        System.out.println("\n" + premier + " commence la partie !\n");
    }
}
