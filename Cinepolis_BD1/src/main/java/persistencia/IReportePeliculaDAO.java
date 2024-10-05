/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dtos.DatosReportePeliculasDTO;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IReportePeliculaDAO {
           public List<DatosReportePeliculasDTO> obtenerGananciasPorPeliculas(List<Integer> ciudadIds, List<Integer> peliculaIds, String fechaInicio, String fechaFin) throws PersistenciaException;

}
