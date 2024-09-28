/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.ClienteDTO;
import dtos.ClienteTablaFiltroDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.NegocioException;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;
import negocio.IClienteBO;

/**
 *
 * @author Temo
 */
public class FrmCrudClientes extends javax.swing.JFrame {

    private ClienteTablaFiltroDTO filtroPaginado;
    private IClienteBO clienteNegocio;

    /**
     * Creates new form frmCRUD
     */
    public FrmCrudClientes(IClienteBO clienteNegocio) {
        initComponents();
        this.clienteNegocio = clienteNegocio;
        filtroPaginado = new ClienteTablaFiltroDTO();
        this.cargarMetodosIniciales();
        this.setTitle("Clientes");
    }

    public void cargarMetodosIniciales() {
        //this.cargarConfiguracionInicialPantalla();
        this.cargarConfiguracionInicialTablaClientes();
        this.cargarClientesEnTabla();
        this.estadoPagina();
    }

   

    public void cargarConfiguracionInicialPantalla() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void cargarClientesEnTabla() {
        try {
            List<ClienteDTO> clienteLista = this.clienteNegocio.buscarPaginadosClienteTabla(1, 1);
            this.llenarTablaCliente(clienteLista);
            if (clienteLista == null && 1 == 1) {
                JOptionPane.showMessageDialog(null, "No hay ningun cliente Registrado", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarConfiguracionInicialTablaClientes() {
        ActionListener onEditarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para editar un cliente
                editar();

            }
        };
        int indiceColumnaEditar = 9;
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
                //Metodo para eliminar un cliente
                eliminar();
            }
        };
        int indiceColumnaEliminar = 10;
        modeloColumnas = this.tblClientes.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar",
                        onEliminarClickListener));
    }

    private void llenarTablaCliente(List<ClienteDTO> clienteLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (clienteLista != null) {
            clienteLista.forEach(row
                    -> {
                Object[] fila = new Object[9];
                fila[0] = row.getIdCliente();
                fila[1] = row.getNombre();
                fila[2] = row.getApellidoMA();
                fila[3] = row.getApellidoPA();
                fila[4] = row.getCorreo();
                fila[5] = row.getContraseña().describeConstable();
                fila[6] = row.getFechaNacimiento();
                fila[7] = row.getUbicacion();
                fila[8] = row.getIdCiudad();

                modeloTabla.addRow(fila);
            });
        }
    }

    private int getIdSeleccionadoTablaClientes() {
        int indiceFilaSeleccionada = this.tblClientes.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblClientes.getModel();
            int indiceColumnaId = 0;
            int idSocioSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return 0;
        }
    }

    private void editar() {
        //Metodo para regresar el cliente seleccionado
        int id = this.getIdSeleccionadoTablaClientes();
        System.out.println(id);
        ClienteDTO clienteDTO = new ClienteDTO(id);
        try {
            if (id == 0) {
                throw new NegocioException("Por favor seleccione un cliente");
            }
            clienteDTO = clienteNegocio.buscarClientePorId(id);
            frmEditarClientes editarCliente = new frmEditarClientes(this, clienteNegocio, clienteDTO);
            editarCliente.setVisible(true);
            cargarClientesEnTabla();

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        int id = this.getIdSeleccionadoTablaClientes();
        ClienteDTO clienteDTO = new ClienteDTO(id);
        try {

            if (id == 0) {
                throw new NegocioException("Por favor seleccione un cliente");
            }

            clienteDTO = clienteNegocio.buscarClientePorId(id);
            frmEliminarClientes eliminar = new frmEliminarClientes(this, clienteNegocio, clienteDTO );
            eliminar.setVisible(true);

        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        jCheckBox1 = new javax.swing.JCheckBox();
        LblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        BtnNuevoRegistro = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        LblPagina1 = new javax.swing.JLabel();
        BtnSiguiente = new javax.swing.JButton();
        BtnAtras = new javax.swing.JButton();

        jCheckBox1.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LblTitulo.setText("Administración de clientes");

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombres", "A Paterno", "A Materno", "Correo", "Contraseña", "Nacimineto", "Ubicacion", "Ciudad", "Editar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.setToolTipText("");
        jScrollPane1.setViewportView(tblClientes);

        BtnNuevoRegistro.setText("Nuevo Registro");
        BtnNuevoRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoRegistroActionPerformed(evt);
            }
        });

        LblPagina1.setText("Pagina 1");

        BtnSiguiente.setText("Siguiente");
        BtnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSiguienteActionPerformed(evt);
            }
        });

        BtnAtras.setText("Atras");
        BtnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158)
                .addComponent(LblPagina1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addComponent(BtnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(BtnSiguiente)
                .addComponent(LblPagina1))
            .addComponent(BtnAtras)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(LblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addGap(447, 447, 447))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnNuevoRegistro)
                        .addGap(0, 553, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(BtnNuevoRegistro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnNuevoRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoRegistroActionPerformed
        frmGuardarClientes nuevoRegistro = new frmGuardarClientes(this, clienteNegocio);
        nuevoRegistro.setVisible(true);
    }//GEN-LAST:event_BtnNuevoRegistroActionPerformed

    private void BtnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSiguienteActionPerformed
        filtroPaginado.sumarPagina();
        this.cargarClientesEnTabla();
        this.estadoPagina();
    }//GEN-LAST:event_BtnSiguienteActionPerformed

    public void estadoPagina() {
        String numPagina = String.valueOf(filtroPaginado.getPagina());
        LblPagina1.setText("Pagina " + numPagina);
        estatusBotonAtras();
        estatusBotonSiguiente();
    }

    private void estatusBotonAtras() {
        if (filtroPaginado.getPagina() > 1) {
            BtnAtras.setEnabled(true);
            return;
        }
        BtnAtras.setEnabled(false);
    }

    private void estatusBotonSiguiente() {

        try {
    // Habilitamos el botón al inicio
    BtnSiguiente.setEnabled(true);

    // Obtenemos la lista de clientes paginada
    List<ClienteDTO> clientesPaginados = this.clienteNegocio.buscarPaginadosClienteTabla(10, 1);

    // Si no hay más resultados (lista vacía), deshabilitamos el botón "Siguiente"
    if (clientesPaginados.isEmpty()) {
        BtnSiguiente.setEnabled(false);
    }

} catch (NegocioException ex) {
    // Mostrar el error en la consola o puedes mostrar un mensaje de error al usuario
    System.err.println("Ocurrió un error al buscar los clientes paginados: " + ex.getMessage());
}
    }
    private void BtnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAtrasActionPerformed
        filtroPaginado.restarPagina();
        this.cargarClientesEnTabla();
        this.estadoPagina();
    }//GEN-LAST:event_BtnAtrasActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try
//        {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
//            {
//                if ("Nimbus".equals(info.getName()))
//                {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex)
//        {
//            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex)
//        {
//            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex)
//        {
//            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex)
//        {
//            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmCRUD().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAtras;
    private javax.swing.JButton BtnNuevoRegistro;
    private javax.swing.JButton BtnSiguiente;
    private javax.swing.JLabel LblPagina1;
    private javax.swing.JLabel LblTitulo;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    // End of variables declaration//GEN-END:variables
}
