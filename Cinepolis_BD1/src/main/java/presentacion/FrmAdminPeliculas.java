/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.CiudadDTO;
import dtos.PeliculaDTO;
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
import negocio.ClienteBO;
import negocio.GeneroBO;
import negocio.ICiudadBO;
import negocio.IClienteBO;
import negocio.IGeneroBO;
import negocio.IPeliculaBO;
import negocio.IReportesSucursalesBO;
import negocio.ISalaBO;
import negocio.ISucursalBO;
import negocio.NegocioException;
import negocio.PeliculaBO;
import negocio.ReportesSucursalesBO;
import negocio.SalaBO;
import negocio.SucursalBO;
import persistencia.ClienteDAO;
import persistencia.ConexionBD;
import persistencia.GeneroDAO;
import persistencia.IClienteDAO;
import persistencia.IConexionBD;
import persistencia.IGeneroDAO;
import persistencia.IPeliculaDAO;
import persistencia.IReportesSucursalesDAO;
import persistencia.ISalaDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.ReportesSucursalesDAO;
import persistencia.SalaDAO;
import persistencia.SucursalDAO;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author Samoano
 */
public class FrmAdminPeliculas extends javax.swing.JFrame {

    private int pagina = 1;
    private final int LIMITE = 20;
    private ISucursalBO sucursalBO;
    private ICiudadBO ciudadBO;
    private IPeliculaBO peliculaBO;
    private List<CiudadDTO> listaCiudades;
    private List<SucursalDTO> listaSucursales;
    //private List<PeliculaDTO> peliculasPorAnadir;
    private boolean pelicuaEnSucursal = true;

