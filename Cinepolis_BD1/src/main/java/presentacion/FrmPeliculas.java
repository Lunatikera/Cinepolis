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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.IPeliculaBO;
import negocio.ISalaBO;
import negocio.NegocioException;
import negocio.PeliculaBO;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author rramirez
 */
public class FrmPeliculas extends javax.swing.JFrame {

    IPeliculaBO peliculaBO;
    private int pagina = 1;
    private final int LIMITE = 5;
    private int idSucursal;

    public FrmPeliculas(IPeliculaBO peliculaBO,int idSucursal) {
        initComponents();
        this.peliculaBO = peliculaBO;
        this.idSucursal = idSucursal;
        this.metodosIniciales();
    }

    private void metodosIniciales() {
        this.cargarConfiguracionInicialPantalla();
        this.cargarConfiguracionInicialTablaClientes();
        this.cargarClientesEnTabla(idSucursal,pagina, LIMITE);
    }

    private void cargarConfiguracionInicialPantalla() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void cargarConfiguracionInicialTablaClientes() {
        ActionListener onEditarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Metodo para editar un alumno
                    editar();
                } catch (NegocioException ex) {
                    Logger.getLogger(FrmPeliculas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        int indiceColumnaEditar = 4;
        TableColumnModel modeloColumnas = this.tblClientes.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("Editar"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("Editar",
                        onEditarClickListener));

        ActionListener onEliminarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para eliminar un alumno
                eliminar();
            }
        };
        int indiceColumnaEliminar = 5;
        modeloColumnas = this.tblClientes.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar",
                        onEliminarClickListener));
    }

    private int getIdSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1)
        {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            int indiceColumnaId = 0;
            int idSocioSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else
        {
            return 0;
        }
    }

    private void editar() throws NegocioException {
        int id = this.getIdSeleccionadoTablaClientes();
        if (id > 0)
        {
            System.out.println("El id para editar es " + id);
            //FrmEditarCliente editarCliente = new FrmEditarCliente(this.clienteNegocio, id);
            //editarCliente.setVisible(true);
        } else
        {
            JOptionPane.showMessageDialog(this, "Selecciona una pelicula para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void eliminar() {
        int id = this.getIdSeleccionadoTablaClientes();
        if (id > 0)
        {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar la pelicula seleccionada?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION)
            {
                try
                {
                    this.peliculaBO.eliminarPelicula(id);
                    // Recargar la tabla después de eliminar
                    cargarClientesEnTabla(idSucursal,pagina, LIMITE);
                } catch (NegocioException ex)
                {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else
        {
            JOptionPane.showMessageDialog(this, "Selecciona un cliente para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void BorrarRegistrosTablaClientes() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();
        if (modeloTabla.getRowCount() > 0)
        {
            for (int row = modeloTabla.getRowCount() - 1; row > -1; row--)
            {
                modeloTabla.removeRow(row);
            }
        }
    }

    private void AgregarRegistrosTablaCliente(List<PeliculaDTO> salaLista) {
        if (salaLista == null)
        {
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();
        salaLista.forEach(row ->
        {
            Object[] fila = new Object[4];
            fila[0] = row.getId();
            fila[1] = row.getTitulo();
            fila[2] = row.getPais();
            fila[3] = row.getDuracion();
            modeloTabla.addRow(fila);
        });
    }

    private void cargarClientesEnTabla(int idSucursal,int pagina, int limite) {
        try
        {
            // Borrar registros previos antes de cargar los nuevos
            BorrarRegistrosTablaClientes();

            // Calcula el offset (inicio de los registros)
            int offset = (pagina - 1) * limite;

            // Obtén solo los clientes necesarios para la página actual
            List<PeliculaDTO> clientesLista = this.peliculaBO.buscarPeliculaSucursal(idSucursal, limite, offset);

            // Agrega los registros paginados a la tabla
            this.AgregarRegistrosTablaCliente(clientesLista);

            // Control de botones de navegación
            BtnAtras.setEnabled(pagina > 1);

            // Deshabilitar "Adelante" si no hay más registros para mostrar
            boolean hayMasRegistros = clientesLista.size() == limite;
            BtnAdelante.setEnabled(hayMasRegistros);

            // Actualiza el label de la página actual
            LblPage.setText("Página " + pagina);

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

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        BtnAgregarPelicula = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        LblPage = new javax.swing.JLabel();
        BtnAtras = new javax.swing.JButton();
        BtnAdelante = new javax.swing.JButton();
        cbCiudades = new javax.swing.JComboBox<>();
        cbSucursales = new javax.swing.JComboBox<>();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 255));

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "idPelicula", "Titulo", "Pais", "Duracion", "editar", "eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        BtnAgregarPelicula.setBackground(new java.awt.Color(204, 204, 204));
        BtnAgregarPelicula.setActionCommand("Agregar Pelicula");
        BtnAgregarPelicula.setLabel("Agregar Pelicula");
        BtnAgregarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarPeliculaActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(153, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Peliculas");

        LblPage.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LblPage.setForeground(new java.awt.Color(255, 255, 255));
        LblPage.setText("Pagina 1");

        BtnAtras.setBackground(new java.awt.Color(204, 204, 204));
        BtnAtras.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BtnAtras.setText("<<");
        BtnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAtrasActionPerformed(evt);
            }
        });

        BtnAdelante.setBackground(new java.awt.Color(204, 204, 204));
        BtnAdelante.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BtnAdelante.setText(">>");
        BtnAdelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdelanteActionPerformed(evt);
            }
        });

        cbCiudades.setBackground(new java.awt.Color(33, 36, 59));
        cbCiudades.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbCiudades.setForeground(new java.awt.Color(255, 255, 255));
        cbCiudades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCiudadesActionPerformed(evt);
            }
        });

        cbSucursales.setBackground(new java.awt.Color(33, 36, 59));
        cbSucursales.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbSucursales.setForeground(new java.awt.Color(255, 255, 255));
        cbSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSucursalesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnAtras)
                        .addGap(507, 507, 507)
                        .addComponent(LblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAdelante))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(266, 266, 266)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnAgregarPelicula)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(cbCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(BtnAgregarPelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblPage)
                    .addComponent(BtnAtras)
                    .addComponent(BtnAdelante))
                .addContainerGap())
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

    private void BtnAgregarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarPeliculaActionPerformed
        //FrmAgregarCliente agregar = new FrmAgregarCliente(this.clienteNegocio);
        //agregar.setVisible(true);
        //cargarClientesEnTabla(pagina, LIMITE);

    }//GEN-LAST:event_BtnAgregarPeliculaActionPerformed

    private void BtnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAtrasActionPerformed
        // TODO add your handling code here:
        if (pagina > 1)
        {
            pagina--;
            cargarClientesEnTabla(idSucursal,pagina, LIMITE);
        }
    }//GEN-LAST:event_BtnAtrasActionPerformed

    private void BtnAdelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdelanteActionPerformed
        // TODO add your handling code here:
        if (BtnAdelante.isEnabled())
        {
            pagina++;
            cargarClientesEnTabla(idSucursal,pagina, LIMITE);
        }
    }//GEN-LAST:event_BtnAdelanteActionPerformed

    private void cbCiudadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCiudadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCiudadesActionPerformed

    private void cbSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSucursalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbSucursalesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdelante;
    private javax.swing.JButton BtnAgregarPelicula;
    private javax.swing.JButton BtnAtras;
    private javax.swing.JLabel LblPage;
    private javax.swing.JComboBox<CiudadDTO> cbCiudades;
    private javax.swing.JComboBox<SucursalDTO> cbSucursales;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    // End of variables declaration//GEN-END:variables
}
