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
        JDialog dialogTest = new JDialog();
        dialogTest.setTitle("Test Visuel Bombe");
        dialogTest.setSize(650, 650);
        dialogTest.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogTest.setLocationRelativeTo(null); 

        PanelEffetBombe panel = new PanelEffetBombe(dialogTest, "tu perds 3 pv");
        dialogTest.add(panel);

        dialogTest.setVisible(true);
    }
}
