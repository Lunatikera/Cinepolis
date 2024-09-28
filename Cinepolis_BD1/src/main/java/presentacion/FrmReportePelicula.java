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
        cbCiudadAñadir = new javax.swing.JComboBox<>();
        btnQuitarCiudad = new javax.swing.JButton();
        btnAnadirCiudad = new javax.swing.JButton();
        cbCiudadQuitar = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbPeliculaAñadir = new javax.swing.JComboBox<>();
        cbPeliculaQuitar = new javax.swing.JComboBox<>();
        btnAnadirPelicula = new javax.swing.JButton();
        btnQuitarPelicula = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

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

        tblReporteSucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Ciudad", "Pelicula", "Fecha", "Ganancia"
            }
        ));
        jScrollPane1.setViewportView(tblReporteSucursal);

        jLabel10.setText("Reporte de ganacias por pelicula");
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        btnImprimir.setText("Imprimir ");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        jLabel12.setText("Pelicula para añadir");
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

        cbCiudadAñadir.setBackground(new java.awt.Color(33, 36, 59));
        cbCiudadAñadir.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbCiudadAñadir.setForeground(new java.awt.Color(255, 255, 255));
        cbCiudadAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCiudadAñadirActionPerformed(evt);
            }
        });

        btnQuitarCiudad.setText("Quitar Ciudad");
        btnQuitarCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarCiudadActionPerformed(evt);
            }
        });

        btnAnadirCiudad.setText("Añadir Ciudad");
        btnAnadirCiudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirCiudadActionPerformed(evt);
            }
        });

        cbCiudadQuitar.setBackground(new java.awt.Color(33, 36, 59));
        cbCiudadQuitar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbCiudadQuitar.setForeground(new java.awt.Color(255, 255, 255));
        cbCiudadQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCiudadQuitarActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Ciudad para añadir");

        cbPeliculaAñadir.setBackground(new java.awt.Color(33, 36, 59));
        cbPeliculaAñadir.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbPeliculaAñadir.setForeground(new java.awt.Color(255, 255, 255));
        cbPeliculaAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPeliculaAñadirActionPerformed(evt);
            }
        });

        cbPeliculaQuitar.setBackground(new java.awt.Color(33, 36, 59));
        cbPeliculaQuitar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbPeliculaQuitar.setForeground(new java.awt.Color(255, 255, 255));
        cbPeliculaQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPeliculaQuitarActionPerformed(evt);
            }
        });

        btnAnadirPelicula.setText("Añadir Pelicula");
        btnAnadirPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirPeliculaActionPerformed(evt);
            }
        });

        btnQuitarPelicula.setText("Quitar Pelicula");
        btnQuitarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarPeliculaActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Fecha inicio");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Fecha fin");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(379, 379, 379)
                                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(btnQuitarCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(155, 155, 155)
                                .addComponent(btnQuitarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cbCiudadAñadir, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbCiudadQuitar, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(49, 49, 49)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cbPeliculaQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cbPeliculaAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(57, 57, 57)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(fechaInicioDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(fechaFinDP, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(89, 89, 89)
                                                .addComponent(jLabel14))))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(btnAnadirCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(154, 154, 154)
                                .addComponent(btnAnadirPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15)
                                .addGap(59, 59, 59)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(btnIr)
                        .addGap(85, 85, 85))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCiudadAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPeliculaAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAnadirCiudad)
                            .addComponent(btnAnadirPelicula)
                            .addComponent(btnIr))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCiudadQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPeliculaQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnQuitarCiudad)
                            .addComponent(btnQuitarPelicula))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void menuButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuButton3ActionPerformed

    private void cbCiudadAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCiudadAñadirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCiudadAñadirActionPerformed

    private void btnQuitarCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarCiudadActionPerformed

    private void btnAnadirCiudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirCiudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnadirCiudadActionPerformed

    private void cbCiudadQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCiudadQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCiudadQuitarActionPerformed

    private void cbPeliculaAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPeliculaAñadirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPeliculaAñadirActionPerformed

    private void cbPeliculaQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPeliculaQuitarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPeliculaQuitarActionPerformed

    private void btnAnadirPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirPeliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnadirPeliculaActionPerformed

    private void btnQuitarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarPeliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarPeliculaActionPerformed
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
    private javax.swing.JButton btnAnadirCiudad;
    private javax.swing.JButton btnAnadirPelicula;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnIr;
    private javax.swing.JButton btnQuitarCiudad;
    private javax.swing.JButton btnQuitarPelicula;
    private javax.swing.JComboBox<PeliculaDTO> cbCiudadAñadir;
    private javax.swing.JComboBox<PeliculaDTO> cbCiudadQuitar;
    private javax.swing.JComboBox<PeliculaDTO> cbPeliculaAñadir;
    private javax.swing.JComboBox<PeliculaDTO> cbPeliculaQuitar;
    private com.github.lgooddatepicker.components.DatePicker fechaFinDP;
    private com.github.lgooddatepicker.components.DatePicker fechaInicioDP;
    private utilerias.ImagenPerfiles imagenPerfiles1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
