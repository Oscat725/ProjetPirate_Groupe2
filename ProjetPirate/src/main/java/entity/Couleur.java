package entity;

public enum Couleur {
	
	ROUGE(0), 
	BLEU(1);
	
	private final int numJ;
	
	Couleur(int numJ) {
		this.numJ = numJ;
	}
	
	public Couleur trouverCouleur(int numJ) { 
		for (Couleur couleur : Couleur.values( )) {
			if (couleur.numJ == numJ) {
				return couleur;
			} 
		}
		throw new IllegalArgumentException("Le numéro du joueur est pas valide pour la couleur : " + numJ);
		
	}

}
