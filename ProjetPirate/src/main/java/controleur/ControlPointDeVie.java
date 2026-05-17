package controleur;
import entity.*;
import boundary.interfaces.*;


public class ControlPointDeVie implements IPointsDeVie{
	private Jeu jeu;
	private IBoundary boundary;
	private ControlJeuPirate controlJeuPirate;
	
	public ControlPointDeVie(Jeu jeu, IBoundary boundary, ControlJeuPirate controlJeuPirate) {
		this.jeu = jeu;
		this.boundary = boundary;
		this.controlJeuPirate = controlJeuPirate;
	}
	
	public void perdrePointsDeVie(int nb, Joueur joueur) {
		if (nb <= 0) {
            throw new IllegalArgumentException("La quantité de PV doit être positive");
        }
		int nouveauxPV = joueur.getPointDeVie()-nb;
		if (nouveauxPV < 0) {
		    nouveauxPV = 0;
		}
		joueur.setPointDeVie(nouveauxPV);
		//le controleur demande au boundary d'afficher les PV 
		//laboundary rappelle finAfficherPV() quannd c'est fait
		boundary.afficherPointDeVie(joueur.getNom(), nouveauxPV, this);
	}
	
	public void gagnerPointsDeVie(int nb, Joueur joueur) {
        if (nb <= 0) {
            throw new IllegalArgumentException("La quantité de PV doit être positive");
        }
        int nouveauxPV = joueur.getPointDeVie() +nb;
        if (nouveauxPV > Joueur.PV_MAX) {
            nouveauxPV = Joueur.PV_MAX;//le joueur ne peut pas avoir plus de 5 points de vie          
        }
        joueur.setPointDeVie(nouveauxPV);
        joueur.setPointDeVie(nouveauxPV);
        boundary.afficherPointDeVie(joueur.getNom(), nouveauxPV, this);
    }
	
	public boolean verifierPointsDeVie(Joueur joueur) {
        return joueur.getPointDeVie() == 0;
    }
	
	//permet au controller ControlCommecnerPartie de reset les PV des 2 joueurs 
	public void resetPointsDeVie() {
		 jeu.getJoueur(0).setPointDeVie(Joueur.PV_MAX);
	     jeu.getJoueur(1).setPointDeVie(Joueur.PV_MAX);
    }
	
    //la boundary appelle calculerPV() si elle a besoin des PV avant affichage
    @Override
    public int calculerPV() {
        return jeu.getJoueurCourant().getPointDeVie();
    }
 
    //la boundary appelle finAfficherPV() une fois l'affichage terminé
    //le contrôleur chaîne vers ControlJeuPirate (contrôleur principal)
    @Override
    public void finAfficherPV() {
        //controlJeuPirate.apresAfficherPV();
    }

}
