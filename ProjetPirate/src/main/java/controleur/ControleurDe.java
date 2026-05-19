package controleur;

import boundary.interfaces.ILancerDe;
import boundary.interfaces.IBoundary;
import entity.Jeu;

// Nolawi
public class ControleurDe implements ILancerDe {

    private Jeu jeu;
    private IBoundary boundary;
    private ControlJeuPirate controlJeuPirate;
    private int[] resultatDes;

    public ControleurDe(Jeu jeu, IBoundary boundary, ControlJeuPirate controlJeuPirate) {
        this.jeu = jeu;
        this.boundary = boundary;
        this.controlJeuPirate = controlJeuPirate;
    }

    // Appelé par ControlJeuPirate
    public void lancerDe() {
        resultatDes = jeu.getJoueurCourant().lancerDe();
       
        boundary.affichageResultatDe(resultatDes[0], resultatDes[1], this);
    }
    
    //Appele pour les fontion generales
    public int lancerDesModif(int nbDes, int valeurMax, int valeurMin) {
		return jeu.getJoueurCourant().lancerDesGeneral(nbDes, valeurMax, valeurMin);
	}

    @Override
    public int resultatDe() {
        return resultatDes[0] + resultatDes[1];
    }

    public int[] getresultatDes() {
        return resultatDes;
    }

    @Override
    public void finLancerDe() {
        controlJeuPirate.apresLancerDe(resultatDes[0] + resultatDes[1]);
    }
}
