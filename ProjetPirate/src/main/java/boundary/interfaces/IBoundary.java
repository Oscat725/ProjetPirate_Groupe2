package boundary.interfaces;

public interface IBoundary {
	
	void affichageResultatDe(int nbrDe, int valeurDe, ILancerDe ilancerDe);
	void deplacerPirates(String nomPirate, int Cases, IDeplacerPirate iDeplacerPirate);
	void afficherPointDeVie(String nomPirate, int pv, IPointDeVie iPointDeVie);
	void afficherFinDePartie(String nomGagnant, IFinDePartie iFinDePartie);
	
}
