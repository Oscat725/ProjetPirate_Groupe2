package controleur;

import boundary.interfaces.IBoundary;
import boundary.interfaces.IControlJeuPirate;
import entity.*;

public class ControlJeuPirate implements IControlJeuPirate {

	private Jeu jeu;
	private Joueur[] joueurs;
	private IBoundary iBoundary;

	// Sous-controleurs
	private ControlCommencerPartie controlCommencerPartie;
	private ControleurDe controleurDe;
	private ControlDeplacer controlDeplacer;
	private ControlPointDeVie controlPointDeVie;
	private ControlVerifierFinPartie controlVerifierFinPartie;
	private ControlActiverCase controlActiverCase;

	// On déclare juste les variables ici, sans les initialiser
	private ControlCacherDe controlCacherDe;
	private ControlActiverCaseBombe controlBombe;
	private ControlActiverCaseCoco controlCoco;
	private ControlActiverCaseMystere controlMystere;

	public ControlJeuPirate(IBoundary iBoundary) {

		this.iBoundary = iBoundary;

		this.jeu = new Jeu();
		this.joueurs = jeu.getJoueurs();

		this.controlCommencerPartie = new ControlCommencerPartie(jeu, iBoundary, this);
		this.controleurDe = new ControleurDe(jeu, iBoundary, this);
		this.controlPointDeVie = new ControlPointDeVie(jeu, iBoundary, this);
		this.controlVerifierFinPartie = new ControlVerifierFinPartie(joueurs);
		this.controlDeplacer = new ControlDeplacer(jeu, iBoundary, this);
		this.controlCacherDe = new ControlCacherDe(null);
		this.controlBombe = new ControlActiverCaseBombe(joueurs, controleurDe, controlPointDeVie, iBoundary);
		this.controlCoco = new ControlActiverCaseCoco(joueurs, null, controlPointDeVie, controlCacherDe, iBoundary);
		this.controlMystere = new ControlActiverCaseMystere(joueurs, 0, controlPointDeVie, this, iBoundary);

		this.controlActiverCase = new ControlActiverCase(controlBombe, controlCoco, controlMystere);
	}

	public Jeu getJeu() {
		return this.jeu;
	}

	public IBoundary getBoundary() {
		return this.iBoundary;
	}

	// -----methodes appellees par les autres controleurs-----

	public void jouer() {
		controlCommencerPartie.commencerPartie();
	}

	public void apresLancerDe(int sommeDes) {
		controlDeplacer.deplacerPirate(sommeDes);
	}

	public void jouerUnTour() {
		controleurDe.lancerDe();
	}

	public void apresDeplacer(int numCase) {
		Case c = jeu.getPlateau().getCase(numCase);
		if (controlActiverCase.isCaseSpecial(c)) {
			controlActiverCase.activerCase(c, jeu.getIndiceJoueurCourant());
		} else {
			iBoundary.afficherMessage("Case normale, aucun effet.");
			apresActiverCase();
		}
	}

	public void apresActiverCase() {
		controlPointDeVie.calculerPV();
	}

//    public void apresAfficherPV() {
//       //On vérifie si la partie est terminée (victoire ou mort)
//        if(controlVerifierFinPartie.verifierFinPartie()) {
//            
//            // On détermine qui a gagné :
//            // Si le joueur courant a atteint la case 30 ou si l'autre est mort, c'est lui le gagnant.
//            String gagnant = jeu.getJoueurCourant().getNom();
//            
//            // Si le joueur courant vient de mourir (ex: case bombe), l'autre gagne.
//            if(jeu.getJoueurCourant().getPointDeVie() <= 0) {
//                // Si courant = joueur 0, l'autre est joueur 1 (et inversement)
//                int autreJoueurIndex = (jeu.getIndiceJoueurCourant() == 0) ? 1 : 0;
//                gagnant = jeu.getJoueur(autreJoueurIndex).getNom();
//            }
//            
//            iBoundary.afficherFinDePartie(gagnant, null); // Fin de la boucle
//            
//        } else {
//            // La partie continue : on change de joueur et on relance le tour
//            finDeTour(); 
//        }
//    }

	public void apresAfficherPV() {
		// On vérifie si la partie est terminée (victoire ou mort)
		if (controlVerifierFinPartie.verifierFinPartie()) {

			// On détermine qui a gagné :
			String gagnant = jeu.getJoueurCourant().getNom();

			// Si le joueur courant vient de mourir (ex: case bombe), l'autre gagne.
			if (jeu.getJoueurCourant().getPointDeVie() <= 0) {
				int autreJoueurIndex = (jeu.getIndiceJoueurCourant() == 0) ? 1 : 0;
				gagnant = jeu.getJoueur(autreJoueurIndex).getNom();
			}

			// on utilise une lambda pour le callback
			// On remplace le 'null' par une lambda de IFinDePartie
			iBoundary.afficherFinDePartie(gagnant, () -> {
				System.exit(0);
			});

		} else {
			// La partie continue : on change de joueur et on relance le tour
			finDeTour();
		}
	}

	public void finDeTour() {
		jeu.passerAuJoueurSuivant();
		iBoundary.changerJoueurActif(jeu.getJoueurCourant().getNom(), this);
	}

	@Override
	public void finAfficherTour() {
		jouerUnTour();
	}

}
