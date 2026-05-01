package entity;

public class Case {

	private int numero;
	private boolean contientJoueur;
	private final Joueur[] joueursCase;

	public Case(Integer numero) {
		this.numero = numero;
		this.contientJoueur = false;
		this.joueursCase = new Joueur[2];
	}

	public int getNumero() {
		return numero;
	}

	public boolean getContientJoueur() {
		return contientJoueur;
	}

	public boolean getEstCaseSpecial() { // Renvoie true si Bombe/Coco/Mystere false sinon
		return this instanceof CaseBombe || this instanceof CaseCoco || this instanceof CaseMystere;
	}

	public void setJoueur(Integer numJoueur, Joueur joueur) {
		if (numJoueur != 0 && numJoueur != 1) {
			throw new IllegalArgumentException("Le numéro du joueur est pas valide : " + numJoueur);
		}
		joueursCase[numJoueur] = joueur;
		contientJoueur = (joueursCase[0] != null || joueursCase[1] != null);
	}

	public Joueur getJoueur(Integer numJoueur) {
		if (numJoueur != 0 && numJoueur != 1) {
			throw new IllegalArgumentException("Le numéro du joueur est pas valide : " + numJoueur);
		}
		return joueursCase[numJoueur];
	}

}
