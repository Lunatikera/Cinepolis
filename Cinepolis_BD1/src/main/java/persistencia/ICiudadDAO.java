/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dominio.CiudadEntidad;
import java.util.List;

/**
 *
 * @author samoano
 */
public interface ICiudadDAO {
    
    public List<CiudadEntidad> listaCiudades() throws PersistenciaException;
     
}
