package controleur;

import boundary.interfaces.*;

public class BoundaryTest implements IBoundary {

    @Override
    public void commencerPartie(ICommencerPartie c) {
        c.initialiserJoueurs("Pirate1", "Pirate2");
        c.determinerQuiCommence();
        c.finCommencerPartie();
    }

    @Override
    public void changerJoueurActif(String nom, IControlJeuPirate c) {
        // ne rien faire pas de scanner.nextLine() qui bloque
    }

    @Override
    public void afficherPointDeVie(String nom, int pv, IPointsDeVie c) {
        // ne rien faire evite la chaîne apresAfficherPV() → finDeTour()
    }

    @Override
    public void affichageResultatDe(int d1, int d2, ILancerDe c) {}

    @Override
    public void afficherEffetCase(String type, String msg, IActiverCase c) {
        c.finActiverCase(); 
    }

    @Override
    public void afficherFinDePartie(String gagnant, IFinDePartie c) {}

    @Override
    public void deplacerPirates(String nom, int ancien, int nouveau, IDeplacerPirate c) {}

    @Override
    public void demanderUtilisationCoco(IControlCacherDe c) {}

    @Override
    public void afficherMessage(String msg) {}
    
    
}
