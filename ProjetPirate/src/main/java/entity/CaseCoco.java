package entity;

import java.util.Random;

public class CaseCoco extends Case {

	private static final int DEGATS_MIN = 1;
    private static final int DEGATS_MAX = 2;
	private final Random random;


	public CaseCoco(Integer numero) {
		super(numero);
		this.random = new Random();
	}

	public int getDegats() {
		return DEGATS_MIN + random.nextInt(DEGATS_MAX - DEGATS_MIN + 1);
	}

}
