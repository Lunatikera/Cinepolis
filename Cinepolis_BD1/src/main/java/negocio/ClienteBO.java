/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.ClienteEntidad;
import dtos.ClienteDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.ClienteDAO;
import persistencia.PersistenciaException;
import utilerias.Encriptacion;

/**
 *
 * @author Temo
 */
public class ClienteBO implements IClienteBO {
    
    private ClienteDAO clienteDAO;
    
    public ClienteBO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    
    @Override
    public void agregaCliente(ClienteDTO registro) throws NegocioException {
        
        try {
            String contrasenaEncriptada = Encriptacion.encriptarPassword(registro.getContraseña());
            ClienteEntidad clienteEntidad = new ClienteEntidad(
                    registro.getNombre(),
                    registro.getApellidoPA(),
                    registro.getApellidoMA(),
                    registro.getCorreo(),
                    contrasenaEncriptada, // Usar la contraseña hasheada
                    registro.getFechaNacimiento(),
                    registro.getUbicacion(),
                    registro.getIdCiudad()
            );
            
            clienteEntidad.setEstaEliminado(false);
            
            if (clienteDAO.existeClienteConCorreo(registro.getCorreo())) {
                ClienteEntidad cuentaVieja = clienteDAO.buscarClientePorCorreo(registro.getCorreo());
                if (cuentaVieja.getEstaEliminado()) {
                    int temp = cuentaVieja.getId();
                    cuentaVieja = clienteEntidad;
                    cuentaVieja.setId(temp);
                    this.actualizarCliente(registro);
                } else {
                    throw new NegocioException("El correro ingresado ya tiene una cuenta existente");
                }
                
            }
            clienteDAO.guardar(clienteEntidad);
            
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudo agregar al cliente a la base de datos");
        }
        
    }
    
    @Override
    public void actualizarCliente(ClienteDTO registro) throws NegocioException {
        try {
            String contrasenaEncriptada = Encriptacion.encriptarPassword(registro.getContraseña());
            ClienteEntidad cliente = new ClienteEntidad(registro.getIdCliente(),
                    registro.getNombre(),
                    registro.getApellidoPA(),
                    registro.getApellidoMA(),
                    registro.getCorreo(),
                    contrasenaEncriptada,
                    registro.getFechaNacimiento(),
                    registro.getUbicacion(),
                    registro.getIdCiudad());
            
            clienteDAO.editar(cliente);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al actualizar el cliente: " + e.getMessage());
        }
    }
    
    @Override
    public void eliminarCliente(int idCliente) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public ClienteDTO buscarClientePorId(int idCliente) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<ClienteDTO> buscarClientes(int limite, int pagina) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
