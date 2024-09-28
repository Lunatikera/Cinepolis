/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.ClienteDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.NegocioException;
import negocio.IClienteBO;

/**
 *
 * @author Rios 233537
 */
public class frmEditarClientes extends javax.swing.JFrame {
    FrmCrudClientes pantallaPrincipal;
    ClienteDTO clienteDTO;
    IClienteBO clienteNegocio;

   
    
    public frmEditarClientes(FrmCrudClientes pantallaPrincipal, IClienteBO clienteNegocio, ClienteDTO clienteDTO) {
        initComponents();
        this.pantallaPrincipal=pantallaPrincipal;
        this.clienteNegocio= clienteNegocio;
        this.clienteDTO = clienteDTO;
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
        ciudadtxt = new javax.swing.JTextField();
        apellidoPATxt = new javax.swing.JTextField();
        radbtnActivo = new javax.swing.JRadioButton();
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
        contraseñatxt1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        naciminetotxt1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ubicaciontxt1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

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

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iDlabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        iDlabel.setText("ID Cliente:");
        jPanel2.add(iDlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 166, -1));
        jPanel2.add(NombresTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 150, 40));

        ciudadtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ciudadtxtActionPerformed(evt);
            }
        });
        jPanel2.add(ciudadtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 150, 39));
        jPanel2.add(apellidoPATxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 150, 40));

        radbtnActivo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        radbtnActivo.setText("¿Eliminar?");
        radbtnActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radbtnActivoActionPerformed(evt);
            }
        });
        jPanel2.add(radbtnActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 107, -1));

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

        jLabel1.setText("ciudad: *");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 110, -1));

        jLabel2.setText("Nombres: *");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, -1));

        jLabel3.setText("Apellido Paterno: *");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 110, -1));

        jLabel4.setText("* Significan campos obligatorios");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, -1, -1));
        jPanel2.add(apellidoMAtxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 150, 39));

        jLabel5.setText("Apellido Materno: *");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 110, -1));
        jPanel2.add(correoAtxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 150, 39));

        jLabel6.setText("Correo: *");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 110, -1));
        jPanel2.add(contraseñatxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 150, 39));

        jLabel7.setText("Contraseña: *");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 110, -1));

        naciminetotxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                naciminetotxt1ActionPerformed(evt);
            }
        });
        jPanel2.add(naciminetotxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 150, 39));

        jLabel8.setText("Fecha Nacimineto: *");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 110, -1));

        ubicaciontxt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubicaciontxt1ActionPerformed(evt);
            }
        });
        jPanel2.add(ubicaciontxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 150, 39));

        jLabel9.setText("ubicacion: *");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 110, -1));

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

    private void metodosInciales() {

        iDlabel.setText("ID Cliente: " + clienteDTO.getIdCliente());
        NombresTxt.setText(clienteDTO.getNombre());
        apellidoPATxt.setText(clienteDTO.getApellidoPA());
        ciudadtxt.setText(clienteDTO.getApellidoMA());
        correoAtxt1.setText(clienteDTO.getCorreo());
        contraseñatxt1.setText(clienteDTO.getContraseña());
        naciminetotxt1.setText(clienteDTO.getFechaNacimiento().format(DateTimeFormatter.ISO_DATE));
        ubicaciontxt1.setText(clienteDTO.getUbicacion());
        ciudadtxt.setText(String.valueOf(clienteDTO.getIdCiudad()));
        
        
        
       
    }
    private void radbtnActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radbtnActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radbtnActivoActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        String nombres = NombresTxt.getText();
        String apellidoPa = apellidoPATxt.getText();
        String apellidoMa = ciudadtxt.getText();
        boolean estatus = radbtnActivo.isSelected();

        ClienteDTO edicionCliente = new ClienteDTO(clienteDTO.getIdCliente(), nombres, apellidoPa, apellidoMa, nombres, LocalDate.MIN, apellidoMa, WIDTH);
         int confirmacion = JOptionPane.showOptionDialog(this,
                    "¿Está seguro de que deseas editar al Cliente "+ clienteDTO.getIdCliente(),
                    "Confirmación de edicion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Cancelar", "Confirmar"},
                    "Confirmar");

            // Si el usuario selecciona "Cancelar", no se hace nada
            if (confirmacion == JOptionPane.YES_OPTION) {
                return;
            }
        try {
            clienteNegocio.actualizarCliente(edicionCliente);
            this.pantallaPrincipal.cargarClientesEnTabla();
            dispose();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void ciudadtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ciudadtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ciudadtxtActionPerformed

    private void naciminetotxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_naciminetotxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_naciminetotxt1ActionPerformed

    private void ubicaciontxt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubicaciontxt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ubicaciontxt1ActionPerformed

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
    private javax.swing.JTextField NombresTxt;
    private javax.swing.JTextField apellidoMAtxt1;
    private javax.swing.JTextField apellidoPATxt;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JTextField ciudadtxt;
    private javax.swing.JTextField contraseñatxt1;
    private javax.swing.JTextField correoAtxt1;
    private javax.swing.JLabel iDlabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField naciminetotxt1;
    private javax.swing.JRadioButton radbtnActivo;
    private javax.swing.JTextField ubicaciontxt1;
    // End of variables declaration//GEN-END:variables
}
