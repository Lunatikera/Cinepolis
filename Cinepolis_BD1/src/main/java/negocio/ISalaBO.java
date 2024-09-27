/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import persistencia.*;
import dtos.SalaDTO;
import java.util.List;
import negocio.NegocioException;

/**
 *
 * @author NaderCroft
 */
public interface ISalaBO {
    public void guardar(SalaDTO sala) throws NegocioException;
    
    public SalaDTO leerPorId(int id) throws NegocioException;
    
    public void editar(SalaDTO sala) throws NegocioException;
    
    public void eliminarPorId(int id) throws NegocioException;
    
    public List<SalaDTO> paginadoSalasPorSucursal(int idSucursal, int limit, int pagina) throws NegocioException;
}
