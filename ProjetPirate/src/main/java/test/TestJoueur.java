package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entity.Couleur;
import entity.Joueur;
import entity.Pion;

class TestJoueur {

	@Test
    public void testSetPointDeVie1() {
        Pion pion = new Pion(0);
        Joueur joueur = new Joueur("pirate1", pion);
        joueur.setPointDeVie(5);

        assertEquals(5, joueur.getPointDeVie());
    }

}
