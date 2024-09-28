/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author samoano
 */
public class ClienteDTO {
     private int idCliente;
    private String nombre;
    private String apellidoPA;
    private String apellidoMA;
    private String correo;
    private String contraseña;
    private LocalDate fechaNacimiento;
    private String ubicacion;
    private int idCiudad;

    public ClienteDTO() {
    }

    public ClienteDTO(String nombre, String apellidoPA, String apellidoMA, String correo, String contraseña, LocalDate fechaNacimiento, String ubicacion, int idCiudad) {
        this.nombre = nombre;
        this.apellidoPA = apellidoPA;
        this.apellidoMA = apellidoMA;
        this.correo = correo;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.ubicacion = ubicacion;
        this.idCiudad = idCiudad;
    }

    public ClienteDTO(int idCliente, String nombre, String apellidoPA, String apellidoMA, String correo, String contraseña, LocalDate fechaNacimiento, String ubicacion, int idCiudad) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPA = apellidoPA;
        this.apellidoMA = apellidoMA;
        this.correo = correo;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.ubicacion = ubicacion;
        this.idCiudad = idCiudad;
    }

    public ClienteDTO(int idCliente, String nombre, String apellidoPA, String apellidoMA, String correo, LocalDate fechaNacimiento, String ubicacion, int idCiudad) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPA = apellidoPA;
        this.apellidoMA = apellidoMA;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.ubicacion = ubicacion;
        this.idCiudad = idCiudad;
    }

    public ClienteDTO(int idCliente) {
        this.idCliente = idCliente;
    }
    
    
    

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    @Override
    public String toString() {
        return "ClienteDTO{" + "idCliente=" + idCliente + ", nombre=" + nombre + ", apellidoPA=" + apellidoPA + ", apellidoMA=" + apellidoMA + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + ", fechaNacimiento=" + fechaNacimiento + ", ubicacion=" + ubicacion + ", idCiudad=" + idCiudad + '}';
    }
    
    

}
