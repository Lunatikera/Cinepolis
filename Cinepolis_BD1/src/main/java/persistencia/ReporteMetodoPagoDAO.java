/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dtos.DatosReporteMetodoPagoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carli
 */
public class ReporteMetodoPagoDAO implements IReporteMetodoPagoDAO {

    IConexionBD conexion;

    public ReporteMetodoPagoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<DatosReporteMetodoPagoDTO> calcularGanancias(List<String> metodosDePago, List<Integer> sucursales, String fechaInicio, String fechaFin) throws PersistenciaException {
  List<DatosReporteMetodoPagoDTO> resultados = new ArrayList<>();
    StringBuilder query = new StringBuilder();
    
    query.append("SELECT ")
         .append("DATE(v.fecha_compra) AS Fecha, ")
         .append("c.nombre AS Ciudad, ")
         .append("s.nombre AS Sucursal, ")
         .append("v.metodoPago AS Tipo_de_pago, ")
         .append("SUM(v.totalCompra) AS Total_ganancia_por_fecha ")
         .append("FROM Ventas v ")
         .append("JOIN Funciones f ON v.funcion_id = f.funcion_id ")
         .append("JOIN Salas sa ON f.sala_id = sa.sala_id ")
         .append("JOIN Sucursales s ON sa.sucursal_id = s.sucursal_id ")
         .append("JOIN Ciudades c ON s.ciudad_id = c.ciudad_id ")
         .append("WHERE v.metodoPago IN (");

    // Añadir placeholders para los métodos de pago dinámicos
    for (int i = 0; i < metodosDePago.size(); i++) {
        query.append("?");
        if (i < metodosDePago.size() - 1) {
            query.append(", ");
        }
    }
    query.append(") AND s.sucursal_id IN (");

    // Añadir placeholders para las sucursales dinámicas
    for (int i = 0; i < sucursales.size(); i++) {
        query.append("?");
        if (i < sucursales.size() - 1) {
            query.append(", ");
        }
    }
    query.append(") AND v.fecha_compra BETWEEN ? AND ? ")
         .append("GROUP BY Fecha, Ciudad, Sucursal, Tipo_de_pago ")
         .append("ORDER BY Fecha, Ciudad, Sucursal, Tipo_de_pago;");

    // Preparar la consulta SQL
    try (Connection conexion = this.conexion.crearConexion(); PreparedStatement stmt = conexion.prepareStatement(query.toString())) {
        int paramIndex = 1;

        // Asignar los valores para los métodos de pago
        for (String metodo : metodosDePago) {
            stmt.setString(paramIndex++, metodo);
        }

        // Asignar los valores para las sucursales
        for (Integer sucursalId : sucursales) {
            stmt.setInt(paramIndex++, sucursalId);
        }

        // Asignar las fechas
        stmt.setString(paramIndex++, fechaInicio);
        stmt.setString(paramIndex++, fechaFin);

        // Ejecutar la consulta
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // Crear un nuevo objeto DTO con los datos de la consulta
                DatosReporteMetodoPagoDTO datos = new DatosReporteMetodoPagoDTO();
                datos.setFechaCompra(rs.getObject("Fecha", LocalDateTime.class));
                datos.setNombreCiudad(rs.getString("Ciudad"));
                datos.setNombreSucursal(rs.getString("Sucursal"));
                datos.setMetodoPago(rs.getString("Tipo_de_pago"));
                datos.setTotalCompra(rs.getBigDecimal("Total_ganancia_por_fecha"));

                // Añadir el DTO a la lista de resultados
                resultados.add(datos);
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ReporteMetodoPagoDAO.class.getName()).log(Level.SEVERE, null, ex);
        throw new PersistenciaException("Error al calcular ganancias", ex);
    }

    return resultados;  // Retornar la lista de resultados
}
}