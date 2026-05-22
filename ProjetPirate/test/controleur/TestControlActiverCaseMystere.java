package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import console.BoundaryConsole;
import entity.CaseMystere;
import entity.Jeu;
import entity.Joueur;
import entity.Pion;

class TestControlActiverCaseMystere {
	static ControlJeuPirate controlJeuPirate;
	static ControlPointDeVie controlPointDeVie;
	static ControlDeplacer controlDeplacer;
	static ControlActiverCaseMystere controlActiverCaseMystere;
	static IBoundary boundary;
	static int joueurCourantIndex;
    static int storedEffect;
    static int storedValue;
    static CaseMystere caseMystere;
	static Joueur[] joueurs;
	static Pion pionJ1;
	static Pion pionJ2;
    static Jeu jeu;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		jeu = new Jeu();
		joueurs = new Joueur[2];
		pionJ1 = new Pion(0);
		pionJ2 = new Pion(1);
		joueurs[0] = new Joueur("A", pionJ1);
		joueurs[1] = new Joueur("B", pionJ2);
		boundary = new BoundaryConsole();
		controlDeplacer = new ControlDeplacer(jeu, boundary, controlJeuPirate);
		caseMystere = new CaseMystere(2);
		controlJeuPirate = new ControlJeuPirate(boundary);
		controlPointDeVie = new ControlPointDeVie(jeu, boundary, controlJeuPirate);
		joueurCourantIndex = 0;
		controlActiverCaseMystere = new ControlActiverCaseMystere(joueurs, joueurCourantIndex, controlPointDeVie, controlJeuPirate, boundary);
		
	}

	@Test
	void testActiverCase() {
		assertNotNull(controlPointDeVie);
		assertNotNull(controlDeplacer);
		assertNotNull(boundary);

	}

	@Test
	void testControlActiverCaseMystere() {
		controlActiverCaseMystere.activerCase(caseMystere, joueurCourantIndex);
		assertNotNull(caseMystere.getValue());
		assertNotNull(storedEffect);
	}
	

	@Test
	void testFinActiverCase() {
		storedEffect = 0;
		storedValue = 3;
		joueurs[0].setPosition(3);
		controlActiverCaseMystere.finActiverCase();
		assertNotNull(joueurs);
		assertEquals(6, joueurs[0].getPosition(), "Joueur avance de 3");
	}

}
