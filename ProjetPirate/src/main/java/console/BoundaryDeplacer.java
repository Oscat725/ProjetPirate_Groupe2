package console;

import boundary.interfaces.IBoundary;

public class BoundaryDeplacer implements IBoundary {
	
	public BoundaryDeplacer(IControlDeplacer controlDeplacer) {
		this.controlDeplacer = controlDeplacer;
	}
	
	public int deplacer(int somme, IControlDeplacer controlDeplacer) {
		System.out.println("Le pirate se déplace de "+ somme +" cases.\n");
		
		
		System.out.println("La nouvelle position du joueur est " + newpos + ".\n");
		controlDeplacer.finDeplacerPirate();
		
		return newpos;
	}
	
	
	
	
	
	
}
