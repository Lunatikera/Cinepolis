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
public class FuncionDTO {
    private int id;
    private String dia;
    private BigDecimal precio;
    private LocalTime hora;
    private LocalTime horaFinal;
    private int idSala;
    private int idPelicula;

    public FuncionDTO(int id, String dia, LocalTime hora, LocalTime horaFinal, int idSala, int idPelicula) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.horaFinal = horaFinal;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
    }

    public FuncionDTO(String dia, BigDecimal precio, LocalTime hora, int idSala, int idPelicula) {
        this.dia = dia;
        this.precio = precio;
        this.hora = hora;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
    }

    public FuncionDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrecion() {
        return precio;
    }

    public void setPrecion(BigDecimal precion) {
        this.precio = precion;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
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

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }
    
    
}
