package controleur;
import entity.Joueur;
import boundary.interfaces.IBoundary;
import boundary.interfaces.IDeplacerPirate;
import console.BoundaryDeplacer;

public class ControlDeplacer implements IDeplacerPirate, ILancerDe{
	//ControlJeu
	IBoundary boundaryDeplacer;
	
    private Joueur j;
    
    public ControlDeplacer(Joueur j, IBoundary boundaryDeplacer) {
    	this.j = j;
    	this.boundaryDeplacer = boundaryDeplacer;
    }
    
    @Override
    public void deplacerPirate(int somme){
    	int newpos = j.deplacer(somme);
    	boundaryDeplacer.deplacer(newpos, this);
    }
    
    @Override
    public void finDeplacerPirate(int newPos) {
    	//Appel controlJeu
    }
    
    @Override
    void afficherDe(int resultat) {
    	
    }
    
    @Override
    void finLancerDe() {
    	
    }

	@Override
	public int[] lancerDe() {
		// TODO Auto-generated method stub
		return null;
	}
}
