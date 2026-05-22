package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import console.BoundaryConsole;
import entity.Jeu;
import entity.Joueur;
import entity.Pion;

class TestControlJeuPirate {
	static ControlJeuPirate controlJeuPirate;
	static IBoundary iBoundary;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		iBoundary = new BoundaryTest();
		controlJeuPirate = new ControlJeuPirate(iBoundary);
	}

	@Test
	void testControlJeuPirate() {
		assertNotNull(iBoundary);
		assertNotNull(controlJeuPirate);
	}


	@Test
	void testFinDeTour() {

	    // on initialise les joueurs sinon getNom() plante
	    controlJeuPirate.getJeu().setJoueur(
	            0,
	            new Joueur("A", new Pion(0)));

	    controlJeuPirate.getJeu().setJoueur(
	            1,
	            new Joueur("B", new Pion(1)));

	    int indiceAvant =
	            controlJeuPirate.getJeu()
	                    .getIndiceJoueurCourant();

	    // on teste directement le jeu
	    controlJeuPirate.getJeu()
	            .passerAuJoueurSuivant();

	    int indiceApres =
	            controlJeuPirate.getJeu()
	                    .getIndiceJoueurCourant();

	    assertNotEquals(indiceAvant, indiceApres);
	}

}
