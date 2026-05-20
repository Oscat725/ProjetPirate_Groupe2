package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controleur.ControlActiverCase;
import entity.Case;
import entity.CaseBombe;
import entity.CaseCoco;
import entity.CaseMystere;

class TestControlActiverCase {
	
	private ControlActiverCase control;


	@Test
	void testCaseNormale() {
		Case caseNormale = new Case(1);
        assertFalse(control.isCaseSpecial(caseNormale));
	}
	
	@Test
    public void testCaseBombe() {
        Case caseBombe = new CaseBombe(4);
        assertTrue(control.isCaseSpecial(caseBombe));
    }

    @Test
    public void testCaseChuteCoco() {
        Case caseCoco = new CaseCoco(7);
        assertTrue(control.isCaseSpecial(caseCoco));
    }

    @Test
    public void testCaseMystereSpeciale() {
        Case caseMystere = new CaseMystere(9);
        assertTrue(control.isCaseSpecial(caseMystere));
    }
}
