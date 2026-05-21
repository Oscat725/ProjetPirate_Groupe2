package console;


public class MainConsole {
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            presentation.MainFrame frame = new presentation.MainFrame();
            dialogue.Dialogue dialogue = new dialogue.Dialogue(frame);
            frame.setDialogue(dialogue);
            frame.setVisible(true);
        });
    }
}