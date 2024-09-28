/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.CiudadDTO;
import dtos.ClienteDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.ICiudadBO;
import negocio.NegocioException;
import negocio.IClienteBO;

/**
 *
 * @author samoano y temo
 */
public class frmEditarClientes extends javax.swing.JFrame{
    ClienteDTO clienteDTO;
    IClienteBO clienteNegocio;
    int id;
    ICiudadBO ciudadBO;
    List<CiudadDTO> listaCiudades;
   
    
    public frmEditarClientes(int id,IClienteBO clienteNegocio,ICiudadBO ciudadBO) throws NegocioException{
        initComponents();
        this.clienteNegocio= clienteNegocio;
        this.ciudadBO = ciudadBO;
        this.id = id;
        this.setTitle("Editar Cliente");
        metodosInciales();
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
        jPanel2 = new javax.swing.JPanel();
        iDlabel = new javax.swing.JLabel();
        NombresTxt = new javax.swing.JTextField();
        apellidoPATxt = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnGuardar1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apellidoMAtxt1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        correoAtxt1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        FechaNacimientoPicker = new com.github.lgooddatepicker.components.DatePicker();
        ciudadesCB = new javax.swing.JComboBox<>();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(36, 44, 99));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iDlabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        iDlabel.setForeground(new java.awt.Color(255, 255, 255));
        iDlabel.setText("Editar Cliente");
        jPanel2.add(iDlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 280, -1));
        jPanel2.add(NombresTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 150, 40));
        jPanel2.add(apellidoPATxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 150, 40));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 157, 33));

        btnGuardar1.setText("Guardar");
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, 157, 33));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ciudad: *");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, 110, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombres: *");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido Paterno: *");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 110, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("* Significan campos obligatorios");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, -1, -1));
        jPanel2.add(apellidoMAtxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 150, 39));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Apellido Materno: *");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 110, -1));
        jPanel2.add(correoAtxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 150, 39));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Correo: *");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 110, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Fecha Nacimineto: *");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 110, -1));
        jPanel2.add(FechaNacimientoPicker, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 200, 30));

        jPanel2.add(ciudadesCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 190, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void metodosInciales() throws NegocioException {
        metodoMostrarDatos();
        llenarComboBoxCiudades();
    }
    
    public void metodoMostrarDatos() throws NegocioException{
        try{
        ClienteDTO c = this.clienteNegocio.buscarClientePorId(this.id);
        NombresTxt.setText(c.getNombre());
        apellidoMAtxt1.setText(c.getApellidoMA());
        apellidoPATxt.setText(c.getApellidoPA());
        correoAtxt1.setText(c.getCorreo());
        FechaNacimientoPicker.setDate(c.getFechaNacimiento());
        }catch(NegocioException ex){
            System.out.println(ex.getMessage());
        } 
        
    };
    
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
    
    
    
    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        String nombre = NombresTxt.getText();
        String apPaterno = apellidoPATxt.getText();
        String apMaterno = apellidoMAtxt1.getText();
        String correo = correoAtxt1.getText();
        LocalDate fecha = FechaNacimientoPicker.getDate();
        try{
        CiudadDTO ciudades = (CiudadDTO) ciudadesCB.getSelectedItem();
        ClienteDTO clienteE = this.clienteNegocio.buscarClientePorId(id);
        clienteE.setNombre(nombre);
        clienteE.setApellidoMA(apMaterno);
        clienteE.setApellidoPA(apPaterno);
        clienteE.setCorreo(correo);
        clienteE.setFechaNacimiento(fecha);
        clienteE.setIdCiudad(ciudades.getId());
        this.clienteNegocio.actualizarCliente(clienteE);
        dispose();
        }catch(Exception m){
            JOptionPane.showMessageDialog(this, "Error inesperado: " + m.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmEditarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEditarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEditarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEditarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker FechaNacimientoPicker;
    private javax.swing.JTextField NombresTxt;
    private javax.swing.JTextField apellidoMAtxt1;
    private javax.swing.JTextField apellidoPATxt;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JComboBox<CiudadDTO> ciudadesCB;
    private javax.swing.JTextField correoAtxt1;
    private javax.swing.JLabel iDlabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
