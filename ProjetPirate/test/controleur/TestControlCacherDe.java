package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import console.BoundaryConsole;
import entity.Jeu;
import entity.Joueur;
import entity.Pion;

class TestControlCacherDe {
	static Joueur[] joueurs;
	static IBoundary boundary;
	static Jeu jeu;
	static Pion pionJ1;
	static Pion pionJ2;
	static ControlJeuPirate controlJeuPirate;
	static ControlCacherDe controlCacherDe;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		joueurs = new Joueur[2];
		pionJ1 = new Pion(0);
		pionJ2 = new Pion(1);
		joueurs[0] = new Joueur("A", pionJ1);
		joueurs[1] = new Joueur("B", pionJ2);
		boundary = new BoundaryConsole();
		jeu = new Jeu();
		controlJeuPirate = new ControlJeuPirate(boundary);
		controlCacherDe = new ControlCacherDe(joueurs, boundary, jeu, controlJeuPirate);
	}

	@Test
	void testControlCacherDe() {
		assertNotNull(boundary);
		assertNotNull(jeu);
		assertNotNull(controlJeuPirate);
		assertNotNull(controlCacherDe);
		assertNotNull(joueurs);
	}

	@Test
	void testSetAffecteCoco() {
		controlCacherDe.setAffecteCoco(joueurs[0], true);
		assertTrue(joueurs[0].getAffecteCoco(), "Joueur affecte par coco");
	}

}
