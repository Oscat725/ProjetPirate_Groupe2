package presentation;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainFrame extends JFrame {

    private dialogue.Dialogue dialogue;

    // Composants du jeu
    private PanelPlateau plateau;
    private PanelDe de1;
    private PanelDe de2;
    private JButton boutonLancer;
    private JButton infoButton;
    private JLabel titre;

    public MainFrame() {
        setTitle("Isla de la Muerte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On met directement le panel du jeu dans la fenêtre pour l'instant
        // (Le CardLayout et le PanelDemarrage seront ajoutés par Yoakin plus tard)
        JPanel panelJeu = setUpJeuPanel();
        add(panelJeu);

        pack();
        setLocationRelativeTo(null);
    }

    // LA MÉTHODE QUI REGROUPE TOUT LE VISUEL ACTUEL(Géré par Yoakin)
    private JPanel setUpJeuPanel() {
        JPanel p = new JPanel(new BorderLayout());

        JPanel panelHaut = new JPanel(new BorderLayout());

        // --- Panel Dés (gauche) ---
        JPanel panelDes = new JPanel();
        panelDes.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        panelDes.setPreferredSize(new Dimension(190, 150));
        panelDes.setLayout(new BoxLayout(panelDes, BoxLayout.Y_AXIS));

        JPanel ligneDes = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
        de1 = new PanelDe();
        de2 = new PanelDe();
        ligneDes.add(de1);
        ligneDes.add(de2);

        boutonLancer = new JButton("Lancer les dés");
        boutonLancer.setAlignmentX(CENTER_ALIGNMENT);
        boutonLancer.addActionListener(e -> {
            if (dialogue != null)
                dialogue.lancerDes();
        });

        JPanel panelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBouton.add(boutonLancer);

        panelDes.add(ligneDes);
        panelDes.add(panelBouton);

        panelHaut.add(panelDes, BorderLayout.WEST);

        // --- Panel Info (centre) ---
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        panelInfo.setPreferredSize(new Dimension(300, 150));

        JPanel panelTitre = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        panelTitre.setPreferredSize(new Dimension(300, 70));

        titre = new JLabel("Isla de la muerte");
        titre.setFont(new Font("Algerian", Font.BOLD | Font.ITALIC, 36));
        titre.setForeground(new Color(10, 10, 10));
        titre.setPreferredSize(new Dimension(350, 50));
        panelTitre.add(titre);

        infoButton = new JButton("?");
        infoButton.setBackground(new Color(186, 224, 255));
        infoButton.setFont(new Font("Segoe UI", Font.BOLD, 10));
        infoButton.setToolTipText("Afficher les règles du jeu");
        infoButton.addActionListener(e -> afficherRegles());
        panelTitre.add(infoButton);

        panelInfo.add(panelTitre, BorderLayout.NORTH);
        panelHaut.add(panelInfo, BorderLayout.CENTER);

        p.add(panelHaut, BorderLayout.NORTH);

        // ===== PANEL PLATEAU (centre) =====
        JPanel conteneurPlateau = new JPanel(new BorderLayout());
        conteneurPlateau.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        plateau = new PanelPlateau();
        conteneurPlateau.add(plateau, BorderLayout.CENTER);

        p.add(conteneurPlateau, BorderLayout.CENTER);

        return p;
    }

    public void setDialogue(dialogue.Dialogue dialogue) {
        this.dialogue = dialogue;
        de1.setOnAnimationFinie(() -> dialogue.animationDeTerminee());
        de2.setOnAnimationFinie(() -> dialogue.animationDeTerminee());
        plateau.setOnPionPlace(caseNumero -> dialogue.pionPlaceCorrectement(caseNumero));
        dialogue.demarrerJeu();
    }

    public void activerBouton(boolean actif) {
        boutonLancer.setEnabled(actif);
    }

    public void afficherDes(int r1, int r2) {
        de1.lancerAnimation(r1);
        de2.lancerAnimation(r2);
    }

    public void activerDrag(int joueurIndex, int cible) {
        plateau.activerDrag(joueurIndex, cible);
    }

    public void deplacerPion(int joueur, int caseNumero, java.awt.Color couleur) {
        plateau.deplacerPion(joueur, caseNumero, couleur);
    }

    public void afficherBombe() {
        JDialog d = new JDialog(this, "Bombe", true);
        // (sera remplacé par Emin)

    }

    public void log(String message) {
        // Laissé vide, en attendant que Mélanie ajoute son JTextArea dans setUpJeuPanel
    }

    private void afficherRegles() {
        JOptionPane.showMessageDialog(
                this,
                "Règles du jeu des pirates :\n"
                        + "-2 joueurs\n"
                        + "-30 cases\n"
                        + "-5 points de vie maximum\n"
                        + "-Le premier à la dernière case gagne ou on gagne si l’adversaire n’a plus de vie\n\n"
                        + "Cases spéciales :\n"
                        + "-Case Bombe\n"
                        + "-Case mystère\n"
                        + "-Chute de noix de coco\n"
                        + "BONNE CHANCE",
                "Règles du jeu",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
