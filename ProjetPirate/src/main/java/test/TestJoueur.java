package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Couleur;
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
        assertEquals("Un joueur démarre avec 5 points de vie",5, joueur.getPointDeVie());
    }
	
	@Test 
	public void setPointDeVie_valeurNomale_modifie() {
        joueur.setPointDeVie(3);
        assertEquals(3, joueur.getPointDeVie());
    }

}
