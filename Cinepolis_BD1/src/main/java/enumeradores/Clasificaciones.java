/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enumeradores;

/**
 *
 * @author samoano
 */
public enum Clasificaciones {
    A("Todos"),
    B("+12 años"),
    B15("+15 años"),
    C("Mayores de 18 años"),
    R("Restringido");

    private final String descripcion;

    // Constructor del enum
    Clasificaciones(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método para obtener la descripción asociada
    public String getDescripcion() {
        return descripcion;
    }
}
