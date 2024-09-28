/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;


import dtos.PeliculaDTO;
import dtos.SalaDTO;
import dtos.SucursalDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import negocio.IPeliculaBO;
import negocio.ISalaBO;
import negocio.NegocioException;

/**
 *
 * @author rramirez
 */
public class FrmEditarPelicula extends javax.swing.JFrame {

    IPeliculaBO peliculaBO;
    int id;
    PeliculaDTO pelicula;
    

    /**
     * Creates new form FrmAgregarCliente
     */
    public FrmEditarPelicula(IPeliculaBO peliculaBO, PeliculaDTO pelicula){
        initComponents();
        this.peliculaBO = peliculaBO;
        this.id = id;
        metodoMostrarDatos();
    }

    public void metodoMostrarDatos(){
            try{
                PeliculaDTO pelicula = this.peliculaBO.buscarPeliculaPorId(id);
                txtTitulo.setText(pelicula.getCartel());
                txtDuracion.setText(String.valueOf(pelicula.getDuracion()));
                txtTrailer.setText(pelicula.getLink_trailer());
                tPaneSinopsis.setText(pelicula.getSinopsis());

            }catch(NegocioException ex){
            System.out.println(ex.getMessage());
            }
            
            
            
    };
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        LblNombre = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        LblPaterno = new javax.swing.JLabel();
        LblMaterno = new javax.swing.JLabel();
        BtnAceptar = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        LblPaterno1 = new javax.swing.JLabel();
        LblPaterno2 = new javax.swing.JLabel();
        LblPaterno3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblImagenCartel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tPaneSinopsis = new javax.swing.JTextPane();
        txtTitulo = new javax.swing.JTextField();
        cbPaises = new javax.swing.JComboBox<>();
        txtTrailer = new javax.swing.JTextField();
        LblPaterno4 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(36, 44, 99));
        jPanel1.setToolTipText("");

        LblNombre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblNombre.setForeground(new java.awt.Color(255, 255, 255));
        LblNombre.setText("Titulo");

        txtDuracion.setBackground(new java.awt.Color(33, 36, 59));

        LblPaterno.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblPaterno.setForeground(new java.awt.Color(255, 255, 255));
        LblPaterno.setText("Pais");

        LblMaterno.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblMaterno.setForeground(new java.awt.Color(255, 255, 255));
        LblMaterno.setText("Link del trailer");

        BtnAceptar.setBackground(new java.awt.Color(204, 204, 204));
        BtnAceptar.setText("Aceptar");
        BtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAceptarActionPerformed(evt);
            }
        });

        BtnCancelar.setBackground(new java.awt.Color(204, 204, 204));
        BtnCancelar.setText("Cancelar");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        LblPaterno1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblPaterno1.setForeground(new java.awt.Color(255, 255, 255));
        LblPaterno1.setText("Duracion");

        LblPaterno2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        LblPaterno2.setForeground(new java.awt.Color(255, 255, 255));
        LblPaterno2.setText("Editar Pelicula");

        LblPaterno3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblPaterno3.setForeground(new java.awt.Color(255, 255, 255));
        LblPaterno3.setText("Sinopsis");

        jPanel2.setBackground(new java.awt.Color(33, 36, 59));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        lblImagenCartel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/3212c44f6aca4bc69d467d4614e6f3dc 1.png"))); // NOI18N

        tPaneSinopsis.setBackground(new java.awt.Color(33, 36, 59));
        jScrollPane2.setViewportView(tPaneSinopsis);

        txtTitulo.setBackground(new java.awt.Color(33, 36, 59));
        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });

        cbPaises.setBackground(new java.awt.Color(33, 36, 59));
        cbPaises.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbPaises.setForeground(new java.awt.Color(255, 255, 255));

        txtTrailer.setBackground(new java.awt.Color(33, 36, 59));

        LblPaterno4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblPaterno4.setForeground(new java.awt.Color(255, 255, 255));
        LblPaterno4.setText("Cartel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(BtnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(lblImagenCartel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LblMaterno)
                                .addGap(18, 18, 18)
                                .addComponent(txtTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LblNombre)
                                    .addComponent(LblPaterno))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LblPaterno1)
                                .addGap(18, 18, 18)
                                .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(LblPaterno4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(319, 319, 319)
                        .addComponent(LblPaterno3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(LblPaterno2)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LblPaterno2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(LblPaterno4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LblPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LblPaterno1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(LblPaterno3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblImagenCartel, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
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

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAceptarActionPerformed
            String titulo = txtDuracion.getText();
            String pais = TxtPais.getText();
            int duracion = Integer.parseInt(TxtDuracion.getText());
            String link = TxtLink.getText();
            String sinopsis = TxtSinopsis.getText();
            
            
            if (titulo.isEmpty() || duracion == 0 || link.isEmpty() || sinopsis.isEmpty() || pais.isEmpty()) {
             JOptionPane.showMessageDialog(this, "Ingrese todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
           
        }
        try
        {
            PeliculaDTO c = this.peliculaBO.buscarPeliculaPorId(pelicula.getId());
            PeliculaDTO editar = new PeliculaDTO();
            this.peliculaBO.actualizarPelicula(editar);
            JOptionPane.showMessageDialog(this, "Registro modificado correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (NegocioException ex)
        {
            Logger.getLogger(FrmEditarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
        
    }//GEN-LAST:event_BtnAceptarActionPerformed

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAceptar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JLabel LblMaterno;
    private javax.swing.JLabel LblNombre;
    private javax.swing.JLabel LblPaterno;
    private javax.swing.JLabel LblPaterno1;
    private javax.swing.JLabel LblPaterno2;
    private javax.swing.JLabel LblPaterno3;
    private javax.swing.JLabel LblPaterno4;
    private javax.swing.JComboBox<SucursalDTO> cbPaises;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblImagenCartel;
    private javax.swing.JTextPane tPaneSinopsis;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtTrailer;
    // End of variables declaration//GEN-END:variables
}
