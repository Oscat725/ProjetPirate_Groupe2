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
    private JTextArea zoneTexte; // affichage des du deroulement du Jeu

    // CardLayout pour naviguer entre les écrans
    private CardLayout cardLayout;
    private JPanel panelPrincipal;
    private PanelDemarrage panelDemarrage;

    // Panels joueurs (pour pouvoir les mettre à jour depuis Dialogue)
    private PanelJoueur panelJoueur1;
    private PanelJoueur panelJoueur2;

    // Yoakin
    public MainFrame() {
        setTitle("Isla de la Muerte");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Mise en place du CardLayout pour naviguer entre les écrans
        // -> C'est à dire entre les deux cartes ci-dessous
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        // Carte 1 = Écran de démarrage avec saisie de texte/nom
        panelDemarrage = new PanelDemarrage(this);
        panelPrincipal.add(panelDemarrage, "Demarrage");

        // Carte 2 = Écran de jeu (plateau, dés, joueurs, etc.)
        JPanel panelJeu = setUpJeuPanel();
        panelPrincipal.add(panelJeu, "JEU");

        // Ajouter le conteneur principal à la fenêtre
        add(panelPrincipal);

        // Afficher l'écran de démarrage en premier
        cardLayout.show(panelPrincipal, "Demarrage");

        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        // Taille Fenêtre => 55% de la largeur et 55% de la hauteur de l'écran
        int largeur = (int) (tailleEcran.width * 0.55);
        int hauteur = (int) (tailleEcran.height * 0.55);
        setSize(largeur, hauteur);

        int centreX = tailleEcran.width / 2;
        int centreY = tailleEcran.height / 2;
        setLocation(centreX - (largeur / 2), centreY - (hauteur / 2));

    }

    // Yoakin
    private JPanel setUpJeuPanel() {
        JPanel panelGeneral = new JPanel(new BorderLayout());

        // Section haute - contiendra le panel "dés" et le panel "infos"
        JPanel panelHaut = new JPanel(new BorderLayout());

        // Panel Dés (en haut à gauche)
        JPanel panelDes = new JPanel();
        panelDes.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        panelDes.setPreferredSize(new Dimension(190, 150));
        panelDes.setLayout(new BoxLayout(panelDes, BoxLayout.Y_AXIS));

        // Ligne contenant les 2 dés ensembles
        JPanel ligneDes = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
        de1 = new PanelDe();
        de2 = new PanelDe();
        ligneDes.add(de1);
        ligneDes.add(de2);

        // Bouton pour lancer les dés
        boutonLancer = new JButton("Lancer les dés");
        boutonLancer.setAlignmentX(CENTER_ALIGNMENT);
        boutonLancer.addActionListener(e -> {
            if (dialogue != null)
                dialogue.onBoutonLancerDesClique();
        });

        JPanel panelBouton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBouton.add(boutonLancer);

        panelDes.add(ligneDes);
        panelDes.add(panelBouton);

        panelHaut.add(panelDes, BorderLayout.WEST);

        // Titre du jeu + bouton "?"
        JPanel panelTitre = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));

        titre = new JLabel("Isla de la muerte");
        titre.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 36));
        titre.setForeground(new Color(10, 10, 10));
        panelTitre.add(titre);

        infoButton = new JButton("?");
        infoButton.setBackground(new Color(186, 224, 255));
        infoButton.setFont(new Font("Segoe UI", Font.BOLD, 10));
        infoButton.setToolTipText("Afficher les règles du jeu");
        infoButton.addActionListener(e -> afficherRegles());
        panelTitre.add(infoButton);

        // Infos (CENTER) : titre + bouton règles + panels joueurs
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.add(panelTitre, BorderLayout.NORTH);

        // Boite de texte du déroulement de jeu
        zoneTexte = new JTextArea(5, 40);
        zoneTexte.setEditable(false);
        zoneTexte.setFont(new Font("Calibri Light", Font.ITALIC, 12));
        JScrollPane texteDeroulement = new JScrollPane(zoneTexte);
        texteDeroulement.setBorder(BorderFactory.createTitledBorder("Déroulement du jeu"));
        panelInfo.add(texteDeroulement, BorderLayout.CENTER);

        
        panelHaut.add(panelInfo, BorderLayout.CENTER);

        // Panels des 2 joueurs
        panelJoueur1 = new PanelJoueur("Joueur 1", Color.RED);
        panelJoueur2 = new PanelJoueur("Joueur 2", Color.BLUE);

        JPanel panelJoueurs = new JPanel(new GridLayout(2, 1));
        panelJoueurs.setPreferredSize(new Dimension(300, 150));
        panelJoueurs.add(panelJoueur1);
        panelJoueurs.add(panelJoueur2);
        panelHaut.add(panelJoueurs, BorderLayout.EAST);
        
        panelGeneral.add(panelHaut, BorderLayout.NORTH);

        // PANEL PLATEAU (en bas au centre)
        JPanel conteneurPlateau = new JPanel(new BorderLayout());
        conteneurPlateau.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        plateau = new PanelPlateau();
        plateau.setCouleurs(Color.RED, Color.BLUE);
        conteneurPlateau.add(plateau, BorderLayout.CENTER);

        panelGeneral.add(conteneurPlateau, BorderLayout.CENTER);
        return panelGeneral;
    }

    public void setDialogue(dialogue.Dialogue dialogue) {
        this.dialogue = dialogue;
        de1.setOnAnimationFinie(() -> dialogue.onAnimationDesTerminee());
        de2.setOnAnimationFinie(() -> dialogue.onAnimationDesTerminee());
        plateau.setOnPionPlace(caseNumero -> dialogue.onAnimationDeplacementTerminee(caseNumero));
        // Yoakin -> plus besoin si on passe par la carte "Demarrage" (clique du bouton
        // "Jouer" commence le jeu)
        // dialogue.demarrerJeu();
    }

    // Yoakin
    void basculerVersJeu(String nom1, String nom2) {
        // Mettre à jour les noms dans les PanelJoueur
        panelJoueur1.setNom(nom1);
        panelJoueur2.setNom(nom2);

        if (dialogue != null)
            dialogue.onNomsSaisis(nom1, nom2);

        setExtendedState(JFrame.MAXIMIZED_BOTH); // mettre en plein ecran
        cardLayout.show(panelPrincipal, "JEU");
    }

    // MÉTHODES D'AFFICHAGE APPELÉES PAR LE DIALOGUE

    public void afficherEcranDemarrage() {
        this.setVisible(true);
    }

    public void afficherPopupQuiCommence(String nomPremier) {
        // on attendant que l'on implemente
        JOptionPane.showMessageDialog(this, nomPremier + " commence ! ");
    }

    public void mettreEnSurbrillanceJoueur(int joueurActifIndex) {
        panelJoueur1.setActif(joueurActifIndex == 0);
        panelJoueur2.setActif(joueurActifIndex == 1);
    }

    public void activerBouton(boolean actif) {
        boutonLancer.setEnabled(actif);
    }

    public void afficherDes(int r1, int r2) {
        de2.setEnabled(r2 > 0);
        de1.lancerAnimation(r1);
        if (r2 > 0) {
            de2.lancerAnimation(r2);
        } else {
            dialogue.onAnimationDesTerminee();
        }
    }

    public void activerDrag(int joueurIndex, int cible) {
        plateau.activerDrag(joueurIndex, cible);
    }

    public void deplacerPion(int joueur, int caseNumero) {
        plateau.deplacerPion(joueur, caseNumero);
    }

    public void setCouleurs(Color j1, Color j2) {
        plateau.setCouleurs(j1, j2);
    }

    public void afficherBombe(int value) {
        JDialog d = new JDialog(this, "Bombe", true);
        // (sera remplacé par Emin)
        dialogue.onPopupCaseSpecialeFermee();
    }

    /*
     * L'effect de cette methode est identique a leffect de la case mystere
     * (0:avancer ; 1:reculer ; 2: gagner pv)
     * Le value aussi est pareil, la case mystere le determine
     * Il rajoute le panel mystere avec l'effect et la valeur correspondant au
     * dialogue
     */
    public void afficherMystere(int effect, int value) {
        JWindow d = new JWindow(this);
        d.add(new PanelMystere(effect, value));
        d.pack();
        d.setLocationRelativeTo(this);
        d.setVisible(true);
        dialogue.onPopupCaseSpecialeFermee();
    }

    public void afficherCoco(int value) {
        JDialog d = new JDialog(this, "Coco", true);
        d.add(new PanelCoco(value));
        d.pack();
        d.setLocationRelativeTo(this);
        d.setVisible(true);
        dialogue.onPopupCaseSpecialeFermee();
    }

    public String afficherPopupChoixCoco() {
        // TODO
        return "oui";
    }

    public void mettreAJourPV(int indexJoueur, int pv) {
        if (indexJoueur == 0) {
            panelJoueur1.setPointsDeVie(pv);
        } else {
            panelJoueur2.setPointsDeVie(pv);
        }
    }

    public void afficherEcranFinPartie(String nomGagnant) {
        JOptionPane.showMessageDialog(this, "LA PARTIE EST TERMINE !\nBravo au gagnant : " + nomGagnant, "Victoire", + JOptionPane.WARNING_MESSAGE);
      // System.exit(0); // fermer le jeu à la fin
    }

    // Melanie
    public void log(String message) {
        if (zoneTexte == null) {
            return;
        }
        zoneTexte.append(message + "\n");
        zoneTexte.setCaretPosition(zoneTexte.getDocument().getLength());
    }

    // AUTRES MÉTHODES UTILITAIRES

    public PanelJoueur getPanelJoueur1() {
        return panelJoueur1;
    }

    public PanelJoueur getPanelJoueur2() {
        return panelJoueur2;
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
                        + "-Case chute de noix de coco\n"
                        + "BONNE CHANCE",
                "Règles du jeu",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void afficherEffetBombe(String message) {
    JDialog dialogBombe = new JDialog(this, "Case Bombe", true);
    PanelEffetBombe panelBombe = new PanelEffetBombe(dialogBombe, message);
    
    dialogBombe.add(panelBombe);
    dialogBombe.setSize(650, 650);
    dialogBombe.setLocationRelativeTo(this); 
    
    // écoutuer qui prévient quand le jeu se termine
    dialogBombe.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            dialogue.onPopupCaseSpecialeFermee();
        }
    });

    dialogBombe.setVisible(true); // affiche le dialogue 
}
}
