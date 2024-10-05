/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author carli
 */
public class DatosReportePeliculasDTO {
    private String nombreCiudad;
    private String nombrePelicula;
    private LocalDate fechaCompra;
    private BigDecimal totalCompra;

    public DatosReportePeliculasDTO() {
    }

    public DatosReportePeliculasDTO(String nombreCiudad, String nombrePelicula, LocalDate fechaCompra, BigDecimal totalCompra) {
        this.nombreCiudad = nombreCiudad;
        this.nombrePelicula = nombrePelicula;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }
    
    
}
