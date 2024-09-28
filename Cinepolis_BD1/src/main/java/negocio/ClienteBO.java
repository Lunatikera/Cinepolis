/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dominio.ClienteEntidad;
import dtos.ClienteDTO;
import dtos.ClienteTablaFiltroDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.IClienteDAO;
import persistencia.PersistenciaException;
import utilerias.Encriptacion;

/**
 *
 * @author Temo
 */
public class ClienteBO implements IClienteBO {
    
    private IClienteDAO clienteDAO;
    
    public ClienteBO( IClienteDAO clienteDAO) {
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
            System.out.println(clienteEntidad.toString());
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
            
            ClienteEntidad cliente = new ClienteEntidad(registro.getIdCliente(),
                    registro.getNombre(),
                    registro.getApellidoPA(),
                    registro.getApellidoMA(),
                    registro.getCorreo(),
                    registro.getContraseña(),
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
        // Verifica que el ID no sea negativo
    if (this.esNumeroNegativo(idCliente)) {
        throw new NegocioException("No ingrese números negativos.");
    }
    try {
        // Busca el cliente en la base de datos
        ClienteEntidad clienteEntidad = this.clienteDAO.leerPorID(idCliente);
        
        // Verifica si el cliente fue encontrado
        if (clienteEntidad == null) {
            throw new NegocioException("Cliente no encontrado con ID: " + idCliente);
        }
        
        // Convierte el cliente a DTO y lo retorna
        return convertirClienteAClienteDTO(clienteEntidad);
    } catch (PersistenciaException ex) {
        throw new NegocioException("Error en la capa de persistencia: " + ex.getMessage(), ex);
    }
    }
    
    @Override
    public List<ClienteDTO> buscarClientes(int limite, int pagina) throws NegocioException {
       throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
   

    @Override
    public List<ClienteDTO> buscarPaginadosClienteTabla(int limite, int pagina) throws NegocioException {
         try{
            int offset = utilerias.Herramientas.RegresarOFFSETMySQL(limite, pagina);
            List<ClienteEntidad> clientes = clienteDAO.leerPaginado(limite, offset);
            List<ClienteDTO> clientesDTO = new ArrayList<>();
            
                for(ClienteEntidad cliente : clientes){
                    ClienteDTO clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getApellidoPA(),cliente.getApellidoMA(), cliente.getCorreo(), cliente.getContraseña(), cliente.getFechaNacimiento(),cliente.getUbicacion(),cliente.getIdCiudad());
                    clientesDTO.add(clienteDTO);
                }
                return clientesDTO;
        }catch(PersistenciaException e){
            Logger.getLogger(ClienteBO.class.getName()).log(Level.SEVERE, null, e);
            
            throw new NegocioException("Eroror en la capa de negocio al querre mostrar los clientes", e);
            
        }
       
    }
    
    public static ClienteDTO convertirClienteAClienteDTO(ClienteEntidad cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cliente.getId());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellidoPA(cliente.getApellidoPA());
        clienteDTO.setApellidoMA(cliente.getApellidoMA());
        clienteDTO.setCorreo(cliente.getCorreo());
        clienteDTO.setContraseña(cliente.getContraseña());
        clienteDTO.setFechaNacimiento(cliente.getFechaNacimiento());
        clienteDTO.setUbicacion(cliente.getUbicacion());
        clienteDTO.setIdCiudad(cliente.getIdCiudad());
        
        
        

        return clienteDTO;
    
    
   }
    private boolean esNumeroNegativo(int numero) {
        return numero < 0;
    }
        
    }
