
package controleur;

import entity.Joueur;


public class ControlJeuPirate {
	private Joueur joueur;
	private ControlCommencerPartie controlCommencerPartie;
	private ControlVerifierFinPartie controlVerifierFinPartie;
	private ControleurDe controlLancerDe;
	private ControlDeplacer controlDeplacer;
	private ControlActiverCase controlActiverCase;
	private ControlPointDeVie controlPointDeVie;
	private ControlPirateCourant controlPirate;
	
	public ControlJeuPirate(Joueur joueur, ControlCommencerPartie controlCommencerPartie,
			ControlVerifierFinPartie controlVerifierFinPartie, ControleurDe controlLancerDe,
			ControlDeplacer controlDeplacer, ControlActiverCase controlActiverCase, ControlPointDeVie controlPointDeVie,
			ControlPirateCourant controlPirate) {
		super();
		this.joueur = joueur;
		this.controlCommencerPartie = controlCommencerPartie;
		this.controlVerifierFinPartie = controlVerifierFinPartie;
		this.controlLancerDe = controlLancerDe;
		this.controlDeplacer = controlDeplacer;
		this.controlActiverCase = controlActiverCase;
		this.controlPointDeVie = controlPointDeVie;
		this.controlPirate = controlPirate;
	}

}

