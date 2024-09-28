/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package presentacion;

import dominio.SucursalEntidad;
import dtos.SucursalDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.CiudadBO;
import negocio.ICiudadBO;
import negocio.IPeliculaBO;
import negocio.ISalaBO;
import negocio.ISucursalBO;
import negocio.NegocioException;
import negocio.PeliculaBO;
import negocio.SalaBO;
import negocio.SucursalBO;
import persistencia.CiudadDAO;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.ISalaDAO;
import persistencia.PeliculaDAO;
import persistencia.SalaDAO;
import persistencia.SucursalDAO;



/**
 *
 * @author rramirez
 */
public class RUN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IConexionBD conexion = new ConexionBD();
        ISalaDAO clienteDAO = new SalaDAO(conexion);
        ISalaBO clienteNegocio = new SalaBO(clienteDAO);
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        SucursalDAO suda = new SucursalDAO(conexion);
        ISucursalBO a = new SucursalBO(suda);
        CiudadDAO cd = new CiudadDAO(conexion);
        ICiudadBO cdd = new CiudadBO(cd);
        SucursalDTO dsd = new SucursalDTO(1, "Cinépolis Sendero Obregón", "Plaza Sendero, C. 300 85180, Franja Comercial 300, 85065, Cdad. Obregón, Son.", 2);
       
        
        //FrmPeliculas frmCliente = new FrmPeliculas(peliculaBO);
        //frmCliente.setVisible(true);
        FrmAdminPeliculas peli = new FrmAdminPeliculas(a, cdd, dsd, peliculaBO);
        peli.setVisible(true);
    }

}
