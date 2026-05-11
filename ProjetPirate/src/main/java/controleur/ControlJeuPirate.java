package controleur;

import entity.Joueur;


public class ControlJeuPirate {
	private Joueur joueur;
	private ControlDeplacer controlDeplacer;
	private ControlVerifierFinPartie controlVerifierFinPartie;
//	private ControlActiverCase controlActiverCase;

	public ControlJeuPirate(Joueur joueur, ControlDeplacer controlDeplacer,
			ControlVerifierFinPartie controlVerifierFinPartie, ControlActiverCase controlActiverCase) {
		this.joueur = joueur;
		this.controlDeplacer = controlDeplacer;
		this.controlVerifierFinPartie = controlVerifierFinPartie;
//		this.controlActiverCase = controlActiverCase;
	
	}
}
