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
import dtos.DatosReporteSucursalDTO;
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
import negocio.ClienteBO;
import negocio.ICiudadBO;
import negocio.IClienteBO;
import negocio.IPeliculaBO;
import negocio.IReporteMetodoPagoBO;
import negocio.IReportePeliculaBO;
import negocio.IReportesSucursalesBO;
import negocio.ISalaBO;
import negocio.ISucursalBO;
import negocio.NegocioException;
import negocio.PeliculaBO;
import negocio.ReporteMetodoPago;
import negocio.ReportePeliculaBO;
import negocio.ReportesSucursalesBO;
import negocio.SalaBO;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.IReporteMetodoPagoDAO;
import persistencia.IReportePeliculaDAO;
import persistencia.IReportesSucursalesDAO;
import persistencia.ISalaDAO;
import persistencia.PeliculaDAO;
import persistencia.ReporteMetodoPagoDAO;
import persistencia.ReportePeliculaDAO;
import persistencia.ReportesSucursalesDAO;
import persistencia.SalaDAO;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author carli
 */
public class FrmReporteSucursales extends javax.swing.JFrame {

    private ISucursalBO sucursalBO;
    private ICiudadBO ciudadBO;

    private List<CiudadDTO> listaCiudades;
    private List<SucursalDTO> listaSucursales;

    private SucursalDTO sucursal;
    private boolean pelicuaEnSucursal = true;

    private IReportesSucursalesBO reportesBO;
    private Set<Integer> lista;
    private List<Integer> listaL;

    /**
     * Creates new form FrmAdminFuncion
     */
    public FrmReporteSucursales(ISucursalBO sucursalBO, ICiudadBO ciudadBO, IReportesSucursalesBO reportesBO) {
        initComponents();
        this.sucursalBO = sucursalBO;
        this.ciudadBO = ciudadBO;
        this.reportesBO = reportesBO;
        lista = new HashSet<>();
        cargarMetodosIniciales();
    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.llenarComboBoxSucrsales();
        this.setTitle("Reporte de Sucursales");
        this.setResizable(false);
        this.setSize(1280, 765);
        this.setLocationRelativeTo(null);

    }

