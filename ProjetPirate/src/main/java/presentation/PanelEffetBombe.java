package presentation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelEffetBombe extends JPanel{
        
        public PanelEffetBombe(JDialog dialogueParent){
            setLayout(new BorderLayout(10,10));
            setBackground(new Color(255, 102, 102));
            
       //   texte
        JLabel message = new JLabel("BOOOM", SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, 14));
        add(message, BorderLayout.CENTER);
        
        JButton notreBoutonContinuer = new JButton("Continuer");
        notreBoutonContinuer.addActionListener(e -> dialogueParent.dispose()); 
        add(notreBoutonContinuer, BorderLayout.SOUTH);
        }
        

}
