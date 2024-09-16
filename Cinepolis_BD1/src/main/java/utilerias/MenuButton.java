/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author eljulls
 */
public class MenuButton extends JButton {
    private Icon iconoSimple;
    private Icon iconoSeleccionado;

    public Icon getIconoSimple() {
        return iconoSimple;
    }

    public void setIconoSimple(Icon iconoSimple) {
        this.iconoSimple = iconoSimple;
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
    }

    @Override
    public void setSelected(boolean b) {
        super.setSelected(b); 
        if (b) {
            setIcon(iconoSeleccionado);
        }else{
            setIcon(iconoSimple);
        }
    }
    
}
