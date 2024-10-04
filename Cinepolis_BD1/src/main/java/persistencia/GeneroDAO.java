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
public class GeneroDAO implements IGeneroDAO {

    private IConexionBD conexionBD;

    public GeneroDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    @Override
    public List<GeneroEntidad> listaGeneros() throws PersistenciaException {
        List<GeneroEntidad> listaGeneros = new ArrayList<>();

        String sentenciaSQL = "SELECT genero_id, nombreGenero FROM Generos";

        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sentenciaSQL); ResultSet resultSet = pS.executeQuery()) {

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

    @Override
    public List<GeneroEntidad> listaGeneroPorPelicula(int idPelicula, boolean incluirRelacionados) throws PersistenciaException {
        List<GeneroEntidad> listaGeneros = new ArrayList<>();

        String sentenciaSQL;

        // Modificar la consulta según el valor de incluirRelacionados
        if (incluirRelacionados) {
            sentenciaSQL = """
                        SELECT g.genero_id, g.nombreGenero FROM generos g
                        INNER JOIN pelicula_genero pg
                        ON g.genero_id = pg.genero_id
                        WHERE pg.pelicula_id = ?;
                        """;
        } else {
            sentenciaSQL = """
                        SELECT g.genero_id, g.nombreGenero FROM generos g
                        WHERE g.genero_id NOT IN (
                            SELECT pg.genero_id 
                            FROM pelicula_genero pg 
                            WHERE pg.pelicula_id = ?
                        );
                        """;
        }

        try (Connection conexion = this.conexionBD.crearConexion(); PreparedStatement pS = conexion.prepareStatement(sentenciaSQL)) {

            // Establecer el parámetro en la consulta preparada
            pS.setInt(1, idPelicula);

            try (ResultSet resultSet = pS.executeQuery()) {
                while (resultSet.next()) {
                    int idGenero = resultSet.getInt("genero_id");
                    String nombre = resultSet.getString("nombreGenero");

                    // Crear un objeto Genero y agregarlo a la lista
                    GeneroEntidad gene = new GeneroEntidad(idGenero, nombre);
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

    @Override
    public void actualizarGenerosPeliculaConTransaccion(int peliculaId, List<Integer> nuevosGeneros) throws PersistenciaException {
        Connection conn = null;
        PreparedStatement psEliminar = null;
        PreparedStatement psInsertar = null;

        // Consulta para eliminar géneros actuales asociados a la película
        String sqlEliminar = "DELETE FROM Pelicula_Genero WHERE pelicula_id = ?";
        // Consulta para insertar nuevos géneros
        String sqlInsertar = "INSERT INTO Pelicula_Genero (pelicula_id, genero_id) VALUES (?, ?)";

        try {
            // Crear conexión
            conn = conexionBD.crearConexion();
            // Deshabilitar autocommit para manejar la transacción manualmente
            conn.setAutoCommit(false);

            // Preparar la eliminación de géneros actuales
            psEliminar = conn.prepareStatement(sqlEliminar);
            psEliminar.setInt(1, peliculaId);

            // Ejecutar la consulta DELETE y verificar si se eliminaron filas
            int filasEliminadas = psEliminar.executeUpdate();
            if (filasEliminadas == 0) {
                System.out.println("No se encontraron géneros previos para eliminar.");
            } else {
                System.out.println(filasEliminadas + " géneros eliminados para la película.");
            }

            // Preparar la inserción de los nuevos géneros
            psInsertar = conn.prepareStatement(sqlInsertar);
            for (Integer generoId : nuevosGeneros) {
                psInsertar.setInt(1, peliculaId);
                psInsertar.setInt(2, generoId);
                psInsertar.addBatch(); // Añadir la inserción al batch
            }

            // Ejecutar el batch de inserciones
            psInsertar.executeBatch();

            // Confirmar la transacción
            conn.commit();
            System.out.println("Transacción completada. Nuevos géneros insertados.");

        } catch (SQLException e) {
            // Manejo del rollback en caso de error
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Transacción revertida debido a un error: " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            // Lanzar una excepción personalizada si ocurre un error
            throw new PersistenciaException("Error al actualizar los géneros de la película.", e);
        } finally {
            // Cerrar recursos en el bloque finally
            try {
                if (psEliminar != null) {
                    psEliminar.close();
                }
                if (psInsertar != null) {
                    psInsertar.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
