package controleur;

import boundary.interfaces.IActiverCase;
import boundary.interfaces.IBoundary;
import entity.Case;
import entity.CaseMystere;
import entity.Joueur;

public class ControlActiverCaseMystere extends ControlActiverCaseSpecial implements IActiverCase {

    private final ControlPointDeVie controlVie;
    private final ControlDeplacer controlDeplacerPirate;
    private final IBoundary iBoundary;
    private int joueurCourantIndex;
    private int storedEffect;
    private int storedValue;

    public ControlActiverCaseMystere(Joueur[] joueurs, int joueurCourant, ControlPointDeVie controlVie,
            ControlJeuPirate controlJeuPirate, IBoundary iBoundary) {
        super(joueurs);
        this.controlVie = controlVie;
        this.controlDeplacerPirate = new ControlDeplacer(controlJeuPirate.getJeu(), iBoundary, controlJeuPirate);
        this.iBoundary = iBoundary;
    }

    @Override
    void activerCase(Case caseSpecial, int joueurCourant) {
        if (caseSpecial instanceof CaseMystere caseMystere) {
            this.joueurCourantIndex = joueurCourant;
            caseMystere.activerCase();
            storedEffect = caseMystere.effect;
            storedValue = caseMystere.getValue(); 
			String message;
			switch (storedEffect) {
                case 0:
					message = "Le joueur avance de " + storedValue + " cases";
					break;
                case 1:
					message = "Le joueur recule de " + storedValue + " cases";
					break;
                case 2:
					message = "Le joueur gagne " + storedValue + " PV";
					break;
                default:
					throw new IllegalArgumentException("Effet mystere inconnu : " + storedEffect);		 
            }
            iBoundary.afficherEffetCase("MYSTERE", message, this,storedEffect,storedValue);
        }
    }

    @Override
    public void finActiverCase() {
        switch (storedEffect) {
            case 0:
				controlDeplacerPirate.deplacerPirate(storedValue);
				break;
            case 1:
				controlDeplacerPirate.deplacerPirate(-storedValue);
				break;
            case 2:
				controlVie.gagnerPointsDeVie(storedValue, joueurs[joueurCourantIndex]);
				break;
        }
    }
}
