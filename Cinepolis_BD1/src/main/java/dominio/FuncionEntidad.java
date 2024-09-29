/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalTime;

/**
 *
 * @author samoano
 */
public class FuncionEntidad {
   private int id;
    private BigDecimal precio;
    private String dia;
    private LocalTime hora;
    private LocalTime horaFinal;
    private LocalTime horaFinalTotal;
    private int asientosDisponibles;
    private int duracionTotalmin;
    private int idSala;
    private int idPelicula;


    public FuncionEntidad() {
    }

    public FuncionEntidad(int id, BigDecimal precio, String dia, LocalTime hora, LocalTime horaFinal, int asientosDisponibles, int duracionTotalmin, int idSala, int idPelicula) {
        this.id = id;
        this.precio = precio;
        this.dia = dia;
        this.hora = hora;
        this.horaFinal = horaFinal;
        this.asientosDisponibles = asientosDisponibles;
        this.duracionTotalmin = duracionTotalmin;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
    }

    public FuncionEntidad(BigDecimal precio, String dia, LocalTime hora, int idSala, int idPelicula) {
        this.precio = precio;
        this.dia = dia;
        this.hora = hora;
        this.idSala = idSala;
        this.idPelicula = idPelicula;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDia() {
        return dia;
    }

    public LocalTime getHoraFinalTotal() {
        return horaFinalTotal;
    }

    public void setHoraFinalTotal(LocalTime horaFinalTotal) {
        this.horaFinalTotal = horaFinalTotal;
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

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public int getDuracionTotalmin() {
        return duracionTotalmin;
    }

    public void setDuracionTotalmin(int duracionTotalmin) {
        this.duracionTotalmin = duracionTotalmin;
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