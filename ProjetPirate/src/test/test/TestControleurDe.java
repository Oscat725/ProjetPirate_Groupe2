package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import boundary.interfaces.ICommencerPartie;
import boundary.interfaces.IDeplacerPirate;
import boundary.interfaces.IFinDePartie;
import boundary.interfaces.ILancerDe;
import boundary.interfaces.IPointsDeVie;
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
	
	@BeforeEach
	public void setUp() {
		boundary = new IBoundary() {
            @Override public void affichageResultatDe(int v1, int v2, boundary.interfaces.ILancerDe cb) {}
            @Override public void deplacerPirates(String n, int a, int b, boundary.interfaces.IDeplacerPirate cb) {}
            @Override public void afficherEffetCase(String t, String m) {}
            @Override public void afficherPointDeVie(String n, int pv, boundary.interfaces.IPointsDeVie cb) {}
            @Override public void afficherFinDePartie(String n, boundary.interfaces.IFinDePartie cb) {}
            @Override public void afficherMessage(String m) {}
            @Override public void changerJoueurActif(String n) {}
            @Override public void commencerPartie(boundary.interfaces.ICommencerPartie cb) {}
        };
		jeu = new Jeu();
		joueur0 = new Joueur("Pirate1", new Pion(0));
        joueur1 = new Joueur("Pirate2",   new Pion(1));
        jeu.setJoueur(0, joueur0);
        jeu.setJoueur(1, joueur1);
	    controleur = new ControleurDe(jeu,boundary,null);
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
