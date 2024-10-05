/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author carli
 */
public class DatosReporteMetodoPagoDTO {

    private LocalDateTime fechaCompra;
    private String nombreCiudad;
    private String nombreSucursal;
    private String metodoPago;
    private BigDecimal totalCompra;

    public DatosReporteMetodoPagoDTO() {
    }

    public DatosReporteMetodoPagoDTO(LocalDateTime fechaCompra, String nombreCiudad, String nombreSucursal, String metodoPago, BigDecimal totalCompra) {
        this.fechaCompra = fechaCompra;
        this.nombreCiudad = nombreCiudad;
        this.nombreSucursal = nombreSucursal;
        this.metodoPago = metodoPago;
        this.totalCompra = totalCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }

}
