package entity;

import java.util.Random;

public class De {
	
	private static final Integer MIN = 1;
    private static final Integer MAX = 6;
	private final Random random;
	
	public De() {
		this.random = new Random();
	}
	
	public int getValeur() {
		return MIN + random.nextInt(MAX);
	}
	
	public int lancerDes(int nbDes, int min, int max) {
		int valeur = 0;
		for(int i=0; i<nbDes; i++) {
			valeur+= random.nextInt(min, max+1);
		}
		return valeur;
	}
}
