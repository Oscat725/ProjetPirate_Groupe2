package boundary.interfaces;

import controleur.ILancerDe;

public interface IBoundary {
	
	void affichageResultatDe(int nbrDe, int valeurDe, ILancerDe ilancerDe);
	void deplacerPirates(String nomPirate, int Cases, IDeplacerPirate iDeplacerPirate);
	void afficherEffetCase(String typeCase, String message, IActiverCase callback);
	void afficherPointDeVie(String nomPirate, int pv, IPointsDeVie iPointDeVie);
	void afficherFinDePartie(String nomGagnant, IFinDePartie iFinDePartie);
	
	 // Affichages simples sans callback (pas besoin d'attendre)
    void afficherMessage(String message);                // pour les cases spéciales
    void changerJoueurActif(String nomPirate);		// indiquer à qui c'est le tour
	
}
