/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author carli
 */
public class PanelAzulBlanco extends JPanel {
    
  
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Obtener el ancho y alto del JPanel
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        
        // Calcular 1/4 de la altura del JPanel
        int oneQuarterHeight = panelHeight / 4;
        
          int tenPercentWidth = panelWidth / 10;
        
        // Pintar el primer 1/4 con un color definido por RGB [255, 0, 0] (rojo)
        g.setColor(new Color(36,44,99));  // Color rojo
        g.fillRect(0, 0, panelWidth, oneQuarterHeight);
        
        // Pintar el resto del panel con otro color definido por RGB [0, 255, 0] (verde)
        g.setColor(new Color(255,255,255));  // Color verde
        g.fillRect(0, oneQuarterHeight, panelWidth, panelHeight - oneQuarterHeight);
        
           // Pintar el resto del panel con otro color definido por RGB [0, 255, 0] (verde)
        g.setColor(new Color(36,44,99));  // Color rojo
        g.fillRect(panelWidth - tenPercentWidth, 0, tenPercentWidth, panelHeight);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 400); // Tamaño del JPanel
    }
}