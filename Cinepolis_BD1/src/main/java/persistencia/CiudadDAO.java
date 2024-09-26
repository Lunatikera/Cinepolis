/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dominio.CiudadEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samoano
 */
public class CiudadDAO implements ICiudadDAO {
     private IConexionBD conexionBD;

    public CiudadDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<CiudadEntidad> listaCiudades() throws PersistenciaException {
      List<CiudadEntidad> listaCiudades = new ArrayList<>();

        // Consulta SQL para obtener los países
        String sentenciaSQL = "SELECT ciudad_id, nombre FROM Ciudades";

        try ( Connection conexion = this.conexionBD.crearConexion();  PreparedStatement pS = conexion.prepareStatement(sentenciaSQL);  ResultSet resultSet = pS.executeQuery()) {

            // Iterar sobre los resultados y mapearlos a objetos Pais
            while (resultSet.next()) {
                int idCiudad = resultSet.getInt("ciudad_id");
                String nombre = resultSet.getString("nombre");

                // Crear un objeto Pais y agregarlo a la lista
                CiudadEntidad ciudad = new CiudadEntidad(idCiudad, nombre);
                listaCiudades.add(ciudad);
            }

        } catch (SQLSyntaxErrorException | SQLDataException e) {
            // Manejar errores específicos de SQL, si es necesario
            throw new PersistenciaException("Error de sintaxis SQL o problema con los datos", e);
        } catch (SQLException e) {
            // Manejar errores generales de SQL
            throw new PersistenciaException("Error al obtener la lista de países", e);
        }

        return listaCiudades;
    }

}
