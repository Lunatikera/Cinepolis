/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.util.Arrays;

/**
 *
 * @author Usuario
 */
public class Dias {

   public static String obtenerDiaSiguiente(String dia) {
    String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    int indice = Arrays.asList(dias).indexOf(dia);
    return dias[(indice + 1) % dias.length];
}

public static String obtenerDiaAnterior(String dia) {
    String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    int indice = Arrays.asList(dias).indexOf(dia);
    return dias[(indice - 1 + dias.length) % dias.length];
}
}
