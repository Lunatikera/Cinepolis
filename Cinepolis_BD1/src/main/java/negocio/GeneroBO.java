/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.GeneroEntidad;
import dtos.GeneroDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.GeneroDAO;
import persistencia.IGeneroDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author samoano
 */
public class GeneroBO implements IGeneroBO {

    private IGeneroDAO generoDAO;

    public GeneroBO(IGeneroDAO generoDAO) {
        this.generoDAO = generoDAO;
    }

    @Override
    public List<GeneroDTO> listasGeneros() throws NegocioException {
        try {
            List<GeneroEntidad> listaEntidad = this.generoDAO.listaGeneros();
            List<GeneroDTO> listaDTO = new ArrayList<>();

            for (GeneroEntidad genero : listaEntidad) {
                GeneroDTO agregar = new GeneroDTO(genero.getIdGenero(), genero.getNombreGenero());
                listaDTO.add(agregar);
            }
            return listaDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo agregar el genero a la base de datos");
        }
    }

    @Override
    public List<GeneroDTO> listaGeneroPorPelicula(int idPelicula, boolean incluirRelacionados) throws NegocioException {
        try {
            List<GeneroEntidad> listaEntidad = this.generoDAO.listaGeneroPorPelicula(idPelicula, incluirRelacionados);
            List<GeneroDTO> listaDTO = new ArrayList<>();

            for (GeneroEntidad genero : listaEntidad) {
                GeneroDTO agregar = new GeneroDTO(genero.getIdGenero(), genero.getNombreGenero());
                listaDTO.add(agregar);
            }

            return listaDTO;
        } catch (PersistenciaException ex) {
            Logger.getLogger(GeneroBO.class.getName()).log(Level.SEVERE, null, ex);

            throw new NegocioException("No se pudo agregar el genero a la base de datos");
        }
    }

    @Override
    public void actualizarGenerosPelicula(int peliculaId, List<Integer> nuevosGeneros) throws NegocioException {
        try {
            // Validación de que la lista de géneros no esté vacía
            if (nuevosGeneros == null || nuevosGeneros.isEmpty()) {
                throw new NegocioException("La lista de géneros no puede estar vacía.");
            }

            // Aquí puedes agregar más lógica de negocio si es necesario
            // Por ejemplo, puedes verificar si la película existe antes de actualizar los géneros.
            // Llamada a la capa de persistencia para realizar la transacción
            generoDAO.actualizarGenerosPeliculaConTransaccion(peliculaId, nuevosGeneros);

            // Mensaje de éxito (puedes también retornar algún tipo de resultado si es necesario)
            System.out.println("Los géneros de la película se han actualizado correctamente.");

        } catch (PersistenciaException e) {
            // Convertimos la excepción de persistencia en una excepción de negocio
            throw new NegocioException("Error al actualizar los géneros de la película.", e);
        }
    }
}
