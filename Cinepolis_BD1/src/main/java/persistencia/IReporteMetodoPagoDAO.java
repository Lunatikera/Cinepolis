/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dtos.DatosReporteMetodoPagoDTO;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IReporteMetodoPagoDAO {

    public List<DatosReporteMetodoPagoDTO> calcularGanancias(List<String> metodosDePago, List<Integer> sucursales, String fechaInicio, String fechaFin) throws PersistenciaException;
}
