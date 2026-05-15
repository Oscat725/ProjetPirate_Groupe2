package controleur;
import entity.Joueur;


public class ControlPointDeVie {
	private Joueur joueur1;
	private Joueur joueur2;
	
	public ControlPointDeVie(Joueur joueur1, Joueur joueur2) {
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
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
    }
	
	public boolean verifierPointsDeVie(Joueur joueur) {
        return joueur.getPointDeVie() == 0;
    }
	
	//permet au controller ControlCommecnerPartie de reset les PV des 2 joueurs 
	public void resetPointsDeVie() {
        joueur1.setPointDeVie(Joueur.PV_MAX);
        joueur2.setPointDeVie(Joueur.PV_MAX);
    }

}
