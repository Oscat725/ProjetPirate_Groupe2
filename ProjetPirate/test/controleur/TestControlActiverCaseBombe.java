package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import console.BoundaryConsole;
import controleur.*;
import entity.CaseBombe;
import entity.Jeu;
import entity.Joueur;
import entity.Pion;

class TestControlActiverCaseBombe {
	
	private Jeu jeu;
	private ControlPointDeVie controlVie;
    private ControleurDe controlDe;
    private IBoundary boundary;
    private Joueur joueur0;
    private Joueur joueur1;
    private Joueur[] joueurs;
    private ControlActiverCaseBombe controlBombe;
	
    @BeforeEach
    public void setUp() {
    	jeu = new Jeu();
    	boundary = new BoundaryConsole();
        
        joueur0 = new Joueur("Pirate1", new Pion(0));
        joueur1 = new Joueur("Pirate2",   new Pion(1));
        joueurs = new Joueur[]{joueur0, joueur1};
        jeu.setJoueur(0, joueur0);
        jeu.setJoueur(1, joueur1);
        controlVie = new ControlPointDeVie(jeu, boundary, null);
        controlDe   = new ControleurDe(jeu, boundary, null);
        controlBombe = new ControlActiverCaseBombe(joueurs, controlDe, controlVie, null);
    }
    
    @Test
    public void testPertePointsDeVie() {
        CaseBombe bombe = new CaseBombe(4);
        int pv_Avant = joueur0.getPointDeVie();
        controlBombe.activerCase(bombe, 0);
        assertTrue(joueur0.getPointDeVie() < pv_Avant);
    }
    
    
}
