package presentation;

import javax.swing.*;
import java.awt.*;

// Nolawi
public class PanelCase extends JPanel {

    public static final String NORMALE = "NORMALE";
    public static final String BOMBE = "BOMBE";
    public static final String COCO = "COCO";
    public static final String MYSTERE = "MYSTERE";
    public static final String ARRIVEE = "ARRIVEE";

    public static final String HORIZONTAL = "HORIZONTAL";
    public static final String COIN_DROITE_HAUT = "COIN_DROITE_HAUT";
    public static final String COIN_DROITE_BAS = "COIN_DROITE_BAS";
    public static final String COIN_GAUCHE_HAUT = "COIN_GAUCHE_HAUT";
    public static final String COIN_GAUCHE_BAS = "COIN_GAUCHE_BAS";

    private static final Color COULEUR_BORD = new Color(173, 216, 230);
    private static final Color COULEUR_MILIEU = new Color(245, 225, 180);

    // Images pour les cases spéciales (null si le fichier est absent)
    private static final Image IMG_BOMBE = chargerImage("/images/bombe.png");
    private static final Image IMG_COCO = chargerImage("/images/coco.png");
    private static final Image IMG_MYSTERE = chargerImage("/images/mystere.png");
    private static final Image IMG_ARRIVEE = chargerImage("/images/arrivee2.png");

    private static Image chargerImage(String path) {
        java.net.URL url = PanelCase.class.getResource(path);
        if (url == null)
            return null;
        return new ImageIcon(url).getImage();
    }

    private int numero;
    private String type;
    private String direction;
    private boolean contientJoueur1;
    private Color couleurPion1;
    private boolean contientJoueur2;
    private Color couleurPion2;
    private Color highlight;

    public PanelCase() {
    }

    public PanelCase(int numero, String type, String direction) {
        this.numero = numero;
        this.type = type;
        this.direction = direction;
        setPreferredSize(new Dimension(90, 90));
    }

    public void setHighlight(Color couleur) {
        this.highlight = couleur;
        repaint();
    }

    public void clearHighlight() {
        this.highlight = null;
        repaint();
    }

    // Appelé une seule fois au début du jeu
    public void setCouleurs(Color joueur1, Color joueur2) {
        this.couleurPion1 = joueur1;
        this.couleurPion2 = joueur2;
    }

    public void setContientJoueur(int joueur, boolean contient) {
        if (joueur == 0)
            contientJoueur1 = contient;
        else
            contientJoueur2 = contient;
        repaint();
    }

