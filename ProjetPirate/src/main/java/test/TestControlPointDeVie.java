package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import controleur.ControlJeuPirate;
import controleur.ControlPointDeVie;
import entity.Jeu;
import entity.Joueur;
import entity.Pion;

class TestControlPointDeVie {
	
	private IBoundary boundary;
    private ControlJeuPirate controlJeuPirate;
    private Jeu jeu;
    private Joueur joueur0;
    private Joueur joueur1;
    private ControlPointDeVie controlPointDeVie;

    @BeforeEach
    public void setUp() {
        jeu = new Jeu();
        joueur0 = new Joueur("Pirate1", new Pion(0));
        joueur1 = new Joueur("Pirate2",   new Pion(1));
        jeu.setJoueur(0, joueur0);
        jeu.setJoueur(1, joueur1);
        controlPointDeVie = new ControlPointDeVie(jeu, boundary, controlJeuPirate);
    }
    
    //----Perdre des points de Vie------
	@Test
	public void testPerdrePoint() {
		controlPointDeVie.perdrePointsDeVie(2, joueur0);
        assertEquals("Le joueur doit perdre 2 PV",3, joueur0.getPointDeVie());
	}
	
	@Test
	public void TestperdrePointsDeVie_depasseMin_pvBloquéAZero() {
        controlPointDeVie.perdrePointsDeVie(10, joueur0); // PV max = 5
        assertEquals("Le joueur ne diot pas avoir un PV négatif -> remis à 0",0, joueur0.getPointDeVie());
    }
	
	@Test
	//on ne peut pas perdre 0 PV
	public void testPerdrePointsDeVie_zero() {
        assertThrows(IllegalArgumentException.class,() -> controlPointDeVie.perdrePointsDeVie(0, joueur0));
    }
	
	
	//---Gagner des points de Vie ------
	@Test
	public void testGagnerPointDevie() {
		joueur0.setPointDeVie(2);
        controlPointDeVie.gagnerPointsDeVie(2, joueur0);
        assertEquals("Le point de vie doit augmenter",4, joueur0.getPointDeVie());
	}
	
	@Test
	public void testGagnerPointDeVieMex() {
        joueur0.setPointDeVie(4);
        controlPointDeVie.gagnerPointsDeVie(3, joueur0);
        assertEquals("Les points de vie doivent pas dépasser 5",5, joueur0.getPointDeVie());
    }
	
	@Test
	//le point de vie doit pas augmenter de 0
	public void gagnerPointsDeVie_zero() {
        assertThrows(IllegalArgumentException.class,() -> controlPointDeVie.gagnerPointsDeVie(0, joueur0));
    }
	
	@Test
	public void testVerifierPointsDeVie() {
        joueur0.setPointDeVie(0);
        assertTrue(controlPointDeVie.verifierPointsDeVie(joueur0));
    }
	
	//---reset point de vie----
	@Test
    public void testResetPointsDeVie() {
        joueur0.setPointDeVie(1);
        joueur1.setPointDeVie(0);
        controlPointDeVie.resetPointsDeVie();
        assertEquals(Joueur.PV_MAX, joueur0.getPointDeVie());
        assertEquals(Joueur.PV_MAX, joueur1.getPointDeVie());
    }
	
	
	
	
	
	
	

}
