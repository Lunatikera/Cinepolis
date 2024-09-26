/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.FuncionEntidad;
import dtos.ConsultaFuncionDTO;
import java.util.List;

/**
 *
 * @author samoano
 */
public interface IFuncionDAO {
    
    public FuncionEntidad guardar(FuncionEntidad entidad) throws PersistenciaException;

    public FuncionEntidad leerPorID(int id) throws PersistenciaException;
    
    public void eliminarPorID(int id) throws PersistenciaException;
    
    public List<FuncionEntidad> listaFuncionporDiaSucursalPelicula(ConsultaFuncionDTO consulta, int limit, int offset) throws PersistenciaException;
}
