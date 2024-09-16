/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author carlos
 */
public class ImagenPerfiles extends JComponent {

    public Icon getImagen() {
        return imagen;
    }

    public void setImagen(Icon imagen) {
        this.imagen = imagen;
    }

    public int getTamanoBorde() {
        return tamanoBorde;
    }

    public void setTamanoBorde(int tamano) {
        this.tamanoBorde = tamano;
    }

    public Color getColorBorde() {
        return colorBorde;
    }

    public void setColorBorde(Color borderColor) {
        this.colorBorde = borderColor;
    }

    private Icon imagen;
    private int tamanoBorde = 0;
    private Color colorBorde = new Color(60, 60, 60);

    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            int ancho = imagen.getIconWidth();
            int altura = imagen.getIconHeight();
            BufferedImage bufferedImage = new BufferedImage(ancho, altura, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.drawImage(toImage(imagen), 0, 0, null);
            g2d.dispose();
            
            // Create a new BufferedImage with a rounded rectangle mask
            BufferedImage imagenMascarada = new BufferedImage(ancho, altura, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imagenMascarada.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

            // Draw rounded rectangle mask
            RoundRectangle2D.Double roundedRectangle = new RoundRectangle2D.Double(0, 0, ancho, altura, 20, 20);
            g2.setClip(roundedRectangle);
            g2.drawImage(bufferedImage, 0, 0, null);
            g2.dispose();

            // Draw the image
            Graphics2D g3 = (Graphics2D) g;
            g3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g3.drawImage(imagenMascarada, 0, 0, getWidth(), getHeight(), this);

            // Draw border if needed
            if (tamanoBorde > 0) {
                g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g3.setColor(colorBorde);
                g3.setStroke(new BasicStroke(tamanoBorde));
                g3.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        }
        super.paint(g);
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
}