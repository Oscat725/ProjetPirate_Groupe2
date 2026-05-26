package noyau_fonctionnel;

import boundary.interfaces.*;
import controleur.ControlJeuPirate;
import interface_noyau_fonctionnel.INoyauFonctionnel;
import interface_noyau_fonctionnel.IPirates;

// Adaptateur qui connecte l'IHM au noyau fonctionnel (contrôleurs).

public class AdaptateurNoyauFonctionel implements IBoundary, INoyauFonctionnel {

	private IPirates dialogue;
	private IControlJeuPirate controlJeuPirate;

	private ICommencerPartie ctrlCommencer;
	private ILancerDe ctrlDe;
	private IDeplacerPirate ctrlDeplacer;
	private IActiverCase ctrlActiverCase;
	private IPointsDeVie ctrlPV;
	private IFinDePartie ctrlFin;
	private IControlCacherDe ctrlCoco;
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

	// ÉTAPE 1 : Commencer la partie (saisie des noms)

	// Noyau → Adaptateur : demande d'afficher la saisie des noms
	@Override
	public void commencerPartie(ICommencerPartie ctrl) {
		this.ctrlCommencer = ctrl;
		dialogue.afficherSaisieNoms();
	}

	// Dialogue → Adaptateur : l'utilisateur a saisi les noms et cliqué "Jouer"
	@Override
	public void soumettreNoms(String nomJ1, String nomJ2) {
		if (ctrlCommencer != null) {
			ctrlCommencer.initialiserJoueurs(nomJ1, nomJ2);
			String premier = ctrlCommencer.determinerQuiCommence();
			dialogue.afficherQuiCommence(premier);
		}
	}

	// Dialogue → Adaptateur : la popup "X commence" est fermée
	@Override
	public void onPopupQuiCommenceFermee() {
		if (ctrlCommencer != null) {
			ICommencerPartie ctrl = ctrlCommencer;
			ctrlCommencer = null;
			ctrl.finCommencerPartie();
		}
	}

	// ÉTAPE 2 : Tour du joueur (afficher à qui c'est le tour)

	// Noyau → Adaptateur : afficher le tour du joueur actif
	@Override
	public void changerJoueurActif(String nomPirate, IControlJeuPirate callback) {
		this.controlJeuPirate = callback;
		dialogue.afficherTourJoueur(nomPirate);
	}

	// Dialogue → Adaptateur : le joueur a cliqué sur "Lancer les dés"
	@Override
	public void onBoutonLancerDesClique() {
		if (controlJeuPirate != null) {
			IControlJeuPirate ctrl = controlJeuPirate;
			controlJeuPirate = null;
			ctrl.finAfficherTour();
		}
	}

	// ÉTAPE 3 : Lancer les dés (animation)

	// Noyau → Adaptateur : afficher le résultat des dés
	@Override
	public void affichageResultatDe(int valeurDe1, int valeurDe2, ILancerDe ilancerDe) {
		this.ctrlDe = ilancerDe;
		dialogue.afficherResultatDes(valeurDe1, valeurDe2);
	}

	// Dialogue → Adaptateur : l'animation des dés est terminée
	@Override
	public void onAnimationDesTerminee() {
		if (ctrlDe != null) {
			ILancerDe ctrl = ctrlDe;
			ctrlDe = null;
			ctrl.finLancerDe();
		}
	}

	// ÉTAPE 4 : Déplacer le pion (drag and drop)

	// Noyau → Adaptateur : animer le déplacement du pion
	@Override
	public void deplacerPirates(String nomPirate, int ancienneCase, int nouvelleCase, IDeplacerPirate iDeplacerPirate) {
		this.ctrlDeplacer = iDeplacerPirate;
		this.dernierNumCase = nouvelleCase;
		dialogue.afficherDeplacement(nomPirate, nouvelleCase + 1);
	}

	// Dialogue → Adaptateur : le pion a été placé correctement
	@Override
	public void onAnimationDeplacementTerminee() {
		if (ctrlDeplacer != null) {
			IDeplacerPirate ctrl = ctrlDeplacer;
			ctrlDeplacer = null;
			ctrl.finDeplacerPirate(dernierNumCase);
		}
	}

	// ÉTAPE 5 : Case spéciale (popup effet)

	// Noyau → Adaptateur : afficher l'effet de la case spéciale
	@Override
	public void afficherEffetCase(String typeCase, String message, IActiverCase callback, int effect, int value) {
		this.ctrlActiverCase = callback;
		dialogue.afficherCaseSpeciale(typeCase, message, effect, value);
	}

	// Dialogue → Adaptateur : le popup de case spéciale est fermé
	@Override
	public void onPopupCaseSpecialeFermee() {
		if (ctrlActiverCase != null) {
			IActiverCase ctrl = ctrlActiverCase;
			ctrlActiverCase = null;
			ctrl.finActiverCase();
		}
	}

	// ÉTAPE 5b : Case Coco (demander si le joueur veut cacher un dé)

	// Noyau → Adaptateur : demander au joueur s'il veut utiliser le coco
	@Override
	public void demanderUtilisationCoco(IControlCacherDe callback) {
		this.ctrlCoco = callback;
		dialogue.afficherChoixCoco();
	}

	// Dialogue → Adaptateur : le joueur a répondu à la question du coco
	@Override
	public void onReponseCoco(String reponse) {
		if (ctrlCoco != null) {
			IControlCacherDe ctrl = ctrlCoco;
			ctrlCoco = null;
			ctrl.finDemandeCoco(reponse);
		}
	}

	// ÉTAPE 6 : Points de vie (mise à jour PV)

	// Noyau → Adaptateur : mettre à jour les points de vie
	@Override
	public void afficherPointDeVie(String nomPirate, int pv, IPointsDeVie iPointDeVie) {
		this.ctrlPV = iPointDeVie;
		dialogue.afficherPV(nomPirate, pv);
	}

	// Dialogue → Adaptateur : l'affichage des PV est terminé
	@Override
	public void onAffichagePVTermine() {
		if (ctrlPV != null) {
			IPointsDeVie ctrl = ctrlPV;
			ctrlPV = null;
			ctrl.finAfficherPV();
		}
	}

	// ÉTAPE 7 : Fin de partie

	// Noyau → Adaptateur : afficher l'écran de fin de partie
	@Override
	public void afficherFinDePartie(String nomGagnant, IFinDePartie iFinDePartie) {
		this.ctrlFin = iFinDePartie;
		dialogue.afficherFinPartie(nomGagnant);
	}

	// Dialogue → Adaptateur : l'écran de fin de partie est fermé
	@Override
	public void onPopupFinPartieFermee() {
		if (ctrlFin != null) {
			IFinDePartie ctrl = ctrlFin;
			ctrlFin = null;
			ctrl.finPartie();
		}
	}

	// Messages simples (pas d'attente)

	@Override
	public void afficherMessage(String message) {
		dialogue.afficherMessage(message);
	}
}
