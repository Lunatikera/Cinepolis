/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.CiudadDTO;
import dtos.ClienteDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.ClienteBO;
import negocio.ICiudadBO;
import negocio.IClienteBO;
import negocio.IInicioSesionBO;
import negocio.ISucursalBO;
import negocio.InicioSesionBO;
import negocio.NegocioException;
import negocio.SucursalBO;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.ISucursalDAO;
import persistencia.SucursalDAO;
import utilerias.Geocalizacion;

/**
 *
 * @author carli
 */
public class FrmRegistro extends javax.swing.JFrame {

    IClienteBO clienteBO;
    ICiudadBO ciudadBO;

    List<CiudadDTO> listaCiudades;

    /**
     * Creates new form FrmRegistro
     */
    public FrmRegistro(IClienteBO clienteBO, ICiudadBO ciudadBO) {
        initComponents();
        this.clienteBO = clienteBO;
        this.ciudadBO = ciudadBO;
        llenarComboBoxCiudades();
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
        nombreTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        apellidoPaternoTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        apellidoMaternoTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        correoTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnCancelar = new utilerias.MenuButton();
        btnRegistrarse = new utilerias.MenuButton();
        FechaNacimientoPicker = new com.github.lgooddatepicker.components.DatePicker();
        jLabel10 = new javax.swing.JLabel();
        ciudadesCB = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        confirmarContraseñaTxt = new javax.swing.JPasswordField();
        contraseñaTxt = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(36, 44, 99));

        panelEsquinasRedondas1.setBackgroundColor(new java.awt.Color(33, 36, 59));

        jLabel2.setText("Nombre");
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Apellido Paterno");
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Fecha Nacimiento");
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Correo Electronico");
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Contraseña");
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Ciudad");
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/littlelogoGrande 2.png"))); // NOI18N

        jLabel8.setText("Registrarse");
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Line 7.png"))); // NOI18N

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar.png"))); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRegistrarse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Registrarse.png"))); // NOI18N
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });

        jLabel10.setText("Apellido Materno");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Confirmar Contraseña");

        javax.swing.GroupLayout panelEsquinasRedondas1Layout = new javax.swing.GroupLayout(panelEsquinasRedondas1);
        panelEsquinasRedondas1.setLayout(panelEsquinasRedondas1Layout);
        panelEsquinasRedondas1Layout.setHorizontalGroup(
            panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsquinasRedondas1Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel5)
                    .addComponent(correoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ciudadesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(149, 149, 149))
            .addGroup(panelEsquinasRedondas1Layout.createSequentialGroup()
                .addGroup(panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEsquinasRedondas1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(apellidoPaternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(apellidoMaternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(FechaNacimientoPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(161, 161, 161)
                        .addGroup(panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(confirmarContraseñaTxt)
                            .addComponent(contraseñaTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                            .addGroup(panelEsquinasRedondas1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelEsquinasRedondas1Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(192, 192, 192))
        );
        panelEsquinasRedondas1Layout.setVerticalGroup(
            panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsquinasRedondas1Layout.createSequentialGroup()
                .addGroup(panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEsquinasRedondas1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEsquinasRedondas1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelEsquinasRedondas1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(apellidoPaternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(apellidoMaternoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(FechaNacimientoPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEsquinasRedondas1Layout.createSequentialGroup()
                        .addGroup(panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelEsquinasRedondas1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(ciudadesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(correoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contraseñaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(confirmarContraseñaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(panelEsquinasRedondas1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(panelEsquinasRedondas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(94, 94, 94))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(panelEsquinasRedondas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        if (nombreTxt.getText().trim().isEmpty()
                || apellidoPaternoTxt.getText().trim().isEmpty()
                || apellidoMaternoTxt.getText().trim().isEmpty()
                || correoTxt.getText().trim().isEmpty()
                || contraseñaTxt.getPassword().length == 0
                || confirmarContraseñaTxt.getPassword().length == 0
                || FechaNacimientoPicker.getDate() == null
                || ciudadesCB.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String contraseña = new String(contraseñaTxt.getPassword()).trim();
        String confirmarContraseña = new String(confirmarContraseñaTxt.getPassword()).trim();

        if (!contraseña.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error de contraseña", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            
        CiudadDTO ciudad = (CiudadDTO) ciudadesCB.getSelectedItem();
        // Create a RegistroUsuarioDTO instance
        ClienteDTO registro = new ClienteDTO();
        registro.setNombre(nombreTxt.getText().trim());
        registro.setApellidoPA(apellidoPaternoTxt.getText().trim());
        registro.setApellidoMA(apellidoMaternoTxt.getText().trim());
        registro.setCorreo(correoTxt.getText().trim());
        registro.setContraseña(contraseña);
        registro.setFechaNacimiento(FechaNacimientoPicker.getDate());
        registro.setIdCiudad( ciudad.getId());
        registro.setUbicacion(Geocalizacion.obtenerCoordenadas());
        System.out.println(registro.toString());
        // Assuming idCiudad is part of the selected city or another input field

        // Proceed to register the user using the BO layer
        try {
            clienteBO.agregaCliente(registro);
            JOptionPane.showMessageDialog(this, "Registro exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error en el registro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        }catch(Exception m){
            JOptionPane.showMessageDialog(this, "Error inesperado: " + m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
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
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    private void llenarComboBoxCiudades() {
        try {
            listaCiudades = ciudadBO.listaCiudades();
            for (CiudadDTO ciudad : listaCiudades) {
                ciudadesCB.addItem(ciudad);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(FrmRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker FechaNacimientoPicker;
    private javax.swing.JTextField apellidoMaternoTxt;
    private javax.swing.JTextField apellidoPaternoTxt;
    private utilerias.MenuButton btnCancelar;
    private utilerias.MenuButton btnRegistrarse;
    private javax.swing.JComboBox<CiudadDTO> ciudadesCB;
    private javax.swing.JPasswordField confirmarContraseñaTxt;
    private javax.swing.JPasswordField contraseñaTxt;
    private javax.swing.JTextField correoTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreTxt;
    private utilerias.PanelEsquinasRedondas panelEsquinasRedondas1;
    // End of variables declaration//GEN-END:variables
}
