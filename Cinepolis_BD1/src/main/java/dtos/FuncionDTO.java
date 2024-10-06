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
    private int asientosDisponibles;
    private int duracionTotal;
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

    public FuncionDTO(int id, String dia, BigDecimal precio, int asientosDisponibles, int duracionTotal, LocalTime hora, LocalTime horaFinal, int idSala, int idPelicula) {
        this.id = id;
        this.dia = dia;
        this.precio = precio;
        this.asientosDisponibles = asientosDisponibles;
        this.duracionTotal = duracionTotal;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precion) {
        this.precio = precion;
    }

    public String getDia() {
        return dia;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public int getDuracionTotal() {
        return duracionTotal;
    }

    public void setDuracionTotal(int duracionTotal) {
        this.duracionTotal = duracionTotal;
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

    @Override
    public String toString() {
        return "FuncionDTO{" + "id=" + id + ", dia=" + dia + ", precio=" + precio + ", asientosDisponibles=" + asientosDisponibles + ", duracionTotal=" + duracionTotal + ", hora=" + hora + ", horaFinal=" + horaFinal + ", idSala=" + idSala + ", idPelicula=" + idPelicula + '}';
    }
    
    
}
