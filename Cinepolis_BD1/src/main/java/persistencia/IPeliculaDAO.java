/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.PeliculaEntidad;
import java.util.List;

/**
 *
 * @author Temo
 */
public interface IPeliculaDAO extends ICrud<PeliculaEntidad> {

    public List<PeliculaEntidad> buscarPeliculaSucursal(int idSucursal, int limit, int offset) throws PersistenciaException;

}
