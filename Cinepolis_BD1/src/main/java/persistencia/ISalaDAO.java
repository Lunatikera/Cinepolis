/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.SalaEntidad;
import java.util.List;

/**
 *
 * @author Temo
 */
public interface ISalaDAO {

    public SalaEntidad guardar(SalaEntidad entidad) throws PersistenciaException;

    public SalaEntidad leerPorID(int id) throws PersistenciaException;

    public void editar(SalaEntidad entidad) throws PersistenciaException;

    public void eliminarPorID(int id) throws PersistenciaException;

    public List<SalaEntidad> paginadoSalasporSucursal(int idSucursal, int limit, int offset) throws PersistenciaException;
    
    public List<SalaEntidad> salasPorSucursal(int idSucursal) throws PersistenciaException;

}
