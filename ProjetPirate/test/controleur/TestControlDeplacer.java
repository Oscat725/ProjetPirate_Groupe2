package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import controleur.ControlDeplacer;
import controleur.ControlJeuPirate;
import entity.Jeu;
import entity.Joueur;
import entity.Pion;

class TestControlDeplacer {
    private IBoundary boundary;
    private ControlJeuPirate controlJeuPirate;
    private Jeu jeu;
    private Joueur joueur;
    private ControlDeplacer controlDeplacer;

    @BeforeEach
    public void setUp() {
        jeu = new Jeu();
        joueur = new Joueur("Pirate1", new Pion(0));
        jeu.setJoueur(0, joueur);
        controlDeplacer = new ControlDeplacer(jeu, boundary, controlJeuPirate);
    }

    @Test
    void testDeplacerPirate() {
        joueur.setPosition(0);
        controlDeplacer.deplacerPirate(5);
        assertEquals(5, joueur.getPosition());
    }

    @Test
    public void testDeplacementJeuLoie() {
        joueur.setPosition(28);
        controlDeplacer.deplacerPirate(4);
        assertEquals(26, joueur.getPosition());
    }

}