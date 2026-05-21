package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
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
        
        joueur0 = new Joueur("Pirate1", new Pion(0));
        joueur1 = new Joueur("Pirate2",   new Pion(1));
        joueurs = new Joueur[]{joueur0, joueur1};
        jeu.setJoueur(0, joueur0);
        jeu.setJoueur(1, joueur1);
        controlVie = new ControlPointDeVie(jeu, boundary, null);
        controlDe   = new ControleurDe(jeu, boundary, null);
        controlBombe = new ControlActiverCaseBombe(joueurs, controlDe, controlVie, null, boundary);
    }
    
    @Test
    public void testPertePointsDeVie() {
        CaseBombe bombe = new CaseBombe(4);
        int pv_Avant = joueur0.getPointDeVie();
        controlBombe.activerCase(bombe, 0);
        assertTrue(joueur0.getPointDeVie() < pv_Avant);
    }
    
    
}
