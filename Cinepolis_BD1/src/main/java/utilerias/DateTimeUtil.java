/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author carli
 */
public class DateTimeUtil {

    public static String formatearFechaHora(LocalDateTime fechaHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaHora.format(formatter);
    }
}
