package controleur;

import boundary.interfaces.IBoundary;
import boundary.interfaces.IControlJeuPirate;
import boundary.interfaces.IDeplacerPirate;
import entity.Jeu;
import entity.Joueur;
import entity.Plateau;


public class ControlDeplacer implements IDeplacerPirate {
	private static final int CASE_MAX = 29;
    private final Jeu jeu;
    IBoundary boundary;
    private final IControlJeuPirate controlJeuPirate; 
    
    
    public ControlDeplacer(Jeu jeu, IBoundary boundary, IControlJeuPirate controlJeuPirate) {
	    	this.jeu = jeu;
	    	this.boundary = boundary;
	    	this.controlJeuPirate = controlJeuPirate;
    }


    public void deplacerPirate(int somme){
    	
    		Joueur joueur = jeu.getJoueurCourant();
    		Plateau plateau = jeu.getPlateau();
    		int indexJoueur = jeu.getIndiceJoueurCourant();
    		
    		
    		int pos = joueur.getPosition();
    		int nouvellePos = pos + somme;
    		
	    	if (nouvellePos > 29) {
	    		nouvellePos -= (nouvellePos%29);
	    	}
	    	
	    	if (nouvellePos != pos) {
            plateau.getCase(pos).removeJoueur(indexJoueur);
	    }
	    	
        plateau.getCase(nouvellePos).setJoueur(indexJoueur, joueur);
	    
	    	joueur.setPosition(nouvellePos);
	    	
	    	boundary.deplacerPirates(joueur.getNom(), pos, nouvellePos, this);
    }
    
    @Override
    public void finDeplacerPirate(int numCase) {
    	controlJeuPirate.apresDeplacer(numCase); // il me faut la case
    }
}
