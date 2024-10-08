/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.GeneroDTO;
import dtos.PeliculaDTO;
import dtos.SalaDTO;
import dtos.SucursalDTO;
import enumeradores.Clasificaciones;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import negocio.CiudadBO;
import negocio.ICiudadBO;
import negocio.IGeneroBO;
import negocio.IPeliculaBO;
import negocio.ISalaBO;
import negocio.ISucursalBO;
import negocio.NegocioException;
import negocio.SucursalBO;
import persistencia.CiudadDAO;
import persistencia.ConexionBD;
import persistencia.ICiudadDAO;
import persistencia.IConexionBD;
import persistencia.ISucursalDAO;
import persistencia.SucursalDAO;
import presentacion.FrmAdminPeliculas;
import static utilerias.Herramientas.getComboBoxItems;

/**
 *
 * @author rramirez
 */
public class FrmEditarPelicula extends javax.swing.JFrame {

    IGeneroBO generoBO;
    IPeliculaBO peliculaBO;
    PeliculaDTO pelicula;
    List<GeneroDTO> listaGeneros;
    Locale[] locales;
    String ruta = "";

    /**
     * Creates new form FrmAgregarCliente
     */
    public FrmEditarPelicula(IPeliculaBO peliculaBO, IGeneroBO generoBO, PeliculaDTO pelicula) {
        initComponents();
        this.peliculaBO = peliculaBO;
        this.generoBO = generoBO;
        this.pelicula = pelicula;
        locales = Locale.getAvailableLocales();
        this.llenarComboPaises();
        this.llenarComboClasificaciones();
        this.setTitle("Editar Pelicula ");
        this.setResizable(false);
        this.setSize(1280, 780);
        this.setLocationRelativeTo(null);
        metodoMostrarDatos();

    }

    public void metodoMostrarDatos() {
        txtTitulo.setText(pelicula.getTitulo());
        txtDuracion.setText(String.valueOf(pelicula.getDuracion()));
        txtTrailer.setText(pelicula.getLink_trailer());
        tPaneSinopsis1.setText(pelicula.getSinopsis());
        cbClasificaciones.setSelectedItem(pelicula.getClasificacion());
        cbPaises.setSelectedItem(pelicula.getPais());
        ImageIcon icon = new ImageIcon(pelicula.getCartel());
        Image scaledImage = icon.getImage().getScaledInstance(210, 268, Image.SCALE_SMOOTH);
        lblImagenCartel.setIcon(new ImageIcon(scaledImage));
        llenarComboBoxGeneroASeleccionar();
        llenarComboBoxGeneroSeleccionados();
    }

