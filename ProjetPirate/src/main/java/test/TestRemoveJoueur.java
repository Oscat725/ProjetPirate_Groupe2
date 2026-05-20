package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Case;
import entity.Joueur;
import entity.Pion;

class TestRemoveJoueur {
	private Case caseTest;
	private Joueur joueur0;
	private Joueur joueur1;
	@BeforeEach
	void setUp() throws Exception {
		caseTest = new Case(5);
		joueur0 = new Joueur("Pirate1", new Pion(0));
		joueur1 = new Joueur("Pirate2", new Pion(0));
	}

	@Test
	void testRemoveJoueur() {
		caseTest.setJoueur(0, joueur0);
		caseTest.removeJoueur(0);
		assertNull(caseTest.getJoueur(0), "doit retourner 0");
	}

}
