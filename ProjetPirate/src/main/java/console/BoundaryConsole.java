package console;


import java.util.Scanner;
import boundary.interfaces.*;
import boundary.interfaces.ICommencerPartie;
import boundary.interfaces.IActiverCase;
import boundary.interfaces.IDeplacerPirate;
import boundary.interfaces.IFinDePartie;
import boundary.interfaces.ILancerDe;
import boundary.interfaces.IPointsDeVie;

public class BoundaryConsole implements IBoundary {

    private final Scanner scanner;
    
    public BoundaryConsole() {
        this.scanner = new Scanner(System.in);
    }
    
    // Initialisation de la partie
    public void commencerPartie(ICommencerPartie controlCommencerPartie) {
    	
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
        		System.out.println("Vous etes affecté par le noix de coco de l'adversaire donc : Un dé invalide ");
        
        
        System.out.println("\nSomme Total : " + (valeurDe1 + valeurDe2) + "\n");
        callback.finLancerDe();
    }
    
    
    @Override
    public void afficherEffetCase(String typeCase, String message, IActiverCase callback) {
        System.out.println("Vous êtes tombés sur une case spéciale : <" + typeCase + ">");
        System.out.println(message + "\n");
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
        System.out.println("         Le gagnant est : " + nomGagnant);
        
        // On vérifie que callback n'est pas null avant de l'appeler
        if (callback != null) {
            callback.finPartie();
        } else {
            // Si on est en console et qu'on n'a pas de callback, on peut juste quitter
            System.exit(0); 
        }
    }
    
    @Override
    public void deplacerPirates(String nomPirate, int ancienneCase, int nouvelleCase, IDeplacerPirate callback) {
        System.out.println(nomPirate + " se deplace de la case " + (ancienneCase + 1)
                         + " à la case " + (nouvelleCase + 1));
        callback.finDeplacerPirate(nouvelleCase);
    }
    
    @Override
    public void changerJoueurActif(String nomPirate, IControlJeuPirate callback) {
        System.out.println("\n   ->  Au tour de :" + nomPirate + "\n");
        System.out.print("Appuyez sur Entrée pour lancer les dés ...");
        scanner.nextLine();
        callback.finAfficherTour();
    }

    public void demanderUtilisationCoco(IControlCacherDe callback) {
        System.out.print("Voulez-vous utiliser votre noix de coco pour ce tour ? (oui/non) : ");
        String reponse = scanner.nextLine().trim().toLowerCase();
        if (reponse.equals("oui") || reponse.equals("o")) {
            System.out.println("Vous avez choisi d'utiliser la noix de coco, l'adversaire lancera un seul dé le tour prochain.\n");
            callback.finDemandeCoco("oui");
        } else {
            System.out.println("Vous avez choisi de ne pas utiliser la noix de coco.\n");
            callback.finDemandeCoco("non"); // On appelle quand même le callback pour continuer le jeu
        }
    }

    //Methode IBoundary sans callback
    
    @Override
    public void afficherMessage(String message) {
        System.out.println(message);
    }
    

    
    
    
    
}