/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.Date;

/**
 *
 * @author carli
 */
public class ClienteEntidad {


    private int id;
    private String nombre;
    private String apellidoPA;
    private String apellidoMA;
    private String correo;
    private String contraseña;
    private Date fechaNacimiento;
    private String ubicacion;
    private boolean estaEliminado;
    private int idCiudad;


    public ClienteEntidad() {
    }

    public ClienteEntidad(int id, String nombre, String apellidoPA, String apellidoMA, String correo, String contraseña, Date fechaNacimiento, String ubicacion, int idCiudad) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPA = apellidoPA;
        this.apellidoMA = apellidoMA;
        this.correo = correo;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.ubicacion = ubicacion;
        this.idCiudad = idCiudad;
      
    }

    public ClienteEntidad(String nombre, String apellidoPA, String apellidoMA, String correo, String contraseña, Date fechaNacimiento, String ubicacion, int idCiudad, String ciudad, String pais) {
        this.nombre = nombre;
        this.apellidoPA = apellidoPA;
        this.apellidoMA = apellidoMA;
        this.correo = correo;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.ubicacion = ubicacion;
        this.idCiudad = idCiudad;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPA() {
        return apellidoPA;
    }

    public void setApellidoPA(String apellidoPA) {
        this.apellidoPA = apellidoPA;
    }

    public String getApellidoMA() {
        return apellidoMA;
    }

    public void setApellidoMA(String apellidoMA) {
        this.apellidoMA = apellidoMA;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isEstaEliminado() {
        return estaEliminado;
    }

    public void setEstaEliminado(boolean estaEliminado) {
        this.estaEliminado = estaEliminado;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("id=").append(id);
        sb.append(", idCiudad=").append(idCiudad);
        sb.append(", nombre=").append(nombre);
        sb.append(", correo=").append(correo);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", ubicacion=").append(ubicacion);
        sb.append('}');
        return sb.toString();
    }

}


