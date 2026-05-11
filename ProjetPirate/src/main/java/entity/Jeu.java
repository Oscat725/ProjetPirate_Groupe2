package entity;

public class Jeu {

    private int currentJoueur;
    private final Plateau plateau;
    private final Joueur[] joueurs;
    //private final De deUn;
    //private final De deDeux;

    public Jeu() {
        this.currentJoueur = 0; 
        this.plateau = new Plateau(); 
        this.joueurs = new Joueur[2];
        //this.deUn = new De(); 
        //this.deDeux = new De(); modification de structure
    }

    public int getCurrentJouer() {
        return currentJoueur;
    }

}