package boundary.interfaces;

public interface IBoundary {
	//Doit prendre deux valeur de deux separemment pour qu'on affiche les valeurs de dé séparemment
	void affichageResultatDe(int valeurDe1, int valeurDe2, ILancerDe callback);
	void deplacerPirates(String nomPirate, int ancienneCase, int nouvelleCase, IDeplacerPirate callback);
	void afficherEffetCase(String typeCase, String message);
	void afficherPointDeVie(String nomPirate, int pv, IPointsDeVie callback);
	void afficherFinDePartie(String nomGagnant, IFinDePartie callback);
	
	 // Affichages simples sans callback d'interface (pas besoin d'attendre)
    void afficherMessage(String message);                // pour les cases spéciales
    void changerJoueurActif(String nomPirate);		// indiquer à qui est le tour
    void commencerPartie(ICommencerPartie callback); 
    
	
}
