/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.VentaEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author carli
 */
public class VentaDAO implements IVentaDAO {

    private IConexionBD conexionBD;

    public VentaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public VentaEntidad agregarVenta(VentaEntidad venta) throws PersistenciaException {
        String sql = "insert into Ventas(cantidad_Boletos, metodoPago, cliente_id, funcion_id) Values(?,?,?,?)";
        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pS.setInt(1, venta.getCantidadBoletos());
            pS.setString(2, venta.getMetodoPago().toString());
            pS.setInt(3, venta.getClienteId());
            pS.setInt(4, venta.getFuncionId());

            int filasAfectadas = pS.executeUpdate();
            if (filasAfectadas > 0) {
                ResultSet res = pS.getGeneratedKeys();
                if (res.next()) {
                    venta.setIdVenta(res.getInt(1));
                }
            } else {
                throw new PersistenciaException("No se pudo guardar la sala.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al guardar la sala: " + e.getMessage());
        }
        return venta;
    }
    }


