/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 *
 * @author samoano
 */
public class FuncionEntidad {
   private int id;
    private BigDecimal precio;
    private String dia;
    private Timestamp hora;
    private Timestamp horaFinal;
    private Timestamp horaFinalTotal;
    private int asientosDisponibles;
    private int duracionTotalmin;
    private int idSala;
    private int idPelicula;


    public FuncionEntidad() {
    }

    public FuncionEntidad(int id, BigDecimal precio, String dia, Timestamp hora, Timestamp horaFinal, int asientosDisponibles, int duracionTotalmin, int idSala, int idPelicula) {
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

    public Timestamp getHoraFinalTotal() {
        return horaFinalTotal;
    }

    public void setHoraFinalTotal(Timestamp horaFinalTotal) {
        this.horaFinalTotal = horaFinalTotal;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public Timestamp getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Timestamp horaFinal) {
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