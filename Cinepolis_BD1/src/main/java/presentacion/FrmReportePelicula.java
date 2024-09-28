/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.CiudadDTO;
import dtos.PeliculaDTO;
import dtos.SucursalDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.ICiudadBO;
import negocio.IPeliculaBO;
import negocio.ISucursalBO;
import negocio.NegocioException;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author carli
 */
public class FrmReportePelicula extends javax.swing.JFrame {

    private int pagina = 1;
    private final int LIMITE = 20;
    private ISucursalBO sucursalBO;
    private ICiudadBO ciudadBO;
    private IPeliculaBO peliculaBO;
    private List<CiudadDTO> listaCiudades;
    private List<SucursalDTO> listaSucursales;
    private List<PeliculaDTO> peliculasPorAnadir;
    private SucursalDTO sucursal;
    private boolean pelicuaEnSucursal = true;

    /**
     * Creates new form FrmAdminFuncion
     */
    public FrmReportePelicula(ISucursalBO sucursalBO, ICiudadBO ciudadBO, SucursalDTO sucursal) {
        initComponents();
        this.sucursalBO = sucursalBO;
        this.ciudadBO = ciudadBO;
        
        this.sucursal = sucursal;
        cargarMetodosIniciales();
    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.llenarComboBoxSucrsales();
        this.llenarComboBoxPeliculasPorAnadir();
        
        this.cargarConfiguracionInicialTablaPeliculas();
        this.cargarPeliculasTabla();
      
    }

