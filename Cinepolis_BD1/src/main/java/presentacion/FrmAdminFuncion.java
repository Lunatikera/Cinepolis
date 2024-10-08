/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.CiudadDTO;
import dtos.FiltroFuncionesDTO;
import dtos.FuncionTablaDTO;
import dtos.PeliculaDTO;
import dtos.SalaDTO;
import dtos.SucursalDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.CiudadBO;
import negocio.ClienteBO;
import negocio.ICiudadBO;
import negocio.IClienteBO;
import negocio.IFuncionBO;
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
import persistencia.IReportePeliculaDAO;
import persistencia.IReportesSucursalesDAO;
import persistencia.ISalaDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.ReporteMetodoPagoDAO;
import persistencia.ReportePeliculaDAO;
import persistencia.ReportesSucursalesDAO;
import persistencia.SalaDAO;
import persistencia.SucursalDAO;
import utilerias.Dias;
import static utilerias.Dias.obtenerDiaSiguiente;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author carli
 */
public class FrmAdminFuncion extends javax.swing.JFrame {

    private IFuncionBO funcionBO;
    private ISalaBO salaBO;
    private List<SalaDTO> listaSalas;
    private SucursalDTO sucursal;
    private PeliculaDTO pelicula;
    private FiltroFuncionesDTO filtro;
    private SalaDTO sala;

    /**
     * Creates new form FrmAdminFuncion
     */
    public FrmAdminFuncion(IFuncionBO funcionBO, ISalaBO salaBO, SucursalDTO sucursal, PeliculaDTO pelicula) {
        initComponents();
        this.funcionBO = funcionBO;
        this.salaBO = salaBO;
        this.sucursal = sucursal;
        this.pelicula = pelicula;
        this.cargarMetodosIniciales();
    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.llenarComboBoxSalas();
        this.cbSalas.setSelectedIndex(0);
        this.sala = (SalaDTO) cbSalas.getSelectedItem();
        this.lblPelicula.setText(utilerias.Herramientas.textoConSaltosLinea(pelicula.getTitulo(), 5));
        this.lblSucursal.setText(sucursal.getNombre());
        this.lblDia.setText(this.obtenerDiaActual());
        this.cargarConfiguracionInicialTablaPeliculas();
        this.filtro = new FiltroFuncionesDTO(this.obtenerDiaActual(), sala.getId());

        this.cargarFuncionesTabla();
        this.configuracionFrame();
    }

    public void configuracionFrame() {
        this.setTitle("Administracion de Funciones " + sucursal.getNombre());
        this.setResizable(false);
        this.setSize(1280, 780);
        this.setLocationRelativeTo(null);
    }

