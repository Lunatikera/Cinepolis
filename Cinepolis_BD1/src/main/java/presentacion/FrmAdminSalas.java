/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.CiudadDTO;
import dtos.SalaDTO;
import dtos.SucursalDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.CiudadBO;
import negocio.ClienteBO;
import negocio.ICiudadBO;
import negocio.IClienteBO;
import negocio.IPeliculaBO;
import negocio.IReportePeliculaBO;
import negocio.IReportesSucursalesBO;
import negocio.ISalaBO;
import negocio.ISucursalBO;
import negocio.NegocioException;
import negocio.PeliculaBO;
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
import persistencia.IReportePeliculaDAO;
import persistencia.IReportesSucursalesDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.ReportePeliculaDAO;
import persistencia.ReportesSucursalesDAO;
import persistencia.SucursalDAO;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author Samoano
 */
public class FrmAdminSalas extends javax.swing.JFrame {

    private int pagina = 1;
    private final int LIMITE = 20;
    private ISucursalBO sucursalBO;
    private ICiudadBO ciudadBO;
    //private IPeliculaBO peliculaBO;
    private List<CiudadDTO> listaCiudades;
    private List<SucursalDTO> listaSucursales;
    //private List<PeliculaDTO> peliculasPorAnadir;
    private SucursalDTO sucursal;
    private boolean pelicuaEnSucursal = true;
    private ISalaBO salaBO;