    public void cargarPeliculasTabla() {
        try {
            List<PeliculaDTO> peliculaLista = this.peliculaBO.buscarPeliculaSucursal(sucursal.getId(), LIMITE, pagina, pelicuaEnSucursal);
            this.llenarTablaPeliculas(peliculaLista);
            if (peliculaLista == null && pagina == 1) {
                JOptionPane.showMessageDialog(null, "No hay ninguna pelicula registrada", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarConfiguracionInicialTablaPeliculas() {
        ActionListener onEditarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para editar un cliente

                removerPeliculaSucursal();
            }
        };
        int indiceColumnaEditar = 5;
        TableColumnModel modeloColumnas = this.tblReporteSucursal.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("Remover"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("Remover",
                        onEditarClickListener));

        ActionListener onEliminarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para eliminar un cliente
                verPeliculaFuncion();
            }
        };
        int indiceColumnaEliminar = 6;
        modeloColumnas = this.tblReporteSucursal.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Ver"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Ver",
                        onEliminarClickListener));
    }

    private void llenarTablaPeliculas(List<PeliculaDTO> peliculaLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblReporteSucursal.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (peliculaLista != null) {
            peliculaLista.forEach(row
                    -> {
                Object[] fila = new Object[5];
                fila[0] = row.getId();
                fila[1] = row.getTitulo();
                fila[2] = row.getDuracion();
                fila[3] = row.getPais();
                fila[4] = row.getClasificacion();

                modeloTabla.addRow(fila);
            });
        }
    }

    private int getIdSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblReporteSucursal.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblReporteSucursal.getModel();
            int indiceColumnaId = 0;
            int idSocioSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return 0;
        }
    }

    private void removerPeliculaSucursal() {
        int id = this.getIdSeleccionadoTablaClientes();
        if (id == 0) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una pelicula", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            peliculaBO.eliminarPeliculaDeSucursal(id, sucursal.getId());
            this.cargarPeliculasTabla();
            this.llenarComboBoxPeliculasPorAnadir();
            
        } catch (NegocioException ex) {
            Logger.getLogger(FrmReportePelicula.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void verPeliculaFuncion() {
        int id = this.getIdSeleccionadoTablaClientes();
        if (id == 0) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una pelicula", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            PeliculaDTO pelicula = peliculaBO.buscarPeliculaPorId(id);
        } catch (NegocioException ex) {
            Logger.getLogger(FrmReportePelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jPanel3 = new javax.swing.JPanel();
        cbSucursal = new javax.swing.JComboBox<>();
        btnIr = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporteSucursal = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        imagenPerfiles1 = new utilerias.ImagenPerfiles();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        menuButton3 = new utilerias.MenuButton();
        jLabel3 = new javax.swing.JLabel();
        menuButton2 = new utilerias.MenuButton();
        jLabel4 = new javax.swing.JLabel();
        menuButton1 = new utilerias.MenuButton();
        jLabel5 = new javax.swing.JLabel();
        menuButton4 = new utilerias.MenuButton();
        jLabel6 = new javax.swing.JLabel();
        menuButton5 = new utilerias.MenuButton();
        jLabel8 = new javax.swing.JLabel();
        menuButton6 = new utilerias.MenuButton();
        jLabel9 = new javax.swing.JLabel();
        fechaFinDP = new com.github.lgooddatepicker.components.DatePicker();
        fechaInicioDP = new com.github.lgooddatepicker.components.DatePicker();
        cbSucursal1 = new javax.swing.JComboBox<>();
        btnQuitarSucursal = new javax.swing.JButton();
        btnAnadirSucursal3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(36, 44, 99));

        cbSucursal.setBackground(new java.awt.Color(33, 36, 59));
        cbSucursal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbSucursal.setForeground(new java.awt.Color(255, 255, 255));
        cbSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSucursalActionPerformed(evt);
            }
        });

        btnIr.setText("Ir");
        btnIr.setBorderPainted(false);
        btnIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrActionPerformed(evt);
            }
        });

        tblReporteSucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Ciudad", "Sucursal", "Funciones", "Fecha", "Ganancia"
            }
        ));
        jScrollPane1.setViewportView(tblReporteSucursal);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Reporte de ganacias por sucursal");

        btnImprimir.setText("Imprimir ");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Sucusal para añadir");

        jPanel2.setBackground(new java.awt.Color(33, 36, 59));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 720));

        imagenPerfiles1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/imagenes/littlelogoGrande 1.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(33, 36, 59));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel2);

        menuButton3.setForeground(new java.awt.Color(255, 255, 255));
        menuButton3.setText("Clientes");
        menuButton3.setBorderPainted(false);
        menuButton3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        menuButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(menuButton3);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel3);

        menuButton2.setForeground(new java.awt.Color(255, 255, 255));
        menuButton2.setText("Peliculas");
        menuButton2.setBorderPainted(false);
        menuButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jPanel4.add(menuButton2);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel4);

        menuButton1.setForeground(new java.awt.Color(255, 255, 255));
        menuButton1.setText("Salas");
        menuButton1.setBorderPainted(false);
        menuButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        menuButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(menuButton1);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel5);

        menuButton4.setForeground(new java.awt.Color(255, 255, 255));
        menuButton4.setText("Sucursales");
        menuButton4.setBorderPainted(false);
        menuButton4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        menuButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(menuButton4);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel6);

        menuButton5.setForeground(new java.awt.Color(255, 255, 255));
        menuButton5.setText("Funciones");
        menuButton5.setBorderPainted(false);
        menuButton5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        menuButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(menuButton5);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel8);

        menuButton6.setForeground(new java.awt.Color(255, 255, 255));
        menuButton6.setText("Reportes");
        menuButton6.setBorderPainted(false);
        menuButton6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        menuButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(menuButton6);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel9);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(imagenPerfiles1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(imagenPerfiles1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        cbSucursal1.setBackground(new java.awt.Color(33, 36, 59));
        cbSucursal1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbSucursal1.setForeground(new java.awt.Color(255, 255, 255));
        cbSucursal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSucursal1ActionPerformed(evt);
            }
        });

        btnQuitarSucursal.setText("Quitar Sucursal");
        btnQuitarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarSucursalActionPerformed(evt);
            }
        });

        btnAnadirSucursal3.setText("Añadir Sucursal");
        btnAnadirSucursal3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirSucursal3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(379, 379, 379)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbSucursal1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(fechaFinDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(297, 297, 297)
                                .addComponent(fechaInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnIr))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(btnQuitarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(286, 286, 286)
                    .addComponent(btnAnadirSucursal3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(768, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSucursal1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(btnIr))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fechaInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fechaFinDP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnQuitarSucursal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(152, 152, 152)
                    .addComponent(btnAnadirSucursal3)
                    .addContainerGap(560, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

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

    private void menuButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton1ActionPerformed

    private void menuButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton4ActionPerformed

    private void menuButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton5ActionPerformed

    private void btnIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrActionPerformed
        
    }//GEN-LAST:event_btnIrActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void menuButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton6ActionPerformed

    private void cbSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSucursalActionPerformed

    private void menuButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton3ActionPerformed

    private void cbSucursal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSucursal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSucursal1ActionPerformed

    private void btnQuitarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarSucursalActionPerformed

    private void btnAnadirSucursal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirSucursal3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnadirSucursal3ActionPerformed
    private void llenarComboBoxPeliculasPorAnadir() {
        try {
            cbSucursal.removeAllItems();
            peliculasPorAnadir = peliculaBO.buscarPeliculaSucursal(sucursal.getId(), LIMITE, pagina, !pelicuaEnSucursal);
            for (PeliculaDTO pelicula : peliculasPorAnadir) {
                cbSucursal.addItem(pelicula);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCatalogoSucursal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
     private void llenarComboBoxSucrsales() {
        try {
            listaSucursales = sucursalBO.obtenerSucursales();

            for (SucursalDTO sucursal : listaSucursales) {
                cbSucursal.addItemListener((ItemListener) sucursal);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCatalogoSucursal.class.getName()).log(Level.SEVERE, null, ex);
        }

        cbSucursal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //actualizarComboBoxCiudad();
            }
        });
    }

   /* private void actualizarComboBoxCiudad() {
        try {
            SucursalDTO sucursal = (SucursalDTO) cbSucursal.getSelectedItem();
            listaSucursales = sucursalBO.listaSucursalesporCiudad(ciudad.getId());
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCatalogoSucursal.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Limpiar el combo box de ciudades
        cbSucursales.removeAllItems();

        // Añadir las ciudades correspondientes
        if (listaSucursales != null) {
            for (SucursalDTO sucursal : listaSucursales) {
                cbSucursales.addItem(sucursal);
            }
        }
    }*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadirSucursal3;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnIr;
    private javax.swing.JButton btnQuitarSucursal;
    private javax.swing.JComboBox<PeliculaDTO> cbSucursal;
    private javax.swing.JComboBox<PeliculaDTO> cbSucursal1;
    private com.github.lgooddatepicker.components.DatePicker fechaFinDP;
    private com.github.lgooddatepicker.components.DatePicker fechaInicioDP;
    private utilerias.ImagenPerfiles imagenPerfiles1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private utilerias.MenuButton menuButton1;
    private utilerias.MenuButton menuButton2;
    private utilerias.MenuButton menuButton3;
    private utilerias.MenuButton menuButton4;
    private utilerias.MenuButton menuButton5;
    private utilerias.MenuButton menuButton6;
    private javax.swing.JTable tblReporteSucursal;
    // End of variables declaration//GEN-END:variables
}
