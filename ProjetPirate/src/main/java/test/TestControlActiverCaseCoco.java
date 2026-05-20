package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
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
    	jeu = new Jeu();
        joueur1 = new Joueur("Pirate1",new Pion(0));
        joueur2 = new Joueur("Pirate2",new Pion(1));
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
