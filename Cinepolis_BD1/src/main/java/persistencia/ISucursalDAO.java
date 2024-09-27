/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.SucursalEntidad;
import dtos.TablaPeliculaSucursalDTO;
import java.util.List;

/**
 *
 * @author samoano
 */
public interface ISucursalDAO {

    public SucursalEntidad buscarSucursalMasCercana(int clienteId) throws PersistenciaException;

    public List<SucursalEntidad> obtenerSucursales() throws PersistenciaException;

    public List<SucursalEntidad> listaSucursalesporCiudad(int idCiudad) throws PersistenciaException;

}
