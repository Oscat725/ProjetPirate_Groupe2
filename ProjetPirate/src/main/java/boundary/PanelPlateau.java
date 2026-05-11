package boundary;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.function.Consumer;
import javax.swing.JPanel;


public class PanelPlateau extends JPanel {

    private static final Color COULEUR_ROUGE = new Color(200, 60, 60);
    private static final Color COULEUR_VERTE = new Color(60, 180, 60);

    private final PanelCase[] cases = new PanelCase[30];
    private int caseCible = -1;
    private int[] positionJoueurs  = {-1, -1};
    private Color[] couleurJoueurs = {null, null};
    private Consumer<Integer> onPionPlace;

    public PanelPlateau() {
        setLayout(new GridLayout(6, 5));
        initialiserCases();
        ajouterCasesEnSnake();
        initialiserDrag();
    }

    private void initialiserCases() {
        String[] types = new String[31];
        for (int i = 1; i <= 30; i++) types[i] = PanelCase.NORMALE;

        types[5]  = PanelCase.BOMBE;
        types[8]  = PanelCase.COCO;
        types[10] = PanelCase.MYSTERE;
        types[12] = PanelCase.BOMBE;
        types[17] = PanelCase.COCO;
        types[20] = PanelCase.BOMBE;
        types[22] = PanelCase.MYSTERE;
        types[25] = PanelCase.COCO;
        types[28] = PanelCase.MYSTERE;

        String[] directions = new String[31];
        for (int i = 1; i <= 30; i++) directions[i] = PanelCase.HORIZONTAL;

        // right edge turns
        directions[5]  = PanelCase.COIN_DROITE_HAUT;
        directions[6]  = PanelCase.COIN_DROITE_BAS;
        directions[15] = PanelCase.COIN_DROITE_HAUT;
        directions[16] = PanelCase.COIN_DROITE_BAS;
        directions[25] = PanelCase.COIN_DROITE_HAUT;
        directions[26] = PanelCase.COIN_DROITE_BAS;

        // left edge turns
        directions[10] = PanelCase.COIN_GAUCHE_HAUT;
        directions[11] = PanelCase.COIN_GAUCHE_BAS;
        directions[20] = PanelCase.COIN_GAUCHE_HAUT;
        directions[21] = PanelCase.COIN_GAUCHE_BAS;

        for (int i = 1; i <= 30; i++) {
            cases[i - 1] = new PanelCase(i, types[i], directions[i]);
        }
    }

    private void ajouterCasesEnSnake() {
        int cols = 5;
        for (int row = 0; row < 6; row++) {
            if (row % 2 == 0) {
                for (int col = 0; col < cols; col++) {
                    add(cases[row * cols + col]);
                }
            } else {
                for (int col = cols - 1; col >= 0; col--) {
                    add(cases[row * cols + col]);
                }
            }
        }
    }

    private void initialiserDrag() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (caseCible < 0) return;
                clearAllHighlights();
                PanelCase c = getCaseAt(e.getX(), e.getY());
                if (c != null) {
                    if (c.getNumero() == caseCible) {
                        c.setHighlight(COULEUR_VERTE);
                    } else {
                        c.setHighlight(COULEUR_ROUGE);
                    }
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (caseCible < 0) return;
                PanelCase c = getCaseAt(e.getX(), e.getY());
                if (c != null && c.getNumero() == caseCible) {
                    clearAllHighlights();
                    int cible = caseCible;
                    caseCible = -1;
                    if (onPionPlace != null) onPionPlace.accept(cible);
                }
            }
        });
    }

    public void activerDrag(int cible) {
        this.caseCible = cible;
    }

    public void deplacerPion(int joueur, int nouvelleCaseNumero, Color couleur) {
        // enlever le pion de l'ancienne case
        if (positionJoueurs[joueur] >= 0) {
            cases[positionJoueurs[joueur] - 1].setContientJoueur(false, null);
        }
        // placer sur la nouvelle case
        positionJoueurs[joueur] = nouvelleCaseNumero;
        couleurJoueurs[joueur]  = couleur;
        cases[nouvelleCaseNumero - 1].setContientJoueur(true, couleur);
    }

    public void setOnPionPlace(Consumer<Integer> callback) {
        this.onPionPlace = callback;
    }

    private PanelCase getCaseAt(int x, int y) {
        java.awt.Component comp = getComponentAt(x, y);
        if (comp instanceof PanelCase panelCase) 
            return panelCase;
        return null;
    }

    private void clearAllHighlights() {
        for (PanelCase c : cases) c.clearHighlight();
    }
}
