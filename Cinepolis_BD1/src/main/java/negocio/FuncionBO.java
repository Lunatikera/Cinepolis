/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.FuncionEntidad;
import dtos.ConsultaFuncionDTO;
import dtos.FiltroFuncionesDTO;
import dtos.FuncionDTO;
import dtos.FuncionTablaDTO;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IFuncionDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author carli
 */
public class FuncionBO implements IFuncionBO {

    private IFuncionDAO funcionDAO;

    public FuncionBO(IFuncionDAO funcionDAO) {
        this.funcionDAO = funcionDAO;
    }

    @Override
    public void guardar(FuncionDTO funcionDTO) throws NegocioException {
        System.out.println(funcionDTO);
        FuncionEntidad funcion = new FuncionEntidad(funcionDTO.getPrecio(), funcionDTO.getDia(), funcionDTO.getHora(), funcionDTO.getIdSala(), funcionDTO.getIdPelicula());
        try {
            funcionDAO.guardar(funcion);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FuncionBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al intentar agregar la función ", ex);

        }
    }

    @Override
    public FuncionDTO leerPorID(int id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPorID(int id) throws NegocioException {
        try {
            funcionDAO.eliminarPorID(id);
            System.out.println("Función eliminada correctamente.");
        } catch (PersistenciaException e) {
            // Handle business exceptions
            throw new NegocioException("Error al intentar eliminar la función con ID: " + id, e);
        }
    }

    @Override
    public List<FuncionDTO> listaFuncionporDiaSucursalPelicula(ConsultaFuncionDTO consulta, int limit, int offset) throws NegocioException {
        List<FuncionDTO> listaFunciones = new ArrayList<>();
        try {
            System.out.println(consulta.toString());
            offset = utilerias.Herramientas.RegresarOFFSETMySQL(limit, offset);
            List<FuncionEntidad> funciones = funcionDAO.listaFuncionporDiaSucursalPelicula(consulta, limit, offset);

            // Convertimos las entidades a DTO
            for (FuncionEntidad funcionEntidad : funciones) {
                FuncionDTO funcionDTO = new FuncionDTO();

                // Llenar el DTO con los valores de la entidad
                funcionDTO.setId(funcionEntidad.getId());
                funcionDTO.setPrecio(funcionEntidad.getPrecio());
                funcionDTO.setDia(funcionEntidad.getDia());
                funcionDTO.setHora(funcionEntidad.getHora());
                funcionDTO.setHoraFinal(funcionEntidad.getHoraFinal());
                funcionDTO.setAsientosDisponibles(funcionEntidad.getAsientosDisponibles());
                funcionDTO.setIdSala(funcionEntidad.getIdSala());
                funcionDTO.setIdPelicula(funcionEntidad.getIdPelicula());

                // Agregamos el DTO a la lista de resultados
                listaFunciones.add(funcionDTO);
            }

        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener las funciones.", e);
        }

        return listaFunciones;
    }

    @Override
    public List<FuncionTablaDTO> listaFuncionporDiaSala(FiltroFuncionesDTO filtro) throws NegocioException {
        try {
            return funcionDAO.listaFuncionporDiaSala(filtro);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener las funciones por día y sala.", e);
        }
    }
}
