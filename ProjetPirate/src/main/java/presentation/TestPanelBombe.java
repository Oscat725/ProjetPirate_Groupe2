/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentation;

/**
 *
 * @author sopar
 */
import javax.swing.JDialog;

public class TestPanelBombe {
    public static void main(String[] args) {
        // Crée un faux dialogue pour le test
        JDialog dialogTest = new JDialog();
        dialogTest.setTitle("Test Visuel Bombe");
        dialogTest.setSize(350, 150);
        dialogTest.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogTest.setLocationRelativeTo(null); // Centre à l'écran

        // Ajoute ton panel dedans
        PanelEffetBombe panel = new PanelEffetBombe(dialogTest);
        dialogTest.add(panel);

        // Affiche le tout
        dialogTest.setVisible(true);
    }
}
