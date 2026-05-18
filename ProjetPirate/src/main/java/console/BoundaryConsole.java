package console;


import java.util.Scanner;
import boundary.interfaces.IBoundary;
import boundary.interfaces.ICommencerPartie;
import boundary.interfaces.IActiverCase;
import boundary.interfaces.IDeplacerPirate;
import boundary.interfaces.IFinDePartie;
import boundary.interfaces.ILancerDe;
import boundary.interfaces.IPointsDeVie;
import controleur.ControlCommencerPartie;

public class BoundaryConsole implements IBoundary {

    private final Scanner scanner;
    private final ControlCommencerPartie controlCommencerPartie;
    
    public BoundaryConsole(ControlCommencerPartie controlCommencerPartie) {
        this.scanner = new Scanner(System.in);
        this.controlCommencerPartie = controlCommencerPartie;
    }
    
    // Initialisation de la partie
    public void commencerPartie(ICommencerPartie callback) {
    	
        System.out.println("\n     ISLA DE LA MUERTE - Jeu des Pirates\n\n");
        
        System.out.print("Nom du joueur 1 (Rouge) : ");
        String nom1 = scanner.nextLine();
        System.out.print("Nom du joueur 2 (Bleu)  : ");
        String nom2 = scanner.nextLine();
        
        controlCommencerPartie.initialiserJoueurs(nom1, nom2);
        
        System.out.println("\nLancer des dés pour déterminer qui commence ...");
        String premier = controlCommencerPartie.determinerQuiCommence();
        System.out.println(premier + " commence la partie !\n");
        
        controlCommencerPartie.finCommencerPartie(); // Notifie la fin de l'action
        
    }
    
    
    @Override
    public void affichageResultatDe(int valeurDe1, int valeurDe2, ILancerDe callback) {
    	
        System.out.println("--- Lancer de dés ---");
        
        System.out.println("Dé 1 : " + valeurDe1);
        if (valeurDe2 != 0)
        		System.out.println("Dé 2 : " + valeurDe2);
        else
        		System.out.println("Le joueur est affecté par le noix de coco donc : deux dé invalide X ");
        
        
        System.out.println("\nSomme Total : " + (valeurDe1 + valeurDe2) + "\n");
        callback.finLancerDe();
    }
    
    
    @Override
    public void afficherEffetCase(String typeCase, String message, IActiverCase callback) {
        System.out.println("Vous êtes tombés sur une case spéciale : <" + typeCase + ">");
        System.out.println(message + "\n"); // Le message represente les conséquences de la case spéciales
        callback.finActiverCase();
    }

    @Override
    public void afficherPointDeVie(String nomPirate, int pv, IPointsDeVie callback) {
        System.out.println(" - PV de " + nomPirate + ": "+ pv + "pts/" + 5);
        callback.finAfficherPV();
    }
    
    @Override
    public void afficherFinDePartie(String nomGagnant, IFinDePartie callback) {
        System.out.println("\n\n--------------------FIN DE LA PARTIE !--------------------");
        System.out.println("         Le gagnant est :" + nomGagnant);
        callback.finPartie();
    }
    
    @Override
    public void deplacerPirates(String nomPirate, int ancienneCase, int nouvelleCase, IDeplacerPirate callback) {
        System.out.println(nomPirate + " avance de la case " + (ancienneCase + 1)
                         + " à la case " + (nouvelleCase + 1));
        callback.finDeplacerPirate();
    }
    
    //Methode IBoundary sans callback
    
    @Override
    public void afficherMessage(String message) {
        System.out.println(message);
    }
    
    @Override
    public void changerJoueurActif(String nomPirate) {
        System.out.println("\n   ->  Au tour de :" + nomPirate + "\n");
        System.out.print("Appuyez sur Entrée pour lancer les dés ...");
        scanner.nextLine();
    }

    
    
    
    
}