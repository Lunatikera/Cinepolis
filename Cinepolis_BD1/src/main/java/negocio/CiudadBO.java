/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.CiudadEntidad;
import dtos.CiudadDTO;
import java.util.ArrayList;
import java.util.List;
import persistencia.CiudadDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author carli
 */
public class CiudadBO implements ICiudadBO {

    private CiudadDAO ciudadDAO;

    public CiudadBO(CiudadDAO ciudadDAO) {
        this.ciudadDAO = ciudadDAO;
    }

    @Override
    public List<CiudadDTO> listaCiudades() throws NegocioException {
        try {
            List<CiudadDTO> listaCiudadesDTO = new ArrayList<>();
            List<CiudadEntidad> listaCiudades = ciudadDAO.listaCiudades();

            for (CiudadEntidad ciudad : listaCiudades) {
                listaCiudadesDTO.add(convertirAPaisDTO(ciudad));
            }
            return listaCiudadesDTO;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener la lista de ciudades", e);
        }
    }

    // Método privado para convertir un objeto Pais a PaisDTO
    private CiudadDTO convertirAPaisDTO(CiudadEntidad ciudad) {
        return new CiudadDTO(ciudad.getId(), ciudad.getNombre());
    }

}
