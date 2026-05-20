package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import controleur.*;
import entity.CaseBombe;
import entity.Joueur;
import entity.Pion;

class TestControlActiverCaseBombe {
	
	private ControlPointDeVie controlVie;
    private ControleurDe controlDe;
    private ControlJeuPirate controlJeu;
    private IBoundary boundary;
    private Joueur joueur0;
    private Joueur joueur1;
    private Joueur[] joueurs;
    private ControlActiverCaseBombe controlBombe;
	
    @BeforeEach
    public void setUp() {
        joueur0 = new Joueur("Alice", new Pion(0));
        joueur1 = new Joueur("Bob",   new Pion(1));
        joueurs = new Joueur[]{joueur0, joueur1};
        controlBombe = new ControlActiverCaseBombe(joueurs, controlDe, controlVie, controlJeu, boundary);
    }
    
    @Test
    public void testPertePointsDeVie() {
        CaseBombe bombe = new CaseBombe(4);
        int pv_Avant = joueur1.getPointDeVie();
        controlBombe.activerCase(bombe, 0);
        assertTrue(joueur1.getPointDeVie() < pv_Avant);
    }
    
    
}
