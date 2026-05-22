package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Case;
import entity.Joueur;
import entity.Pion;

class TestCase {
	static Case caseTest;
	static Joueur joueur0;
	static boolean contientJoueur;
	static Jeu jeu;
	@BeforeEach
	void setUp() throws Exception {
		caseTest = new Case(5);
		joueur0 = new Joueur("Pirate1", new Pion(0));
	}

	@Test
	void testRemoveJoueur() {
		caseTest.setJoueur(0, joueur0);
		caseTest.removeJoueur(0);
		assertNull(caseTest.getJoueur(0), "doit retourner 0");
	}
	
	@Test
	void testRemoveJoueurInvalide2() {

	    assertThrows(IllegalArgumentException.class, () -> {
	        caseTest.removeJoueur(2);
	    });
	}
	

}
