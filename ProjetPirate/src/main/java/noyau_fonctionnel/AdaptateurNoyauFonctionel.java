package noyau_fonctionnel;

import boundary.interfaces.*;
import controleur.ControlJeuPirate;
import interface_noyau_fonctionnel.INoyauFonctionnel;
import interface_noyau_fonctionnel.IPirates;

// Adaptateur qui connecte l'IHM au noyau fonctionnel (contrôleurs).

public class AdaptateurNoyauFonctionel implements IBoundary, INoyauFonctionnel {

	private IPirates dialogue;
	private IControlJeuPirate controlJeuPirate;

	private ILancerDe ctrlDe;
	private ICommencerPartie ctrlCommencer;
	private IDeplacerPirate ctrlDeplacer;
	private IActiverCase ctrlActiverCase;
	private IPointsDeVie ctrlPV;
	private IFinDePartie ctrlFin;
	private int dernierNumCase;

	public AdaptateurNoyauFonctionel(IPirates dialogue) {
		this.dialogue = dialogue;
		this.controlJeuPirate = new ControlJeuPirate(this);
	}

	// Appelé par le Main pour lancer le jeu
	@Override
	public void jouer() {
		controlJeuPirate.jouer();
	}

	// ----------INoyauFonctionnel — appelé par le Dialogue----------

	// Appelé par le Dialogue quand l'utilisateur clique sur "Jouer" après avoir
	// saisi les noms
	@Override
	public void soumettreNoms(String nomJ1, String nomJ2) {
		if (ctrlCommencer != null) {
			ctrlCommencer.initialiserJoueurs(nomJ1, nomJ2);
			String premier = ctrlCommencer.determinerQuiCommence();
			dialogue.afficherQuiCommence(premier);
		}
	}

	// Appelé par le Dialogue quand la popup "X commence" est fermée
	@Override
	public void onPopupQuiCommenceFermee() {
		if (ctrlCommencer != null) {
			ICommencerPartie ctrl = ctrlCommencer;
			ctrlCommencer = null;
			ctrl.finCommencerPartie();
		}
	}

	// Appelé par le Dialogue quand l'utilisateur clique sur "Lancer les dés"
	@Override
	public void onBoutonLancerDesClique() {
		if (controlJeuPirate != null) {
			IControlJeuPirate ctrl = controlJeuPirate;
			controlJeuPirate = null;
			ctrl.finAfficherTour();
		}
	}

	// Appelé par le Dialogue quand l'animation des dés est terminée
	@Override
	public void onAnimationDesTerminee() {
		if (ctrlDe != null) {
			ILancerDe ctrl = ctrlDe;
			ctrlDe = null;
			ctrl.finLancerDe();
		}
	}

	// Appelé par le Dialogue quand l'animation du pion est terminée
	@Override
	public void onAnimationDeplacementTerminee() {
		if (ctrlDeplacer != null) {
			IDeplacerPirate ctrl = ctrlDeplacer;
			ctrlDeplacer = null;
			ctrl.finDeplacerPirate(dernierNumCase);
		}
	}

	// Appelé par le Dialogue quand le popup d'une case spéciale est fermé
	@Override
	public void onPopupCaseSpecialeFermee() {
		if (ctrlActiverCase != null) {
			IActiverCase ctrl = ctrlActiverCase;
			ctrlActiverCase = null;
			ctrl.finActiverCase();
		}
	}

	// Appelé par le Dialogue quand l'affichage des Points de Vie est terminé
	@Override
	public void onAffichagePVTermine() {
		if (ctrlPV != null) {
			IPointsDeVie ctrl = ctrlPV;
			ctrlPV = null;
			ctrl.finAfficherPV();
		}
	}

	// Appelé par le Dialogue quand l'écran de fin de partie est fermé
	@Override
	public void onPopupFinPartieFermee() {
		if (ctrlFin != null) {
			IFinDePartie ctrl = ctrlFin;
			ctrlFin = null;
			ctrl.finPartie();
		}
	}

	// ----------IBoundary — appelé par les contrôleurs----------

	@Override
	public void commencerPartie(ICommencerPartie ctrl) {
		this.ctrlCommencer = ctrl;
		dialogue.afficherSaisieNoms();
		// On attend soumettreNoms() (saisie utilisateur)
	}

	@Override
	public void affichageResultatDe(int valeurDe1, int valeurDe2, ILancerDe ilancerDe) {
		this.ctrlDe = ilancerDe;
		dialogue.afficherResultatDes(valeurDe1, valeurDe2);
		// On attend onAnimationDesTerminee() (fin animation)
	}

	@Override
	public void deplacerPirates(String nomPirate, int ancienneCase, int nouvelleCase, IDeplacerPirate iDeplacerPirate) {
		this.ctrlDeplacer = iDeplacerPirate;
		this.dernierNumCase = nouvelleCase;
		dialogue.afficherDeplacement(nomPirate, nouvelleCase);
		// On attend onAnimationDeplacementTerminee() (pion placé)
	}

	@Override
	public void afficherEffetCase(String typeCase, String message, IActiverCase callback) {
		this.ctrlActiverCase = callback;
		dialogue.afficherCaseSpeciale(typeCase, message);
		// On attend onPopupCaseSpecialeFermee() (popup fermé)
	}

	@Override
	public void afficherPointDeVie(String nomPirate, int pv, IPointsDeVie iPointDeVie) {
		this.ctrlPV = iPointDeVie;
		dialogue.afficherPV(nomPirate, pv);
		// On attend onAffichagePVTermine() (affichage terminé)
	}

	@Override
	public void afficherFinDePartie(String nomGagnant, IFinDePartie iFinDePartie) {
		this.ctrlFin = iFinDePartie;
		dialogue.afficherFinPartie(nomGagnant);
		// On attend onPopupFinPartieFermee() (écran fermé)
	}

	@Override
	public void changerJoueurActif(String nomPirate, IControlJeuPirate callback) {
		this.controlJeuPirate = callback;
		dialogue.afficherTourJoueur(nomPirate);
		// On attend onBoutonLancerDesClique() (clic sur Lancer les dés)
	}

	@Override
	public void afficherMessage(String message) {
		dialogue.afficherMessage(message);
		// Pas d'attente pour un simple message textuel
	}

	@Override
	public void demanderUtilisationCoco(IControlCacherDe callback) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from
																		// nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
}
