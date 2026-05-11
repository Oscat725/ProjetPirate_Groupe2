package entity;

public class Joueur {

	public static final Integer PV_MAX = 5;

	private final String nom;
	private int pointDeVie;
	private final Pion pion;

	public Joueur(String nom, Pion pion) {
		this.nom = nom;
		this.pointDeVie = PV_MAX;
		this.pion = pion;
	}

	public Integer getPointDeVie() {
		return pointDeVie;
	}

	public String getNom() {
		return nom;
	}
    
    public Pion getPion() {
        return pion;
    }

}
