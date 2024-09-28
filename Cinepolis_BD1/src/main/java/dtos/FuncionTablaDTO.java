/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 *
 * @author Usuario
 */
public class FuncionTablaDTO {

    private int id;
    private String peliculaTitulo;
    private int duracion;
    private LocalTime hora;
    private LocalTime horaFinal;
    private BigDecimal precio;

    public FuncionTablaDTO() {
    }

    public FuncionTablaDTO(int id, String peliculaTitulo, int duracion, LocalTime hora, LocalTime horaFinal, BigDecimal precio) {
        this.id = id;
        this.peliculaTitulo = peliculaTitulo;
        this.duracion = duracion;
        this.hora = hora;
        this.horaFinal = horaFinal;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeliculaTitulo() {
        return peliculaTitulo;
    }

    public void setPeliculaTitulo(String peliculaTitulo) {
        this.peliculaTitulo = peliculaTitulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }


    
    
    
    

}
