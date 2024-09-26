/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author carli
 */
public class MenuButton extends JButton {
//    private Icon iconoSimple;
//    private Icon iconoSeleccionado;
//
//    public Icon getIconoSimple() {
//        return iconoSimple;
//    }
//
//    public void setIconoSimple(Icon iconoSimple) {
//        this.iconoSimple = iconoSimple;
//    }
//
//    public Icon getIconoSeleccionado() {
//        return iconoSeleccionado;
//    }
//
//    public void setIconoSeleccionado(Icon iconoSeleccionado) {
//        this.iconoSeleccionado = iconoSeleccionado;
//    }
//    public MenuButton() {
//        setContentAreaFilled(false);
//        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//    }
//
//    @Override
//    public void setSelected(boolean b) {
//        super.setSelected(b); 
//        if (b) {
//            setIcon(iconoSeleccionado);
//        }else{
//            setIcon(iconoSimple);
//        }
//    }
     private Icon iconoSimple;
    private Icon iconoSeleccionado;

    public Icon getIconoSimple() {
        return iconoSimple;
    }

    public void setIconoSimple(Icon iconoSimple) {
        this.iconoSimple = iconoSimple;
        setIcon(iconoSimple); // Set the initial icon
    }

    public Icon getIconoSeleccionado() {
        return iconoSeleccionado;
    }

    public void setIconoSeleccionado(Icon iconoSeleccionado) {
        this.iconoSeleccionado = iconoSeleccionado;
    }

    public MenuButton() {
        setContentAreaFilled(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Add mouse listener for hover effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(iconoSeleccionado); // Change to selected icon on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(iconoSimple); // Change back to simple icon when not hovering
            }
        });
    }

    @Override
    public void setSelected(boolean b) {
        super.setSelected(b);
        if (b) {
            setIcon(iconoSeleccionado);
        } else {
            setIcon(iconoSimple);
        }
    }
    
}
