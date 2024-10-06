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
import dtos.DatosReporteMetodoPagoDTO;
import dtos.DatosReportePeliculasDTO;
import dtos.PeliculaDTO;
import dtos.SucursalDTO;
import enumeradores.MetodosDePago;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import negocio.CiudadBO;
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
import negocio.SucursalBO;
import persistencia.CiudadDAO;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.ICiudadDAO;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.IReporteMetodoPagoDAO;
import persistencia.IReportesSucursalesDAO;
import persistencia.ISalaDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.ReporteMetodoPagoDAO;
import persistencia.ReportesSucursalesDAO;
import persistencia.SalaDAO;
import persistencia.SucursalDAO;
import static presentacion.FrmReporteSucursales.convertToBigDecimal;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author carli
 */
public class FrmReporteMetodoPago extends javax.swing.JFrame {

    private IReporteMetodoPagoBO reporteMetodoPagoBO;
    private ISucursalBO sucursalBO;
    private List<SucursalDTO> listaSucursales;
    private Set<Integer> lista;
    private Set<String> listaMetodo;
    private List<Integer> listaSucursalesID;
    private List<String> listaMetodosPago;

    /**
     * Creates new form FrmAdminFuncion
     */
    public FrmReporteMetodoPago(IReporteMetodoPagoBO reporteMetodoPagoBO, ISucursalBO sucursalBO) {
        initComponents();
        this.reporteMetodoPagoBO = reporteMetodoPagoBO;
        this.sucursalBO = sucursalBO;
        lista = new HashSet<>();
        listaMetodo = new HashSet<>();

        cargarMetodosIniciales();
    }

    public void cargarMetodosIniciales() {
        this.llenarComboBoxSucursales();
        this.llenarComboMetodosPago();
        this.setTitle("Reporte de Peliculas");
        this.setResizable(false);
        this.setSize(1280, 775);
        this.setLocationRelativeTo(null);

    }

    private void cargarDatosEnTabla(List<String> listaMetodosPago, List<Integer> listaSucursalesID, String fechaInicio, String fechaFin) {
        try {

            // Obtén solo los clientes necesarios para la página actual
            List<DatosReporteMetodoPagoDTO> reporteLista = this.reporteMetodoPagoBO.calcularGanancias(listaMetodosPago, listaSucursalesID, fechaInicio, fechaFin);

            // Agrega los registros paginados a la tabla
            this.llenarTablaPeliculas(reporteLista);

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void llenarTablaPeliculas(List<DatosReporteMetodoPagoDTO> peliculaLista) {
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
                fila[0] = convertLocalDateTimeToString(row.getFechaCompra());
                fila[1] = row.getNombreCiudad();
                fila[2] = row.getNombreSucursal();
                fila[3] = row.getMetodoPago();
                fila[4] = row.getTotalCompra();

                modeloTabla.addRow(fila);
            });
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
        btnGenerarReporte = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        imagenPerfiles1 = new utilerias.ImagenPerfiles();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnMenuCliente = new utilerias.MenuButton();
        jLabel3 = new javax.swing.JLabel();
        btnMenuPelicula = new utilerias.MenuButton();
        jLabel4 = new javax.swing.JLabel();
        btnMenuSalas = new utilerias.MenuButton();
        jLabel5 = new javax.swing.JLabel();
        btnMenuSucursales = new utilerias.MenuButton();
        jLabel6 = new javax.swing.JLabel();
        btnMenuFunciones = new utilerias.MenuButton();
        jLabel8 = new javax.swing.JLabel();
        btnMenuReporteSucursales = new utilerias.MenuButton();
        jLabel9 = new javax.swing.JLabel();
        btnMenuReporteSucursales1 = new utilerias.MenuButton();
        jLabel7 = new javax.swing.JLabel();
        btnMenuReportePagos = new utilerias.MenuButton();
        jLabel11 = new javax.swing.JLabel();
        fechaFinDP = new com.github.lgooddatepicker.components.DatePicker();
        fechaInicioDP = new com.github.lgooddatepicker.components.DatePicker();
        cbMetodoPagoAñadir = new javax.swing.JComboBox<>();
        btnQuitarMetodoPago = new javax.swing.JButton();
        btnAnadirMetodoPago = new javax.swing.JButton();
        cbMetodoPagoQuitar = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbSucursalAñadir = new javax.swing.JComboBox<>();
        cbSucursalQuitar = new javax.swing.JComboBox<>();
        btnAnadirSucursal = new javax.swing.JButton();
        btnQuitarSucursal = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnImprimir1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(36, 44, 99));

        tblReporteSucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Ciudad", "Sucursal", "Tipo de Pago", "Ganancia"
            }
        ));
        jScrollPane1.setViewportView(tblReporteSucursal);

        jLabel10.setText("Reporte de ganacias por Metodo de Pago");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        jLabel12.setText("Añadir Sucursal");
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

        btnMenuPelicula.setText("Peliculas");
        btnMenuPelicula.setBorderPainted(false);
        btnMenuPelicula.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuPelicula.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPeliculaActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuPelicula);

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

        btnMenuReporteSucursales.setText("Reporte Sucursal");
        btnMenuReporteSucursales.setBorderPainted(false);
        btnMenuReporteSucursales.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuReporteSucursales.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuReporteSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuReporteSucursalesActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuReporteSucursales);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel9);

        btnMenuReporteSucursales1.setText("Reporte Pelicula");
        btnMenuReporteSucursales1.setBorderPainted(false);
        btnMenuReporteSucursales1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuReporteSucursales1.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuReporteSucursales1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuReporteSucursales1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuReporteSucursales1);

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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        cbMetodoPagoAñadir.setBackground(new java.awt.Color(33, 36, 59));
        cbMetodoPagoAñadir.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbMetodoPagoAñadir.setForeground(new java.awt.Color(255, 255, 255));
        cbMetodoPagoAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMetodoPagoAñadirActionPerformed(evt);
            }
        });

        btnQuitarMetodoPago.setText("Quitar Metodo Pago");
        btnQuitarMetodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarMetodoPagoActionPerformed(evt);
            }
        });

        btnAnadirMetodoPago.setText("Añadir Metodo Pago");
        btnAnadirMetodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirMetodoPagoActionPerformed(evt);
            }
        });

        cbMetodoPagoQuitar.setBackground(new java.awt.Color(33, 36, 59));
        cbMetodoPagoQuitar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbMetodoPagoQuitar.setForeground(new java.awt.Color(255, 255, 255));
        cbMetodoPagoQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMetodoPagoQuitarActionPerformed(evt);
            }
        });

        jLabel13.setText("Añadir metodo pago");
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));

        cbSucursalAñadir.setBackground(new java.awt.Color(33, 36, 59));
        cbSucursalAñadir.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbSucursalAñadir.setForeground(new java.awt.Color(255, 255, 255));
        cbSucursalAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSucursalAñadirActionPerformed(evt);
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

        btnAnadirSucursal.setText("Añadir Sucursal");
        btnAnadirSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirSucursalActionPerformed(evt);
            }
        });

        btnQuitarSucursal.setText("Quitar Sucursal");
        btnQuitarSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarSucursalActionPerformed(evt);
            }
        });

        jLabel14.setText("Fecha inicio");
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));

        jLabel15.setText("Fecha fin");
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));

        btnImprimir1.setText("Imprimir ");
        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbMetodoPagoAñadir, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbMetodoPagoQuitar, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(55, 55, 55)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbSucursalQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbSucursalAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(57, 57, 57)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(fechaInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fechaFinDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(89, 89, 89)
                                        .addComponent(jLabel14))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(btnAnadirMetodoPago)
                                .addGap(148, 148, 148)
                                .addComponent(btnAnadirSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15)
                                .addGap(59, 59, 59)))
                        .addGap(191, 191, 191))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnImprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(340, 340, 340))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(btnQuitarMetodoPago)
                                .addGap(154, 154, 154)
                                .addComponent(btnQuitarSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(jLabel10)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbMetodoPagoAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSucursalAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAnadirSucursal)
                                    .addComponent(btnAnadirMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbMetodoPagoQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbSucursalQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnQuitarMetodoPago)
                                    .addComponent(btnQuitarSucursal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fechaFinDP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)))
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGenerarReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addContainerGap())
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

    private void btnMenuSalasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuSalasActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        ICiudadDAO ciudadDAO = new CiudadDAO(conexionBD);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        ISalaDAO salaDAO = new SalaDAO(conexionBD);
        ISalaBO salaBO = new SalaBO(salaDAO);

        FrmAdminSalas frmAdminSalas = new FrmAdminSalas(sucursalBO, ciudadBO, salaBO);
        frmAdminSalas.setVisible(true);
        this.dispose();              // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuSalasActionPerformed

    private void btnMenuSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuSucursalesActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        ICiudadDAO ciudadDAO = new CiudadDAO(conexionBD);
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexionBD);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);

        FrmAdminSucursal frmAdminSucursal = new FrmAdminSucursal(sucursalBO, ciudadBO, peliculaBO);
        frmAdminSucursal.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuSucursalesActionPerformed

    private void btnMenuFuncionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuFuncionesActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexionBD);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexionBD);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        FrmAdminEscogerFuncion escogerFuncion = new FrmAdminEscogerFuncion(sucursalBO, ciudadBO, peliculaBO);
        escogerFuncion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuFuncionesActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        if (fechaInicioDP.getDate() == null || fechaFinDP.getDate() == null) {
            JOptionPane.showMessageDialog(this, "No se selecciono ninguna fecha.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        listaMetodo.clear();
        for (int i = 0; i < cbMetodoPagoQuitar.getItemCount(); i++) {
            MetodosDePago metodoPago = (MetodosDePago) cbMetodoPagoQuitar.getItemAt(i);
            this.listaMetodo.add(metodoPago.toString());
        }
        listaMetodosPago = new ArrayList(listaMetodo);

        lista.clear();
        for (int i = 0; i < cbSucursalQuitar.getItemCount(); i++) {
            SucursalDTO sucursalDTO = cbSucursalQuitar.getItemAt(i);
            this.lista.add(sucursalDTO.getId());
        }
        listaSucursalesID = new ArrayList(lista);
        LocalDate fechaInicio = fechaInicioDP.getDate();
        LocalDate fechaFin = fechaFinDP.getDate();
        String fInicio = convertLocalDateToString(fechaInicio);
        String fFin = convertLocalDateToString(fechaFin);

        cargarDatosEnTabla(listaMetodosPago, listaSucursalesID, fInicio, fFin);

    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void btnMenuReporteSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReporteSucursalesActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        ICiudadDAO ciudadDAO = new CiudadDAO(conexionBD);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        IReportesSucursalesDAO reportesSucursalesDAO = new ReportesSucursalesDAO(conexionBD);
        IReportesSucursalesBO reportesSucursalesBO = new ReportesSucursalesBO(reportesSucursalesDAO);

        FrmReporteSucursales reporteSucursales = new FrmReporteSucursales(sucursalBO, ciudadBO, reportesSucursalesBO);
        reporteSucursales.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReporteSucursalesActionPerformed

    private void btnMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuClienteActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IClienteDAO clienteDAO = new ClienteDAO(conexionBD);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexionBD);

        IClienteBO clienteBO = new ClienteBO(clienteDAO);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);

        FrmAdminClientes frmAdminClientes = new FrmAdminClientes(ciudadBO, clienteBO);
        frmAdminClientes.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuClienteActionPerformed

    private void cbMetodoPagoAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMetodoPagoAñadirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMetodoPagoAñadirActionPerformed

    private void btnQuitarMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarMetodoPagoActionPerformed
        MetodosDePago metodoPago = (MetodosDePago) cbMetodoPagoQuitar.getSelectedItem();
        if (metodoPago != null) {
            cbMetodoPagoQuitar.removeItem(metodoPago);
            cbMetodoPagoAñadir.addItem(metodoPago);
        }
    }//GEN-LAST:event_btnQuitarMetodoPagoActionPerformed

    private void btnAnadirMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirMetodoPagoActionPerformed
        MetodosDePago metodoPago = (MetodosDePago) cbMetodoPagoAñadir.getSelectedItem();
        if (metodoPago != null) {
            cbMetodoPagoAñadir.removeItem(metodoPago);
            cbMetodoPagoQuitar.addItem(metodoPago);
        }
    }//GEN-LAST:event_btnAnadirMetodoPagoActionPerformed

    private void cbMetodoPagoQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMetodoPagoQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMetodoPagoQuitarActionPerformed

    private void cbSucursalAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSucursalAñadirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSucursalAñadirActionPerformed

    private void cbSucursalQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSucursalQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSucursalQuitarActionPerformed

    private void btnAnadirSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirSucursalActionPerformed
        SucursalDTO sucursal = (SucursalDTO) cbSucursalAñadir.getSelectedItem();
        if (sucursal != null) {
            cbSucursalAñadir.removeItem(sucursal);
            cbSucursalQuitar.addItem(sucursal);
        }

    }//GEN-LAST:event_btnAnadirSucursalActionPerformed

    private void btnQuitarSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarSucursalActionPerformed
        SucursalDTO sucursal = (SucursalDTO) cbSucursalQuitar.getSelectedItem();
        if (sucursal != null) {
            cbSucursalQuitar.removeItem(sucursal);
            cbSucursalAñadir.addItem(sucursal);
        }
     }//GEN-LAST:event_btnQuitarSucursalActionPerformed

    private void btnMenuPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPeliculaActionPerformed
        IConexionBD conexion = new ConexionBD();
        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        FrmAdminPeliculas frnAdminPeliculas = new FrmAdminPeliculas(sucursalBO, ciudadBO, peliculaBO);
        frnAdminPeliculas.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuPeliculaActionPerformed

    private void btnMenuReporteSucursales1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReporteSucursales1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuReporteSucursales1ActionPerformed

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
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
            PdfWriter.getInstance(doc, new FileOutputStream(String.format("%s/ReporteMetodosPago.pdf", path)));
            doc.open();

            // Descripción de los filtros
            doc.add(new Paragraph("Reporte de Ganancias por Peliculas y Ciudades", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            doc.add(new Paragraph("Filtros Aplicados:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            doc.add(new Paragraph("Fechas: " + fechaInicioDP.getText() + " a " + fechaFinDP.getText()));
            doc.add(new Paragraph("Sucursales: " + obtenerValoresSeparadosPorComaSucursales(cbSucursalQuitar))); // Asegúrate de convertir la lista a cadena
            doc.add(new Paragraph("Metodos Pago: " + obtenerValoresSeparadosPorComaMetodosPago(cbMetodoPagoQuitar))); // Asegúrate de convertir la lista a cadena
            doc.add(new Paragraph("\n")); // Espacio en blanco

            // Tabla
            PdfPTable tbl = new PdfPTable(5);
            tbl.addCell("Fecha");
            tbl.addCell("Ciudad");
            tbl.addCell("Sucursal");
            tbl.addCell("Metodo Pago");
            tbl.addCell("Ganancia");
            BigDecimal suma = BigDecimal.ZERO;
            for (int i = 0; i < tblReporteSucursal.getRowCount(); i++) {
                String fecha = tblReporteSucursal.getValueAt(i, 0).toString();
                String ciudad = tblReporteSucursal.getValueAt(i, 1).toString();
                String sucursal = tblReporteSucursal.getValueAt(i, 2).toString();
                String metodoPago = tblReporteSucursal.getValueAt(i, 3).toString();
                String ganancia = tblReporteSucursal.getValueAt(i, 4).toString();
                suma = suma.add(convertToBigDecimal(tblReporteSucursal.getValueAt(i, 4)));
                tbl.addCell(fecha);
                tbl.addCell(ciudad);
                tbl.addCell(sucursal);
                tbl.addCell(metodoPago);
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
    }//GEN-LAST:event_btnImprimir1ActionPerformed

    private void btnMenuReportePagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReportePagosActionPerformed

        FrmReporteMetodoPago reporteMetodoPago = new FrmReporteMetodoPago(reporteMetodoPagoBO, sucursalBO);
        reporteMetodoPago.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReportePagosActionPerformed
    private void llenarComboBoxSucursales() {
        try {
            listaSucursales = sucursalBO.obtenerSucursales();

            for (SucursalDTO sucursal : listaSucursales) {
                cbSucursalAñadir.addItem(sucursal);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCatalogoSucursal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void llenarComboMetodosPago() {
        for (MetodosDePago metodosDePago : MetodosDePago.values()) {
            cbMetodoPagoAñadir.addItem(metodosDePago);
        }
    }

    private String convertLocalDateToString(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("The date cannot be null.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    private String convertLocalDateTimeToString(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("The date cannot be null.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }

    private String obtenerValoresSeparadosPorComaSucursales(JComboBox<SucursalDTO> comboBox) {
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

    private String obtenerValoresSeparadosPorComaMetodosPago(JComboBox<Object> comboBox) {
        StringBuilder valores = new StringBuilder();

        // Iterar sobre todos los elementos del comboBox
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            MetodosDePago item = (MetodosDePago) comboBox.getItemAt(i);
            valores.append(item.toString());

            // Añadir una coma si no es el último elemento
            if (i < comboBox.getItemCount() - 1) {
                valores.append(", ");
            }
        }

        return valores.toString();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnadirMetodoPago;
    private javax.swing.JButton btnAnadirSucursal;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnImprimir1;
    private utilerias.MenuButton btnMenuCliente;
    private utilerias.MenuButton btnMenuFunciones;
    private utilerias.MenuButton btnMenuPelicula;
    private utilerias.MenuButton btnMenuReportePagos;
    private utilerias.MenuButton btnMenuReporteSucursales;
    private utilerias.MenuButton btnMenuReporteSucursales1;
    private utilerias.MenuButton btnMenuSalas;
    private utilerias.MenuButton btnMenuSucursales;
    private javax.swing.JButton btnQuitarMetodoPago;
    private javax.swing.JButton btnQuitarSucursal;
    private javax.swing.JComboBox<Object> cbMetodoPagoAñadir;
    private javax.swing.JComboBox<Object> cbMetodoPagoQuitar;
    private javax.swing.JComboBox<SucursalDTO> cbSucursalAñadir;
    private javax.swing.JComboBox<SucursalDTO> cbSucursalQuitar;
    private com.github.lgooddatepicker.components.DatePicker fechaFinDP;
    private com.github.lgooddatepicker.components.DatePicker fechaInicioDP;
    private utilerias.ImagenPerfiles imagenPerfiles1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
