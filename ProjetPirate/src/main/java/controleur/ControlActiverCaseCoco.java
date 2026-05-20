package controleur;

import java.util.Random;

import boundary.interfaces.IBoundary;
import entity.Case;
import entity.Joueur;

public class ControlActiverCaseCoco extends ControlActiverCaseSpecial {

	private static final int DEGATS_MIN = 1;
	private static final int DEGATS_MAX = 2;

	private final ControlPointDeVie controlVie;
	private final ControlCacherDe controlDe;
	private final Random random;
	private int derniersDegats;
	private IBoundary iBoundary;

	public ControlActiverCaseCoco(Joueur[] joueurs, Case caseSpecial, ControlPointDeVie controlVie,
			ControlCacherDe controlDe, IBoundary iBoundary) {
		super(joueurs);
		this.controlVie = controlVie;
		this.controlDe = controlDe;
		this.random = new Random();
		this.derniersDegats = 0;
		this.iBoundary= iBoundary;
	}

    @Override
    public void activerCase(Case caseCourant, int joueurCourant) {
        // 1) 1 ou 2
        derniersDegats = DEGATS_MIN + random.nextInt(DEGATS_MAX - DEGATS_MIN + 1);
        
        //affichage chute de noix de coco
        if(iBoundary != null) {
        iBoundary.afficherEffetCase(
            "CHUTE DE NOIX DE COCO","Une noix de coco tombe : -" + derniersDegats + " PV");
        }
        // 2) //On met les dégats sur le joueurs
        controlVie.perdrePointsDeVie(derniersDegats, joueurs[joueurCourant]);
        
        // 3) True car effecté par la coco
        controlDe.setAffecteCoco(joueurs[joueurCourant], true);
    }

	public int getDerniersDegats() {
		return derniersDegats;
	}


}