    public int getNumero() {
        return numero;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        int s = Math.min(w, h) / 4;
        int cx = w / 2;
        int cy = h / 2;

        // remplit tout en beige d'abord
        g2d.setColor(COULEUR_MILIEU);
        g2d.fillRect(0, 0, w, h);

        // dessine les deux bandes en fonction de la direction
        g2d.setColor(COULEUR_BORD);
        switch (direction == null ? HORIZONTAL : direction) {
            case COIN_DROITE_HAUT:
                g2d.fillRect(0, 0, w, s); // haut
                g2d.fillRect(w - s, 0, s, h); // droite
                g2d.fillRect(0, h - s, s, h - s); // bas-gauche
                break;
            case COIN_DROITE_BAS:
                g2d.fillRect(w - s, 0, s, h); // droite
                g2d.fillRect(0, h - s, w, s); // bas
                g2d.fillRect(0, 0, s, s); // haut-gauche

                break;
            case COIN_GAUCHE_HAUT:
                g2d.fillRect(0, 0, w, s); // haut
                g2d.fillRect(0, 0, s, h); // gauche
                g2d.fillRect(w - s, h - s, s, s);
                break;
            case COIN_GAUCHE_BAS:
                g2d.fillRect(0, 0, s, h); // gauche
                g2d.fillRect(0, h - s, w, s);
                g2d.fillRect(w - s, 0, s, s); // bas-droite
                break;
            default: // HORIZONTAL
                g2d.fillRect(0, 0, w, s); // haut
                g2d.fillRect(0, h - s, w, s); // bas
                break;
        }

        if (!ARRIVEE.equals(type)) {
            // tirets de la ligne centrale
            float dashLen = w * 0.12f;
            float gapLen = w * 0.10f;

            g2d.setColor(new Color(180, 160, 130));
            g2d.setStroke(new BasicStroke(
                    Math.max(1, h / 30f),
                    BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    10, new float[] { dashLen, gapLen }, 0));
        }
        switch (direction == null ? HORIZONTAL : direction) {
            case COIN_DROITE_HAUT:
                g2d.drawLine(w / 9, cy, cx, cy); // horizontal : gauche → centre
                g2d.drawLine(cx, cy, cx, 8 * h / 9); // vertical : centre → bas
                break;
            case COIN_DROITE_BAS:
                g2d.drawLine(w / 9, cy, cx, cy); // horizontal : centre → gauche
                g2d.drawLine(cx, h / 9, cx, cy); // vertical : haut → centre
                break;
            case COIN_GAUCHE_HAUT:
                g2d.drawLine(8 * w / 9, cy, cx, cy); // horizontal : droite → centre
                g2d.drawLine(cx, cy, cx, 8 * h / 9); // vertical : centre → bas
                break;
            case COIN_GAUCHE_BAS:
                g2d.drawLine(8 * w / 9, cy, cx, cy); // horizontal : centre → droite
                g2d.drawLine(cx, h / 9, cx, cy); // vertical : haut → centre
                break;
            default:
                g2d.drawLine(w / 9, cy, 8 * w / 9, cy);
                break;
        }

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(0, 0, w - 1, h - 1);

        // 5. numero — Blanc
        int fontSize = Math.max(9, s - 4);
        g2d.setFont(new Font("Arial", Font.BOLD, fontSize));
        g2d.setColor(Color.WHITE);
        g2d.drawString(String.valueOf(numero), 3, s - 2);

        if (null != type) // 6. icône centrée dans la case
            switch (type) {
                case BOMBE:
                    if (IMG_BOMBE != null)
                        g2d.drawImage(IMG_BOMBE, cx - h / 3, cy - h / 3, h * 2 / 3, h * 2 / 3, this);
                    else
                        dessinerBombe(g2d, cx, cy, h / 2); // ancien dessin géométrique
                    break;
                case COCO:
                    if (IMG_COCO != null)
                        g2d.drawImage(IMG_COCO, cx - h / 3, cy - h / 3, h * 2 / 3, h * 2 / 3, this);
                    else
                        dessinerCoco(g2d, cx, cy, h / 2); // ancien dessin géométrique
                    break;
                case MYSTERE:
                    if (IMG_MYSTERE != null)
                        g2d.drawImage(IMG_MYSTERE, cx - h / 3, cy - h / 3, h * 2 / 3, h * 2 / 3, this);

                    else
                        dessinerMystere(g2d, cx, cy, h / 2, w); // ancien dessin géométrique
                    break;
                case ARRIVEE:
                    // --- OPTION 1 : TEXTE "FIN" AGRANDI ---
                    // 1. Définir une belle police cursive, taille très grande pour remplir la case
                    Font anciennePolice = g2d.getFont();
                    int taillePolice = (int) (h * 0.65); // 65% de la hauteur de la case !
                    Font policeFin = new Font("Brush Script MT", Font.BOLD | Font.ITALIC, taillePolice);

                    // Si Brush Script n'est pas dispo, on rabat sur du Serif classique
                    if (!policeFin.getFamily().equals("Brush Script MT")) {
                        policeFin = new Font("Serif", Font.BOLD | Font.ITALIC, taillePolice);
                    }
                    g2d.setFont(policeFin);

                    String texteFin = "FIN";
                    FontMetrics fmFin = g2d.getFontMetrics();
                    // Centrage parfait du texte
                    int texteX = cx - fmFin.stringWidth(texteFin) / 2;
                    int texteY = cy + fmFin.getAscent() / 3;

                    // 2. Dessiner une ombre portée plus prononcée (noir transparent)
                    g2d.setColor(new Color(0, 0, 0, 150));
                    g2d.drawString(texteFin, texteX + 3, texteY + 3);

                    // 3. Dessiner le texte principal (rouge écarlate ou or)
                    g2d.setColor(new Color(200, 20, 20)); // Rouge sombre pirate
                    g2d.drawString(texteFin, texteX, texteY);

                    // On restaure la police normale par sécurité
                    g2d.setFont(anciennePolice);

                    // --- OPTION 2 : IMAGE ARRIVEE AGRANDIE ---
                    // if (IMG_ARRIVEE != null) {
                    // // On affiche l'image sur 90% de la taille de la case au lieu de 66% (h *
                    // 2/3)
                    // int tailleImg = (int) (h * 0.9);
                    // g2d.drawImage(IMG_ARRIVEE, cx - tailleImg / 2, cy - tailleImg / 2, tailleImg,
                    // tailleImg, this);
                    // }
                    break;

                default:
                    break;
            }

        int rayon = h / 6;
        if (contientJoueur1 && contientJoueur2) {
            g2d.setColor(couleurPion1);
            g2d.fillOval(cx - rayon * 2, cy - rayon, 2 * rayon, 2 * rayon);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(cx - rayon * 2, cy - rayon, 2 * rayon, 2 * rayon);

            g2d.setColor(couleurPion2);
            g2d.fillOval(cx + 2, cy - rayon, 2 * rayon, 2 * rayon);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(cx + 2, cy - rayon, 2 * rayon, 2 * rayon);
        } else if (contientJoueur1) {
            g2d.setColor(couleurPion1);
            g2d.fillOval(cx - rayon, cy - rayon, 2 * rayon, 2 * rayon);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(cx - rayon, cy - rayon, 2 * rayon, 2 * rayon);
        } else if (contientJoueur2) {
            g2d.setColor(couleurPion2);
            g2d.fillOval(cx - rayon, cy - rayon, 2 * rayon, 2 * rayon);
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(cx - rayon, cy - rayon, 2 * rayon, 2 * rayon);
        }

        // bordure de surbrillance
        if (highlight != null) {
            int b = 4;
            g2d.setColor(highlight);
            g2d.setStroke(new BasicStroke(b));
            g2d.drawRect(b / 2, b / 2, w - b, h - b);
        }
    }

