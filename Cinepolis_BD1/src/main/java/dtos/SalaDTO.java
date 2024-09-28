/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author NaderCroft
 */
public class SalaDTO {
    private int id;
    private String nombre;
    private int numeroAsiento;
    private int duracionLimpieza;
    private int idSucursal;
    private boolean estaEliminada;

    public SalaDTO(int id, String nombre, int numeroAsiento, int duracionLimpieza, int idSucursal, boolean estaEliminada) {
        this.id = id;
        this.nombre = nombre;
        this.numeroAsiento = numeroAsiento;
        this.duracionLimpieza = duracionLimpieza;
        this.idSucursal = idSucursal;
        this.estaEliminada = estaEliminada;
    }

    public SalaDTO() {
    }

    
    public SalaDTO(String nombre, int numeroAsiento, int duracionLimpieza, int idSucursal, boolean estaEliminada) {
        this.nombre = nombre;
        this.numeroAsiento = numeroAsiento;
        this.duracionLimpieza = duracionLimpieza;
        this.idSucursal = idSucursal;
        this.estaEliminada = estaEliminada;
    }

    public SalaDTO(int id, String nombre, int numeroAsiento, int duracionLimpieza, int idSucursal) {
        this.id = id;
        this.nombre = nombre;
        this.numeroAsiento = numeroAsiento;
        this.duracionLimpieza = duracionLimpieza;
        this.idSucursal = idSucursal;
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

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public int getDuracionLimpieza() {
        return duracionLimpieza;
    }

    public void setDuracionLimpieza(int duracionLimpieza) {
        this.duracionLimpieza = duracionLimpieza;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public boolean isEstaEliminada() {
        return estaEliminada;
    }

    public void setEstaEliminada(boolean estaEliminada) {
        this.estaEliminada = estaEliminada;
    }
    
}
