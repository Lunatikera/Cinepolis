/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.DatosReporteMetodoPagoDTO;
import java.util.List;
import persistencia.PersistenciaException;
import persistencia.IReporteMetodoPagoDAO;

/**
 *
 * @author carli
 */
public class ReporteMetodoPago implements IReporteMetodoPagoBO {
    IReporteMetodoPagoDAO reportePeliculaDAO;

    public ReporteMetodoPago(IReporteMetodoPagoDAO reportePeliculaDAO) {
        this.reportePeliculaDAO = reportePeliculaDAO;
    }

    @Override
    public List<DatosReporteMetodoPagoDTO> calcularGanancias(List<String> metodosDePago, List<Integer> sucursales, String fechaInicio, String fechaFin) throws NegocioException {
         try {
        // Delegar la consulta al DAO
        return reportePeliculaDAO.calcularGanancias(metodosDePago, sucursales, fechaInicio, fechaFin);
    } catch (PersistenciaException e) {
        // Manejar la excepción y envolverla en una NegocioException
        throw new NegocioException("Error al calcular las ganancias", e);
    }
}
    
    
}
