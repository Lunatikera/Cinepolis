/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.ClienteDTO;
import dtos.InicioSesionDTO;

/**
 *
 * @author Temo
 */
public interface IInicioSesionBO {
    public ClienteDTO inicioSesion(InicioSesionDTO credenciales) throws NegocioException;
}
