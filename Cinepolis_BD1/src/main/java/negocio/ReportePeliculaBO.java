/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.DatosReportePeliculasDTO;
import java.util.List;
import persistencia.IReportePeliculaDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author carli
 */
public class ReportePeliculaBO implements IReportePeliculaBO {

    IReportePeliculaDAO reportePeliculaDAO;

    public ReportePeliculaBO(IReportePeliculaDAO reportePeliculaDAO) {
        this.reportePeliculaDAO = reportePeliculaDAO;
    }

    @Override
    public List<DatosReportePeliculasDTO> obtenerGananciasPorPeliculas(List<Integer> ciudadIds, List<Integer> peliculaIds, String fechaInicio, String fechaFin) throws NegocioException {
        try {
            // Llamada al DAO para obtener los datos de las ganancias
            return reportePeliculaDAO.obtenerGananciasPorPeliculas(ciudadIds, peliculaIds, fechaInicio, fechaFin);
        } catch (PersistenciaException e) {
            // Convertir cualquier excepción de persistencia a NegocioException
            throw new NegocioException("Error al obtener ganancias por películas.", e);
        }
    }
}