    /**
     * Creates new form FrmAdminFuncion
     */
    public FrmAdminPeliculas(ISucursalBO sucursalBO, ICiudadBO ciudadBO, IPeliculaBO peliculaBO) {
        initComponents();
        this.sucursalBO = sucursalBO;
        this.ciudadBO = ciudadBO;
        this.peliculaBO = peliculaBO;
        cargarMetodosIniciales();
    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.cargarConfiguracionInicialTablaPeliculas();
        this.setTitle("Administracion de Peliculas ");
        this.setResizable(false);
        this.setSize(1280, 780);
        this.setLocationRelativeTo(null);
        this.cargarClientesEnTabla(pagina, LIMITE);
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

    private void cargarClientesEnTabla(int pagina, int limite) {
        try {
            // Borrar registros previos antes de cargar los nuevos
            BorrarRegistrosTablaClientes();

            // Calcula el offset (inicio de los registros)
            int offset = (pagina - 1) * limite;

            // Obtén solo los clientes necesarios para la página actual
            List<PeliculaDTO> clientesLista = this.peliculaBO.buscarPaginadoPeliculas(limite, pagina);

            // Agrega los registros paginados a la tabla
            this.AgregarRegistrosTablaSalas(clientesLista);

            // Control de botones de navegación
            btnAtras.setEnabled(pagina > 1);

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void AgregarRegistrosTablaSalas(List<PeliculaDTO> salaLista) {
        if (salaLista == null) {
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblPeliculas.getModel();
        salaLista.forEach(row
                -> {
            Object[] fila = new Object[4];
            fila[0] = row.getId();
            fila[1] = row.getTitulo();
            fila[2] = row.getPais();
            fila[3] = row.getDuracion();
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
            JOptionPane.showMessageDialog(this, "Por favor selecciona una pelicula", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar la pelicula seleccionada?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                this.peliculaBO.eliminarPelicula(id);
                // Recargar la tabla después de eliminar
                cargarClientesEnTabla(pagina, LIMITE);
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void editarSala() {
        int id = this.getIdSeleccionadoTablaClientes();
        if (id == 0) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona una pelicula", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            PeliculaDTO pelicula = peliculaBO.buscarPeliculaPorId(id);
            IConexionBD conexionBD = new ConexionBD();
            IGeneroDAO generoDAO = new GeneroDAO(conexionBD);
            IGeneroBO generoBO = new GeneroBO(generoDAO);
            FrmEditarPelicula editarSala = new FrmEditarPelicula(this.peliculaBO, generoBO, pelicula);
            editarSala.setVisible(true);
            this.dispose();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmAdminPeliculas.class.getName()).log(Level.SEVERE, null, ex);
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
            if (this.peliculaBO.buscarPaginadoPeliculas(LIMITE, pagina + 1) == null
                    || this.peliculaBO.buscarPaginadoPeliculas(LIMITE, pagina + 1).isEmpty()) {
                btnSiguiente.setEnabled(false);
            }
        } catch (NegocioException ex) {
            System.out.println(ex);
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
        tblPeliculas = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        lblPagina = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        imagenPerfiles1 = new utilerias.ImagenPerfiles();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnMenuCliente = new utilerias.MenuButton();
        jLabel3 = new javax.swing.JLabel();
        menuButton2 = new utilerias.MenuButton();
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
        jLabel11 = new javax.swing.JLabel();
        BtnAgregarSala = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(36, 44, 99));

        tblPeliculas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Titulo", "Pais", "Duracion", "Remover Pelicula", "Editar Pelicula"
            }
        ));
        jScrollPane1.setViewportView(tblPeliculas);

        jLabel10.setText("Peliculas En Cinepolis");
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

        menuButton2.setForeground(new java.awt.Color(255, 255, 255));
        menuButton2.setText("Peliculas");
        menuButton2.setBorderPainted(false);
        menuButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        menuButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(menuButton2);

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
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        BtnAgregarSala.setBackground(new java.awt.Color(204, 204, 204));
        BtnAgregarSala.setText("Agregar Pelicula");
        BtnAgregarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarSalaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(btnAtras)
                        .addGap(44, 44, 44)
                        .addComponent(lblPagina)
                        .addGap(54, 54, 54)
                        .addComponent(btnSiguiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAgregarSala, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(146, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPagina)
                    .addComponent(btnSiguiente)
                    .addComponent(btnAtras)
                    .addComponent(BtnAgregarSala, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        FrmAdminSalas frmAdminSalas = new FrmAdminSalas(sucursalBO, ciudadBO, salaBO);
        frmAdminSalas.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuSalasActionPerformed

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
        FrmAdminEscogerFuncion escogerFuncion = new FrmAdminEscogerFuncion(sucursalBO, ciudadBO, peliculaBO);
        escogerFuncion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuFuncionesActionPerformed

    private void btnMenuReporteSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReporteSucursalActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IReportesSucursalesDAO reportesSucursalesDAO = new ReportesSucursalesDAO(conexionBD);
        IReportesSucursalesBO reportesSucursalesBO = new ReportesSucursalesBO(reportesSucursalesDAO);

        FrmReporteSucursales reporteSucursales = new FrmReporteSucursales(sucursalBO, ciudadBO, reportesSucursalesBO);
        reporteSucursales.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReporteSucursalActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        pagina--;
        this.estadoPagina();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        pagina++;
        this.estadoPagina();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void BtnAgregarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarSalaActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        IGeneroDAO generoDAO = new GeneroDAO(conexionBD);
        IGeneroBO generoBO = new GeneroBO(generoDAO);
        FrmAgregarPelicula agregarPelicula = new FrmAgregarPelicula(peliculaBO, generoBO);
        agregarPelicula.setVisible(true);
        this.dispose();


    }//GEN-LAST:event_BtnAgregarSalaActionPerformed

    private void btnMenuReportePeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuReportePeliculaActionPerformed
        FrmReportePelicula reportePelicula = new FrmReportePelicula(sucursalBO, ciudadBO);
        reportePelicula.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuReportePeliculaActionPerformed

    private void menuButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarSala;
    private javax.swing.JButton btnAtras;
    private utilerias.MenuButton btnMenuCliente;
    private utilerias.MenuButton btnMenuFunciones;
    private utilerias.MenuButton btnMenuReportePelicula;
    private utilerias.MenuButton btnMenuReporteSucursal;
    private utilerias.MenuButton btnMenuSalas;
    private utilerias.MenuButton btnMenuSucursales;
    private javax.swing.JButton btnSiguiente;
    private utilerias.ImagenPerfiles imagenPerfiles1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel lblPagina;
    private utilerias.MenuButton menuButton2;
    private javax.swing.JTable tblPeliculas;
    // End of variables declaration//GEN-END:variables
}
