/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dtos.DatosReporteSucursalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NaderCroft
 */
public class ReportesSucursalesDAO implements IReportesSucursalesDAO{
    private IConexionBD conexionBD;

    public ReportesSucursalesDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    public List<DatosReporteSucursalDTO> obtenerGananciasPorSucursales(List<Integer> sucursalIds, String fechaInicio, String fechaFin) throws PersistenciaException{
    StringBuilder query = new StringBuilder();
    query.append("SELECT ci.nombre AS Ciudad, su.nombre AS Sucursal, COUNT(DISTINCT f.funcion_id) AS CantidadFunciones, ");
    query.append("DATE(v.fecha_compra) AS Fecha, SUM(v.totalCompra) AS TotalGanancia ");
    query.append("FROM Ventas v ");
    query.append("JOIN Funciones f ON v.funcion_id = f.funcion_id ");
    query.append("JOIN Salas s ON f.sala_id = s.sala_id ");
    query.append("JOIN Sucursales su ON s.sucursal_id = su.sucursal_id ");
    query.append("JOIN Ciudades ci ON su.ciudad_id = ci.ciudad_id ");
    query.append("WHERE su.sucursal_id IN (");
    
    // Añadir tantos signos de interrogación como sucursales seleccionadas
    for (int i = 0; i < sucursalIds.size(); i++) {
        query.append("?");
        if (i < sucursalIds.size() - 1) {
            query.append(", ");
        }
    }
    query.append(") AND v.fecha_compra BETWEEN ? AND ? ");
    query.append("GROUP BY Ciudad, Sucursal, Fecha ");
    query.append("ORDER BY Fecha");

    
    // Preparar la consulta
        System.out.println(query.toString());
    try(Connection conexion = this.conexionBD.crearConexion(); PreparedStatement ps = conexion.prepareStatement(query.toString())){
        

    // Establecer los valores dinámicos de los IDs de sucursales
    for (int i = 0; i < sucursalIds.size(); i++) {
        ps.setInt(i + 1, sucursalIds.get(i));
    }

    // Establecer los valores de fechaInicio y fechaFin
    ps.setString(sucursalIds.size() + 1, fechaInicio);
    ps.setString(sucursalIds.size() + 2, fechaFin);

    ResultSet rs = ps.executeQuery();
        System.out.println(rs);
    // Lista para almacenar los resultados
    List<DatosReporteSucursalDTO> datosReporte = new ArrayList<>();
    
    while (rs.next()) {
        DatosReporteSucursalDTO datos = new DatosReporteSucursalDTO(
            rs.getString("Ciudad"),
            rs.getString("Sucursal"),
            rs.getInt("CantidadFunciones"),
            rs.getObject("Fecha",LocalDate.class),
            rs.getBigDecimal("TotalGanancia")
        );
        datosReporte.add(datos);
    }
        System.out.println(datosReporte);
    return datosReporte;
    }catch(SQLException e) {
        throw new PersistenciaException("Error al buscar el cliente por ID: " + e.getMessage());
    }
}
}
