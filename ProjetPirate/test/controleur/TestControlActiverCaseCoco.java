package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import entity.*;

class TestControlActiverCaseCoco {

    private Jeu jeu;
    private Joueur joueur1;
    private Joueur joueur2;
    static ControlActiverCaseCoco controlActiverCaseCoco;
    private IBoundary boundary;
    private ControlJeuPirate controlJeuPirate;

    @BeforeEach
    public void setup() {

        boundary = new BoundaryTest();

        controlJeuPirate = new ControlJeuPirate(boundary);
        jeu = controlJeuPirate.getJeu();
        joueur1 = new Joueur("Pirate1", new Pion(0));
        joueur2 = new Joueur("Pirate2", new Pion(1));
        jeu.setJoueur(0, joueur1);
        jeu.setJoueur(1, joueur2);
        joueur1 = jeu.getJoueur(0); // ← ajoute
        joueur2 = jeu.getJoueur(1); // ← ajoute
        Joueur[] joueurs = jeu.getJoueurs();
        ControlPointDeVie controlVie = controlJeuPirate.getControlPointDeVie();
        ControlCacherDe controlDe = controlJeuPirate.getControlCacherDe();
        controlActiverCaseCoco = new ControlActiverCaseCoco(joueurs, new CaseCoco(7), controlVie, controlDe, boundary);
    }

    @Test
    public void testPertePointsDeVie() {
        int pv_Avant = joueur1.getPointDeVie();
        controlActiverCaseCoco.activerCase(new CaseCoco(7), 0);
        assertTrue(joueur1.getPointDeVie() < pv_Avant);
    }

    @Test
    public void testDegatsEntre1Et2() {
        controlActiverCaseCoco.activerCase(new CaseCoco(7), 0);
        int degat = controlActiverCaseCoco.getDerniersDegats();
        assertTrue(degat >= 1);
        assertTrue(degat <= 2);
    }

    @Test
    public void testJoueurAffecteCoco() {
        controlActiverCaseCoco.activerCase(new CaseCoco(7), 0);
        assertTrue(joueur2.getAffecteCoco());
    }

}
