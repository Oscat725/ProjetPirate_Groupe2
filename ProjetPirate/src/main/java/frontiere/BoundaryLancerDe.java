package frontiere;

import java.util.Scanner;
import controleur.ControleurDe;

// Nolawi
public class BoundaryLancerDe {

    private ControleurDe controleurDe;
    private Scanner scanner = new Scanner(System.in);

    public BoundaryLancerDe(ControleurDe controleurDe) {
        this.controleurDe = controleurDe;
    }

    public int lancerDes() {
        System.out.print("Appuyez sur Entrée pour lancer les dés...");
        scanner.nextLine();

        int[] resultats = controleurDe.lancerDe();
        int somme = resultats[0] + resultats[1];

        System.out.println("Dé 1 : " + resultats[0]
                + " | Dé 2 : " + resultats[1]
                + " | Total : " + somme);

        return somme;
    }
}