    private void llenarTablaPeliculas(List<DatosReporteSucursalDTO> peliculaLista) {
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

    private void cargarDatosEnTabla(List<Integer> sucursalIds, String fechaInicio, String fechaFin) {
        try {
            System.out.println(sucursalIds + "    " + fechaInicio + "    " + fechaFin);

            // Obtén solo los clientes necesarios para la página actual
            List<DatosReporteSucursalDTO> clientesLista = this.reportesBO.obtenerGananciasPorSucursales(sucursalIds, fechaInicio, fechaFin);

            // Agrega los registros paginados a la tabla
            this.llenarTablaPeliculas(clientesLista);

        } catch (NegocioException ex) {
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
        cbSucursalQuitar = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReporteSucursal = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        imagenPerfiles1 = new utilerias.ImagenPerfiles();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnMenuCliente = new utilerias.MenuButton();
        jLabel3 = new javax.swing.JLabel();
        btnMenuPeliculas = new utilerias.MenuButton();
        jLabel4 = new javax.swing.JLabel();
        btnMenuSalas = new utilerias.MenuButton();
        jLabel5 = new javax.swing.JLabel();
        btnMenuSucursales = new utilerias.MenuButton();
        jLabel6 = new javax.swing.JLabel();
        btnMenuFunciones = new utilerias.MenuButton();
        jLabel8 = new javax.swing.JLabel();
        btnMenuReportePelicula = new utilerias.MenuButton();
        jLabel9 = new javax.swing.JLabel();
        btnMenuReportePelicula1 = new utilerias.MenuButton();
        jLabel7 = new javax.swing.JLabel();
        btnMenuReportePagos = new utilerias.MenuButton();
        jLabel11 = new javax.swing.JLabel();
        fechaFinDP = new com.github.lgooddatepicker.components.DatePicker();
        fechaInicioDP = new com.github.lgooddatepicker.components.DatePicker();
        cbSucursalAgregar = new javax.swing.JComboBox<>();
        btnQuitarSucursal = new javax.swing.JButton();
        btnAnadirSucursal3 = new javax.swing.JButton();
        btnGenerarReporte = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(36, 44, 99));

        cbSucursalQuitar.setBackground(new java.awt.Color(33, 36, 59));
        cbSucursalQuitar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbSucursalQuitar.setForeground(new java.awt.Color(255, 255, 255));
        cbSucursalQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSucursalQuitarActionPerformed(evt);
            }
        });

        tblReporteSucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ciudad", "Sucursal", "Funciones", "Fecha", "Ganancia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblReporteSucursal);

        jLabel10.setText("Reporte de ganacias por sucursal");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        btnImprimir.setText("Imprimir");
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

        btnMenuCliente.setText("Clientes");
        btnMenuCliente.setBorderPainted(false);
        btnMenuCliente.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuClienteActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuCliente);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel3);

        btnMenuPeliculas.setText("Peliculas");
        btnMenuPeliculas.setBorderPainted(false);
        btnMenuPeliculas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuPeliculas.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPeliculasActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuPeliculas);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel4);

        btnMenuSalas.setText("Salas");
        btnMenuSalas.setBorderPainted(false);
        btnMenuSalas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuSalas.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuSalasActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuSalas);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel5);

        btnMenuSucursales.setText("Sucursales");
        btnMenuSucursales.setBorderPainted(false);
        btnMenuSucursales.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuSucursales.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuSucursalesActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuSucursales);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel6);

        btnMenuFunciones.setText("Funciones");
        btnMenuFunciones.setBorderPainted(false);
        btnMenuFunciones.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuFunciones.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuFunciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuFuncionesActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuFunciones);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel8);

        btnMenuReportePelicula.setText("Reporte Sucursal");
        btnMenuReportePelicula.setBorderPainted(false);
        btnMenuReportePelicula.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuReportePelicula.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuReportePelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuReportePeliculaActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuReportePelicula);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel9);

        btnMenuReportePelicula1.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuReportePelicula1.setText("Reporte Pelicula");
        btnMenuReportePelicula1.setBorderPainted(false);
        btnMenuReportePelicula1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuReportePelicula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuReportePelicula1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuReportePelicula1);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel7);

        btnMenuReportePagos.setText("Reporte Pagos");
        btnMenuReportePagos.setBorderPainted(false);
        btnMenuReportePagos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuReportePagos.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuReportePagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuReportePagosActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuReportePagos);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel11);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(imagenPerfiles1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
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

        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Fecha inicio");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Fecha Final");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(297, 297, 297))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(cbSucursalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cbSucursalQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(fechaInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(fechaFinDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(btnQuitarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(275, 275, 275)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(212, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(286, 286, 286)
                    .addComponent(btnAnadirSucursal3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(860, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel12)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSucursalQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSucursalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuitarSucursal))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fechaInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addGap(1, 1, 1)
                        .addComponent(fechaFinDP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
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
            doc.add(new Paragraph("Ganancias Totales: " + suma, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
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
        // TODO add youaar handling code here:
    }//GEN-LAST:event_menuButton6ActionPerformed

    private void menuButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton3ActionPerformed

    private void cbSucursalAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSucursalAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSucursalAgregarActionPerformed

    private void btnQuitarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarSucursalActionPerformed
        SucursalDTO sucursal = (SucursalDTO) cbSucursalQuitar.getSelectedItem();
        if (sucursal != null) {
            cbSucursalQuitar.removeItem(sucursal);
            cbSucursalAgregar.addItem(sucursal);
        }

    }//GEN-LAST:event_btnQuitarSucursalActionPerformed

    private void btnAnadirSucursal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirSucursal3ActionPerformed
        SucursalDTO sucursal = (SucursalDTO) cbSucursalAgregar.getSelectedItem();
        if (sucursal != null) {
            cbSucursalAgregar.removeItem(sucursal);
            cbSucursalQuitar.addItem(sucursal);
        }
    }//GEN-LAST:event_btnAnadirSucursal3ActionPerformed

    private void cbSucursalQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSucursalQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSucursalQuitarActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
        if (fechaInicioDP.getDate() == null || fechaFinDP.getDate() == null) {
            JOptionPane.showMessageDialog(this, "No se selecciono ninguna fecha.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
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
        cargarDatosEnTabla(listaL, fInicio, fFin);
    }//GEN-LAST:event_btnImprimir1ActionPerformed

    private void btnMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuClienteActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IClienteDAO clienteDAO = new ClienteDAO(conexionBD);
        IClienteBO clienteBO = new ClienteBO(clienteDAO);
        FrmAdminClientes frmAdminClientes = new FrmAdminClientes(ciudadBO, clienteBO);
        frmAdminClientes.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuClienteActionPerformed

    private void btnMenuReportePelicula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReportePelicula1ActionPerformed
        IConexionBD conexion = new ConexionBD();
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        IReportePeliculaDAO reportePeliculaDAO = new ReportePeliculaDAO(conexion);
        IReportePeliculaBO reportePeliculaBO = new ReportePeliculaBO(reportePeliculaDAO);
        FrmReportePelicula reportePelicula = new FrmReportePelicula(reportePeliculaBO, ciudadBO, peliculaBO);
        reportePelicula.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReportePelicula1ActionPerformed

    private void btnMenuPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPeliculasActionPerformed
        IConexionBD conexion = new ConexionBD();
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        FrmAdminPeliculas frnAdminPeliculas = new FrmAdminPeliculas(sucursalBO, ciudadBO, peliculaBO);
        frnAdminPeliculas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuPeliculasActionPerformed

    private void btnMenuSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuSalasActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        ISalaDAO salaDAO = new SalaDAO(conexionBD);
        ISalaBO salaBO = new SalaBO(salaDAO);
        FrmAdminSalas frmAdminSalas = new FrmAdminSalas(sucursalBO, ciudadBO, salaBO);
        frmAdminSalas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuSalasActionPerformed

    private void btnMenuSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuSucursalesActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexionBD);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);

        FrmAdminSucursal frmAdminSucursal = new FrmAdminSucursal(sucursalBO, ciudadBO, peliculaBO);
        frmAdminSucursal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuSucursalesActionPerformed

    private void btnMenuFuncionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuFuncionesActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexionBD);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);

        FrmAdminEscogerFuncion escogerFuncion = new FrmAdminEscogerFuncion(sucursalBO, ciudadBO, peliculaBO);
        escogerFuncion.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuFuncionesActionPerformed

    private void btnMenuReportePeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReportePeliculaActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IReportesSucursalesDAO reportesSucursalesDAO = new ReportesSucursalesDAO(conexionBD);
        IReportesSucursalesBO reportesSucursalesBO = new ReportesSucursalesBO(reportesSucursalesDAO);

        FrmReporteSucursales reporteSucursales = new FrmReporteSucursales(sucursalBO, ciudadBO, reportesSucursalesBO);
        reporteSucursales.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReportePeliculaActionPerformed

    private void btnMenuReportePagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReportePagosActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IReporteMetodoPagoDAO reporteMetodoPagoDAO = new ReporteMetodoPagoDAO(conexionBD);
        IReporteMetodoPagoBO reporteMetodoPagoBO = new ReporteMetodoPago(reporteMetodoPagoDAO);
        FrmReporteMetodoPago reporteMetodoPago = new FrmReporteMetodoPago(reporteMetodoPagoBO, sucursalBO);
        reporteMetodoPago.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReportePagosActionPerformed

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
    private utilerias.MenuButton btnMenuCliente;
    private utilerias.MenuButton btnMenuFunciones;
    private utilerias.MenuButton btnMenuPeliculas;
    private utilerias.MenuButton btnMenuReportePagos;
    private utilerias.MenuButton btnMenuReportePelicula;
    private utilerias.MenuButton btnMenuReportePelicula1;
    private utilerias.MenuButton btnMenuSalas;
    private utilerias.MenuButton btnMenuSucursales;
    private javax.swing.JButton btnQuitarSucursal;
    private javax.swing.JComboBox<SucursalDTO> cbSucursalAgregar;
    private javax.swing.JComboBox<SucursalDTO> cbSucursalQuitar;
    private com.github.lgooddatepicker.components.DatePicker fechaFinDP;
    private com.github.lgooddatepicker.components.DatePicker fechaInicioDP;
    private utilerias.ImagenPerfiles imagenPerfiles1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblReporteSucursal;
    // End of variables declaration//GEN-END:variables
}
