/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.DatosReporteSucursalDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IReportesSucursalesDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author NaderCroft
 */
public class ReportesSucursalesBO implements IReportesSucursalesBO{

    private IReportesSucursalesDAO datos;

    public ReportesSucursalesBO(IReportesSucursalesDAO datos) {
        this.datos = datos;
    }
    
    
    
    @Override
    public List<DatosReporteSucursalDTO> obtenerGananciasPorSucursales(List<Integer> sucursalIds, String fechaInicio, String fechaFin) throws NegocioException {
        try{
            if (sucursalIds.isEmpty() || fechaInicio.isEmpty() || fechaFin.isEmpty()){
                throw new NegocioException("Existen campos vacios");
            }
            List<DatosReporteSucursalDTO> reporte = this.datos.obtenerGananciasPorSucursales(sucursalIds, fechaInicio, fechaFin);
            List<DatosReporteSucursalDTO> mandar = new ArrayList<>();
            
            for (DatosReporteSucursalDTO repo : reporte) {
                DatosReporteSucursalDTO m = new DatosReporteSucursalDTO(repo.getCiudad(), repo.getSucursal(), repo.getCantidadFunciones(), repo.getFecha(),repo.getTotal());
                mandar.add(m);
            }
            return mandar;
        }catch(PersistenciaException e){
        Logger.getLogger(PeliculaBO.class.getName()).log(Level.SEVERE, null, e);
            throw new NegocioException("Error en la capa de negocio al agregar la pelicula", e);
        }
    }
    
}
