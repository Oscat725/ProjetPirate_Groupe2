/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package presentation;

/**
 *
 * @author Lauriana
 */
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelBateau extends JPanel {

    private static final Image IMG_BATEAU = chargerImage("/images/bateau.png");

    private static Image chargerImage(String path) {
        java.net.URL url = PanelCase.class.getResource(path);
        if (url == null)
            return null;
        return new ImageIcon(url).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(
                IMG_BATEAU,
                0, 0,
                getWidth(), getHeight(),
                this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bateau");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new PanelBateau());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}