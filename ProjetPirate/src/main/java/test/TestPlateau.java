package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import entity.Plateau;
import entity.Case;
import entity.CaseBombe;
import entity.CaseCoco;
import entity.CaseMystere;


public class TestPlateau {
	
	private Plateau plateau;
	
	@BeforeEach
	public void setup() {
		plateau =new Plateau();
	}
	
	@Test
    public void testGetCaseNormale() {
        Case c = plateau.getCase(0);
        assertNotNull(c);
        assertFalse(c.getEstCaseSpecial());
    }
	
	@Test
	public void testDeuxCase() {
		Case c1 = plateau.getCase(5);
		Case c2 = plateau.getCase(5);
		assertEquals("Deux cases ont le même type",c1,c2);
	}

	
	@Test
    public void testGetCaseBombe() {
        Case c = plateau.getCase(4);
        assertTrue(c instanceof CaseBombe);
    }
	
	@Test
    public void testGetCaseCoco() {
        Case c = plateau.getCase(7);
        assertTrue(c instanceof CaseCoco);
    }
	
	@Test
    public void testGetCaseMystere() {
        Case c = plateau.getCase(9);
        assertTrue(c instanceof CaseMystere);
    }
}

