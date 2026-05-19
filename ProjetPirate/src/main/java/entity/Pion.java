package entity;

public class Pion  {
	
	private int position = 0;
	private Couleur couleur;
	
	public Pion(int numJoueur) {
		couleur = Couleur.trouverCouleur(numJoueur);
	}
	
	public int getPosition() {
		return position;
	}
	
	public int setPosition(int pos) {
		position = pos;
		return position;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	

}
