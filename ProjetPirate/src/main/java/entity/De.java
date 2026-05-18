package entity;

import java.util.Random;

public class De {
	
	private static final Integer MIN = 1;
    private static final Integer MAX = 6;
	private int valeur;
	private final Random random;
	
	public De() {
		this.random = new Random();
	}
	
	public int getValeur() {
		valeur = MIN + random.nextInt(MAX);
		return valeur;
	}
	
	public int lancerDes(int nbDes, int valeurMax, int valeurMin) {
		valeur = 0;
		for(int i=0; i<nbDes; i++) {
			valeur+= random.nextInt(valeurMin,valeurMax+1);
		}
		return valeur;
	}
	

}
