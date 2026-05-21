package controleur;

import java.util.Random;

import boundary.interfaces.IActiverCase;
import boundary.interfaces.IBoundary;
import entity.Case;
import entity.Joueur;

public class ControlActiverCaseCoco extends ControlActiverCaseSpecial implements IActiverCase {

    private static final int DEGATS_MIN = 1;
    private static final int DEGATS_MAX = 2;

    private final ControlPointDeVie controlVie;
    private final ControlCacherDe controlDe;
    private final Random random;
    private final IBoundary iBoundary;
    private int derniersDegats;
    private int joueurCourantIndex;

    public ControlActiverCaseCoco(Joueur[] joueurs, Case caseSpecial, ControlPointDeVie controlVie,
            ControlCacherDe controlDe, IBoundary iBoundary) {
        super(joueurs);
        this.controlVie = controlVie;
        this.controlDe = controlDe;
        this.random = new Random();
        this.iBoundary = iBoundary;
    }

    @Override
    public void activerCase(Case caseCourant, int joueurCourant) {
        this.joueurCourantIndex = joueurCourant;
        derniersDegats = DEGATS_MIN + random.nextInt(DEGATS_MAX - DEGATS_MIN + 1);
        iBoundary.afficherEffetCase("CHUTE DE NOIX DE COCO",
                "Une noix de coco tombe : -" + derniersDegats + " PV", this);
    }

    @Override
    public void finActiverCase() {
        controlDe.setAffecteCoco(joueurs[joueurCourantIndex], true);
        controlVie.perdrePointsDeVie(derniersDegats, joueurs[joueurCourantIndex]);
    }

    public int getDerniersDegats() {
        return derniersDegats;
    }
}
