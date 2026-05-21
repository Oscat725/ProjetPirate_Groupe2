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
import controleur.*;
import entity.*;

class TestControlActiverCaseCoco {
	
	private Jeu jeu;
	private Joueur joueur1;
	private Joueur joueur2;
    private ControlActiverCaseCoco controlActiverCaseCoco;
    private IBoundary boundary;
    private ControlJeuPirate controlJeuPirate;

    @BeforeEach
    public void setup() {
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
        joueur1 = new Joueur("Pirate1",new Pion(0));
        joueur2 = new Joueur("Pirate2",new Pion(1));
        jeu.setJoueur(0, joueur1);
        jeu.setJoueur(1, joueur2);
        Joueur[] joueurs = {joueur1, joueur2};
        ControlPointDeVie controlVie =new ControlPointDeVie(jeu, boundary,controlJeuPirate);
        ControlCacherDe controlDe =new ControlCacherDe(joueur2);
        controlActiverCaseCoco = new ControlActiverCaseCoco(joueurs, new CaseCoco(7),controlVie,controlDe,null);
    }
	
    @Test
    public void testPertePointsDeVie() {
        int pv_Avant = joueur1.getPointDeVie();
        controlActiverCaseCoco.activerCase(new CaseCoco(7),0);
        //le joueur perds un nombre aleatoire de coco
        assertTrue(joueur1.getPointDeVie() < pv_Avant);
    }

    @Test
    public void testDegatsEntre1Et2() {
    	controlActiverCaseCoco.activerCase(new CaseCoco(7),0);
        int degat = controlActiverCaseCoco.getDerniersDegats();
        assertTrue(degat>= 1);
        assertTrue(degat <= 2);
    }

    @Test
    public void testJoueurAffecteCoco() {
    	controlActiverCaseCoco.activerCase(new CaseCoco(7),0);
        assertTrue(joueur1.getAffecteCoco());
    }

}
