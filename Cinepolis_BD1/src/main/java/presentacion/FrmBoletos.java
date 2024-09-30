/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dtos.ClienteDTO;
import dtos.SucursalDTO;
import dtos.TicketDTO;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import negocio.CiudadBO;
import negocio.ICiudadBO;
import negocio.IPeliculaBO;
import negocio.ISucursalBO;
import negocio.ITicketBO;
import negocio.NegocioException;
import negocio.PeliculaBO;
import negocio.SucursalBO;
import persistencia.CiudadDAO;
import persistencia.ConexionBD;
import persistencia.ICiudadDAO;
import persistencia.IConexionBD;
import persistencia.IPeliculaDAO;
import persistencia.ISucursalDAO;
import persistencia.PeliculaDAO;
import persistencia.SucursalDAO;
import utilerias.DateTimeUtil;
import utilerias.Herramientas;

/**
 *
 * @author carli
 */
public class FrmBoletos extends javax.swing.JFrame {

    private ITicketBO ticketBO;
    private final int LIMITE = 3;
    private int pagina = 1;
    private ClienteDTO cliente;
    private SucursalDTO sucursal;
    private TicketDTO ticket;
    private List<TicketDTO> ventasCargadas;
    private JButton[] carteles;
    private JButton[] qrs;

    private JLabel[] titulos;
    private JLabel[] dias;
    private JLabel[] horarios;
    private JLabel[] salas;
    private JLabel[] sucursales;

    public FrmBoletos(ITicketBO ticketBO, ClienteDTO cliente, SucursalDTO sucursal) {
        initComponents();
        this.ticketBO = ticketBO;
        this.cliente = cliente;
        this.sucursal = sucursal;
        ventasCargadas = new ArrayList<>();
        carteles = new JButton[]{btnCartel1, btnCartel2, btnCartel3};
        qrs = new JButton[]{btnImprimir1, btnImprimir2, btnImprimir3};
        titulos = new JLabel[]{lblPelicula1, lblPelicula2, lblPelicula3};
        dias = new JLabel[]{lblDIa1, lblDia2, lblDia3};
        horarios = new JLabel[]{lblHorario1, lblHorario2, lblHorario3};
        salas = new JLabel[]{lblSala1, lblSala2, lblSala3};
        sucursales = new JLabel[]{lblSucursal1, lblSucursal2, lblSucursal3};

        cargarMetodosIniciales();
    }

    private void cargarMetodosIniciales() {
        this.setTitle(sucursal.getNombre());
        this.setLocationRelativeTo(null);
        this.cargarPeliculas();
        this.estadoPagina();

    }