    public void cargarFuncionesTabla() {
        try {
            System.out.println(filtro.getDia() + " ," + filtro.getIdSala());
            List<FuncionTablaDTO> peliculaLista = this.funcionBO.listaFuncionporDiaSala(filtro);
            this.llenarTablaPeliculas(peliculaLista);
            if (peliculaLista == null) {
                JOptionPane.showMessageDialog(null, "No hay ninguna pelicula registrada", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(FrmAdminFuncion.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    private void cargarConfiguracionInicialTablaPeliculas() {
        ActionListener onEditarClickListener = new ActionListener() {

            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para editar un cliente

                eliminar();
            }
        };
        int indiceColumnaEditar = 6;
        TableColumnModel modeloColumnas = this.tblFunciones.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("Eliminar",
                        onEditarClickListener));

    }

    private void llenarTablaPeliculas(List<FuncionTablaDTO> peliculaLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblFunciones.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (peliculaLista != null) {
            peliculaLista.forEach(row
                    -> {
                Object[] fila = new Object[6];
                fila[0] = row.getId();
                fila[1] = row.getPeliculaTitulo();
                fila[2] = row.getDuracion();
                fila[3] = row.getHora();
                fila[4] = row.getHoraFinal();
                fila[5] = row.getPrecio();

                modeloTabla.addRow(fila);
            });
        }
    }

    private int getIdSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblFunciones.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblFunciones.getModel();
            int indiceColumnaId = 0;
            int idSocioSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return 0;
        }
    }

    private void eliminar() {
        int id = this.getIdSeleccionadoTablaClientes();
        if (id == 0) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una pelicula", "Información", JOptionPane.ERROR_MESSAGE);
            try {
                funcionBO.eliminarPorID(id);
                JOptionPane.showMessageDialog(this, "Funcion eliminada correctamente");

            } catch (NegocioException ex) {
                Logger.getLogger(FrmAdminFuncion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//            PeliculaDTO pelicula = peliculaBO.buscarPeliculaPorId(id);
    }

    private void llenarComboBoxSalas() {
        try {
            listaSalas = salaBO.salasPorSucursal(sucursal.getId());
            for (SalaDTO sala : listaSalas) {
                cbSalas.addItem(sala);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCatalogoSucursal.class.getName()).log(Level.SEVERE, null, ex);
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
        btnIr = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFunciones = new javax.swing.JTable();
        lblSucursal = new javax.swing.JLabel();
        lblDia = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        cbSalas = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        imagenPerfiles1 = new utilerias.ImagenPerfiles();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnMenuCliente = new utilerias.MenuButton();
        jLabel3 = new javax.swing.JLabel();
        btnmenuPelicula = new utilerias.MenuButton();
        jLabel4 = new javax.swing.JLabel();
        btnMenuSalas = new utilerias.MenuButton();
        jLabel5 = new javax.swing.JLabel();
        btnMenuSucursales = new utilerias.MenuButton();
        jLabel6 = new javax.swing.JLabel();
        btnMenuFunciones = new utilerias.MenuButton();
        jLabel8 = new javax.swing.JLabel();
        btnMenuReporteSucursal = new utilerias.MenuButton();
        jLabel9 = new javax.swing.JLabel();
        btnMenuReportePelicula = new utilerias.MenuButton();
        jLabel10 = new javax.swing.JLabel();
        btnMenuReportePagos = new utilerias.MenuButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblPelicula = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(36, 44, 99));

        btnIr.setText("Ir");
        btnIr.setBorderPainted(false);
        btnIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrActionPerformed(evt);
            }
        });

        tblFunciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Pelicula", "Duracion (min)", "Hora Inicio", "Hora Final", "Precio", "Eliminar"
            }
        ));
        jScrollPane1.setViewportView(tblFunciones);

        lblSucursal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSucursal.setForeground(new java.awt.Color(255, 255, 255));
        lblSucursal.setText("Sucursal Bella Vista");

        lblDia.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblDia.setForeground(new java.awt.Color(255, 255, 255));
        lblDia.setText("Lunes");

        btnAtras.setText("atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnSiguiente.setText("siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        cbSalas.setBackground(new java.awt.Color(33, 36, 59));
        cbSalas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbSalas.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(33, 36, 59));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 720));

        imagenPerfiles1.setImagen(new javax.swing.ImageIcon(getClass().getResource("/imagenes/littlelogoGrande 1.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(33, 36, 59));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel2);

        btnMenuCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuCliente.setText("Clientes");
        btnMenuCliente.setBorderPainted(false);
        btnMenuCliente.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuClienteActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuCliente);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel3);

        btnmenuPelicula.setForeground(new java.awt.Color(255, 255, 255));
        btnmenuPelicula.setText("Peliculas");
        btnmenuPelicula.setBorderPainted(false);
        btnmenuPelicula.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnmenuPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmenuPeliculaActionPerformed(evt);
            }
        });
        jPanel4.add(btnmenuPelicula);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel4);

        btnMenuSalas.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuSalas.setText("Salas");
        btnMenuSalas.setBorderPainted(false);
        btnMenuSalas.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuSalas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuSalasActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuSalas);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel5);

        btnMenuSucursales.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuSucursales.setText("Sucursales");
        btnMenuSucursales.setBorderPainted(false);
        btnMenuSucursales.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuSucursalesActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuSucursales);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel6);

        btnMenuFunciones.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuFunciones.setText("Funciones");
        btnMenuFunciones.setBorderPainted(false);
        btnMenuFunciones.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuFunciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuFuncionesActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuFunciones);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel8);

        btnMenuReporteSucursal.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuReporteSucursal.setText("Reporte Sucursal");
        btnMenuReporteSucursal.setBorderPainted(false);
        btnMenuReporteSucursal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuReporteSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuReporteSucursalActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuReporteSucursal);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel9);

        btnMenuReportePelicula.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuReportePelicula.setText("Reporte Pelicula");
        btnMenuReportePelicula.setBorderPainted(false);
        btnMenuReportePelicula.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuReportePelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuReportePeliculaActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuReportePelicula);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel10);

        btnMenuReportePagos.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuReportePagos.setText("Reporte Pagos");
        btnMenuReportePagos.setBorderPainted(false);
        btnMenuReportePagos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuReportePagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuReportePagosActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuReportePagos);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lineaBlanca.png"))); // NOI18N
        jPanel4.add(jLabel12);

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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Seleccionar Sala");

        lblPelicula.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblPelicula.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula.setText("Top Gun Maverick");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Line 7.png"))); // NOI18N

        btnAgregar.setText("Agregar Funcion");
        btnAgregar.setBorderPainted(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnIr)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPelicula))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addGap(145, 145, 145)
                                .addComponent(lblSucursal))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(238, 238, 238)
                                .addComponent(btnAtras)
                                .addGap(44, 44, 44)
                                .addComponent(lblDia)
                                .addGap(54, 54, 54)
                                .addComponent(btnSiguiente)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSalas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIr)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblPelicula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSucursal)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDia)
                    .addComponent(btnSiguiente)
                    .addComponent(btnAtras))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        ISalaDAO salaDAO = new SalaDAO(conexionBD);
        ISalaBO salaBO = new SalaBO(salaDAO);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexionBD);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);

        FrmAdminSalas frmAdminSalas = new FrmAdminSalas(sucursalBO, ciudadBO, salaBO);
        frmAdminSalas.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuSalasActionPerformed

    private void btnMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuClienteActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IClienteDAO clienteDAO = new ClienteDAO(conexionBD);
        IClienteBO clienteBO = new ClienteBO(clienteDAO);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexionBD);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        FrmAdminClientes frmAdminClientes = new FrmAdminClientes(ciudadBO, clienteBO);
        frmAdminClientes.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuClienteActionPerformed

    private void btnMenuSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuSucursalesActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexionBD);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexionBD);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);

        FrmAdminSucursal frmAdminSucursal = new FrmAdminSucursal(sucursalBO, ciudadBO, peliculaBO);
        frmAdminSucursal.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuSucursalesActionPerformed

    private void btnMenuFuncionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuFuncionesActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexionBD);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexionBD);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);

        FrmAdminEscogerFuncion escogerFuncion = new FrmAdminEscogerFuncion(sucursalBO, ciudadBO, peliculaBO);
        escogerFuncion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuFuncionesActionPerformed

    private void btnMenuReporteSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReporteSucursalActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IReportesSucursalesDAO reportesSucursalesDAO = new ReportesSucursalesDAO(conexionBD);
        IReportesSucursalesBO reportesSucursalesBO = new ReportesSucursalesBO(reportesSucursalesDAO);
        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexionBD);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);

        FrmReporteSucursales reporteSucursales = new FrmReporteSucursales(sucursalBO, ciudadBO, reportesSucursalesBO);
        reporteSucursales.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReporteSucursalActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        lblDia.setText(Dias.obtenerDiaAnterior(lblDia.getText()));
        this.filtro.setDia(lblDia.getText());
        System.out.println(filtro.getDia());
        this.cargarFuncionesTabla();

    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        lblDia.setText(obtenerDiaSiguiente(lblDia.getText()));
        this.filtro.setDia(lblDia.getText());
        this.cargarFuncionesTabla();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrActionPerformed
        if (cbSalas.getSelectedItem() != null) {
            sala = (SalaDTO) cbSalas.getSelectedItem();
            this.filtro.setIdSala(sala.getId());
            this.cargarFuncionesTabla();
        }
    }//GEN-LAST:event_btnIrActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        FrmAgregarFuncion agregarFuncion = new FrmAgregarFuncion(funcionBO, pelicula, sala, sucursal);
        agregarFuncion.setVisible(true);
        this.dispose();


    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnmenuPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmenuPeliculaActionPerformed
        // TODO add your handling code here:
        IConexionBD conexion = new ConexionBD();
        ISucursalDAO sucursalDAO = new SucursalDAO(conexion);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        FrmAdminPeliculas frnAdminPeliculas = new FrmAdminPeliculas(sucursalBO, ciudadBO, peliculaBO);
        frnAdminPeliculas.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnmenuPeliculaActionPerformed

    private void btnMenuReportePeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReportePeliculaActionPerformed
        IConexionBD conexion = new ConexionBD();
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        IReportePeliculaDAO reportePeliculaDAO = new ReportePeliculaDAO(conexion);
        IReportePeliculaBO reportePeliculaBO = new ReportePeliculaBO(reportePeliculaDAO);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        FrmReportePelicula reportePelicula = new FrmReportePelicula(reportePeliculaBO, ciudadBO, peliculaBO);
        reportePelicula.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReportePeliculaActionPerformed

    private void btnMenuReportePagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReportePagosActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        ISucursalDAO sucursalDAO = new SucursalDAO(conexionBD);
        IReporteMetodoPagoDAO reporteMetodoPagoDAO = new ReporteMetodoPagoDAO(conexionBD);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        IReporteMetodoPagoBO reporteMetodoPagoBO = new ReporteMetodoPago(reporteMetodoPagoDAO);
        FrmReporteMetodoPago reporteMetodoPago = new FrmReporteMetodoPago(reporteMetodoPagoBO, sucursalBO);
        reporteMetodoPago.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReportePagosActionPerformed
    private String obtenerDiaActual() {
        LocalDate hoy = LocalDate.now();
        DayOfWeek dia = hoy.getDayOfWeek();
        String nombreDia = dia.getDisplayName(TextStyle.FULL, Locale.getDefault());
        String nombreDiaFormato = nombreDia.substring(0, 1).toUpperCase() + nombreDia.substring(1);
        return nombreDiaFormato;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnIr;
    private utilerias.MenuButton btnMenuCliente;
    private utilerias.MenuButton btnMenuFunciones;
    private utilerias.MenuButton btnMenuReportePagos;
    private utilerias.MenuButton btnMenuReportePelicula;
    private utilerias.MenuButton btnMenuReporteSucursal;
    private utilerias.MenuButton btnMenuSalas;
    private utilerias.MenuButton btnMenuSucursales;
    private javax.swing.JButton btnSiguiente;
    private utilerias.MenuButton btnmenuPelicula;
    private javax.swing.JComboBox<SalaDTO> cbSalas;
    private utilerias.ImagenPerfiles imagenPerfiles1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel lblDia;
    private javax.swing.JLabel lblPelicula;
    private javax.swing.JLabel lblSucursal;
    private javax.swing.JTable tblFunciones;
    // End of variables declaration//GEN-END:variables
}