    private void dessinerBombe(Graphics2D g2d, int cx, int cy, int midH) {
        int r = midH / 3;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillOval(cx - r, cy - r, 2 * r, 2 * r);
        int fuseH = r;
        g2d.setColor(new Color(80, 50, 20));
        g2d.setStroke(new BasicStroke(Math.max(2, r / 4)));
        g2d.drawLine(cx, cy - r, cx + fuseH / 2, cy - r - fuseH);
        int sparkR = Math.max(3, r / 3);
        g2d.setColor(Color.ORANGE);
        g2d.fillOval(cx + fuseH / 2 - sparkR / 2, cy - r - fuseH - sparkR / 2, sparkR, sparkR);
    }

    private void dessinerCoco(Graphics2D g2d, int cx, int cy, int midH) {
        int trunkH = (int) (midH * 0.7);
        int base = cy + trunkH / 2;
        int haut = base - trunkH;
        int leafLen = (int) (midH * 0.45);
        g2d.setColor(new Color(139, 90, 43));
        g2d.setStroke(new BasicStroke(Math.max(2, midH / 12)));
        g2d.drawLine(cx, base, cx - 3, haut);
        g2d.setColor(new Color(34, 139, 34));
        g2d.setStroke(new BasicStroke(Math.max(1, midH / 18)));
        g2d.drawLine(cx, haut, cx - leafLen, haut - leafLen / 2);
        g2d.drawLine(cx, haut, cx + leafLen, haut - leafLen / 3);
        g2d.drawLine(cx, haut, cx, haut - leafLen);
        int cocoR = Math.max(3, midH / 8);
        g2d.setColor(new Color(139, 90, 43));
        g2d.fillOval(cx - cocoR * 2, haut - cocoR / 2, cocoR * 2, cocoR * 2);
    }

    private void dessinerMystere(Graphics2D g2d, int cx, int cy, int midH, int w) {
        int cw = w / 3;
        int ch = midH / 2;
        int lidH = ch / 2;
        g2d.setColor(new Color(139, 90, 43));
        g2d.fillRect(cx - cw / 2, cy - ch / 2, cw, ch);
        g2d.setColor(new Color(101, 67, 33));
        g2d.fillRect(cx - cw / 2, cy - ch / 2 - lidH, cw, lidH);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(cx - cw / 2, cy - ch / 2, cw, ch);
        g2d.drawRect(cx - cw / 2, cy - ch / 2 - lidH, cw, lidH);
        int lockR = Math.max(3, cw / 8);
        g2d.setColor(new Color(255, 215, 0));
        g2d.fillOval(cx - lockR, cy - lockR, lockR * 2, lockR * 2);
    }
}
