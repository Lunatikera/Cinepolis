/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dtos.TicketDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carli
 */
public class TicketDAO implements ITicketDAO {

    private IConexionBD conexionBD;

    public TicketDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<TicketDTO> obtenerVentasFuncionesPorCliente(int clienteId, int limit, int offset) throws PersistenciaException {
        List<TicketDTO> listaVentasFunciones = new ArrayList<>();
        String sql = "SELECT "
                + "Peliculas.titulo AS nombre_pelicula, "
                + "Peliculas.cartel, "
                + "Sucursales.nombre AS nombre_sucursal, "
                + "Salas.nombre AS nombre_sala, "
                + "Funciones.hora_inicio, "
                + "Funciones.hora_final, "
                + "Funciones.dia, "
                + "Ventas.precioUnitario, "
                + "Ventas.cantidad_Boletos, "
                + "Ventas.totalCompra, "
                + "Ventas.metodoPago, "
                + "Ventas.fecha_compra, "
                + "Ciudades.nombre AS nombre_ciudad "
                + // Añadir el nombre de la ciudad
                "FROM Ventas "
                + "INNER JOIN Clientes ON Ventas.cliente_id = Clientes.cliente_id "
                + "INNER JOIN Funciones ON Ventas.funcion_id = Funciones.funcion_id "
                + "INNER JOIN Peliculas ON Funciones.pelicula_id = Peliculas.pelicula_id "
                + "INNER JOIN Salas ON Funciones.sala_id = Salas.sala_id "
                + "INNER JOIN Sucursales ON Salas.sucursal_id = Sucursales.sucursal_id "
                + "INNER JOIN Ciudades ON Sucursales.ciudad_id = Ciudades.ciudad_id "
                + // Añadir el JOIN para la ciudad
                "WHERE Clientes.cliente_id = ? "
                + // Filtramos por cliente
                "ORDER BY Clientes.nombres, Clientes.apellidoPA, Clientes.apellidoMA, Ventas.fecha_compra "
                + "LIMIT ? OFFSET ?;";

        try (Connection connection = conexionBD.crearConexion(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Seteamos los parámetros: clienteId, pageSize, y offset
            preparedStatement.setInt(1, clienteId);  // Filtro por cliente
            preparedStatement.setInt(2, limit);     // Offset para empezar desde la posición correcta
            preparedStatement.setInt(3, offset);   // Límite de registros por página

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TicketDTO ventaFuncion = new TicketDTO();

                ventaFuncion.setNombrePelicula(resultSet.getString("nombre_pelicula"));
                ventaFuncion.setCartelPelicula(resultSet.getString("cartel"));
                ventaFuncion.setNombreSucursal(resultSet.getString("nombre_sucursal"));
                ventaFuncion.setNombreSala(resultSet.getString("nombre_sala"));
                ventaFuncion.setHoraInicio(resultSet.getObject("hora_inicio", LocalTime.class));
                ventaFuncion.setHoraFinal(resultSet.getObject("hora_final", LocalTime.class));
                ventaFuncion.setDia(resultSet.getString("dia"));
                ventaFuncion.setPrecioUnitario(resultSet.getBigDecimal("precioUnitario"));
                ventaFuncion.setCantidadBoletos(resultSet.getInt("cantidad_Boletos"));
                ventaFuncion.setTotalCompra(resultSet.getBigDecimal("totalCompra"));
                ventaFuncion.setMetodoPago(resultSet.getString("metodoPago"));
                ventaFuncion.setFechaCompra(resultSet.getObject("fecha_compra", LocalDateTime.class));
                ventaFuncion.setCiudad(resultSet.getString("nombre_ciudad")); // Establece el nombre de la ciudad

                listaVentasFunciones.add(ventaFuncion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaVentasFunciones;
    }
}
