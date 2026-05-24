package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.function.Consumer;

//Nolawi
public class PanelPlateau extends JPanel {

    private static final Color COULEUR_ROUGE = new Color(200, 60, 60);
    private static final Color COULEUR_VERTE = new Color(60, 180, 60);

    private final PanelCase[] cases = new PanelCase[30];
    private int caseCible = -1;
    private boolean isDraggingPion = false;
    private int joueurActif = 0;
    private int[] positionJoueurs = { -1, -1 };
    private Color[] couleurJoueurs = { null, null };
    private Consumer<Integer> onPionPlace;

    public PanelPlateau() {
        setLayout(new GridLayout(6, 5));
        initialisertypes();
        ajoutertypesEnSnake();
        initialiserDrag();
    }

    private void initialisertypes() {
        String[] types = new String[31];
        for (int i = 1; i <= 30; i++)
            types[i] = PanelCase.NORMALE;

        types[3] = PanelCase.MYSTERE; // case 3
        types[4] = PanelCase.COCO;
        types[11] = PanelCase.COCO;
        types[14] = PanelCase.MYSTERE;
        types[18] = PanelCase.COCO;
        types[22] = PanelCase.MYSTERE;
        types[26] = PanelCase.BOMBE;
        types[28] = PanelCase.MYSTERE;

        String[] directions = new String[31];
        for (int i = 1; i <= 30; i++)
            directions[i] = PanelCase.HORIZONTAL;

        // right edge turns
        directions[5] = PanelCase.COIN_DROITE_HAUT;
        directions[6] = PanelCase.COIN_DROITE_BAS;
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

    private void ajoutertypesEnSnake() {
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
                if (!isDraggingPion || caseCible < 0)
                    return;
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
            public void mousePressed(MouseEvent e) {
                if (caseCible < 0)
                    return;
                PanelCase c = getCaseAt(e.getX(), e.getY());
                if (c != null && c.getNumero() == positionJoueurs[joueurActif]) {
                    isDraggingPion = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!isDraggingPion || caseCible < 0) {
                    isDraggingPion = false;
                    return;
                }
                isDraggingPion = false;
                PanelCase c = getCaseAt(e.getX(), e.getY());
                if (c != null && c.getNumero() == caseCible) {
                    clearAllHighlights();
                    int cible = caseCible;
                    caseCible = -1;
                    if (onPionPlace != null)
                        onPionPlace.accept(cible);
                }
                clearAllHighlights();
            }
        });
    }

    public void activerDrag(int joueurIndex, int cible) {
        this.joueurActif = joueurIndex;
        this.caseCible = cible;
    }

    public void deplacerPion(int joueur, int nouvelleCaseNumero, Color couleur) {
        // enlever le pion de l'ancienne case
        if (positionJoueurs[joueur] >= 0) {
            cases[positionJoueurs[joueur] - 1].setContientJoueur(false, null);
        }
        // placer sur la nouvelle case
        positionJoueurs[joueur] = nouvelleCaseNumero;
        couleurJoueurs[joueur] = couleur;
        cases[nouvelleCaseNumero - 1].setContientJoueur(true, couleur);
    }

    public void setOnPionPlace(Consumer<Integer> callback) {
        this.onPionPlace = callback;
    }

    private PanelCase getCaseAt(int x, int y) {
        Component comp = getComponentAt(x, y);
        if (comp instanceof PanelCase panelCase)
            return panelCase;
        return null;
    }

    private void clearAllHighlights() {
        for (PanelCase c : cases)
            c.clearHighlight();
    }
}
