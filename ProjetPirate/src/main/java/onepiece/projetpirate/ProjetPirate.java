/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package onepiece.projetpirate;

/**
 *
 * @author pns5022a
 */
public class ProjetPirate {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            boundary.MainFrame frame = new boundary.MainFrame();
            dialogue.Dialogue dialogue = new dialogue.Dialogue(frame);
            frame.setDialogue(dialogue);
            frame.setVisible(true);
        });
    }
}
