package entity;

public class Jeu {

    private int joueurCourant;
    private final Plateau plateau;
    private final Joueur[] joueurs;
    //private final De deUn;
    //private final De deDeux;

    public Jeu() {
        this.joueurCourant = 0; 
        this.plateau = new Plateau(); 
        this.joueurs = new Joueur[2];
        //this.deUn = new De(); 
        //this.deDeux = new De(); modification de structure
    }

    public int getIndiceJoueurCourant() {
        return joueurCourant;
    }

    public Joueur getJoueurCourant() {
        return joueurs[joueurCourant];
    }
    
    public Plateau getPlateau() {
		return plateau;
	}

    public void setJoueur(int index, Joueur joueur) {
        joueurs[index] = joueur;
    }

    public Joueur getJoueur(int index) {
        return joueurs[index];
    }

    public Joueur[] getJoueurs() {
    	return joueurs;
    }
    
    public void passerAuJoueurSuivant() {
    		joueurCourant = (joueurCourant + 1) % 2;
    }
    

}