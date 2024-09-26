/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.ClienteEntidad;

/**
 *
 * @author carli
 */
public interface IClienteDAO extends ICrud<ClienteEntidad>{

    public ClienteEntidad buscarClientePorCorreo(String correo) throws PersistenciaException;

    public boolean existeClienteConCorreo(String correo) throws PersistenciaException;
}
