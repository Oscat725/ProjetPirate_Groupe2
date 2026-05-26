package console;

import noyau_fonctionnel.AdaptateurNoyauFonctionel;
import dialogue.Dialogue;
import presentation.MainFrame;

public class MainIHM {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            Dialogue dialogue = new Dialogue(frame);
            frame.setDialogue(dialogue);

            AdaptateurNoyauFonctionel adaptateur = new AdaptateurNoyauFonctionel(dialogue);
            dialogue.setAdaptateur(adaptateur);
            adaptateur.jouer();
        });
    }
}
