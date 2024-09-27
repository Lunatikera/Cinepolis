/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.PeliculaEntidad;
import dtos.PeliculaDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IPeliculaDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author carli
 */
public class PeliculaBO implements IPeliculaBO {

    private IPeliculaDAO peliculaDAO;

    public PeliculaBO(IPeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }

    @Override
    public void agregaPelicula(PeliculaDTO peliculaDTO) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarPelicula(PeliculaDTO peliculaDTO) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPelicula(int idPelicula) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PeliculaDTO buscarPeliculaPorId(int idCliente) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PeliculaDTO> buscarPaginadoPeliculas(int limite, int pagina) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PeliculaDTO> buscarPeliculaSucursal(int idSucursal, int limit, int pagina, boolean peliculaEnSucursal) throws NegocioException {
        try {
            int offset = utilerias.Herramientas.RegresarOFFSETMySQL(limit, pagina);
            List<PeliculaEntidad> peliculas = peliculaDAO.buscarPeliculaSucursal(idSucursal, limit, offset, peliculaEnSucursal);
            List<PeliculaDTO> peliculasDTO = new ArrayList<>();

            // Convertir las películas obtenidas a objetos PeliculaDTO
            for (PeliculaEntidad pelicula : peliculas) {
                PeliculaDTO peliculaDTO = convertirADTO(pelicula);
                peliculasDTO.add(peliculaDTO);
            }

            return peliculasDTO;
        } catch (PersistenciaException e) {
            Logger.getLogger(PeliculaBO.class.getName()).log(Level.SEVERE, null, e);

            throw new NegocioException("Error en la capa de negocio al buscar películas para la sucursal", e);
        }
    }

    private PeliculaDTO convertirADTO(PeliculaEntidad pelicula) throws NegocioException {
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(pelicula.getId());
        dto.setTitulo(pelicula.getTitulo());
        dto.setSinopsis(pelicula.getSinopsis());
        dto.setPais(pelicula.getPais());
        dto.setLink_trailer(pelicula.getLink_trailer());
        dto.setDuracion(pelicula.getDuracion());
        dto.setCartel(pelicula.getCartel());
        dto.setClasificacion(pelicula.getClasificacion().name());
        dto.setClasificacionDescripcion(pelicula.getClasificacion().getDescripcion());

        return dto;

    }

    @Override
    public void eliminarPeliculaDeSucursal(int peliculaId, int sucursalId) throws NegocioException {
        try {
            peliculaDAO.actualizarFechaRetiro(peliculaId, sucursalId);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PeliculaBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo eliminar la pelicula de la Sucursal");

        }
    }

    @Override
    public void guardarPeliculaEnSucursal(int peliculaId, int sucursalId) throws NegocioException {
        try {
            peliculaDAO.guardarPeliculaEnSucursal(peliculaId, sucursalId);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PeliculaBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("No se pudo guardar la pelicula en la Sucursal");
        }
    }

}
