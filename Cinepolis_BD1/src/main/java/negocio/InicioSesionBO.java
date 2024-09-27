/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.ClienteEntidad;
import dtos.ClienteDTO;
import dtos.InicioSesionDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.AuthenticationException;
import persistencia.IClienteDAO;
import persistencia.PersistenciaException;
import utilerias.Encriptacion;

/**
 *
 * @author Temo
 */
public class InicioSesionBO implements IInicioSesionBO {

    private IClienteDAO clienteDAO;

    public InicioSesionBO(IClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public ClienteDTO inicioSesion(InicioSesionDTO credenciales) throws NegocioException {
        try {
            // Retrieve the user by email from the DAO
            ClienteEntidad cliente = clienteDAO.buscarClientePorCorreo(credenciales.getCorreo());
            if (cliente == null) {
                throw new NegocioException("Correo no encontrado.");
            }
            // Check if the client exists and if the password matches
            if (cliente != null && !cliente.getEstaEliminado() && Encriptacion.verificarPasswordConHash(credenciales.getContrasena(), cliente.getContraseña())) {
                // Successful login, return user data without the password
                return new ClienteDTO(
                        cliente.getId(),
                        cliente.getNombre(),
                        cliente.getApellidoPA(),
                        cliente.getApellidoMA(),
                        cliente.getCorreo(),
                        cliente.getFechaNacimiento(),
                        cliente.getUbicacion(),
                        cliente.getIdCiudad()
                );
            } else {
                // Throw authentication exception if credentials are invalid
                throw new NegocioException("Correo o Contraseña Incorretos");
            }
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al iniciar Sesion: " + ex.getMessage());  // Rethrow the exception as AuthenticationException
        }
    }
}
