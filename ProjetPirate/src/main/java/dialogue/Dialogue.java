package dialogue;

import boundary.MainFrame;
import interface_noyau_fonctionnel.INoyauFonctionnel;
import interface_noyau_fonctionnel.IPirates;

public class Dialogue implements IPirates {

    private MainFrame mainFrame;
    private INoyauFonctionnel noyau;
    private int desTermines = 0;

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
            int r1 = (int)(Math.random() * 6) + 1;
            int r2 = (int)(Math.random() * 6) + 1;
            mainFrame.afficherDes(r1, r2);
        }
    }

    public void animationDeTerminee() {
        desTermines++;
        if (desTermines == 2) {
            desTermines = 0;
            mainFrame.activerBouton(true);
        }
    }

    @Override
    public void permettreLancerDes(boolean actif) {
        mainFrame.activerBouton(actif);
    }
}
