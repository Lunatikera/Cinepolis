/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dtos.DatosReportePeliculasDTO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carli
 */
public class ReportePeliculaDAO implements IReportePeliculaDAO {
    IConexionBD conexionBD;

    public ReportePeliculaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<DatosReportePeliculasDTO> obtenerGananciasPorPeliculas(List<Integer> ciudadIds, List<Integer> peliculaIds, String fechaInicio, String fechaFin) throws PersistenciaException {
        List<DatosReportePeliculasDTO> reportes = new ArrayList<>();

        // Consulta SQL con placeholders para listas de ciudades y películas
        String sql = "SELECT " +
                     "    c.nombre AS Ciudad, " +
                     "    p.titulo AS Película, " +
                     "    DATE(v.fecha_compra) AS Fecha, " +
                     "    SUM(v.totalCompra) AS GananciaPorFecha " +
                     "FROM " +
                     "    Ventas v " +
                     "JOIN " +
                     "    Funciones f ON v.funcion_id = f.funcion_id " +
                     "JOIN " +
                     "    Peliculas p ON f.pelicula_id = p.pelicula_id " +
                     "JOIN " +
                     "    Sucursales s ON f.sala_id = s.sucursal_id " +
                     "JOIN " +
                     "    Ciudades c ON s.ciudad_id = c.ciudad_id " +
                     "WHERE " +
                     "    c.ciudad_id IN (" + generarPlaceholders(ciudadIds.size()) + ") " +
                     "    AND p.pelicula_id IN (" + generarPlaceholders(peliculaIds.size()) + ") " +
                     "    AND v.fecha_compra BETWEEN ? AND ? " +
                     "GROUP BY " +
                     "    c.nombre, p.titulo, DATE(v.fecha_compra) " +
                     "ORDER BY " +
                     "    c.nombre, p.titulo, Fecha;";

    try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement stmt = conexion.prepareStatement(sql)) {
            int index = 1;

            // Insertar los IDs de las ciudades en el PreparedStatement
            for (Integer ciudadId : ciudadIds) {
                stmt.setInt(index++, ciudadId);
            }

            // Insertar los IDs de las películas en el PreparedStatement
            for (Integer peliculaId : peliculaIds) {
                stmt.setInt(index++, peliculaId);
            }

            // Insertar las fechas de inicio y fin (conversión de String a Date en SQL)
            stmt.setString(index++, fechaInicio);
            stmt.setString(index++, fechaFin);

            // Ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                // Recorrer el resultado y agregarlo a la lista de reportes
                while (rs.next()) {
                    String ciudad = rs.getString("Ciudad");
                    String pelicula = rs.getString("Película");
                    LocalDate fecha = rs.getObject("Fecha",LocalDate.class);
                    BigDecimal gananciaPorFecha = rs.getBigDecimal("GananciaPorFecha");

                    DatosReportePeliculasDTO reporte = new DatosReportePeliculasDTO(ciudad, pelicula, fecha, gananciaPorFecha);
                    reportes.add(reporte);
                }
            }
        } catch (SQLException e) {
            // Manejo de excepción SQL
            throw new PersistenciaException("Error al obtener ganancias por películas", e);
        }

        return reportes;
    }

    // Método auxiliar para generar placeholders (?, ?, ?, ...) basado en el tamaño de las listas
    private String generarPlaceholders(int size) {
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < size; i++) {
            placeholders.append("?");
            if (i < size - 1) {
                placeholders.append(", ");
            }
        }
        return placeholders.toString();
    }
}

