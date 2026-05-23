package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import boundary.interfaces.ICommencerPartie;
import boundary.interfaces.IDeplacerPirate;
import boundary.interfaces.IFinDePartie;
import boundary.interfaces.ILancerDe;
import boundary.interfaces.IPointsDeVie;
import console.BoundaryConsole;
import controleur.ControleurDe;
import entity.Jeu;
import entity.Joueur;
import entity.Pion;

class TestControleurDe {
	
	private Jeu jeu;
	private Joueur joueur0;
    private Joueur joueur1;
	private ControleurDe controleur;
	private IBoundary boundary;
	private ControlJeuPirate controlJeuPirate;
	
	@BeforeEach
	public void setUp() {
		boundary = new BoundaryConsole();
		controlJeuPirate = new ControlJeuPirate(boundary);
		jeu = controlJeuPirate.getJeu();
		joueur0 = new Joueur("Pirate1", new Pion(0));
        joueur1 = new Joueur("Pirate2",   new Pion(1));
        jeu.setJoueur(0, joueur0);
        jeu.setJoueur(1, joueur1);
	    controleur = controlJeuPirate.getControleurDe();
	}

	@Test
	public void testLancerDeModif() {
		int res= controleur.lancerDesModif(1, 1, 5);
        assertTrue(res >= 1);
        assertTrue(res <= 5);
	}
	
	@Test
	public void testLancerDe() {
		controleur.lancerDe();
		int[] des = controleur.getresultatDes();
		assertNotNull(des);
		assertEquals(2, des.length);
		//on verifie que les dés appartiennent pas à 1 à 6 
        assertTrue(des[0] >= 1 && des[0] <= 6);
        assertTrue(des[1] >= 1 && des[1] <= 6);
	}
	
	@Test
    public void testResultatDe() {
        controleur.lancerDe();
        int[] des = controleur.getresultatDes();
        int somme = des[0] + des[1];
        assertEquals(somme, controleur.resultatDe());
    }
}
