/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.SucursalDTO;
import java.util.List;

/**
 *
 * @author Temo
 */
public interface ISucursalBO {

    public SucursalDTO buscarSucursalMasCercana(int clienteId) throws NegocioException;

    public List<SucursalDTO> obtenerSucursales() throws NegocioException;

    public List<SucursalDTO> listaSucursalesporCiudad(int idCiudad) throws NegocioException;

}
