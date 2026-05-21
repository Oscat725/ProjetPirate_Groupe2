package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import boundary.interfaces.IBoundary;
import console.BoundaryConsole;

class TestControlJeuPirate {
	static ControlJeuPirate controlJeuPirate;
	static IBoundary iBoundary;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		iBoundary = new BoundaryConsole();
		controlJeuPirate = new ControlJeuPirate(iBoundary);
	}

	@Test
	void testControlJeuPirate() {
		assertNotNull(iBoundary);
		assertNotNull(controlJeuPirate);
	}

	@Test
	void testJouer() {
		fail("Not yet implemented");
	}

	@Test
	void testApresLancerDe() {
		fail("Not yet implemented");
	}

	@Test
	void testJouerUnTour() {
		fail("Not yet implemented");
	}

	@Test
	void testApresDemandeCoco() {
		fail("Not yet implemented");
	}

	@Test
	void testApresDeplacer() {
		fail("Not yet implemented");
	}

	@Test
	void testApresActiverCase() {
		fail("Not yet implemented");
	}

	@Test
	void testApresAfficherPV() {
		fail("Not yet implemented");
	}

	@Test
	void testFinDeTour() {
		fail("Not yet implemented");
	}

	@Test
	void testFinAfficherTour() {
		fail("Not yet implemented");
	}

}
