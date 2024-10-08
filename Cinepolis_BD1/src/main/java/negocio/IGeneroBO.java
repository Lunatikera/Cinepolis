/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.GeneroDTO;
import java.util.List;
import persistencia.GeneroDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author NaderCroft
 */
public interface IGeneroBO {

    public List<GeneroDTO> listasGeneros() throws NegocioException;

    public List<GeneroDTO> listaGeneroPorPelicula(int idPelicula, boolean incluirRelacionados) throws NegocioException;

    public void actualizarGenerosPelicula(int peliculaId, List<Integer> nuevosGeneros) throws NegocioException;

}
