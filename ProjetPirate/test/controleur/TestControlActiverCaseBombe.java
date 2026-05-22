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
	
	static Jeu jeu;
	static ControlPointDeVie controlVie;
	static ControleurDe controlDe;
	static IBoundary boundary;
	static Joueur joueur0;
	static Joueur joueur1;
	static Joueur[] joueurs;
	static ControlActiverCaseBombe controlBombe;
	static ControlJeuPirate controlJeuPirate;
	
    @BeforeEach
    public void setUp() {
    	boundary = new BoundaryConsole();
        controlJeuPirate = new ControlJeuPirate(boundary);
        jeu = controlJeuPirate.getJeu();
        joueur0 = new Joueur("Pirate1", new Pion(0));
        joueur1 = new Joueur("Pirate2",   new Pion(1));
        joueurs = new Joueur[]{joueur0, joueur1};
        jeu.setJoueur(0, joueur0);
        jeu.setJoueur(1, joueur1);
        controlVie = controlJeuPirate.getControlPointDeVie();
        controlDe   = controlJeuPirate.getControleurDe();
        controlBombe = controlJeuPirate.getControlBombe();
    }
    
    @Test
    public void testPertePointsDeVie() {
        CaseBombe bombe = new CaseBombe(4);
        int pv_Avant = joueur0.getPointDeVie();
        controlBombe.activerCase(bombe, 0);
        assertTrue(joueur0.getPointDeVie() < pv_Avant);
    }
    
    
}
