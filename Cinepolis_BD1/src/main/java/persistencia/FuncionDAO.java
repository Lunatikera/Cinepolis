/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.FuncionEntidad;
import dtos.ConsultaFuncionDTO;
import dtos.FiltroFuncionesDTO;
import dtos.FuncionTablaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samoano
 */
public class FuncionDAO implements IFuncionDAO {

    private IConexionBD conexionBD;

    public FuncionDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public FuncionEntidad guardar(FuncionEntidad funcion) throws PersistenciaException {
        String sql = "INSERT INTO Funciones (precio, dia, hora_inicio, sala_id, pelicula_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer los valores del PreparedStatement
            pstmt.setBigDecimal(1, funcion.getPrecio());
            pstmt.setString(2, funcion.getDia());
            pstmt.setTime(3, Time.valueOf(funcion.getHora()));
            pstmt.setInt(4, funcion.getIdSala());
            pstmt.setInt(5, funcion.getIdPelicula());

            // Ejecutar la inserción
            pstmt.executeUpdate();
            return funcion;
        } catch (SQLException e) {
            throw new PersistenciaException("No se pudo guardar la funcion en la base de datos");
        }
    }

    @Override
    public FuncionEntidad leerPorID(int id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPorID(int id) throws PersistenciaException {
        String sql = "UPDATE Funciones SET estaEliminado = 1 WHERE funcion_id = ?";

        try (Connection connection = conexionBD.crearConexion(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Seteamos el parámetro de la consulta (el id de la función que se desea eliminar)
            preparedStatement.setInt(1, id);

            // Ejecutamos la consulta
            int rowsAffected = preparedStatement.executeUpdate();

            // Verificamos si se actualizó alguna fila
            if (rowsAffected == 0) {
                throw new PersistenciaException("No se encontró ninguna función con el ID especificado.");
            }

        } catch (SQLException e) {
            // Manejamos cualquier error relacionado con la base de datos
            throw new PersistenciaException("Error al realizar el soft delete en la función con ID: " + id, e);
        }
    }

    @Override
    public List<FuncionEntidad> listaFuncionporDiaSucursalPelicula(ConsultaFuncionDTO consulta, int limit, int offset) throws PersistenciaException {
        List<FuncionEntidad> listaFunciones = new ArrayList<>();
        String query = "SELECT f.funcion_id, f.precio, f.dia, f.hora_inicio, f.hora_final, f.asientos_Disponibles, f.sala_id, f.pelicula_id "
                + "FROM Funciones f "
                + "JOIN Salas s ON f.sala_id = s.sala_id "
                + "JOIN Peliculas p ON f.pelicula_id = p.pelicula_id "
                + "WHERE f.dia = ? "
                + "AND s.sucursal_id = ? "
                + "AND f.pelicula_id = ? "
                + "AND f.estaEliminado = 0 "
                + // Para considerar solo las funciones activas
                "LIMIT ? OFFSET ?";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, consulta.getDia());
            pstmt.setInt(2, consulta.getIdSala());
            pstmt.setInt(3, consulta.getIdPelicula());
            pstmt.setInt(4, limit);
            pstmt.setInt(5, offset);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FuncionEntidad funcion = new FuncionEntidad();
                    funcion.setId(rs.getInt("funcion_id"));
                    funcion.setPrecio(rs.getBigDecimal("precio"));
                    funcion.setDia(rs.getString("dia"));
                    funcion.setHora(rs.getTime("hora_inicio").toLocalTime());
                    funcion.setHoraFinal(rs.getTime("hora_final").toLocalTime());
                    funcion.setAsientosDisponibles(rs.getInt("asientos_Disponibles"));
                    funcion.setIdSala(rs.getInt("sala_id"));
                    funcion.setIdPelicula(rs.getInt("pelicula_id"));

                    listaFunciones.add(funcion);
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al listar las funciones por día, sucursal y película", e);
        }

        return listaFunciones;
    }

    @Override
    public List<FuncionTablaDTO> listaFuncionporDiaSala(FiltroFuncionesDTO filtro) throws PersistenciaException {
        List<FuncionTablaDTO> listaFunciones = new ArrayList<>();
        String sql = "SELECT f.funcion_id, p.titulo, p.duracion, hora_inicio, f.hora_final_total, f.precio "
                + "FROM Funciones f "
                + "INNER JOIN Peliculas p ON f.pelicula_id = p.pelicula_id "
                + "INNER JOIN Salas s ON f.sala_id = s.sala_id "
                + "WHERE f.estaEliminado = 0 "
                + "AND f.dia = ? "
                + "AND s.sala_id = ? "
                + "ORDER BY s.sala_id ";

        try (Connection conn = conexionBD.crearConexion(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, filtro.getDia());
            pstmt.setInt(2, filtro.getIdSala());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                FuncionTablaDTO funcion = new FuncionTablaDTO();
                funcion.setId(rs.getInt("funcion_id"));
                funcion.setPeliculaTitulo(rs.getString("titulo"));
                funcion.setDuracion(rs.getInt("duracion"));
                funcion.setHora(rs.getObject("hora_inicio", LocalTime.class));
                funcion.setHoraFinal(rs.getObject("hora_final_total", LocalTime.class));
                funcion.setPrecio(rs.getBigDecimal("precio"));

                listaFunciones.add(funcion);
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener las funciones por día y sala.", e);
        }

        return listaFunciones;
    }

}
