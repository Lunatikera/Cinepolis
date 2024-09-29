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
import negocio.ClienteBO;
import negocio.ICiudadBO;
import negocio.IClienteBO;
import negocio.IPeliculaBO;
import negocio.IReportesSucursalesBO;
import negocio.ISalaBO;
import negocio.ISucursalBO;
import negocio.NegocioException;
import negocio.PeliculaBO;
import negocio.ReportesSucursalesBO;
import negocio.SalaBO;
import negocio.SucursalBO;
import persistencia.CiudadDAO;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.IReportesSucursalesDAO;
import persistencia.ISalaDAO;
import persistencia.PeliculaDAO;
import persistencia.ReportesSucursalesDAO;
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
        
        ClienteDAO cl = new ClienteDAO(conexion);
        IClienteBO clienteBO = new ClienteBO(cl);
        
        SucursalDTO dsd = new SucursalDTO(1, "Cinépolis Sendero Obregón", "Plaza Sendero, C. 300 85180, Franja Comercial 300, 85065, Cdad. Obregón, Son.", 2);
        IReportesSucursalesDAO repdao = new ReportesSucursalesDAO(conexion);
        IReportesSucursalesBO sdsds = new ReportesSucursalesBO(repdao); 
       
        
        //FrmPeliculas frmCliente = new FrmPeliculas(peliculaBO);
        //frmCliente.setVisible(true);
        //FrmAdminPeliculas peli = new FrmAdminPeliculas(a, cdd, dsd, peliculaBO);
        //peli.setVisible(true);
        //FrmAdminClientes admin = new FrmAdminClientes(cdd, clienteBO);
        //admin.setVisible(true);
        FrmReporteSucursales admi = new FrmReporteSucursales(a, cdd, dsd,sdsds);
        admi.setVisible(true);
    }

}
