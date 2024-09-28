/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.SalaEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Temo
 */
public class SalaDAO implements ISalaDAO {

    private IConexionBD conexionBD;

    public SalaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public SalaEntidad guardar(SalaEntidad entidad) throws PersistenciaException {
        String sql = "INSERT INTO Salas (nombre, numAsientos, duracionLimpieza, sucursal_id, estaEliminado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pS.setString(1, entidad.getNombre());
            pS.setInt(2, entidad.getNumeroAsiento());
            pS.setInt(3, entidad.getDuracionLimpieza());
            pS.setInt(4, entidad.getIdSucursal());
            pS.setBoolean(5, entidad.isEstaEliminada());

            int filasAfectadas = pS.executeUpdate();
            if (filasAfectadas > 0) {
                ResultSet res = pS.getGeneratedKeys();
                if (res.next()) {
                    entidad.setId(res.getInt(1));
                }
            } else {
                throw new PersistenciaException("No se pudo guardar la sala.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al guardar la sala: " + e.getMessage());
        }
        return entidad;
    }

    @Override
    public SalaEntidad leerPorID(int id) throws PersistenciaException {
        String sql = "SELECT sala_id, nombre, numAsientos, duracionLimpieza, sucursal_id, estaEliminado "
                + "FROM Salas WHERE sala_id = ?";
        SalaEntidad sala = null;
        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sql)) {

            pS.setInt(1, id);
            ResultSet rs = pS.executeQuery();

            if (rs.next()) {
                sala = new SalaEntidad();
                sala.setId(rs.getInt("sala_id"));
                sala.setNombre(rs.getString("nombre"));
                sala.setNumeroAsiento(rs.getInt("numAsientos"));
                sala.setDuracionLimpieza(rs.getInt("duracionLimpieza"));
                sala.setIdSucursal(rs.getInt("sucursal_id"));
                sala.setEstaEliminada(rs.getBoolean("estaEliminado"));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al leer la sala: " + e.getMessage());
        }
        return sala;
    }

    @Override
    public void editar(SalaEntidad entidad) throws PersistenciaException {
        String sql = "UPDATE Salas SET nombre = ?, numAsientos = ?, duracionLimpieza = ?, sucursal_id = ? WHERE sala_id = ?";
        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sql)) {

            pS.setString(1, entidad.getNombre());
            pS.setInt(2, entidad.getNumeroAsiento());
            pS.setInt(3, entidad.getDuracionLimpieza());
            pS.setInt(4, entidad.getIdSucursal());
            pS.setInt(5, entidad.getId());

            int filasAfectadas = pS.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se encontró la sala para actualizar.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al editar la sala: " + e.getMessage());
        }
    }

    @Override
    public void eliminarPorID(int id) throws PersistenciaException {
        String sql = "UPDATE Salas SET estaEliminado = 1 WHERE sala_id = ?";
        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sql)) {

            pS.setInt(1, id);
            int filasAfectadas = pS.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("No se encontró la sala con ID: " + id);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al eliminar la sala: " + e.getMessage());
        }
    }

    @Override
    public List<SalaEntidad> paginadoSalasporSucursal(int idSucursal, int limit, int offset) throws PersistenciaException {
        String sql = "SELECT sala_id, nombre, numAsientos, duracionLimpieza, sucursal_id, estaEliminado "
                + "FROM Salas WHERE sucursal_id = ? and estaEliminado=0  LIMIT ? OFFSET ?";
        List<SalaEntidad> salas = new ArrayList<>();
        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sql)) {

            pS.setInt(1, idSucursal);
            pS.setInt(2, limit);
            pS.setInt(3, offset);
            ResultSet rs = pS.executeQuery();

            while (rs.next()) {
                SalaEntidad sala = new SalaEntidad();
                sala.setId(rs.getInt("sala_id"));
                sala.setNombre(rs.getString("nombre"));
                sala.setNumeroAsiento(rs.getInt("numAsientos"));
                sala.setDuracionLimpieza(rs.getInt("duracionLimpieza"));
                sala.setIdSucursal(rs.getInt("sucursal_id"));
                sala.setEstaEliminada(rs.getBoolean("estaEliminado"));
                salas.add(sala);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener las salas: " + e.getMessage());
        }
        return salas;
    }

    @Override
    public List<SalaEntidad> salasPorSucursal(int idSucursal) throws PersistenciaException {
        String sql = "SELECT sala_id, nombre, numAsientos, duracionLimpieza, sucursal_id, estaEliminado "
                + "FROM Salas WHERE sucursal_id = ? and estaEliminado=0 ";
        List<SalaEntidad> salas = new ArrayList<>();
        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sql)) {

            pS.setInt(1, idSucursal);
            ;
            ResultSet rs = pS.executeQuery();

            while (rs.next()) {
                SalaEntidad sala = new SalaEntidad();
                sala.setId(rs.getInt("sala_id"));
                sala.setNombre(rs.getString("nombre"));
                sala.setNumeroAsiento(rs.getInt("numAsientos"));
                sala.setDuracionLimpieza(rs.getInt("duracionLimpieza"));
                sala.setIdSucursal(rs.getInt("sucursal_id"));
                sala.setEstaEliminada(rs.getBoolean("estaEliminado"));
                salas.add(sala);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener las salas: " + e.getMessage());
        }
        return salas;
    }
}
