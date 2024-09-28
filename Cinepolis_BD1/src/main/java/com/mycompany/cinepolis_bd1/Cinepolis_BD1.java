/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cinepolis_bd1;

import negocio.ClienteBO;
import negocio.IClienteBO;
import negocio.IInicioSesionBO;
import negocio.ISucursalBO;
import negocio.InicioSesionBO;
import negocio.SucursalBO;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.ISucursalDAO;
import persistencia.SucursalDAO;

import presentacion.FrmAdminSucursal;

import presentacion.FrmInicioSesion;
import presentacion.FrmReporteSucursales;

/**
 *
 * @author carli
 */
public class Cinepolis_BD1 {

    public static void main(String[] args) {
       
 // Assuming these have been injected into your class through the constructor or set methods
        IConexionBD conexion = new ConexionBD(); // Consider injecting this
        IClienteDAO clienteDAO = new ClienteDAO(conexion);
        ISucursalDAO sucursalDAO = new SucursalDAO(conexion);

        // Create Business Objects
        ISucursalBO sucursalBO = new SucursalBO((SucursalDAO) sucursalDAO);
        IInicioSesionBO inicioSesionBO = new InicioSesionBO((ClienteDAO) clienteDAO);
        IClienteBO clienteBO = new ClienteBO((ClienteDAO) clienteDAO);

        // Initialize and display the login form

        FrmInicioSesion iniciarSesion = new FrmInicioSesion(inicioSesionBO, clienteBO, sucursalBO);
        iniciarSesion.setVisible(true);
        


        
       

    }
}