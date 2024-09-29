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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IFuncionDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author carli
 */
public class FuncionBO implements IFuncionBO{

    private IFuncionDAO funcionDAO;

    public FuncionBO(IFuncionDAO funcionDAO) {
        this.funcionDAO = funcionDAO;
    }

    @Override
    public void guardar(FuncionDTO funcionDTO) throws NegocioException {
        FuncionEntidad funcion=new FuncionEntidad(funcionDTO.getPrecion(), funcionDTO.getDia(), funcionDTO.getHora(), funcionDTO.getIdSala(), funcionDTO.getIdPelicula());
        try {
            funcionDAO.guardar(funcion);
        } catch (PersistenciaException ex) {
            Logger.getLogger(FuncionBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public FuncionDTO leerPorID(int id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPorID(int id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<FuncionDTO> listaFuncionporDiaSucursalPelicula(ConsultaFuncionDTO consulta, int limit, int offset) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
