/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author carli
 */
public class Herramientas {

    public static int RegresarOFFSETMySQL(int limite, int pagina) {
        if (pagina <= 1) {
            return 0;
        }

        if (pagina == 2) {
            return limite;
        }

        return ((int) (limite * (pagina - 1)));
    }

    public static String textoConSaltosLinea(String texto, int maxPalabrasporLinea) {
        StringBuilder formattedText = new StringBuilder();
        String[] words = texto.split("\\s+");
        int wordsInLine = 0;

        for (String word : words) {
            // If adding the word exceeds the maximum words per line, insert a line break
            if (wordsInLine >= maxPalabrasporLinea) {
                formattedText.append("<br>");
                wordsInLine = 0;
            }
            formattedText.append(word).append(" ");
            wordsInLine++;
        }

        // Set the formatted text with HTML line breaks
        return ("<html>" + formattedText.toString() + "</html>");
    }
}