    public void cargarPeliculas() {
        try {
            List<TicketDTO> ventasLista = this.ticketBO.obtenerVentasFuncionesPorCliente(cliente.getIdCliente(),LIMITE, pagina);
            System.out.println(ventasLista);
            ventasCargadas.clear();
            ventasCargadas.addAll(ventasLista);
            this.llenarCampos(ventasLista);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Informacion", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void llenarCampos(List<TicketDTO> ventasLista) {
        for (int i = 0; i < ventasLista.size(); i++) {
            qrs[i].setEnabled(true);
            carteles[i].setEnabled(true);

            ImageIcon icon = new ImageIcon(ventasLista.get(i).getCartelPelicula());
            Image scaledImage = icon.getImage().getScaledInstance(198, 264, Image.SCALE_SMOOTH);
            carteles[i].setIcon(new ImageIcon(scaledImage));
            titulos[i].setText(Herramientas.textoConSaltosLinea(ventasLista.get(i).getNombrePelicula(), 5));
            dias[i].setText(ventasLista.get(i).getDia());
            horarios[i].setText(ventasLista.get(i).getHoraInicio() + "-" + ventasLista.get(i).getHoraFinal());
            salas[i].setText(ventasLista.get(i).getNombreSala());
            sucursales[i].setText(ventasLista.get(i).getNombreSucursal());

        }
        // Limpiar botones y etiquetas restantes
        for (int i = ventasLista.size(); i < LIMITE; i++) {

            titulos[i].setText("No Disponible");
            dias[i].setText("No Disponible");
            horarios[i].setText("No Disponible");
            salas[i].setText("No Disponible");
            sucursales[i].setText("No Disponible");
            carteles[i].setEnabled(false);
            qrs[i].setEnabled(false);

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

        jPanel6 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnInicio = new utilerias.MenuButton();
        btnSucursales = new utilerias.MenuButton();
        btnBoletos = new utilerias.MenuButton();
        btnInbox = new utilerias.MenuButton();
        btnPerfil = new utilerias.MenuButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        btnAtras = new utilerias.MenuButton();
        btnSiguiente = new utilerias.MenuButton();
        panelAzulBlanco6 = new utilerias.PanelAzulBlanco();
        btnImprimir1 = new utilerias.MenuButton();
        lblDIa1 = new javax.swing.JLabel();
        lblSala1 = new javax.swing.JLabel();
        lblHorario1 = new javax.swing.JLabel();
        btnCartel1 = new javax.swing.JButton();
        lblSucursal1 = new javax.swing.JLabel();
        lblPagina = new javax.swing.JLabel();
        lblSucursal2 = new javax.swing.JLabel();
        panelAzulBlanco9 = new utilerias.PanelAzulBlanco();
        btnImprimir2 = new utilerias.MenuButton();
        lblDia2 = new javax.swing.JLabel();
        lblSala2 = new javax.swing.JLabel();
        lblHorario2 = new javax.swing.JLabel();
        btnCartel2 = new javax.swing.JButton();
        panelAzulBlanco11 = new utilerias.PanelAzulBlanco();
        btnImprimir3 = new utilerias.MenuButton();
        lblDia3 = new javax.swing.JLabel();
        lblSala3 = new javax.swing.JLabel();
        lblHorario3 = new javax.swing.JLabel();
        btnCartel3 = new javax.swing.JButton();
        lblSucursal3 = new javax.swing.JLabel();
        lblPelicula1 = new javax.swing.JLabel();
        lblPelicula2 = new javax.swing.JLabel();
        lblPelicula3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(33, 36, 59));
        jPanel2.setPreferredSize(new java.awt.Dimension(1280, 35));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1280, 65));
        jPanel3.setLayout(new java.awt.GridLayout(1, 5, 50, 0));

        btnInicio.setBorderPainted(false);
        btnInicio.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/InicioSelected.png"))); // NOI18N
        btnInicio.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Inicio.png"))); // NOI18N
        btnInicio.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/InicioSelected.png"))); // NOI18N
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        jPanel3.add(btnInicio);

