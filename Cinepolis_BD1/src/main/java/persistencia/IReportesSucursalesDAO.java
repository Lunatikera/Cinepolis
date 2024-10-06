/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dtos.DatosReporteSucursalDTO;
import java.util.List;

/**
 *
 * @author NaderCroft
 */
public interface IReportesSucursalesDAO {
    public List<DatosReporteSucursalDTO> obtenerGananciasPorSucursales(List<Integer> sucursalIds, String fechaInicio, String fechaFin) throws PersistenciaException;
}
