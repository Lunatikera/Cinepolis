/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.SucursalEntidad;
import dtos.SucursalDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ISucursalDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author Temo
 */
public class SucursalBO implements ISucursalBO {

    private ISucursalDAO sucursalDAO;

    public SucursalBO(ISucursalDAO sucursalDAO) {
        this.sucursalDAO = sucursalDAO;
    }

    @Override
    public SucursalDTO buscarSucursalMasCercana(int clienteId) throws NegocioException {
        try {
            SucursalEntidad sucursal = sucursalDAO.buscarSucursalMasCercana(clienteId);

            if (sucursal == null) {
                throw new NegocioException("No se encontró una sucursal cercana");
            }

            SucursalDTO sucursalDTO = new SucursalDTO();
            sucursalDTO.setId(sucursal.getId());
            sucursalDTO.setNombre(sucursal.getNombre());
            sucursalDTO.setDireccion(sucursal.getDireccion());

            return sucursalDTO;
        } catch (PersistenciaException e) {
            Logger.getLogger(SucursalBO.class.getName()).log(Level.SEVERE, null, e);
            throw new NegocioException("Error al obtener la sucursal más cercana", e);

        }
    }

    @Override
    public List<SucursalDTO> obtenerSucursales() throws NegocioException {
        try {
            // Llamar al DAO para obtener la lista de sucursales
            List<SucursalEntidad> sucursales = sucursalDAO.obtenerSucursales();

            // Convertir la lista de Sucursal a una lista de SucursalDTO
            List<SucursalDTO> sucursalDTOs = new ArrayList<>();
            for (SucursalEntidad sucursal : sucursales) {
                SucursalDTO dto = new SucursalDTO();
                dto.setId(sucursal.getId());
                dto.setNombre(sucursal.getNombre());
                dto.setDireccion(sucursal.getDireccion());
                sucursalDTOs.add(dto);
            }
            return sucursalDTOs;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al obtener las sucursales", ex);
        }
    }

    @Override
    public List<SucursalDTO> listaSucursalesporCiudad(int idCiudad) throws NegocioException {
        try {
            List<SucursalEntidad> sucursales = sucursalDAO.listaSucursalesporCiudad(idCiudad);
            List<SucursalDTO> sucursalesDTO = new ArrayList<>();

            for (SucursalEntidad sucursal : sucursales) {
                // Convertir Sucursal a SucursalDTO
                SucursalDTO sucursalDTO = convertirASucursalDTO(sucursal);
                sucursalesDTO.add(sucursalDTO);
            }

            return sucursalesDTO;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error en la capa de negocio al obtener la lista de sucursales", e);
        }
    }

    private SucursalDTO convertirASucursalDTO(SucursalEntidad sucursal) {
        // Implementa la lógica para convertir un objeto Sucursal a un objeto SucursalDTO
        // Aquí supongo que SucursalDTO tiene un constructor que toma los mismos parámetros que Sucursal
        return new SucursalDTO(sucursal.getId(), sucursal.getNombre(), sucursal.getDireccion(), sucursal.getIdCiudad());
    }
}
