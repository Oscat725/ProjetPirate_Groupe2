//package console;
//
//import boundary.interfaces.*;
//
//public class BoundaryDeplacer implements IBoundary {
//
//	@Override
//	public void deplacerPirates(String nomPirate, int ancienneCase, int nouvelleCase, IDeplacerPirate callback) {
//		System.out.println(nomPirate + " avance de la case " + (ancienneCase + 1) + " à la case " + (nouvelleCase + 1));
//		callback.finDeplacerPirate();
//	}
//
//	
//	
//	
//	
//	
//	
//	
//	
//	@Override
//	public void affichageResultatDe(int v1, int v2, ILancerDe c) {
//	}
//
//	@Override
//	public void afficherEffetCase(String t, String m, IActiverCase c) {
//	}
//
//	@Override
//	public void afficherPointDeVie(String n, int p, IPointsDeVie c) {
//	}
//
//	@Override
//	public void afficherFinDePartie(String n, IFinDePartie c) {
//	}
//
//	@Override
//	public void afficherMessage(String m) {
//	}
//
//	@Override
//	public void changerJoueurActif(String n) {
//	}
//
//	@Override
//	public void commencerPartie(ICommencerPartie c) {
//	}
//}