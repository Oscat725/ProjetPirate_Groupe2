package controleur;

import boundary.interfaces.IBoundary;
import boundary.interfaces.IDeplacerPirate;
import entity.Jeu;
import entity.Joueur;
import entity.Plateau;


public class ControlDeplacer implements IDeplacerPirate {
	
	private static final int CASE_MAX = 29;
    private final Jeu jeu;
    IBoundary boundary;
    
    
    public ControlDeplacer(Jeu jeu, IBoundary boundary, ControlActiverCase controlActiverCase) {
	    	this.jeu = jeu;
	    	this.boundary = boundary;
    }
    
    @Override
    public int deplacerPirate(int somme){
    		Joueur joueur = jeu.getJoueurCourant();
    		Plateau plateau = jeu.getPlateau();
    		int pos = j.getPosition();
    		int indexJoueur = jeu.getIndiceJoueurCourant();
    		
    		int nouvellePos = pos + somme;
	    	if (nouvellePos > 29) {
	    		nouvellePos -= (pos%30);
	    	}
	    	
	    	if (nouvellePos != pos) {
	            plateau.getCase(pos).removeJoueur(indexJoueur);
	            plateau.getCase(nouvellePos).setJoueur(indexJoueur, joueur);
	        }
	            boundary.deplacerPirates(j.getNom(), nouvellePos, this);
	    	
	    	j.setPosition(pos);
	    	
    		
    		
        return jeu;
    }
    
    @Override
    public void finDeplacerPirate() {
        controlActiverCase.activer();
    }
}
