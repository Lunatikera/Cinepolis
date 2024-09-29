/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;


import dominio.GeneroEntidad;
import dominio.PeliculaEntidad;
import enumeradores.Clasificaciones;
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
public class GeneroDAO implements IGeneroDAO{

    private IConexionBD conexionBD;

    public GeneroDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
    
    
    @Override
    public List<GeneroEntidad> listaGeneros() throws PersistenciaException {
        List<GeneroEntidad> listaGeneros = new ArrayList<>();
        
        String sentenciaSQL = "SELECT genero_id, nombreGenero FROM Generos";
        
        try ( Connection conexion = this.conexionBD.crearConexion();  PreparedStatement pS = conexion.prepareStatement(sentenciaSQL);  ResultSet resultSet = pS.executeQuery()) {

            // Iterar sobre los resultados y mapearlos a objetos Pais
            while (resultSet.next()) {
                int idGenero = resultSet.getInt("genero_id");
                String nombre = resultSet.getString("nombreGenero");

                // Crear un objeto genero y agregarlo a la lista
                GeneroEntidad genero = new GeneroEntidad(idGenero, nombre);
                listaGeneros.add(genero);
            }

        } catch (SQLSyntaxErrorException | SQLDataException e) {
            // Manejar errores específicos de SQL, si es necesario
            throw new PersistenciaException("Error de sintaxis SQL o problema con los datos", e);
        } catch (SQLException e) {
            // Manejar errores generales de SQL
            throw new PersistenciaException("Error al obtener la lista de generos", e);
        }
            return listaGeneros;
    }
    
    public List<GeneroEntidad> listaGeneroPorPelicula(int idPelicula)throws PersistenciaException {
        List<GeneroEntidad> listaGeneros = new ArrayList<>();
        
        String sentenciaSQL ="""
                             SELECT genero_id,nombreGenero FROM generos g
                             Inner join pelicula_genero pg
                             ON g.genero_id = pg.genero_id
                             where pg.pelicula_id = ?;
                             """;
        
        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sentenciaSQL)) {
            // Establecer los parámetros en la consulta preparada
            pS.setInt(1, idPelicula);
            try(ResultSet resultSet = pS.executeQuery()) {
                while (resultSet.next()) {
                    int idGenero = resultSet.getInt("genero_id");
                    String nombre = resultSet.getString("nombreGenero");

                    // Crear un objeto Genero y agregarlo a la lista
                    GeneroEntidad gene = new GeneroEntidad(idGenero,nombre);
                    listaGeneros.add(gene);
                }
            }
            
        } catch (SQLSyntaxErrorException | SQLDataException e) {
            // Manejar errores específicos de SQL, si es necesario
            throw new PersistenciaException("Error de sintaxis SQL o problema con los datos", e);
        } catch (SQLException e) {
            // Manejar errores generales de SQL
            throw new PersistenciaException("Error al obtener la lista de generos", e);
        }
        return listaGeneros;
        
    }
    
}
