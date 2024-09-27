/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.CiudadDTO;
import dtos.ClienteDTO;
import dtos.PeliculaDTO;
import dtos.SucursalDTO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import negocio.ICiudadBO;
import negocio.IPeliculaBO;
import negocio.ISucursalBO;
import negocio.NegocioException;

/**
 *
 * @author carli
 */
public class FrmCatalogoSucursal extends javax.swing.JFrame {

    private int pagina = 1;
    private final int LIMITE = 4;
    private JButton[] botones;
    private JLabel[] labels;
    private List<PeliculaDTO> peliculasCargadas;
    private List<CiudadDTO> listaCiudades;
    private List<SucursalDTO> listaSucursales;
    private IPeliculaBO peliculaBO;
    private ICiudadBO ciudadBO;
    private ISucursalBO sucursalBO;
    private SucursalDTO sucursal;
    private ClienteDTO cliente;
    private final boolean peliculaEnSucursal= true; 

    public FrmCatalogoSucursal(IPeliculaBO peliculaBO, ICiudadBO ciudadBO, ISucursalBO sucursalBO, SucursalDTO sucursal, ClienteDTO cliente) {
        initComponents();
        System.out.println(sucursal.toString());
        this.peliculaBO = peliculaBO;
        this.ciudadBO = ciudadBO;
        this.sucursalBO = sucursalBO;
        this.sucursal = sucursal;
        this.cliente = cliente;
        botones = new JButton[]{btnCartel1, btnCartel2, btnCartel3, btnCartel4};
        labels = new JLabel[]{lblPelicula1, lblPelicula2, lblPelicula3, lblPelicula4};
        this.peliculasCargadas = new ArrayList<>();
        this.cargarMetodosIniciales();

    }

    private void cargarMetodosIniciales() {
        this.setTitle(sucursal.getNombre());
        this.setLocationRelativeTo(null);
        this.cargarPeliculas();
        this.estadoPagina();
        this.llenarComboBoxCiudad();
        this.seleccionarCiudadYSucursal();

    }

