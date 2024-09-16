/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 *
 * @author carli
 */
public class PanelConFondo extends JPanel  {
    private BufferedImage backgroundImage;

    public PanelConFondo() {
        try {
            // Load the background image
            backgroundImage = ImageIO.read(new File("C:\\Users\\carli\\OneDrive\\Documentos\\GitHub\\Cinepolis\\Cinepolis_BD1\\src\\main\\resources\\imagenes\\Rectangle28.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}