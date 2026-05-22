package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import console.BoundaryConsole;
import entity.Jeu;
import entity.Joueur;
import entity.Pion;

class TestControlCommencerPartie {
	static Jeu jeu;
	static IBoundary boundary;
	static ControlJeuPirate controlJeuPirate;
	static ControlCommencerPartie controlCommencerPartie;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		jeu = new Jeu();
		boundary = new BoundaryConsole();
		controlJeuPirate = new ControlJeuPirate(boundary);
		controlCommencerPartie = new ControlCommencerPartie(jeu, boundary, controlJeuPirate);
		
	}

	@Test
	void testControlCommencerPartie() {
		assertNotNull(jeu);
		assertNotNull(boundary);
		assertNotNull(controlJeuPirate);		
	}


	@Test
	void testInitialiserJoueurs() {
		controlCommencerPartie.initialiserJoueurs("A", "B");
        assertNotNull(jeu.getJoueurs());
        assertEquals(jeu.getJoueur(0).getNom(), "A");
        assertEquals(jeu.getJoueur(1).getNom(), "B");
	}



}
