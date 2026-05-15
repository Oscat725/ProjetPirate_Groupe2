package boundary.interfaces;

public interface ICommencerPartie {
	void initialiserJoueurs(String nomJ1, String nomJ2);
	String determinerQuiCommence();
	void finCommencerPartie();
}
