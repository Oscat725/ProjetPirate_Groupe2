package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Joueur;
import entity.Pion;

class TestControlVerifierFinPartie {
	static final int CASE_FINALE = 29;
	static Joueur[] joueurs;
	static Pion pionJ1;
	static Pion pionJ2;
	static ControlVerifierFinPartie controlVerifierFinPartie;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		joueurs = new Joueur[2];
		pionJ1 = new Pion(0);
		pionJ2 = new Pion(1);
		joueurs[0] = new Joueur("A", pionJ1);
		joueurs[1] = new Joueur("B", pionJ2);
		controlVerifierFinPartie = new ControlVerifierFinPartie(joueurs);
	}

	@Test
	void testControlVerifierFinPartie() {
	    assertNotNull(controlVerifierFinPartie);
	}
	
	@Test
	void testConstructeurJoueursNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new ControlVerifierFinPartie(null);});
	}

	@Test
	void testVerifierFinPartie() {
		joueurs[0].setPointDeVie(0);
		assertTrue(controlVerifierFinPartie.verifierFinPartie(), "Joueur 1 a 0 PV");
		joueurs[0].setPointDeVie(5);
		assertFalse(controlVerifierFinPartie.verifierFinPartie());
		joueurs[1].setPosition(CASE_FINALE);
		assertTrue(controlVerifierFinPartie.verifierFinPartie(), "Joueur 2 est a la derniere case");
	
	}

}
