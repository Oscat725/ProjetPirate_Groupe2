package console;

import controleur.*;

public class MainConsole {
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            presentation.MainFrame frame = new presentation.MainFrame();
            dialogue.Dialogue dialogue = new dialogue.Dialogue(frame);
            frame.setDialogue(dialogue);
            frame.setVisible(true);
        });
//        BoundaryConsole boundary = new BoundaryConsole();
//        
//        ControlJeuPirate controlJeu = new ControlJeuPirate(boundary);
// 
//        controlJeu.jouer();
    }
}