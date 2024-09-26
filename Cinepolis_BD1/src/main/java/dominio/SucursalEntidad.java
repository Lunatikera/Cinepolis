/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author samoano
 */
public class SucursalEntidad {
  
    private int id;
    private String nombre;
    private String direccion;
    private String ubicacion;
    private int idCiudad;

    public SucursalEntidad() {
    }

    public SucursalEntidad(int id, String nombre, String direccion, String ubicacion, int idCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ubicacion = ubicacion;
        this.idCiudad = idCiudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }
    
    
}