    ;
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
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
        txtTitulo = new javax.swing.JTextField();
        cbGeneros = new javax.swing.JComboBox<>();
        txtTrailer = new javax.swing.JTextField();
        cbClasificaciones = new javax.swing.JComboBox<>();
        LblPaterno5 = new javax.swing.JLabel();
        cbPaises = new javax.swing.JComboBox<>();
        LblPaterno6 = new javax.swing.JLabel();
        LblNombre1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tPaneSinopsis1 = new javax.swing.JTextPane();
        btnGeneros = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnGenerosSeleccionados = new javax.swing.JButton();
        cbGenerosSeleccionados = new javax.swing.JComboBox<>();
        LblPaterno4 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(36, 44, 99));
        jPanel1.setToolTipText("");

        LblNombre.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblNombre.setForeground(new java.awt.Color(255, 255, 255));
        LblNombre.setText("Cartel");

        txtDuracion.setBackground(new java.awt.Color(33, 36, 59));
        txtDuracion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtDuracion.setForeground(new java.awt.Color(255, 255, 255));

        LblPaterno.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblPaterno.setForeground(new java.awt.Color(255, 255, 255));
        LblPaterno.setText("Generos");

        LblMaterno.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblMaterno.setForeground(new java.awt.Color(255, 255, 255));
        LblMaterno.setText("Trailer");

        BtnAceptar.setText("Aceptar");
        BtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAceptarActionPerformed(evt);
            }
        });

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

        txtTitulo.setBackground(new java.awt.Color(33, 36, 59));
        txtTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTitulo.setForeground(new java.awt.Color(255, 255, 255));
        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });

        cbGeneros.setBackground(new java.awt.Color(33, 36, 59));
        cbGeneros.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbGeneros.setForeground(new java.awt.Color(255, 255, 255));

        txtTrailer.setBackground(new java.awt.Color(33, 36, 59));
        txtTrailer.setForeground(new java.awt.Color(255, 255, 255));

        cbClasificaciones.setBackground(new java.awt.Color(33, 36, 59));
        cbClasificaciones.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbClasificaciones.setForeground(new java.awt.Color(255, 255, 255));

        LblPaterno5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblPaterno5.setForeground(new java.awt.Color(255, 255, 255));
        LblPaterno5.setText("Clasificacion");

        cbPaises.setBackground(new java.awt.Color(33, 36, 59));
        cbPaises.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbPaises.setForeground(new java.awt.Color(255, 255, 255));

        LblPaterno6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblPaterno6.setForeground(new java.awt.Color(255, 255, 255));
        LblPaterno6.setText("Pais");

        LblNombre1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblNombre1.setForeground(new java.awt.Color(255, 255, 255));
        LblNombre1.setText("Titulo");

        tPaneSinopsis1.setBackground(new java.awt.Color(33, 36, 59));
        tPaneSinopsis1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(tPaneSinopsis1);

        btnGeneros.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGeneros.setText("+");
        btnGeneros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerosActionPerformed(evt);
            }
        });

        jButton3.setText("Explorar...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnGenerosSeleccionados.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGenerosSeleccionados.setText("-");
        btnGenerosSeleccionados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerosSeleccionadosActionPerformed(evt);
            }
        });

        cbGenerosSeleccionados.setBackground(new java.awt.Color(33, 36, 59));
        cbGenerosSeleccionados.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbGenerosSeleccionados.setForeground(new java.awt.Color(255, 255, 255));

        LblPaterno4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblPaterno4.setForeground(new java.awt.Color(255, 255, 255));
        LblPaterno4.setText("Generos Seleccionados");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblImagenCartel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblPaterno1)
                    .addComponent(LblMaterno)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTrailer, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtDuracion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbClasificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(cbPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(58, 58, 58)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LblPaterno4)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(cbGenerosSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnGenerosSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(51, 51, 51))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(cbGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(LblPaterno)
                                .addComponent(LblPaterno5))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblPaterno6)
                        .addGap(92, 92, 92))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(LblNombre)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(BtnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(LblPaterno2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jButton3)
                        .addGap(175, 175, 175)
                        .addComponent(LblPaterno3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(270, 270, 270)
                    .addComponent(LblNombre1)
                    .addContainerGap(943, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(LblPaterno2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblImagenCartel, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(LblPaterno1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(10, 10, 10)
                                            .addComponent(LblMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtTrailer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(8, 8, 8)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(LblPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(8, 8, 8)
                                                    .addComponent(cbGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(LblPaterno5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cbClasificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(LblPaterno6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cbPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(182, 182, 182)
                                                    .addComponent(LblPaterno4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(8, 8, 8)
                                                    .addComponent(cbGenerosSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(1, 1, 1)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(btnGeneros, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(240, 240, 240)
                                .addComponent(btnGenerosSeleccionados, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(LblPaterno3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(43, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(105, 105, 105))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(144, 144, 144)
                    .addComponent(LblNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(540, Short.MAX_VALUE)))
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

        volverAdminPeliculas();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void BtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAceptarActionPerformed
        String titulo = txtTitulo.getText().trim();
        String sinopsis = tPaneSinopsis1.getText().trim();
        String clasificacion = cbClasificaciones.getSelectedItem().toString();
        String trailer = txtTrailer.getText().trim();
        String pais = cbPaises.getSelectedItem().toString().trim();
        String duracionStr = txtDuracion.getText().trim();
        String cartel = ruta.trim();
        List<Integer> listaGeneros = obtenerGenerosSeleccionados();
        if (listaGeneros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La pelicula debe tener un genero", "Campos vacíos", JOptionPane.ERROR_MESSAGE);

        }
        if (cartel == "") {
            cartel = pelicula.getCartel();
        }

        if (titulo.isEmpty() || sinopsis.isEmpty() || trailer.isEmpty()
                || pais.isEmpty() || duracionStr.isEmpty() || cartel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Campos vacíos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int duracion;
        try {
            duracion = Integer.parseInt(duracionStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La duración debe ser un número entero.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        pelicula.setTitulo(titulo);
        pelicula.setSinopsis(sinopsis);
        pelicula.setLink_trailer(trailer);
        pelicula.setPais(pais);
        pelicula.setCartel(cartel);
        pelicula.setClasificacion(clasificacion);
        pelicula.setDuracion(duracion);

        try {
            generoBO.actualizarGenerosPelicula(pelicula.getId(), listaGeneros);
            peliculaBO.actualizarPelicula(pelicula);
            JOptionPane.showMessageDialog(this, "Película actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            volverAdminPeliculas();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "La pelicula no ha sio actualizada!.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_BtnAceptarActionPerformed
    private void volverAdminPeliculas() {
        IConexionBD cconexion = new ConexionBD();
        ISucursalDAO sucursalDAO = new SucursalDAO(cconexion);
        ICiudadDAO ciudadDAO = new CiudadDAO(cconexion);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        FrmAdminPeliculas peliculas = new FrmAdminPeliculas(sucursalBO, ciudadBO, peliculaBO);
        peliculas.setVisible(true);
        this.dispose();
    }
    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG Y JPEG", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(extensionFilter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            ruta = fileChooser.getSelectedFile().getAbsolutePath();
            ImageIcon icon = new ImageIcon(ruta);
            Image scaledImage = icon.getImage().getScaledInstance(210, 268, Image.SCALE_SMOOTH);
            lblImagenCartel.setIcon(new ImageIcon(scaledImage));
            pelicula.setCartel(ruta);
        }
     }//GEN-LAST:event_jButton3ActionPerformed

    private void btnGenerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerosActionPerformed
        GeneroDTO generoElegido = (GeneroDTO) cbGeneros.getSelectedItem();
        cbGeneros.removeItem(generoElegido);
        cbGenerosSeleccionados.addItem(generoElegido);
    }//GEN-LAST:event_btnGenerosActionPerformed

    private void btnGenerosSeleccionadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerosSeleccionadosActionPerformed
        GeneroDTO generoElegido = (GeneroDTO) cbGenerosSeleccionados.getSelectedItem();
        cbGenerosSeleccionados.removeItem(generoElegido);
        cbGeneros.addItem(generoElegido);
    }//GEN-LAST:event_btnGenerosSeleccionadosActionPerformed

    private void llenarComboClasificaciones() {
        for (Clasificaciones clasi : Clasificaciones.values()) {
            cbClasificaciones.addItem(clasi);
        }
    }

    private void llenarComboPaises() {
        cbPaises.removeAllItems();

        List<String> countryList = new ArrayList<>();

        // Obtener todos los locales disponibles y añadir los países a la lista
        for (Locale locale : Locale.getAvailableLocales()) {
            String country = locale.getDisplayCountry();
            if (!country.isEmpty() && !countryList.contains(country)) {  // Verifica si el país ya está en la lista
                countryList.add(country);  // Añade el país a la lista temporal
            }
        }

        // Ordenar la lista de países en orden alfabético
        Collections.sort(countryList);

        // Añadir los países ordenados al JComboBox
        for (String country : countryList) {
            cbPaises.addItem(country);
        }
    }

    private void llenarComboBoxGeneroSeleccionados() {
        try {
            listaGeneros = generoBO.listaGeneroPorPelicula(pelicula.getId(), true);

            for (GeneroDTO genero : listaGeneros) {
                cbGenerosSeleccionados.addItem(genero);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCatalogoSucursal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void llenarComboBoxGeneroASeleccionar() {
        try {
            listaGeneros = generoBO.listaGeneroPorPelicula(pelicula.getId(), false);

            for (GeneroDTO genero : listaGeneros) {
                cbGeneros.addItem(genero);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(FrmEditarPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Integer> obtenerGenerosSeleccionados() {
        List<Integer> listaGenerosSeleccionados = new ArrayList<>();
        for (int i = 0; i < cbGenerosSeleccionados.getItemCount(); i++) {
            listaGenerosSeleccionados.add(cbGenerosSeleccionados.getItemAt(i).getIdGenero());
        }

        return listaGenerosSeleccionados;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAceptar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JLabel LblMaterno;
    private javax.swing.JLabel LblNombre;
    private javax.swing.JLabel LblNombre1;
    private javax.swing.JLabel LblPaterno;
    private javax.swing.JLabel LblPaterno1;
    private javax.swing.JLabel LblPaterno2;
    private javax.swing.JLabel LblPaterno3;
    private javax.swing.JLabel LblPaterno4;
    private javax.swing.JLabel LblPaterno5;
    private javax.swing.JLabel LblPaterno6;
    private javax.swing.JButton btnGeneros;
    private javax.swing.JButton btnGenerosSeleccionados;
    private javax.swing.JComboBox<Object> cbClasificaciones;
    private javax.swing.JComboBox<GeneroDTO> cbGeneros;
    private javax.swing.JComboBox<GeneroDTO> cbGenerosSeleccionados;
    private javax.swing.JComboBox<String> cbPaises;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblImagenCartel;
    private javax.swing.JTextPane tPaneSinopsis1;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtTitulo;
    private javax.swing.JTextField txtTrailer;
    // End of variables declaration//GEN-END:variables
}
