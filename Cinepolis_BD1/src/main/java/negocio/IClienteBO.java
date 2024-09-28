/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.ClienteDTO;
import dtos.ClienteTablaFiltroDTO;
import java.util.List;

/**
 *
 * @author Temo
 */
public interface IClienteBO {

    public void agregaCliente(ClienteDTO registro) throws NegocioException;

    public void actualizarCliente(ClienteDTO registro) throws NegocioException;

    public void eliminarCliente(int idCliente) throws NegocioException;

    public ClienteDTO buscarClientePorId(int idCliente) throws NegocioException;

    public List<ClienteDTO> buscarClientes(int limite, int pagina) throws NegocioException;
    
    public List<ClienteDTO> buscarPaginadosClienteTabla(int limite, int pagina) throws NegocioException;;

}
