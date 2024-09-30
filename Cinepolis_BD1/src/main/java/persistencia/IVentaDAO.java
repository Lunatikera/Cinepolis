/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.VentaEntidad;

/**
 *
 * @author carli
 */
public interface IVentaDAO {
   public VentaEntidad agregarVenta(VentaEntidad venta) throws PersistenciaException;
  
}
