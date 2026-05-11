package entity;

public class Plateau {

	public static final Integer NB_CASES = 30;
	private final Case[] cases;

	public Plateau() {
		this.cases = new Case[NB_CASES];
		for (int i = 0; i < NB_CASES; i++) {
			cases[i] = new Case(i);
		}
	}

	public Case getCase(Integer indice) {
		if (indice < 0 || indice >= NB_CASES) {
			throw new IndexOutOfBoundsException("Indice de case invalide : " + indice);
		}
		return cases[indice];
	}
}