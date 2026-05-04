package dialogue;

import boundary.MainFrame;
import interface_noyau_fonctionnel.INoyauFonctionnel;
import interface_noyau_fonctionnel.IPirates;

public class Dialogue implements IPirates {

    private MainFrame mainFrame;
    private INoyauFonctionnel noyau;
    private int desTermines = 0;
    private int r1, r2;
    private int positionJoueur = 0;

    public Dialogue(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void setNoyau(INoyauFonctionnel noyau) {
        this.noyau = noyau;
    }

    public void lancerDes() {
        mainFrame.activerBouton(false);
        if (noyau != null) {
            noyau.lancerDes();
        } else { // on attendant que l'adaptateur soit fait
            r1 = (int)(Math.random() * 6) + 1;
            r2 = (int)(Math.random() * 6) + 1;
            mainFrame.afficherDes(r1, r2);
        }
    }

    public void animationDeTerminee() {
        desTermines++;
        if (desTermines == 2) {
            desTermines = 0;
            int cible = positionJoueur + r1 + r2;
            if (cible > 30) cible = 30;
            mainFrame.activerDrag(cible);
        }
    }

    public void pionPlaceCorrectement(int caseNumero) {
        positionJoueur = caseNumero;
        mainFrame.deplacerPion(0, caseNumero, java.awt.Color.RED);
        mainFrame.activerBouton(true);
    }

    @Override
    public void permettreLancerDes(boolean actif) {
        mainFrame.activerBouton(actif);
    }

    @Override
    public void setCaseCible(int caseNumero) {
        mainFrame.activerDrag(caseNumero);
    }
}