        btnSucursales.setBorderPainted(false);
        btnSucursales.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/SucursalesSelected.png"))); // NOI18N
        btnSucursales.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sucursales.png"))); // NOI18N
        btnSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSucursalesActionPerformed(evt);
            }
        });
        jPanel3.add(btnSucursales);

        btnBoletos.setBorderPainted(false);
        btnBoletos.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ticketsSelected.png"))); // NOI18N
        btnBoletos.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tickets.png"))); // NOI18N
        btnBoletos.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ticketsSelected.png"))); // NOI18N
        jPanel3.add(btnBoletos);

        btnInbox.setBorderPainted(false);
        btnInbox.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/inboxSelected.png"))); // NOI18N
        btnInbox.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/inbox.png"))); // NOI18N
        btnInbox.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/inboxSelected.png"))); // NOI18N
        btnInbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInboxActionPerformed(evt);
            }
        });
        jPanel3.add(btnInbox);

        btnPerfil.setBorderPainted(false);
        btnPerfil.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfilSelected.png"))); // NOI18N
        btnPerfil.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfil.png"))); // NOI18N
        btnPerfil.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/perfilSelected.png"))); // NOI18N
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });
        jPanel3.add(btnPerfil);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBackground(new java.awt.Color(36, 44, 99));
        jPanel4.setPreferredSize(new java.awt.Dimension(1280, 1280));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(36, 44, 99));

        jLabel1.setBackground(new java.awt.Color(36, 44, 99));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Cinépolis_logo 1.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Line 7.png"))); // NOI18N

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Historial de Boletos");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addGap(113, 113, 113)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(157, 157, 157))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        jPanel4.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel8.setBackground(new java.awt.Color(36, 44, 99));
        jPanel8.setPreferredSize(new java.awt.Dimension(1280, 10));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel5.setBackground(new java.awt.Color(36, 44, 99));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAtras.setBorderPainted(false);
        btnAtras.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/leftSelected.png"))); // NOI18N
        btnAtras.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/left.png"))); // NOI18N
        btnAtras.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/leftSelected.png"))); // NOI18N
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        jPanel5.add(btnAtras, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        btnSiguiente.setBorderPainted(false);
        btnSiguiente.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rightSelected.png"))); // NOI18N
        btnSiguiente.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/right.png"))); // NOI18N
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        jPanel5.add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 160, -1, -1));

        btnImprimir1.setBorderPainted(false);
        btnImprimir1.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/qr.png"))); // NOI18N
        btnImprimir1.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/qr.png"))); // NOI18N
        btnImprimir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir1ActionPerformed(evt);
            }
        });

        lblDIa1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDIa1.setText("000 Minutos");

        lblSala1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSala1.setText("Sala Itson VIP");

        lblHorario1.setText(" 00:00 - 00:00");

        btnCartel1.setBorderPainted(false);

        javax.swing.GroupLayout panelAzulBlanco6Layout = new javax.swing.GroupLayout(panelAzulBlanco6);
        panelAzulBlanco6.setLayout(panelAzulBlanco6Layout);
        panelAzulBlanco6Layout.setHorizontalGroup(
            panelAzulBlanco6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAzulBlanco6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnCartel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelAzulBlanco6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDIa1)
                    .addComponent(lblSala1)
                    .addComponent(lblHorario1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnImprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelAzulBlanco6Layout.setVerticalGroup(
            panelAzulBlanco6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAzulBlanco6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAzulBlanco6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAzulBlanco6Layout.createSequentialGroup()
                        .addComponent(btnCartel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAzulBlanco6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelAzulBlanco6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAzulBlanco6Layout.createSequentialGroup()
                                .addComponent(lblDIa1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSala1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHorario1))
                            .addComponent(btnImprimir1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))))
        );

        jPanel5.add(panelAzulBlanco6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 330, 180));

        lblSucursal1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblSucursal1.setForeground(new java.awt.Color(255, 255, 255));
        lblSucursal1.setText("Sucursal Bellavista, Obregon");
        jPanel5.add(lblSucursal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, -1, -1));

        lblPagina.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblPagina.setText("Pagina 1");
        jPanel5.add(lblPagina, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, -1, -1));

        lblSucursal2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblSucursal2.setForeground(new java.awt.Color(255, 255, 255));
        lblSucursal2.setText("Sucursal Bellavista, Obregon");
        jPanel5.add(lblSucursal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, -1, -1));

        btnImprimir2.setBorderPainted(false);
        btnImprimir2.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/qr.png"))); // NOI18N
        btnImprimir2.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/qr.png"))); // NOI18N
        btnImprimir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir2ActionPerformed(evt);
            }
        });

        lblDia2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDia2.setText("000 Minutos");

        lblSala2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSala2.setText("Sala Itson VIP");

        lblHorario2.setText(" 00:00 - 00:00");

        btnCartel2.setBorderPainted(false);

        javax.swing.GroupLayout panelAzulBlanco9Layout = new javax.swing.GroupLayout(panelAzulBlanco9);
        panelAzulBlanco9.setLayout(panelAzulBlanco9Layout);
        panelAzulBlanco9Layout.setHorizontalGroup(
            panelAzulBlanco9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAzulBlanco9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnCartel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelAzulBlanco9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDia2)
                    .addComponent(lblSala2)
                    .addComponent(lblHorario2))
                .addGap(30, 30, 30)
                .addComponent(btnImprimir2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelAzulBlanco9Layout.setVerticalGroup(
            panelAzulBlanco9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAzulBlanco9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAzulBlanco9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAzulBlanco9Layout.createSequentialGroup()
                        .addComponent(btnCartel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAzulBlanco9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelAzulBlanco9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAzulBlanco9Layout.createSequentialGroup()
                                .addComponent(lblDia2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSala2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHorario2))
                            .addComponent(btnImprimir2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))))
        );

        jPanel5.add(panelAzulBlanco9, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 330, 180));

        btnImprimir3.setBorderPainted(false);
        btnImprimir3.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/qr.png"))); // NOI18N
        btnImprimir3.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/qr.png"))); // NOI18N
        btnImprimir3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimir3ActionPerformed(evt);
            }
        });

        lblDia3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDia3.setText("000 Minutos");

        lblSala3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSala3.setText("Sala Itson VIP");

        lblHorario3.setText(" 00:00 - 00:00");

        btnCartel3.setBorderPainted(false);

        javax.swing.GroupLayout panelAzulBlanco11Layout = new javax.swing.GroupLayout(panelAzulBlanco11);
        panelAzulBlanco11.setLayout(panelAzulBlanco11Layout);
        panelAzulBlanco11Layout.setHorizontalGroup(
            panelAzulBlanco11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAzulBlanco11Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnCartel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelAzulBlanco11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDia3)
                    .addComponent(lblSala3)
                    .addComponent(lblHorario3))
                .addGap(30, 30, 30)
                .addComponent(btnImprimir3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelAzulBlanco11Layout.setVerticalGroup(
            panelAzulBlanco11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAzulBlanco11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAzulBlanco11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAzulBlanco11Layout.createSequentialGroup()
                        .addComponent(btnCartel3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAzulBlanco11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelAzulBlanco11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAzulBlanco11Layout.createSequentialGroup()
                                .addComponent(lblDia3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblSala3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHorario3))
                            .addComponent(btnImprimir3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43))))
        );

        jPanel5.add(panelAzulBlanco11, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 330, 180));

        lblSucursal3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblSucursal3.setForeground(new java.awt.Color(255, 255, 255));
        lblSucursal3.setText("Sucursal Bellavista, Obregon");
        jPanel5.add(lblSucursal3, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 290, -1, -1));

        lblPelicula1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPelicula1.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula1.setText("Pelicula");
        jPanel5.add(lblPelicula1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 260, -1));

        lblPelicula2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPelicula2.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula2.setText("Pelicula");
        jPanel5.add(lblPelicula2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 310, -1));

        lblPelicula3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblPelicula3.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula3.setText("Pelicula");
        jPanel5.add(lblPelicula3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 330, -1));

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir1ActionPerformed
        int confirmacion = JOptionPane.showOptionDialog(this,
                "¿Deseas imprmir una copia de tu Ticket para " + ventasCargadas.get(0).getNombrePelicula() + " a las " + ventasCargadas.get(0).getHoraInicio() + " el " + ventasCargadas.get(0).getDia(),
                "Confirmación de eliminacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Cancelar", "Aceptar"},
                "Confirmar");

        // Si el usuario selecciona "Cancelar", no se hace nada
        if (confirmacion == JOptionPane.YES_OPTION) {
            return;
        }
        try {
            ticket = ventasCargadas.get(0);
            imprimirTicket(ticket);
        } catch (IOException ex) {
            Logger.getLogger(FrmBoletos.class.getName()).log(Level.SEVERE, null, ex);
        }
     }//GEN-LAST:event_btnImprimir1ActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        IConexionBD conexion = new ConexionBD();

        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        ISucursalDAO sucursalDAO = new SucursalDAO(conexion);

        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        FrmCatalogoSucursal catalogo = new FrmCatalogoSucursal(peliculaBO, ciudadBO, sucursalBO, sucursal, cliente);
        catalogo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSucursalesActionPerformed
        IConexionBD conexion = new ConexionBD();

        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        ISucursalDAO sucursalDAO = new SucursalDAO(conexion);

        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);

        FrmSucursales sucursales = new FrmSucursales(ciudadBO, sucursalBO, sucursal, cliente);
        sucursales.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSucursalesActionPerformed

    private void btnInboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInboxActionPerformed
        FrmInbox inbox = new FrmInbox();
        inbox.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInboxActionPerformed

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        FrmConfiguracionPerfil perfil = new FrmConfiguracionPerfil();
        perfil.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void btnImprimir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir2ActionPerformed
        int confirmacion = JOptionPane.showOptionDialog(this,
                "¿Deseas imprmir una copia de tu Ticket para " + ventasCargadas.get(1).getNombrePelicula() + " a las " + ventasCargadas.get(1).getHoraInicio() + " el " + ventasCargadas.get(1).getDia(),
                "Confirmación de eliminacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Cancelar", "Aceptar"},
                "Confirmar");

        // Si el usuario selecciona "Cancelar", no se hace nada
        if (confirmacion == JOptionPane.YES_OPTION) {
            return;
        }
        try {
            ticket = ventasCargadas.get(1);
            imprimirTicket(ticket);
        } catch (IOException ex) {
            Logger.getLogger(FrmBoletos.class.getName()).log(Level.SEVERE, null, ex);
        }

     }//GEN-LAST:event_btnImprimir2ActionPerformed

    private void btnImprimir3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimir3ActionPerformed
        int confirmacion = JOptionPane.showOptionDialog(this,
                "¿Deseas imprmir una copia de tu Ticket para " + ventasCargadas.get(2).getNombrePelicula() + " a las " + ventasCargadas.get(2).getHoraInicio() + " el " + ventasCargadas.get(2).getDia(),
                "Confirmación de eliminacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Cancelar", "Aceptar"},
                "Confirmar");

        // Si el usuario selecciona "Cancelar", no se hace nada
        if (confirmacion == JOptionPane.YES_OPTION) {
            return;
        }
        try {
            ticket = ventasCargadas.get(2);
            imprimirTicket(ticket);
        } catch (IOException ex) {
            Logger.getLogger(FrmBoletos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnImprimir3ActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.pagina = this.pagina - 1;
        this.cargarPeliculas();
        this.estadoPagina();
     }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        this.pagina = this.pagina + 1;
        this.cargarPeliculas();
        this.estadoPagina();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void estadoPagina() {
        String numPagina = String.valueOf(pagina);
        if (numPagina.length() == 1) {
            numPagina = "0" + numPagina;
        }

        lblPagina.setText("Pagina " + numPagina);
        estatusBotonAtras();
        estatusBotonSiguiente();
    }

    private void estatusBotonAtras() {
        if (this.pagina > 1) {
            btnAtras.setEnabled(true);
            return;
        }
        btnAtras.setEnabled(false);
    }

    private void estatusBotonSiguiente() {

        try {
            btnSiguiente.setEnabled(true);
            if (this.ticketBO.obtenerVentasFuncionesPorCliente(cliente.getIdCliente(),LIMITE, pagina+1) == null
                    || this.ticketBO.obtenerVentasFuncionesPorCliente(cliente.getIdCliente(),LIMITE, pagina+1).isEmpty()) {
                btnSiguiente.setEnabled(false);
            }
        } catch (NegocioException ex) {
            System.out.println(ex);
        }
    }

    private void imprimirTicket(TicketDTO ticket) throws IOException {
        Document document = new Document();

        try {
            String fileName = "BoletoCine.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Fuentes para el texto
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL);

            // Título del establecimiento (Centrado con más espacios)
            Paragraph title = new Paragraph("******************************************************************\n"
                    + ticket.getNombrePelicula().toUpperCase() + "\n"
                    + this.ticket.getNombreSala().toUpperCase() + "\n"
                    + this.ticket.getHoraInicio() + "-" + this.ticket.getHoraFinal() + "\n"
                    + "******************************************************************\n", boldFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Información del cliente (Alinear con más espacios)
            Paragraph clientInfo = new Paragraph(
                    "     NOMBRE CLIENTE: " + this.cliente.getNombre().toUpperCase() + " " + this.cliente.getApellidoPA().toUpperCase() + " " + this.cliente.getApellidoMA().toUpperCase() + "\n", normalFont);
            clientInfo.setAlignment(Element.ALIGN_LEFT);
            document.add(clientInfo);

            // Espacio para separar secciones
            document.add(new Paragraph("\n"));

            // Detalle de los artículos
            Paragraph articlesHeader = new Paragraph(
                    "     ARTICULO                     P C/U                - CANT                  TOTAL\n"
                    + "     ------------------------------------------------------------------------------------------\n", normalFont);
            articlesHeader.setAlignment(Element.ALIGN_LEFT);
            document.add(articlesHeader);

            // Artículos con más espacios para alinear
            Paragraph articles = new Paragraph(
                    "     BOLETO " + "            $" + ticket.getPrecioUnitario() + "                  -" + ticket.getCantidadBoletos() + "                   $" + ticket.getTotalCompra() + "\n",
                    normalFont);
            articles.setAlignment(Element.ALIGN_LEFT);
            document.add(articles);

            // Espacio para separar secciones
            document.add(new Paragraph("\n"));

            // Resumen de compra
            Paragraph summary = new Paragraph(
                    "     TOTAL COMPRA:      " + ticket.getTotalCompra()
                    + "     METODO PAGO:     " + ticket.getMetodoPago().toUpperCase() + "\n"
                    + "     FECHA:         " + DateTimeUtil.formatearFechaHora(ticket.getFechaCompra() )+ "\n"
                    + "     CIUDAD:       " + ticket.getCiudad().toUpperCase() + "\n", normalFont);
            summary.setAlignment(Element.ALIGN_LEFT);
            document.add(summary);

            // Espacio para separar secciones
            document.add(new Paragraph("\n"));

            // Espacio para separar secciones
            document.add(new Paragraph("\n"));

            // Mensaje de agradecimiento (Centrado con más espacios)
            Paragraph thanks = new Paragraph("******************************************************************\n"
                    + ticket.getNombreSucursal().toUpperCase() + "\n"
                    + "\n******************************************************************\n", boldFont);
            thanks.setAlignment(Element.ALIGN_CENTER);
            document.add(thanks);
            try {
                com.itextpdf.text.Image logo;
                logo = com.itextpdf.text.Image.getInstance("src\\main\\resources\\imagenes\\QRreal.png"); // Cambia por la ruta de tu logo
                logo.scaleToFit(250, 250); // Tamaño del logo
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
            } catch (BadElementException ex) {
                Logger.getLogger(FrmCompraBoleto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FrmCompraBoleto.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Cerrar el documento
            document.close();

            File pdfFile = new File(fileName);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("Abrir archivos no es soportado en este sistema.");
            }

            System.out.println("¡Boleto generado correctamente !");

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilerias.MenuButton btnAtras;
    private utilerias.MenuButton btnBoletos;
    private javax.swing.JButton btnCartel1;
    private javax.swing.JButton btnCartel2;
    private javax.swing.JButton btnCartel3;
    private utilerias.MenuButton btnImprimir1;
    private utilerias.MenuButton btnImprimir2;
    private utilerias.MenuButton btnImprimir3;
    private utilerias.MenuButton btnInbox;
    private utilerias.MenuButton btnInicio;
    private utilerias.MenuButton btnPerfil;
    private utilerias.MenuButton btnSiguiente;
    private utilerias.MenuButton btnSucursales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblDIa1;
    private javax.swing.JLabel lblDia2;
    private javax.swing.JLabel lblDia3;
    private javax.swing.JLabel lblHorario1;
    private javax.swing.JLabel lblHorario2;
    private javax.swing.JLabel lblHorario3;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JLabel lblPelicula1;
    private javax.swing.JLabel lblPelicula2;
    private javax.swing.JLabel lblPelicula3;
    private javax.swing.JLabel lblSala1;
    private javax.swing.JLabel lblSala2;
    private javax.swing.JLabel lblSala3;
    private javax.swing.JLabel lblSucursal1;
    private javax.swing.JLabel lblSucursal2;
    private javax.swing.JLabel lblSucursal3;
    private javax.swing.JLabel lblTitulo;
    private utilerias.PanelAzulBlanco panelAzulBlanco11;
    private utilerias.PanelAzulBlanco panelAzulBlanco6;
    private utilerias.PanelAzulBlanco panelAzulBlanco9;
    // End of variables declaration//GEN-END:variables
}
