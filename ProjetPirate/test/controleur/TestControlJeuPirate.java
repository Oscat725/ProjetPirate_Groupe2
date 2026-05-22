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
	void testFinDeTour() {
		fail("Not yet implemented");
	}

	@Test
	void testFinAfficherTour() {
		fail("Not yet implemented");
	}

}
