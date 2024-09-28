/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import persistencia.*;
import dominio.SalaEntidad;
import dtos.SalaDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.NegocioException;

/**
 *
 * @author Samoano
 */
public class SalaBO implements ISalaBO {

    private ISalaDAO salaDAO;

    public SalaBO(ISalaDAO salaDAO) {
        this.salaDAO = salaDAO;
    }

    @Override
    public void guardar(SalaDTO sala) throws NegocioException {
        try {
            SalaEntidad salasE = new SalaEntidad(
                    sala.getId(),
                    sala.getNombre(),
                    sala.getNumeroAsiento(),
                    sala.getDuracionLimpieza(),
                    sala.getIdSucursal(),
                    sala.isEstaEliminada()
            );
            this.salaDAO.guardar(salasE);
        } catch (PersistenciaException e) {
            Logger.getLogger(SalaBO.class.getName()).log(Level.SEVERE, null, e);
            throw new NegocioException("Error en la capa de negocio guardar una sala", e);
        }
    }

    @Override
    public SalaDTO leerPorId(int id) throws NegocioException {
        try {
            SalaEntidad sala = this.salaDAO.leerPorID(id);
            SalaDTO salasE = new SalaDTO(
                    sala.getId(),
                    sala.getNombre(),
                    sala.getNumeroAsiento(),
                    sala.getDuracionLimpieza(),
                    sala.getIdSucursal(),
                    sala.isEstaEliminada()
            );
            return salasE;
        } catch (PersistenciaException e) {
            Logger.getLogger(SalaBO.class.getName()).log(Level.SEVERE, null, e);
            throw new NegocioException("Error en la capa de negocio al buscar películas para la sucursal", e);
        }
    }

    @Override
    public void editar(SalaDTO sala) throws NegocioException {
        try {
            SalaEntidad salasE = new SalaEntidad(
                    sala.getId(),
                    sala.getNombre(),
                    sala.getNumeroAsiento(),
                    sala.getDuracionLimpieza(),
                    sala.getIdSucursal(),
                    sala.isEstaEliminada()
            );
            this.salaDAO.editar(salasE);
        } catch (PersistenciaException e) {
            Logger.getLogger(SalaBO.class.getName()).log(Level.SEVERE, null, e);
            throw new NegocioException("Error en la capa de negocio al editar las salas", e);
        }
    }

    @Override
    public void eliminarPorId(int id) throws NegocioException {
        try {
            this.salaDAO.eliminarPorID(id);
        } catch (PersistenciaException e) {
            Logger.getLogger(SalaBO.class.getName()).log(Level.SEVERE, null, e);
            throw new NegocioException("Error en la capa de negocio al buscar películas para la sucursal", e);
        }
    }

    @Override
    public List<SalaDTO> paginadoSalasPorSucursal(int idSucursal, int limit, int pagina) throws NegocioException {
        try {
            int offset = utilerias.Herramientas.RegresarOFFSETMySQL(limit, pagina);
            List<SalaEntidad> salaListasE = this.salaDAO.paginadoSalasporSucursal(idSucursal, limit, offset);
            List<SalaDTO> salasD = new ArrayList<>();

            for (SalaEntidad sala : salaListasE) {
                SalaDTO salasP = new SalaDTO();
                salasP.setId(sala.getId());
                salasP.setIdSucursal(sala.getIdSucursal());
                salasP.setDuracionLimpieza(sala.getDuracionLimpieza());
                salasP.setNombre(sala.getNombre());
                salasP.setNumeroAsiento(sala.getNumeroAsiento());
                salasD.add(salasP);
            }
            return salasD;
        } catch (PersistenciaException e) {
            Logger.getLogger(SalaBO.class.getName()).log(Level.SEVERE, null, e);
            throw new NegocioException("Error en la capa de negocio al buscar películas para la sucursal", e);
        }
    }

    @Override
    public List<SalaDTO> salasPorSucursal(int idSucursal) throws NegocioException {
        List<SalaEntidad> salaListasE;
        try {
            salaListasE = this.salaDAO.salasPorSucursal(idSucursal);

            List<SalaDTO> salasD = new ArrayList<>();

            for (SalaEntidad sala : salaListasE) {
                SalaDTO salasP = new SalaDTO();
                salasP.setId(sala.getId());
                salasP.setIdSucursal(sala.getIdSucursal());
                salasP.setDuracionLimpieza(sala.getDuracionLimpieza());
                salasP.setNombre(sala.getNombre());
                salasP.setNumeroAsiento(sala.getNumeroAsiento());
                salasD.add(salasP);
            }
            return salasD;
        } catch (PersistenciaException e) {
            Logger.getLogger(SalaBO.class.getName()).log(Level.SEVERE, null, e);
            throw new NegocioException("Error en la capa de negocio al buscar películas para la sucursal", e);
        }
    }
}
