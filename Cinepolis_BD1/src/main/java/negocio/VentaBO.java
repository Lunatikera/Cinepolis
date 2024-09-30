/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.VentaEntidad;
import dtos.VentaDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IVentaDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author carli
 */
public class VentaBO implements IVentaBO {

    private IVentaDAO ventaDAO;

    public VentaBO(IVentaDAO ventaDAO) {
        this.ventaDAO = ventaDAO;
    }

    @Override
    public void agregarVenta(VentaDTO venta) throws NegocioException {
        try {
            VentaEntidad ventaEntidad = new VentaEntidad(
                    venta.getMetodoPago(),
                    venta.getCantidadBoleto(),
                    venta.getIdCliente(),
                    venta.getIdFuncion()
            );
            this.ventaDAO.agregarVenta(ventaEntidad);
        } catch (PersistenciaException e) {
            Logger.getLogger(SalaBO.class.getName()).log(Level.SEVERE, null, e);
            throw new NegocioException("Error en la capa de negocio guardar una sala", e);
        }
    }
}
