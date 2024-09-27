/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package presentacion;

import negocio.IPeliculaBO;
import negocio.ISalaBO;
import negocio.PeliculaBO;
import negocio.SalaBO;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.ISalaDAO;
import persistencia.PeliculaDAO;
import persistencia.SalaDAO;



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
        //ISalaDAO clienteDAO = new SalaDAO(conexion);
        //ISalaBO clienteNegocio = new SalaBO(clienteDAO);
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        FrmPeliculas frmCliente = new FrmPeliculas(peliculaBO,1);
        frmCliente.setVisible(true);
    }

}
