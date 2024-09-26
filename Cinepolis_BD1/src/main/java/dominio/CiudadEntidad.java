/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author samoano
 */
public class CiudadEntidad {
    private int ciudadId;
    private String nombre;

    public CiudadEntidad(int ciudadId, String nombre) {
        this.ciudadId=ciudadId;
        this.nombre = nombre;
    }

    public int getId() {
        return ciudadId;
    }

    public void setId(int id) {
        this.ciudadId = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Ciudad{" + "id=" + ciudadId + ", nombre=" + nombre + '}';
    }
    

}
