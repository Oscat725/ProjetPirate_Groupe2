package boundary.interfaces;

public interface IBoundary {
    void affichageResultatDe(int valeurDe1, int valeurDe2, ILancerDe callback);
    void deplacerPirates(String nomPirate, int ancienneCase, int nouvelleCase, IDeplacerPirate callback);
    void afficherEffetCase(String typeCase, String message, IActiverCase callback, int effect, int value);
    void afficherPointDeVie(String nomPirate, int pv, IPointsDeVie callback);
    void afficherFinDePartie(String nomGagnant, IFinDePartie callback);
    void afficherMessage(String message);                
    void changerJoueurActif(String nomPirate, IControlJeuPirate callback);		
    void commencerPartie(ICommencerPartie callback); 
    void demanderUtilisationCoco(IControlCacherDe callback, int joueurCourant);
}
