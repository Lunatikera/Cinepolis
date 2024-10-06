/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author NaderCroft
 */
public class DatosReporteSucursalDTO {

    private String ciudad;
    private String sucursal;
    private int cantidadFunciones;
    private LocalDate fecha;
    private BigDecimal total;

    public DatosReporteSucursalDTO() {
    }

    public DatosReporteSucursalDTO(String ciudad, String sucursal, int cantidadFunciones, LocalDate fecha, BigDecimal total) {
        this.ciudad = ciudad;
        this.sucursal = sucursal;
        this.cantidadFunciones = cantidadFunciones;
        this.fecha = fecha;
        this.total = total;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public int getCantidadFunciones() {
        return cantidadFunciones;
    }

    public void setCantidadFunciones(int cantidadFunciones) {
        this.cantidadFunciones = cantidadFunciones;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DatosReporteDTO{" + "ciudad=" + ciudad + ", sucursal=" + sucursal + ", cantidadFunciones=" + cantidadFunciones + ", fecha=" + fecha + ", total=" + total + '}';
    }

}
