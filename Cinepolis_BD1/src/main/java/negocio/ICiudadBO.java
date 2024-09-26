/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.CiudadDTO;
import java.util.List;

/**
 *
 * @author Temo
 */
public interface ICiudadBO {

    public List<CiudadDTO> listaCiudades() throws NegocioException;

}
