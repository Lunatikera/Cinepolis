/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author carli
 */
public class TestAutoResizeImage extends JComponent {

   private Icon imagen;
    private int tamanoBorde = 0;
    private Color colorBorde = new Color(60, 60, 60);

    public Icon getImagen() {
        return imagen;
    }

    public void setImagen(Icon imagen) {
        this.imagen = imagen;
        repaint();
    }

    public int getTamanoBorde() {
        return tamanoBorde;
    }

    public void setTamanoBorde(int tamano) {
        this.tamanoBorde = tamano;
        repaint();
    }

    public Color getColorBorde() {
        return colorBorde;
    }

    public void setColorBorde(Color borderColor) {
        this.colorBorde = borderColor;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagen != null) {
            // Convert the icon to a BufferedImage
            Image img = toImage(imagen);
            int imgWidth = img.getWidth(null);
            int imgHeight = img.getHeight(null);

            // Get component size
            int componentWidth = getWidth();
            int componentHeight = getHeight();

            // Compute the scaling factor to maintain aspect ratio
            double scaleX = componentWidth * 1.0 / imgWidth;
            double scaleY = componentHeight * 1.0 / imgHeight;
            double scale = Math.min(scaleX, scaleY);

            // Compute the new size of the image
            int newWidth = (int) (imgWidth * scale);
            int newHeight = (int) (imgHeight * scale);

            // Compute the position to center the image
            int x = (componentWidth - newWidth) / 2;
            int y = (componentHeight - newHeight) / 2;

            // Create a new BufferedImage to draw the resized image
            BufferedImage resizedImage = new BufferedImage(componentWidth, componentHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.drawImage(img, x, y, newWidth, newHeight, null);
            g2d.dispose();

            // Draw the resized image with rounded corners
            BufferedImage maskedImage = new BufferedImage(componentWidth, componentHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = maskedImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw rounded rectangle mask
            RoundRectangle2D.Double roundedRectangle = new RoundRectangle2D.Double(0, 0, componentWidth, componentHeight, 20, 20);
            g2.setClip(roundedRectangle);
            g2.drawImage(resizedImage, 0, 0, null);
            g2.dispose();

            // Draw the final image
            Graphics2D g3 = (Graphics2D) g;
            g3.drawImage(maskedImage, 0, 0, this);

            // Draw border if needed
            if (tamanoBorde > 0) {
                g3.setColor(colorBorde);
                g3.setStroke(new BasicStroke(tamanoBorde));
                g3.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
            }
        }
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
}