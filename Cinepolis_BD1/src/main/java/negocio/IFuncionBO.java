/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dominio.FuncionEntidad;
import dtos.ConsultaFuncionDTO;
import dtos.FiltroFuncionesDTO;
import dtos.FuncionDTO;
import dtos.FuncionTablaDTO;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IFuncionBO {

    public void guardar(FuncionDTO funcion) throws NegocioException;

    public FuncionDTO leerPorID(int id) throws NegocioException;

    public void eliminarPorID(int id) throws NegocioException;

    public List<FuncionDTO> listaFuncionporDiaSucursalPelicula(ConsultaFuncionDTO consulta, int limit, int offset) throws NegocioException;

    public List<FuncionTablaDTO> listaFuncionporDiaSala(FiltroFuncionesDTO filtro) throws NegocioException;

}
