/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.GeneroEntidad;
import dtos.GeneroDTO;
import java.util.ArrayList;
import java.util.List;
import persistencia.GeneroDAO;
import persistencia.IGeneroDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author samoano
 */
public class GeneroBO implements IGeneroBO{

    private IGeneroDAO generoDAO;

    @Override
    public List<GeneroDTO> listasGeneros() throws NegocioException {
        try{
            List<GeneroEntidad> listaEntidad = this.generoDAO.listaGeneros();
            List<GeneroDTO> listaDTO = new ArrayList<>();
            
            for (GeneroEntidad genero : listaEntidad) {
                GeneroDTO agregar = new GeneroDTO(genero.getIdGenero(),genero.getNombreGenero());
                listaDTO.add(agregar);
            }
            return listaDTO;
        }catch(PersistenciaException ex){
        throw new NegocioException("No se pudo agregar el genero a la base de datos");
        }
    }

    @Override
    public List<GeneroDTO> listasGenerosPorPeliculas(int idPelicula) throws NegocioException {
        try{
            List<GeneroEntidad> listaEntidad = this.generoDAO.listaGeneroPorPelicula(idPelicula);
            List<GeneroDTO> listaDTO = new ArrayList<>();
            
            for (GeneroEntidad genero : listaEntidad) {
                GeneroDTO agregar = new GeneroDTO(genero.getIdGenero(),genero.getNombreGenero());
                listaDTO.add(agregar);
            }
            
            return listaDTO;
        }catch(PersistenciaException ex){
        throw new NegocioException("No se pudo agregar el genero a la base de datos");
        }
    }

    
    
    
    
}
