package entity;

import java.util.Random;

public class CaseMystere extends Case {
	
	public int effect; 
	private int value;
	private final Random random = new Random();
	
	public CaseMystere(Integer numero) {
		super(numero);
	}

	public void activerCase() {
		this.effect = random.nextInt(3); 
		if (effect == 2) {
	        //PV : 1 à 2
	        this.value = random.nextInt(2) + 1;
	    } else {
	        //déplacement : 1 à 4
	        this.value = random.nextInt(4) + 1;
	    }
	}

	public int getValue() {
		return value;
	}
}