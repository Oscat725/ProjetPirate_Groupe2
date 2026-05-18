package controleur;
import entity.Joueur;

public class ControlDeplacer {
	
    private Joueur[] j;
    
    public ControlDeplacer(Joueur[] j) {
    	this.j = j;
    }
    
    public int deplacer(int nbDe, int numJoueur){
        return j[numJoueur].deplacer(nbDe);
    }
}