    /**
     * Creates new form FrmAdminFuncion
     */
    public FrmAdminSalas(ISucursalBO sucursalBO, ICiudadBO ciudadBO, ISalaBO salaBO) {
        initComponents();
        this.sucursalBO = sucursalBO;
        this.ciudadBO = ciudadBO;
        this.salaBO = salaBO;
        this.sucursal = new SucursalDTO();
        cargarMetodosIniciales();
    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.setTitle("Administracion de Salas ");
        this.setResizable(false);
        this.setSize(1280, 780);
        this.setLocationRelativeTo(null);
        this.llenarComboBoxCiudad();
        this.cbCiudades.setSelectedIndex(1);
        this.actualizarComboBoxCiudad();
        this.cbSucursales.setSelectedIndex(0);

        this.sucursal = (SucursalDTO) cbSucursales.getSelectedItem();
        System.out.println(sucursal);
        this.seleccionarCiudadYSucursal();
        lblSucursal.setText(sucursal.getNombre());
        this.cargarConfiguracionInicialTablaPeliculas();
        this.cargarClientesEnTabla(sucursal.getId(), pagina, LIMITE);
        this.estadoPagina();
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
        int indiceColumnaEditar = 4;
        TableColumnModel modeloColumnas = this.tblPeliculas.getColumnModel();
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
                editarSala();
            }
        };
        int indiceColumnaEliminar = 5;
        modeloColumnas = this.tblPeliculas.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Editar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Editar",
                        onEliminarClickListener));
    }

    private void BorrarRegistrosTablaClientes() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblPeliculas.getModel();
        if (modeloTabla.getRowCount() > 0) {
            for (int row = modeloTabla.getRowCount() - 1; row > -1; row--) {
                modeloTabla.removeRow(row);
            }
        }
    }

    private void cargarClientesEnTabla(int idSucursal, int pagina, int limite) {
        try {
            // Borrar registros previos antes de cargar los nuevos
            BorrarRegistrosTablaClientes();

            // Calcula el offset (inicio de los registros)
            int offset = (pagina - 1) * limite;

            // Obtén solo los clientes necesarios para la página actual
            List<SalaDTO> clientesLista = this.salaBO.paginadoSalasPorSucursal(idSucursal, limite, pagina);

            // Agrega los registros paginados a la tabla
            this.AgregarRegistrosTablaSalas(clientesLista);

            // Control de botones de navegación
            btnAtras.setEnabled(pagina > 1);

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void AgregarRegistrosTablaSalas(List<SalaDTO> salaLista) {
        if (salaLista == null) {
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblPeliculas.getModel();
        salaLista.forEach(row
                -> {
            Object[] fila = new Object[4];
            fila[0] = row.getId();
            fila[1] = row.getNombre();
            fila[2] = row.getNumeroAsiento();
            fila[3] = row.getDuracionLimpieza();
            modeloTabla.addRow(fila);
        });
    }

    private int getIdSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblPeliculas.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblPeliculas.getModel();
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
            JOptionPane.showMessageDialog(this, "Por favor selecciona una sala", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar la sala seleccionado?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                this.salaBO.eliminarPorId(id);
                // Recargar la tabla después de eliminar
                cargarClientesEnTabla(sucursal.getId(), pagina, LIMITE);
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void editarSala() {
        int id = this.getIdSeleccionadoTablaClientes();
        if (id == 0) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una sala", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            System.out.println("El id para editar es " + id);
            FrmEditarSala editarSala = new FrmEditarSala(this, this.salaBO, id, this.sucursal.getId());
            editarSala.setVisible(true);
            cargarClientesEnTabla(sucursal.getId(), pagina, LIMITE);
            estadoPagina();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmAdminSalas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void estadoPagina() {
        String numPagina = String.valueOf(pagina);
        if (numPagina.length() == 1) {
            numPagina = "0" + numPagina;
        }

        lblPagina.setText("Pagina " + numPagina);
        estatusBotonAtras();
        estatusBotonSiguiente();
    }

    private void estatusBotonAtras() {
        if (pagina > 1) {
            btnAtras.setEnabled(true);
            return;
        }
        btnAtras.setEnabled(false);
    }

    private void estatusBotonSiguiente() {

        try {
            btnSiguiente.setEnabled(true);
            if (this.salaBO.paginadoSalasPorSucursal(sucursal.getId(), LIMITE, pagina + 1) == null
                    || this.salaBO.paginadoSalasPorSucursal(sucursal.getId(), LIMITE, pagina + 1).isEmpty()) {
                btnSiguiente.setEnabled(false);
            }
        } catch (NegocioException ex) {
            System.out.println(ex);
        }
    }

    private void seleccionarCiudadYSucursal() {
        // Obtener la ciudad correspondiente a la sucursal más cercana
        CiudadDTO ciudadSeleccionada = null;

        for (CiudadDTO ciudad : listaCiudades) {
            // Verificar si la ciudad contiene la sucursal
            List<SucursalDTO> sucursalesCiudad;
            try {
                sucursalesCiudad = sucursalBO.listaSucursalesporCiudad(ciudad.getId());

                if (sucursalesCiudad.contains(sucursal)) {
                    ciudadSeleccionada = ciudad;
                    break;
                }
            } catch (NegocioException ex) {
                Logger.getLogger(FrmCatalogoSucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ciudadSeleccionada != null) {
            // Seleccionar la ciudad en el combo box
            cbCiudades.setSelectedItem(ciudadSeleccionada);
            cbCiudades.setSelectedItem(ciudadSeleccionada);
            // Actualizar el combo box de sucursales con las sucursales de esa ciudad
            actualizarComboBoxCiudad();

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
        cbCiudades = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnIr = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeliculas = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        lblPagina = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        cbSucursales = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        imagenPerfiles1 = new utilerias.ImagenPerfiles();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnMenuCliente = new utilerias.MenuButton();
        jLabel3 = new javax.swing.JLabel();
        btnMenuPelicula = new utilerias.MenuButton();
        jLabel4 = new javax.swing.JLabel();
        menuButton1 = new utilerias.MenuButton();
        jLabel5 = new javax.swing.JLabel();
        btnMenuSucursales = new utilerias.MenuButton();
        jLabel6 = new javax.swing.JLabel();
        btnMenuFunciones = new utilerias.MenuButton();
        jLabel8 = new javax.swing.JLabel();
        btnMenuReporteSucursales = new utilerias.MenuButton();
        jLabel9 = new javax.swing.JLabel();
        btnMenuReportePelicula = new utilerias.MenuButton();
        jLabel12 = new javax.swing.JLabel();
        BtnAgregarSala = new javax.swing.JButton();
        lblSucursal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(36, 44, 99));

        cbCiudades.setBackground(new java.awt.Color(33, 36, 59));
        cbCiudades.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbCiudades.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/locacion.png"))); // NOI18N

        btnIr.setText("Ir");
        btnIr.setBorderPainted(false);
        btnIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrActionPerformed(evt);
            }
        });

        tblPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Numero de asientos", "Duracion de limpieza", "Remover Sala", "Editar Sala"
            }
        ));
        jScrollPane1.setViewportView(tblPeliculas);

        jLabel10.setText("Salas");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        lblPagina.setText("Pagina 01");
        lblPagina.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblPagina.setForeground(new java.awt.Color(255, 255, 255));

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

        cbSucursales.setBackground(new java.awt.Color(33, 36, 59));
        cbSucursales.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbSucursales.setForeground(new java.awt.Color(255, 255, 255));

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

        btnMenuPelicula.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuPelicula.setText("Peliculas");
        btnMenuPelicula.setBorderPainted(false);
        btnMenuPelicula.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPeliculaActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuPelicula);

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

        btnMenuReporteSucursales.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuReporteSucursales.setText("Reporte Sucursal");
        btnMenuReporteSucursales.setBorderPainted(false);
        btnMenuReporteSucursales.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnMenuReporteSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuReporteSucursalesActionPerformed(evt);
            }
        });
        jPanel4.add(btnMenuReporteSucursales);

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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        BtnAgregarSala.setText("Agregar Sala");
        BtnAgregarSala.setBackground(new java.awt.Color(204, 204, 204));
        BtnAgregarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarSalaActionPerformed(evt);
            }
        });

        lblSucursal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblSucursal.setForeground(new java.awt.Color(255, 255, 255));
        lblSucursal.setText("Sucursal Bella Vista");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(BtnAgregarSala)
                                .addGap(188, 188, 188)
                                .addComponent(lblSucursal))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(238, 238, 238)
                                .addComponent(btnAtras)
                                .addGap(44, 44, 44)
                                .addComponent(lblPagina)
                                .addGap(54, 54, 54)
                                .addComponent(btnSiguiente)))
                        .addContainerGap(161, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnIr))
                            .addComponent(cbSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(cbCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(btnIr)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregarSala)
                    .addComponent(lblSucursal))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPagina)
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

    private void menuButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton1ActionPerformed

    private void btnMenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuClienteActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IClienteDAO clienteDAO = new ClienteDAO(conexionBD);
        IClienteBO clienteBO = new ClienteBO(clienteDAO);
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

        FrmAdminSucursal frmAdminSucursal = new FrmAdminSucursal(sucursalBO, ciudadBO, peliculaBO);
        frmAdminSucursal.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuSucursalesActionPerformed

    private void btnMenuFuncionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuFuncionesActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexionBD);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);

        FrmAdminEscogerFuncion escogerFuncion = new FrmAdminEscogerFuncion(sucursalBO, ciudadBO, peliculaBO);
        escogerFuncion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuFuncionesActionPerformed

    private void btnIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrActionPerformed
        if (cbCiudades.getSelectedItem() != null) {
            this.sucursal = (SucursalDTO) cbSucursales.getSelectedItem();
            this.pagina = 1;
            lblSucursal.setText(sucursal.getNombre());
            this.cargarClientesEnTabla(sucursal.getId(), pagina, LIMITE);
            this.estadoPagina();
            return;
        }
        JOptionPane.showMessageDialog(this, "Error al buscar el catalogo, Intente de nuevo ", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnIrActionPerformed

    private void btnMenuReporteSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReporteSucursalesActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IReportesSucursalesDAO reportesSucursalesDAO = new ReportesSucursalesDAO(conexionBD);
        IReportesSucursalesBO reportesSucursalesBO = new ReportesSucursalesBO(reportesSucursalesDAO);

        FrmReporteSucursales reporteSucursales = new FrmReporteSucursales(sucursalBO, ciudadBO, reportesSucursalesBO);
        reporteSucursales.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReporteSucursalesActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        pagina--;
        this.estadoPagina();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        pagina++;
        this.estadoPagina();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void BtnAgregarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarSalaActionPerformed
        FrmAgregarSala agregar = new FrmAgregarSala(this, this.salaBO, this.sucursal.getId());
        agregar.setVisible(true);
        cargarClientesEnTabla(sucursal.getId(), pagina, LIMITE);
        estadoPagina();

    }//GEN-LAST:event_BtnAgregarSalaActionPerformed

    private void btnMenuReportePeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReportePeliculaActionPerformed
         IConexionBD conexion = new ConexionBD();
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        IReportePeliculaDAO reportePeliculaDAO = new ReportePeliculaDAO(conexion);
        IReportePeliculaBO reportePeliculaBO = new ReportePeliculaBO(reportePeliculaDAO);
        FrmReportePelicula reportePelicula = new FrmReportePelicula(reportePeliculaBO, ciudadBO, peliculaBO);
        reportePelicula.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReportePeliculaActionPerformed

    private void btnMenuPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPeliculaActionPerformed
        IConexionBD conexion = new ConexionBD();
        ISucursalDAO sucursalDAO = new SucursalDAO(conexion);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        FrmAdminPeliculas frnAdminPeliculas = new FrmAdminPeliculas(sucursalBO, ciudadBO, peliculaBO);
        frnAdminPeliculas.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuPeliculaActionPerformed

    private void llenarComboBoxCiudad() {
        try {
            listaCiudades = ciudadBO.listaCiudades();

            for (CiudadDTO ciudad : listaCiudades) {
                cbCiudades.addItem(ciudad);
            }
        } catch (NegocioException ex) {
            Logger.getLogger(FrmCatalogoSucursal.class.getName()).log(Level.SEVERE, null, ex);
        }

        cbCiudades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarComboBoxCiudad();
            }
        });
    }

    private void actualizarComboBoxCiudad() {
        try {
            CiudadDTO ciudad = (CiudadDTO) cbCiudades.getSelectedItem();
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
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarSala;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnIr;
    private utilerias.MenuButton btnMenuCliente;
    private utilerias.MenuButton btnMenuFunciones;
    private utilerias.MenuButton btnMenuPelicula;
    private utilerias.MenuButton btnMenuReportePelicula;
    private utilerias.MenuButton btnMenuReporteSucursales;
    private utilerias.MenuButton btnMenuSucursales;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<CiudadDTO> cbCiudades;
    private javax.swing.JComboBox<SucursalDTO> cbSucursales;
    private utilerias.ImagenPerfiles imagenPerfiles1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel lblPagina;
    private javax.swing.JLabel lblSucursal;
    private utilerias.MenuButton menuButton1;
    private javax.swing.JTable tblPeliculas;
    // End of variables declaration//GEN-END:variables
}
