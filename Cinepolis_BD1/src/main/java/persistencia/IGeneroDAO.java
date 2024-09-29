/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.GeneroEntidad;
import java.util.List;

/**
 *
 * @author NaderCroft
 */
public interface IGeneroDAO {
    public List<GeneroEntidad> listaGeneros()throws PersistenciaException;
    public List<GeneroEntidad> listaGeneroPorPelicula(int idPelicula)throws PersistenciaException;
}
