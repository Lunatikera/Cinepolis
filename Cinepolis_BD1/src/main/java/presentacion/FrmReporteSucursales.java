/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dtos.CiudadDTO;
import dtos.DatosReporteDTO;
import dtos.PeliculaDTO;
import dtos.SucursalDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.ICiudadBO;
import negocio.IPeliculaBO;
import negocio.IReportesSucursalesBO;
import negocio.ISucursalBO;
import negocio.NegocioException;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author carli
 */
public class FrmReporteSucursales extends javax.swing.JFrame {

   ;
    private ISucursalBO sucursalBO;
    private ICiudadBO ciudadBO;
    
    private List<CiudadDTO> listaCiudades;
    private List<SucursalDTO> listaSucursales;
    
    private SucursalDTO sucursal;
    private boolean pelicuaEnSucursal = true;
    
    private IReportesSucursalesBO reportes;
    private Set<Integer> lista;
    private List<Integer> listaL;

    /**
     * Creates new form FrmAdminFuncion
     */
    public FrmReporteSucursales(ISucursalBO sucursalBO, ICiudadBO ciudadBO, SucursalDTO sucursal,IReportesSucursalesBO reportes) {
        initComponents();
        this.sucursalBO = sucursalBO;
        this.ciudadBO = ciudadBO;
        this.reportes = reportes;
        this.sucursal = sucursal;
        lista = new HashSet<>();
        cargarMetodosIniciales();
    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.llenarComboBoxSucrsales();
        
    }

    

    private void cargarConfiguracionInicialTablaPeliculas() {
        ActionListener onEditarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para editar un cliente

                
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

    private void llenarTablaPeliculas(List<DatosReporteDTO> peliculaLista) {
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
                fila[0] = row.getCiudad();
                fila[1] = row.getSucursal();
                fila[2] = row.getCantidadFunciones();
                fila[3] = row.getFecha();
                fila[4] = row.getTotal();

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

    private void BorrarRegistrosTablaClientes() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblReporteSucursal.getModel();
        if (modeloTabla.getRowCount() > 0)
        {
            for (int row = modeloTabla.getRowCount() - 1; row > -1; row--)
            {
                modeloTabla.removeRow(row);
            }
        }
    }
    
    private void cargarDatosEnTabla(List<Integer> sucursalIds, String fechaInicio, String fechaFin) {
    try
        {
            System.out.println(sucursalIds+"    "+fechaInicio+"    "+fechaFin);
            
            // Obtén solo los clientes necesarios para la página actual
            List<DatosReporteDTO> clientesLista = this.reportes.obtenerGananciasPorSucursales(sucursalIds, fechaInicio, fechaFin);
            
            // Agrega los registros paginados a la tabla
            this.llenarTablaPeliculas(clientesLista);

        } catch (NegocioException ex)
        {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
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
        cbSucursalAgregar = new javax.swing.JComboBox<>();
        btnQuitarSucursal = new javax.swing.JButton();
        btnAnadirSucursal3 = new javax.swing.JButton();
        cbSucursalQuitar = new javax.swing.JComboBox<>();
        btnGenerarReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(36, 44, 99));

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

        jLabel10.setText("Reporte de ganacias por sucursal");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        btnImprimir.setText("Imprimir ");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel12.setText("Sucusal para añadir");
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));

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

        cbSucursalAgregar.setBackground(new java.awt.Color(33, 36, 59));
        cbSucursalAgregar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbSucursalAgregar.setForeground(new java.awt.Color(255, 255, 255));
        cbSucursalAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSucursalAgregarActionPerformed(evt);
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

