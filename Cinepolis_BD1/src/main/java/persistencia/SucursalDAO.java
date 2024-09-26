/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.SucursalEntidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.SucursalBO;

/**
 *
 * @author samoano
 */
public class SucursalDAO implements ISucursalDAO {

    private IConexionBD conexionBD;

    public SucursalDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public SucursalEntidad buscarSucursalMasCercana(int clienteId) throws PersistenciaException {
        SucursalEntidad sucursal = null;
        String query = "{CALL BuscarSucursalMasCercana(?)}";

        try (Connection connection = conexionBD.crearConexion(); CallableStatement stmt = connection.prepareCall(query)) {

            // Configurar el parámetro de entrada
            stmt.setInt(1, clienteId);

            // Ejecutar la consulta
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    sucursal = new SucursalEntidad();
                    sucursal.setId(rs.getInt("sucursal_id"));
                    sucursal.setNombre(rs.getString("nombre"));
                    sucursal.setDireccion(rs.getString("direccion"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SucursalDAO.class.getName()).log(Level.SEVERE, null, ex);

            throw new PersistenciaException("Error al obtener la sucursal mas cercana");
        }

        return sucursal;
    }

    @Override
    public List<SucursalEntidad> obtenerSucursales() throws PersistenciaException {
        List<SucursalEntidad> sucursales = new ArrayList<>();
        String query = "SELECT sucursal_id, nombre, direccion, ubicacion, ciudad_id FROM Sucursales";

        try (Connection connection = conexionBD.crearConexion(); PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                SucursalEntidad sucursal = new SucursalEntidad();
                sucursal.setId(rs.getInt("sucursal_id"));
                sucursal.setNombre(rs.getString("nombre"));
                sucursal.setDireccion(rs.getString("direccion"));
                sucursal.setUbicacion(rs.getString("ubicacion"));
                sucursal.setIdCiudad(rs.getInt("ciudad_id"));
                sucursales.add(sucursal);
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al obtener las sucursales", ex);
        }

        return sucursales;
    }

    @Override
    public List<SucursalEntidad> listaSucursalesporCiudad(int idCiudad) throws PersistenciaException {
        List<SucursalEntidad> sucursales = new ArrayList<>();

        // Consulta SQL para obtener las sucursales de una ciudad
        String sentenciaSQL = "SELECT sucursal_id, nombre, direccion, ubicacion, ciudad_id "
                + "FROM Sucursales "
                + "WHERE ciudad_id = ?";

        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sentenciaSQL)) {

            // Establecer el parámetro del ID de ciudad en la consulta preparada
            pS.setInt(1, idCiudad);

            // Ejecutar la consulta
            try (ResultSet resultSet = pS.executeQuery()) {
                // Iterar sobre los resultados y mapearlos a objetos Sucursal
                while (resultSet.next()) {
                    int sucursalId = resultSet.getInt("sucursal_id");
                    String nombre = resultSet.getString("nombre");
                    String direccion = resultSet.getString("direccion");
                    String ubicacion = resultSet.getString("ubicacion");
                    // Crear un objeto Sucursal y agregarlo a la lista
                    SucursalEntidad sucursal = new SucursalEntidad(sucursalId, nombre, direccion, ubicacion, idCiudad);
                    sucursales.add(sucursal);
                }

            }

        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener la lista de sucursales", e);
        }
        return sucursales;

    }

}