    public void cargarPeliculas() {
        try {
            List<PeliculaDTO> peliculasLista = this.peliculaBO.buscarPeliculaSucursal(sucursal.getId(), LIMITE, pagina, peliculaEnSucursal);
            System.out.println(peliculasLista);
            peliculasCargadas.clear();
            peliculasCargadas.addAll(peliculasLista);
            this.llenarCampos(peliculasLista);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Informacion", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void llenarCampos(List<PeliculaDTO> peliculasLista) {
        for (int i = 0; i < peliculasLista.size(); i++) {
            botones[i].setEnabled(true);
            ImageIcon icon = new ImageIcon(peliculasLista.get(i).getCartel());
            Image scaledImage = icon.getImage().getScaledInstance(198, 264, Image.SCALE_SMOOTH);
            botones[i].setIcon(new ImageIcon(scaledImage));
            labels[i].setText(utilerias.Herramientas.textoConSaltosLinea(peliculasLista.get(i).getTitulo(), 5));
        }
        // Limpiar botones y etiquetas restantes
        for (int i = peliculasLista.size(); i < LIMITE; i++) {
            ImageIcon icono = new ImageIcon(("carteles/ComingSoon.jpg"));
            Image scaledIcono = icono.getImage().getScaledInstance(198, 264, Image.SCALE_SMOOTH);
            botones[i].setIcon(new ImageIcon(scaledIcono));
            labels[i].setText("");
            botones[i].setEnabled(false);

        }

    }

    private void detallesPelicula1() {
        PeliculaDTO pelicula = peliculasCargadas.get(0);
        System.out.println(pelicula);
        FrmDetallePelicula detalles = new FrmDetallePelicula(pelicula, cliente, sucursal);
        detalles.setVisible(true);
        this.dispose();
    }

    private void detallesPelicula2() {
        PeliculaDTO pelicula = peliculasCargadas.get(1);
        System.out.println(pelicula);
        FrmDetallePelicula detalles = new FrmDetallePelicula(pelicula, cliente, sucursal);
        detalles.setVisible(true);
        this.dispose();
    }

    private void detallesPelicula3() {
        PeliculaDTO pelicula = peliculasCargadas.get(2);
        System.out.println(pelicula);
        FrmDetallePelicula detalles = new FrmDetallePelicula(pelicula, cliente, sucursal);
        detalles.setVisible(true);
        this.dispose();
    }

    private void detallesPelicula4() {
        PeliculaDTO pelicula = peliculasCargadas.get(3);
        System.out.println(pelicula);
        FrmDetallePelicula detalles = new FrmDetallePelicula(pelicula, cliente, sucursal);
        detalles.setVisible(true);
        this.dispose();
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
        cbCiudades = new javax.swing.JComboBox<>();
        cbSucursales = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnIr = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        lblPagina = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lblPelicula1 = new javax.swing.JLabel();
        btnPelicula1 = new utilerias.MenuButton();
        btnCartel1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        lblPelicula2 = new javax.swing.JLabel();
        btnPelicula2 = new utilerias.MenuButton();
        btnCartel2 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        lblPelicula3 = new javax.swing.JLabel();
        btnPelicula3 = new utilerias.MenuButton();
        btnCartel3 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        lblPelicula4 = new javax.swing.JLabel();
        btnPelicula4 = new utilerias.MenuButton();
        btnCartel4 = new javax.swing.JButton();
        btnSiguiente = new utilerias.MenuButton();
        btnAtras = new utilerias.MenuButton();

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
        btnInicio.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/InicioSelected.png"))); // NOI18N
        btnInicio.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/InicioSelected.png"))); // NOI18N
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
        btnBoletos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoletosActionPerformed(evt);
            }
        });
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

        cbCiudades.setBackground(new java.awt.Color(33, 36, 59));
        cbCiudades.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbCiudades.setForeground(new java.awt.Color(255, 255, 255));

        cbSucursales.setBackground(new java.awt.Color(33, 36, 59));
        cbSucursales.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbSucursales.setForeground(new java.awt.Color(255, 255, 255));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/locacion.png"))); // NOI18N

        btnIr.setText("Ir");
        btnIr.setBorderPainted(false);
        btnIr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGap(276, 276, 276)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIr)
                .addGap(112, 112, 112))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cbCiudades, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbSucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(btnIr)
                                .addGap(33, 33, 33))))))
        );

        jPanel4.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel8.setBackground(new java.awt.Color(36, 44, 99));
        jPanel8.setPreferredSize(new java.awt.Dimension(1280, 70));

        jPanel14.setBackground(new java.awt.Color(36, 44, 99));

        lblPagina.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblPagina.setText("Pagina 01");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 65, Short.MAX_VALUE)
                .addComponent(lblPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(lblPagina)
                .addGap(0, 46, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel14);

        jPanel4.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel5.setBackground(new java.awt.Color(36, 44, 99));

        jPanel9.setBackground(new java.awt.Color(36, 44, 99));

        jPanel10.setBackground(new java.awt.Color(36, 44, 99));

        lblPelicula1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblPelicula1.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula1.setText("Pelicula 1");

        btnPelicula1.setBorderPainted(false);
        btnPelicula1.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicketSelected.png"))); // NOI18N
        btnPelicula1.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicket.png"))); // NOI18N
        btnPelicula1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicketSelected.png"))); // NOI18N
        btnPelicula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelicula1ActionPerformed(evt);
            }
        });

        btnCartel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/3212c44f6aca4bc69d467d4614e6f3dc 1.png"))); // NOI18N
        btnCartel1.setBorderPainted(false);
        btnCartel1.setContentAreaFilled(false);
        btnCartel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPelicula1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPelicula1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCartel1))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnCartel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPelicula1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPelicula1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        jPanel11.setBackground(new java.awt.Color(36, 44, 99));

        lblPelicula2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblPelicula2.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula2.setText("Pelicula 1");

        btnPelicula2.setBorderPainted(false);
        btnPelicula2.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicketSelected.png"))); // NOI18N
        btnPelicula2.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicket.png"))); // NOI18N
        btnPelicula2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicketSelected.png"))); // NOI18N
        btnPelicula2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelicula2ActionPerformed(evt);
            }
        });

        btnCartel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/3212c44f6aca4bc69d467d4614e6f3dc 1.png"))); // NOI18N
        btnCartel2.setBorderPainted(false);
        btnCartel2.setContentAreaFilled(false);
        btnCartel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPelicula2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPelicula2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCartel2))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnCartel2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPelicula2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPelicula2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        jPanel12.setBackground(new java.awt.Color(36, 44, 99));

        lblPelicula3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblPelicula3.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula3.setText("Pelicula 1");

        btnPelicula3.setBorderPainted(false);
        btnPelicula3.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicketSelected.png"))); // NOI18N
        btnPelicula3.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicket.png"))); // NOI18N
        btnPelicula3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicketSelected.png"))); // NOI18N
        btnPelicula3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelicula3ActionPerformed(evt);
            }
        });

        btnCartel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/3212c44f6aca4bc69d467d4614e6f3dc 1.png"))); // NOI18N
        btnCartel3.setBorderPainted(false);
        btnCartel3.setContentAreaFilled(false);
        btnCartel3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartel3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPelicula3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPelicula3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCartel3))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnCartel3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPelicula3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPelicula3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        jPanel13.setBackground(new java.awt.Color(36, 44, 99));

        lblPelicula4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lblPelicula4.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula4.setText("Pelicula 1");

        btnPelicula4.setBorderPainted(false);
        btnPelicula4.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicketSelected.png"))); // NOI18N
        btnPelicula4.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicket.png"))); // NOI18N
        btnPelicula4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/comprarTicketSelected.png"))); // NOI18N
        btnPelicula4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPelicula4ActionPerformed(evt);
            }
        });

        btnCartel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/3212c44f6aca4bc69d467d4614e6f3dc 1.png"))); // NOI18N
        btnCartel4.setBorderPainted(false);
        btnCartel4.setContentAreaFilled(false);
        btnCartel4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCartel4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPelicula4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPelicula4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCartel4))
                .addGap(0, 7, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnCartel4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPelicula4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPelicula4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnSiguiente.setBorderPainted(false);
        btnSiguiente.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rightSelected.png"))); // NOI18N
        btnSiguiente.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/right.png"))); // NOI18N
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAtras.setBorderPainted(false);
        btnAtras.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/leftSelected.png"))); // NOI18N
        btnAtras.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/left.png"))); // NOI18N
        btnAtras.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/leftSelected.png"))); // NOI18N
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(133, 133, 133))
        );

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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnPelicula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelicula1ActionPerformed
        this.detallesPelicula1();
    }//GEN-LAST:event_btnPelicula1ActionPerformed

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


    private void btnPelicula2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelicula2ActionPerformed
        this.detallesPelicula2();
    }//GEN-LAST:event_btnPelicula2ActionPerformed

    private void btnPelicula3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelicula3ActionPerformed
        this.detallesPelicula3();
    }//GEN-LAST:event_btnPelicula3ActionPerformed

    private void btnPelicula4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPelicula4ActionPerformed
        this.detallesPelicula4();
    }//GEN-LAST:event_btnPelicula4ActionPerformed

    private void btnCartel4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartel4ActionPerformed
        this.detallesPelicula4();
    }//GEN-LAST:event_btnCartel4ActionPerformed

    private void btnCartel3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartel3ActionPerformed
        this.detallesPelicula3();
    }//GEN-LAST:event_btnCartel3ActionPerformed

    private void btnBoletosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoletosActionPerformed
        FrmBoletos boletos = new FrmBoletos();
        boletos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBoletosActionPerformed

    private void btnSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSucursalesActionPerformed
        FrmSucursales sucursales = new FrmSucursales(ciudadBO, sucursalBO, sucursal, cliente);
        sucursales.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSucursalesActionPerformed

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

    private void btnCartel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartel2ActionPerformed
        this.detallesPelicula2();
    }//GEN-LAST:event_btnCartel2ActionPerformed

    private void btnCartel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCartel1ActionPerformed
        this.detallesPelicula1();
    }//GEN-LAST:event_btnCartel1ActionPerformed

    private void btnIrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrActionPerformed
        if (cbCiudades.getSelectedItem() != null) {
            this.sucursal = (SucursalDTO) cbSucursales.getSelectedItem();
            this.pagina = 1;
            this.cargarPeliculas();
            this.estadoPagina();
            return;
        }
        JOptionPane.showMessageDialog(this, "Error al buscar el catalogo, Intente de nuevo ", "Error", JOptionPane.ERROR_MESSAGE);

    }//GEN-LAST:event_btnIrActionPerformed

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
            if (this.peliculaBO.buscarPeliculaSucursal(sucursal.getId(), this.LIMITE, this.pagina + 1, peliculaEnSucursal) == null
                    || this.peliculaBO.buscarPeliculaSucursal(sucursal.getId(), this.LIMITE, this.pagina + 1, peliculaEnSucursal).isEmpty()) {
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

            // Seleccionar la sucursal más cercana en el combo box de sucursales
            cbSucursales.setSelectedItem(sucursal);
        }
    }

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
    private utilerias.MenuButton btnAtras;
    private utilerias.MenuButton btnBoletos;
    private javax.swing.JButton btnCartel1;
    private javax.swing.JButton btnCartel2;
    private javax.swing.JButton btnCartel3;
    private javax.swing.JButton btnCartel4;
    private utilerias.MenuButton btnInbox;
    private utilerias.MenuButton btnInicio;
    private javax.swing.JButton btnIr;
    private utilerias.MenuButton btnPelicula1;
    private utilerias.MenuButton btnPelicula2;
    private utilerias.MenuButton btnPelicula3;
    private utilerias.MenuButton btnPelicula4;
    private utilerias.MenuButton btnPerfil;
    private utilerias.MenuButton btnSiguiente;
    private utilerias.MenuButton btnSucursales;
    private javax.swing.JComboBox<CiudadDTO> cbCiudades;
    private javax.swing.JComboBox<SucursalDTO> cbSucursales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JLabel lblPelicula1;
    private javax.swing.JLabel lblPelicula2;
    private javax.swing.JLabel lblPelicula3;
    private javax.swing.JLabel lblPelicula4;
    // End of variables declaration//GEN-END:variables
}