        cbSucursalQuitar.setBackground(new java.awt.Color(33, 36, 59));
        cbSucursalQuitar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbSucursalQuitar.setForeground(new java.awt.Color(255, 255, 255));
        cbSucursalQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSucursalQuitarActionPerformed(evt);
            }
        });

        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
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
                        .addGap(247, 247, 247)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbSucursalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(cbSucursalQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(fechaFinDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(297, 297, 297)
                                .addComponent(fechaInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(btnQuitarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121)
                                .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(120, Short.MAX_VALUE))
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
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSucursalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSucursalQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        String path = "";
JFileChooser j = new JFileChooser();
j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
int x = j.showSaveDialog(this);
if (x == JFileChooser.APPROVE_OPTION) {
    path = j.getSelectedFile().getPath();
}

if (path.isEmpty()) {
    JOptionPane.showMessageDialog(this, "No se seleccionó ninguna carpeta.", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

Document doc = new Document();
try {
    PdfWriter.getInstance(doc, new FileOutputStream(String.format("%s/ReporteSucursales.pdf", path)));
    doc.open();

    // Descripción de los filtros
    doc.add(new Paragraph("Reporte de Ganancias por Sucursales", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
    doc.add(new Paragraph("Filtros Aplicados:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
    doc.add(new Paragraph("Fechas: " + fechaInicioDP.getText() + " a " + fechaFinDP.getText()));
    doc.add(new Paragraph("Sucursales: " + obtenerValoresSeparadosPorComa(cbSucursalQuitar))); // Asegúrate de convertir la lista a cadena
    doc.add(new Paragraph("\n")); // Espacio en blanco

    // Tabla
    PdfPTable tbl = new PdfPTable(5);
    tbl.addCell("Ciudad");
    tbl.addCell("Sucursales");
    tbl.addCell("Funciones");
    tbl.addCell("Fecha");
    tbl.addCell("Ganancia");
    BigDecimal suma = BigDecimal.ZERO;
    for (int i = 0; i < tblReporteSucursal.getRowCount(); i++) {
        String ciudad = tblReporteSucursal.getValueAt(i, 0).toString();
        String sucursales = tblReporteSucursal.getValueAt(i, 1).toString();
        String funciones = tblReporteSucursal.getValueAt(i, 2).toString();
        String fecha = tblReporteSucursal.getValueAt(i, 3).toString();
        String ganancia = tblReporteSucursal.getValueAt(i, 4).toString();
        suma = suma.add(convertToBigDecimal(tblReporteSucursal.getValueAt(i, 4)));
        tbl.addCell(ciudad);
        tbl.addCell(sucursales);
        tbl.addCell(funciones);
        tbl.addCell(fecha);
        tbl.addCell(ganancia);
    }

    doc.add(tbl);
    doc.add(new Paragraph("Ganancias Totales: "+suma, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
    JOptionPane.showMessageDialog(this, "Se imprimió con éxito el documento!");

} catch (FileNotFoundException ex) {
    JOptionPane.showMessageDialog(this, "Error al crear el archivo PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
} catch (DocumentException ex) {
    Logger.getLogger(FrmReporteSucursales.class.getName()).log(Level.SEVERE, null, ex);
} finally {
    doc.close(); // Asegúrate de cerrar el documento en el bloque finally
}

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void menuButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton6ActionPerformed

    private void menuButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton3ActionPerformed

    private void cbSucursalAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSucursalAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSucursalAgregarActionPerformed

    private void btnQuitarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarSucursalActionPerformed
         SucursalDTO sucursal = (SucursalDTO) cbSucursalQuitar.getSelectedItem();
        cbSucursalQuitar.removeItem(sucursal);
        cbSucursalAgregar.addItem(sucursal);
    }//GEN-LAST:event_btnQuitarSucursalActionPerformed

    private void btnAnadirSucursal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirSucursal3ActionPerformed
        SucursalDTO sucursal = (SucursalDTO) cbSucursalAgregar.getSelectedItem();
        cbSucursalAgregar.removeItem(sucursal);
        cbSucursalQuitar.addItem(sucursal);
    }//GEN-LAST:event_btnAnadirSucursal3ActionPerformed

    private void cbSucursalQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSucursalQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSucursalQuitarActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed

        lista.removeAll(lista);
        for (int i = 0; i < cbSucursalQuitar.getItemCount(); i++) {
            SucursalDTO suc = cbSucursalQuitar.getItemAt(i);
            this.lista.add(suc.getId());
        }
        LocalDate fechaInicio = fechaInicioDP.getDate();
        LocalDate fechaFin = fechaFinDP.getDate();
        String fInicio = convertLocalDateToString(fechaInicio);
        String fFin = convertLocalDateToString(fechaFin);
        listaL = new ArrayList(lista);
        cargarDatosEnTabla(listaL,fInicio,fFin);
    }//GEN-LAST:event_btnGenerarReporteActionPerformed
    
    private String obtenerValoresSeparadosPorComa(JComboBox<SucursalDTO> comboBox) {
        StringBuilder valores = new StringBuilder();

        // Iterar sobre todos los elementos del comboBox
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            SucursalDTO item = comboBox.getItemAt(i);
            valores.append(item.getNombre());

            // Añadir una coma si no es el último elemento
            if (i < comboBox.getItemCount() - 1) {
                valores.append(", ");
            }
        }

        return valores.toString();
    }
    private String convertLocalDateToString(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("The date cannot be null.");
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
    
     private void llenarComboBoxSucrsales() {
        try {
            listaSucursales = sucursalBO.obtenerSucursales();

            for (SucursalDTO sucursal : listaSucursales) {
                cbSucursalAgregar.addItem(sucursal);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCatalogoSucursal.class.getName()).log(Level.SEVERE, null, ex);
        }

        cbSucursalAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //actualizarComboBoxCiudad();
            }
        });
    }

     public static BigDecimal convertToBigDecimal(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("El objeto no puede ser nulo.");
        }
        
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;  // Ya es un BigDecimal
        } else if (obj instanceof String) {
            return new BigDecimal((String) obj);  // Convertir desde String
        } else if (obj instanceof Number) {
            return BigDecimal.valueOf(((Number) obj).doubleValue());  // Convertir desde Number
        } else {
            throw new IllegalArgumentException("El objeto no se puede convertir a BigDecimal.");
        }
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
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnQuitarSucursal;
    private javax.swing.JComboBox<SucursalDTO> cbSucursalAgregar;
    private javax.swing.JComboBox<SucursalDTO> cbSucursalQuitar;
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
