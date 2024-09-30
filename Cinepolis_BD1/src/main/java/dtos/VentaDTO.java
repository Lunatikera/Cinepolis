/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enumeradores.MetodosDePago;

/**
 *
 * @author carli
 */
public class VentaDTO {
    private int idVenta;
    private int cantidadBoleto;
    private MetodosDePago metodoPago;
    private int idCliente;
    private int idFuncion;

    public VentaDTO() {
    }

    public VentaDTO(int cantidadBoleto, MetodosDePago metodoPago, int idCliente, int idFuncion) {
        this.cantidadBoleto = cantidadBoleto;
        this.metodoPago = metodoPago;
        this.idCliente = idCliente;
        this.idFuncion = idFuncion;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getCantidadBoleto() {
        return cantidadBoleto;
    }

    public void setCantidadBoleto(int cantidadBoleto) {
        this.cantidadBoleto = cantidadBoleto;
    }

    public MetodosDePago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodosDePago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(int idFuncion) {
        this.idFuncion = idFuncion;
    }
    
    
}
