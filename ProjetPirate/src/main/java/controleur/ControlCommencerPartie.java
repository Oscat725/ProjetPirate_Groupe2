package controleur;

import boundary.interfaces.ICommencerPartie;
import boundary.interfaces.IBoundary;
import entity.De;
import entity.Jeu;
import entity.Joueur;
import entity.Pion;

public class ControlCommencerPartie implements ICommencerPartie {

    private Jeu jeu;
    private IBoundary boundary;
    private ControlJeuPirate controlJeuPirate;

    public ControlCommencerPartie(Jeu jeu, IBoundary boundary, ControlJeuPirate controlJeuPirate) {
        this.jeu = jeu;
        this.boundary = boundary;
        this.controlJeuPirate = controlJeuPirate;
    }

    // Appelé par ControlJeuPirate pour démarrer la partie
    public void commencerPartie() {
        boundary.commencerPartie(this);
    }

    @Override
    public void initialiserJoueurs(String nomJ1, String nomJ2) {
        jeu.setJoueur(0, new Joueur(nomJ1, new Pion()));
        jeu.setJoueur(1, new Joueur(nomJ2, new Pion()));
        
        //initaliser sur la case de depart
        jeu.getPlateau().getCase(0).setJoueur(0, jeu.getJoueur(0));
        jeu.getPlateau().getCase(0).setJoueur(1, jeu.getJoueur(1));
    }

    @Override
    public String determinerQuiCommence() {
        De de = new De();
        int resJ1, resJ2;
        do {
            resJ1 = de.getValeur();
            resJ2 = de.getValeur();
        } while (resJ1 == resJ2);

        if (resJ1 > resJ2) {
            return jeu.getJoueur(0).getNom();
        } else {
            jeu.passerAuJoueurSuivant();
            return jeu.getJoueur(1).getNom();
        }
    }

    @Override
    public void finCommencerPartie() {
        controlJeuPirate.jouerUnTour();
    }
}
