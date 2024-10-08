/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import enumeradores.MetodosDePago;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author samoano
 */
public class VentaEntidad {
    private int idVenta;
    private LocalDateTime fechaCompra;
    private MetodosDePago metodoPago;
    private int cantidadBoletos;
    private BigDecimal precioTotal;
    private int clienteId;
    private int funcionId;

    public VentaEntidad() {
    }

    public VentaEntidad(MetodosDePago metodoPago, int cantidadBoletos, int clienteId, int funcionId) {
        this.metodoPago = metodoPago;
        this.cantidadBoletos = cantidadBoletos;
        this.clienteId = clienteId;
        this.funcionId = funcionId;
    }



    public VentaEntidad(int idVenta, LocalDateTime fechaCompra, MetodosDePago metodoPago, int cantidadBoletos, BigDecimal precioTotal, int clienteId, int funcionId) {
        this.idVenta = idVenta;
        this.fechaCompra = fechaCompra;
        this.metodoPago = metodoPago;
        this.cantidadBoletos = cantidadBoletos;
        this.precioTotal = precioTotal;
        this.clienteId = clienteId;
        this.funcionId = funcionId;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public void setCantidadBoletos(int cantidadBoletos) {
        this.cantidadBoletos = cantidadBoletos;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public MetodosDePago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodosDePago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(int funcionId) {
        this.funcionId = funcionId;
    }

}
