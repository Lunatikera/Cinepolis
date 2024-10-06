/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.DatosReporteMetodoPagoDTO;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IReporteMetodoPagoBO {

    public List<DatosReporteMetodoPagoDTO> calcularGanancias(List<String> metodosDePago, List<Integer> sucursales, String fechaInicio, String fechaFin) throws NegocioException;

}
