/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.DatosReporteSucursalDTO;
import java.util.List;
import java.util.Set;

/**
 *
 * @author NaderCroft
 */
public interface IReportesSucursalesBO {
    public List<DatosReporteSucursalDTO> obtenerGananciasPorSucursales(List<Integer> sucursalIds, String fechaInicio, String fechaFin) throws NegocioException;
}
