/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.ClienteDTO;
import dtos.InicioSesionDTO;
import dtos.SucursalDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.CiudadBO;
import negocio.ICiudadBO;
import negocio.IClienteBO;
import negocio.IInicioSesionBO;
import negocio.IPeliculaBO;
import negocio.ISucursalBO;
import negocio.NegocioException;
import negocio.PeliculaBO;
import negocio.SucursalBO;
import persistencia.CiudadDAO;
import persistencia.ConexionBD;
import persistencia.ICiudadDAO;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.SucursalDAO;
import static utilerias.Geocalizacion.obtenerCoordenadas;

/**
 *
 * @author carli
 */
public class FrmInicioSesion extends javax.swing.JFrame {

    private IInicioSesionBO inicioSesionBO;
    private IClienteBO clientoBO;
    private ISucursalBO sucursalBO;
    private boolean modoAdmin = false;

    public FrmInicioSesion(IInicioSesionBO inicioSesionBO, IClienteBO clientoBO, ISucursalBO sucursalBO) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.inicioSesionBO = inicioSesionBO;
        this.clientoBO = clientoBO;
        this.sucursalBO = sucursalBO;
       
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelEsquinasRedondas1 = new utilerias.PanelEsquinasRedondas();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        ingresarBtn = new utilerias.MenuButton();
        registrarseBtn = new utilerias.MenuButton();
        modoAdminBtn = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(36, 44, 99));

        panelEsquinasRedondas1.setBackgroundColor(new java.awt.Color(33, 36, 59));
        panelEsquinasRedondas1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/littlelogoGrande 1.png"))); // NOI18N
        panelEsquinasRedondas1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Correo");
        panelEsquinasRedondas1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 125, -1, 32));
        panelEsquinasRedondas1.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 163, 290, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Inicio Sesion");
        panelEsquinasRedondas1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 59, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contraseña");
        panelEsquinasRedondas1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 210, -1, 32));
        panelEsquinasRedondas1.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 254, 290, 30));

        ingresarBtn.setBorderPainted(false);
        ingresarBtn.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ingresar.png"))); // NOI18N
        ingresarBtn.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ingresar.png"))); // NOI18N
        ingresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBtnActionPerformed(evt);
            }
        });
        panelEsquinasRedondas1.add(ingresarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, -1, 58));

        registrarseBtn.setBorderPainted(false);
        registrarseBtn.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Registro.png"))); // NOI18N
        registrarseBtn.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Registro.png"))); // NOI18N
        registrarseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarseBtnActionPerformed(evt);
            }
        });
        panelEsquinasRedondas1.add(registrarseBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, -1, 43));

        modoAdminBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        modoAdminBtn.setForeground(new java.awt.Color(255, 255, 255));
        modoAdminBtn.setText("Modo Administrador");
        modoAdminBtn.setContentAreaFilled(false);
        modoAdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modoAdminBtnActionPerformed(evt);
            }
        });
        panelEsquinasRedondas1.add(modoAdminBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(panelEsquinasRedondas1, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(panelEsquinasRedondas1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBtnActionPerformed
        String correo = txtCorreo.getText().trim();
        String contraseña = new String(txtContraseña.getPassword());

        if (correo.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese su correo electrónico y contraseña.", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!modoAdmin) {
            try {
                InicioSesionDTO credenciales = new InicioSesionDTO(correo, contraseña);
                ClienteDTO cliente = inicioSesionBO.inicioSesion(credenciales);
                System.out.println(cliente.getUbicacion());
                if (cliente != null) {
                    // Inicio de sesión exitoso
                    JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso. ¡Bienvenido, " + cliente.getNombre() + "!");

//                     Obtener la ubicación del cliente
                    String ubicacion = obtenerCoordenadas();
                    cliente.setUbicacion(ubicacion);

                    // Realizar acciones adicionales necesarias después del inicio de sesión
                    this.procesarInicioSesionExitoso(cliente);

                } else {
                    // Credenciales incorrectas
                    JOptionPane.showMessageDialog(this, "Inicio de sesión fallido. Por favor, verifica tus credenciales.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error durante el inicio de sesión. Por favor verifique sus credenciales,.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado. Por favor, intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(FrmInicioSesion.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (modoAdmin) {
            if (contraseña.equals("admin") && correo.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Inicio de Administrador exitoso. ¡Bienvenido!");
                IConexionBD conexion = new ConexionBD();
                ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
                ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
                
                 FrmAdminClientes clientes = new FrmAdminClientes(ciudadBO, clientoBO);
                 clientes.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Inicio de sesión fallido. Por favor, verifica tus credenciales de Administrador.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_ingresarBtnActionPerformed
    private void procesarInicioSesionExitoso(ClienteDTO cliente) {
        try {
            clientoBO.actualizarCliente(cliente);
            SucursalDTO sucursalFavorable = sucursalBO.buscarSucursalMasCercana(cliente.getIdCliente());

            IConexionBD conexion = new ConexionBD();

            IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
            ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
            ISucursalDAO sucursalDAO = new SucursalDAO(conexion);

            IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
            ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
            ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);

            FrmCatalogoSucursal catalogo = new FrmCatalogoSucursal(peliculaBO, ciudadBO, sucursalBO, sucursalFavorable, cliente);
            catalogo.setVisible(true);
            this.dispose();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmInicioSesion.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    private void modoAdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modoAdminBtnActionPerformed
        this.modoAdmin = !modoAdmin;
    }//GEN-LAST:event_modoAdminBtnActionPerformed

    private void registrarseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarseBtnActionPerformed
        IConexionBD conexion = new ConexionBD();
        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);

        FrmRegistro registrarse = new FrmRegistro(clientoBO, ciudadBO);

        registrarse.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_registrarseBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilerias.MenuButton ingresarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton modoAdminBtn;
    private utilerias.PanelEsquinasRedondas panelEsquinasRedondas1;
    private utilerias.MenuButton registrarseBtn;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
