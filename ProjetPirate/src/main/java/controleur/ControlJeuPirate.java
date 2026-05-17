package controleur;


import boundary.interfaces.IBoundary;
import entity.*;

public class ControlJeuPirate implements boundary.interfaces.ITourJoueur {

    private Jeu jeu;
    private IBoundary boundary;

    // Sous-contrôleurs
    private ControlCommencerPartie controlCommencerPartie;
    private ControleurDe controleurDe;
    private ControlDeplacer controlDeplacer;
    private ControlPointDeVie controlPointDeVie;
    private ControlVerifierFinPartie controlVerifierFinPartie;
    private ControlActiverBombe controlActiverBombe;
    private ControlActiverCaseCoco controlActiverCaseCoco;
    private ControlActiverMystere controlActiverMystere;

    public ControlJeuPirate(IBoundary boundary) {
        this.jeu = new Jeu();
        this.boundary = boundary;

        this.controlCommencerPartie = new ControlCommencerPartie(jeu, boundary, this);
        this.controleurDe = new ControleurDe(jeu, boundary, this);
        this.controlDeplacer = new ControlDeplacer(jeu, boundary, this);
        this.controlPointDeVie = new ControlPointDeVie(boundary, this);
        this.controlVerifierFinPartie = new ControlVerifierFinPartie(boundary, this);
        this.controlActiverBombe = new ControlActiverBombe(boundary, this, controlPointDeVie);
        this.controlActiverCaseCoco = new ControlActiverCaseCoco(boundary, this, controlPointDeVie);
        this.controlActiverMystere = new ControlActiverMystere(boundary, this, controlPointDeVie);
    }

    // Point d'entrée — démarre le jeu (utilisé en mode console et IHM)
    public void jouer() {
        controlCommencerPartie.commencerPartie();
    }

    // Appelé par ControlCommencerPartie.finCommencerPartie() ou
    // Adaptateur.confirmationCommencer()
    public void jouerUnTour() {
        if (controlPointDeVie != null && jeu.getJoueur(0) != null) {
            controlPointDeVie.setJoueurs(jeu.getJoueur(0), jeu.getJoueur(1));
            controlVerifierFinPartie.setJoueurs(jeu.getJoueurs());
        }

        Joueur joueurCourant = jeu.getJoueurCourant();
        boundary.changerJoueurActif(joueurCourant.getNom(), joueurCourant.getPosition(), joueurCourant.getPointDeVie(),
                this);
    }

    @Override
    public void finAfficherTour() {
        lancerDesDuTour();
    }

    // Appelé par finAfficherTour() ou Adaptateur.confirmationTourVu()
    public void lancerDesDuTour() {
        controleurDe.lancerDe();
    }

    // Appelé par ControleurDe.finLancerDe()
    public void apresLancerDe(int sommeDes) {
        controlDeplacer.deplacer(sommeDes);
    }

    // Appelé par ControlDeplacer.finDeplacerPirate()
    public void apresDeplacer() {
        // UC4/5/6 — Vérifier si case spéciale
        Joueur joueurCourant = jeu.getJoueurCourant();
        int position = joueurCourant.getPosition();
        Case caseCourante = jeu.getPlateau().getCase(position);

        if (caseCourante instanceof CaseBombe) {
            controlActiverBombe.activerCase(joueurCourant);
        } else if (caseCourante instanceof CaseCoco) {
            controlActiverCaseCoco.activerCase(joueurCourant);
        } else if (caseCourante instanceof CaseMystere) {
            controlActiverMystere.activerCase(joueurCourant);
        } else {
            boundary.afficherMessage("Case normale, aucun effet.");
            apresCaseSpeciale();
        }
    }

    // Appelé par les contrôleurs de cases spéciales (fin*)
    public void apresCaseSpeciale() {
        // UC9 — Vérifier fin de partie
        controlVerifierFinPartie.verifierEtAfficher();
    }

    // Appelé par ControlPointDeVie.finAfficherPV()
    public void apresAfficherPV() {
        // Continuer le flux (après affichage PV, on continue normalement)
    }

    // Appelé par ControlVerifierFinPartie
    public void apreVerifierFin(boolean partieFinie) {
        if (!partieFinie) {
            jeu.passerAuJoueurSuivant();
            jouerUnTour();
        }
    }

    public Jeu getJeu() {
        return jeu;
    }
}
