package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;   
import org.junit.jupiter.api.Test;

import entity.Joueur;
import entity.Pion;

class TestJoueur {
    private Joueur joueur;

    @BeforeEach
    public void setup() {
        joueur = new Joueur("Pirate", new Pion(0));
    }

    @Test
    public void testSetPointDeVieMAx() {
        assertEquals(5, joueur.getPointDeVie(), "Un joueur démarre avec 5 points de vie");
    }

    @Test
    public void setPointDeVie_valeurNomale_modifie() {
        joueur.setPointDeVie(3);
        assertEquals(3, joueur.getPointDeVie());
    }
}
