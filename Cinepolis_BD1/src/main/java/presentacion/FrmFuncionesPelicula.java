/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import com.mysql.cj.CoreSession;
import dtos.CiudadDTO;
import dtos.ClienteDTO;
import dtos.ConsultaFuncionDTO;
import dtos.FuncionDTO;
import dtos.PeliculaDTO;
import dtos.SalaDTO;
import dtos.SucursalDTO;
import java.awt.Image;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import negocio.CiudadBO;
import negocio.GeneroBO;
import negocio.ICiudadBO;
import negocio.IFuncionBO;
import negocio.IGeneroBO;
import negocio.IPeliculaBO;
import negocio.ISalaBO;
import negocio.ISucursalBO;
import negocio.ITicketBO;
import negocio.IVentaBO;
import negocio.NegocioException;
import negocio.PeliculaBO;
import negocio.SalaBO;
import negocio.SucursalBO;
import negocio.TicketBO;
import negocio.VentaBO;
import persistencia.CiudadDAO;
import persistencia.ConexionBD;
import persistencia.GeneroDAO;
import persistencia.ICiudadDAO;
import persistencia.IConexionBD;
import persistencia.IGeneroDAO;
import persistencia.IPeliculaDAO;
import persistencia.ISalaDAO;
import persistencia.ISucursalDAO;
import persistencia.ITicketDAO;
import persistencia.IVentaDAO;
import persistencia.PeliculaDAO;
import persistencia.SalaDAO;
import persistencia.SucursalDAO;
import persistencia.TicketDAO;
import persistencia.VentaDAO;

/**
 *
 * @author carli
 */
public class FrmFuncionesPelicula extends javax.swing.JFrame {

    private int pagina = 1;
    private final int LIMITE = 9;
    private JButton[] botones;
    private ClienteDTO cliente;
    private IFuncionBO funcionBO;
    private SucursalDTO sucursal;
    private PeliculaDTO pelicula;
    private CiudadDTO ciudad;
    private ConsultaFuncionDTO consulta;
    private List<FuncionDTO> funcioneCargadas;

    /**
     * Creates new form FrmCatalogos
     */
    public FrmFuncionesPelicula(IFuncionBO funcionBO, PeliculaDTO pelicula, SucursalDTO sucursal, ClienteDTO cliente, CiudadDTO ciudad) {
        initComponents();
        this.funcionBO = funcionBO;
        this.pelicula = pelicula;
        this.sucursal = sucursal;
        this.cliente = cliente;
        this.ciudad = ciudad;
        System.out.println(pelicula.getId());
        botones = new JButton[]{btnFuncion, btnFuncion1, btnFuncion2, btnFuncion3, btnFuncion4, btnFuncion5, btnFuncion6, btnFuncion7, btnFuncion8};
        consulta = new ConsultaFuncionDTO(obtenerNombreDiaDeHoy(), sucursal.getId(), pelicula.getId());
        this.funcioneCargadas = new ArrayList<>();
        cargarMetodosIniciales();
    }

    private void cargarMetodosIniciales() {
        this.setTitle(sucursal.getNombre());
        ImageIcon icon = new ImageIcon(pelicula.getCartel());
        Image scaledImage = icon.getImage().getScaledInstance(280, 340, Image.SCALE_SMOOTH);
        btnImage.setIcon(new ImageIcon(scaledImage));
        this.lblPelicula.setText(pelicula.getTitulo());
        this.lblDuracion.setText("Duracion: " + pelicula.getDuracion() + " minutos");
        this.setLocationRelativeTo(null);
        this.cargarFunciones();
        this.estadoPagina();

    }

    public void cargarFunciones() {
        try {
            System.out.println(consulta.getIdPelicula() + "hola");
            List<FuncionDTO> funcionLista = this.funcionBO.listaFuncionporDiaSucursalPelicula(consulta, LIMITE, pagina);
            System.out.println(funcionLista);
            funcioneCargadas.clear();
            funcioneCargadas.addAll(funcionLista);
            this.llenarCampos(funcionLista);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Informacion", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void llenarCampos(List<FuncionDTO> funcionLista) {
        for (int i = 0; i < funcionLista.size(); i++) {
            botones[i].setEnabled(true);
            botones[i].setText(funcionLista.get(i).getHora() + "-" + funcionLista.get(i).getHoraFinal());
        }
        // Limpiar botones y etiquetas restantes
        for (int i = funcionLista.size(); i < LIMITE; i++) {
            botones[i].setText("No disponible");
            botones[i].setEnabled(false);

        }
    }

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
            if (this.funcionBO.listaFuncionporDiaSucursalPelicula(consulta, LIMITE, pagina + 1) == null
                    || this.funcionBO.listaFuncionporDiaSucursalPelicula(consulta, LIMITE, pagina).isEmpty()) {
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
        lblPelicula = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        btnImage = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        btnLunes = new utilerias.MenuButton();
        btnMartes = new utilerias.MenuButton();
        btnMiercoles = new utilerias.MenuButton();
        btnJueves = new utilerias.MenuButton();
        btnViernes = new utilerias.MenuButton();
        Sabado = new utilerias.MenuButton();
        btnDomingo = new utilerias.MenuButton();
        btnAtras = new utilerias.MenuButton();
        btnSiguiente = new utilerias.MenuButton();
        btnDetalles = new utilerias.MenuButton();
        jPanel13 = new javax.swing.JPanel();
        panelConFondoHora3 = new utilerias.PanelConFondoHora();
        btnFuncion = new javax.swing.JButton();
        panelConFondoHora1 = new utilerias.PanelConFondoHora();
        btnFuncion1 = new javax.swing.JButton();
        panelConFondoHora10 = new utilerias.PanelConFondoHora();
        btnFuncion2 = new javax.swing.JButton();
        panelConFondoHora2 = new utilerias.PanelConFondoHora();
        btnFuncion3 = new javax.swing.JButton();
        panelConFondoHora6 = new utilerias.PanelConFondoHora();
        btnFuncion4 = new javax.swing.JButton();
        panelConFondoHora5 = new utilerias.PanelConFondoHora();
        btnFuncion5 = new javax.swing.JButton();
        panelConFondoHora8 = new utilerias.PanelConFondoHora();
        btnFuncion6 = new javax.swing.JButton();
        panelConFondoHora11 = new utilerias.PanelConFondoHora();
        btnFuncion7 = new javax.swing.JButton();
        panelConFondoHora12 = new utilerias.PanelConFondoHora();
        btnFuncion8 = new javax.swing.JButton();
        lblPagina = new javax.swing.JLabel();

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
            .addGap(0, 1312, Short.MAX_VALUE)
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

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Line 7.png"))); // NOI18N

        lblPelicula.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblPelicula.setForeground(new java.awt.Color(255, 255, 255));
        lblPelicula.setText("Top Gun Maverick");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addGap(113, 113, 113)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPelicula)
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
                        .addComponent(lblPelicula)
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
            .addGap(0, 1312, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel5.setBackground(new java.awt.Color(36, 44, 99));

        jPanel9.setBackground(new java.awt.Color(36, 44, 99));

        jPanel10.setBackground(new java.awt.Color(36, 44, 99));

        btnImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/3212c44f6aca4bc69d467d4614e6f3dc 1.png"))); // NOI18N
        btnImage.setBorderPainted(false);
        btnImage.setContentAreaFilled(false);
        btnImage.setPreferredSize(new java.awt.Dimension(229, 353));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnImage, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(36, 44, 99));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setToolTipText("");

        lblDuracion.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblDuracion.setForeground(new java.awt.Color(255, 255, 255));
        lblDuracion.setText("Duracion: 160 minutos");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(582, 582, 582)
                                .addComponent(jLabel2))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDuracion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        jPanel12.setBackground(new java.awt.Color(36, 44, 99));

        jPanel11.setBackground(new java.awt.Color(36, 44, 99));

        btnLunes.setBorder(null);
        btnLunes.setForeground(new java.awt.Color(255, 255, 255));
        btnLunes.setText("Lunes");
        btnLunes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnLunes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLunesActionPerformed(evt);
            }
        });

        btnMartes.setBorder(null);
        btnMartes.setForeground(new java.awt.Color(255, 255, 255));
        btnMartes.setText("Martes");
        btnMartes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnMartes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMartesActionPerformed(evt);
            }
        });

        btnMiercoles.setBorder(null);
        btnMiercoles.setForeground(new java.awt.Color(255, 255, 255));
        btnMiercoles.setText("Miercoles");
        btnMiercoles.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnMiercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMiercolesActionPerformed(evt);
            }
        });

        btnJueves.setBorder(null);
        btnJueves.setForeground(new java.awt.Color(255, 255, 255));
        btnJueves.setText("Jueves");
        btnJueves.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnJueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJuevesActionPerformed(evt);
            }
        });

        btnViernes.setBorder(null);
        btnViernes.setForeground(new java.awt.Color(255, 255, 255));
        btnViernes.setText("Viernes");
        btnViernes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnViernes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViernesActionPerformed(evt);
            }
        });

        Sabado.setBorder(null);
        Sabado.setForeground(new java.awt.Color(255, 255, 255));
        Sabado.setText("Sabado");
        Sabado.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Sabado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SabadoActionPerformed(evt);
            }
        });

        btnDomingo.setBorder(null);
        btnDomingo.setForeground(new java.awt.Color(255, 255, 255));
        btnDomingo.setText("Domingo");
        btnDomingo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnDomingo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDomingoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(btnLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(Sabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14)
                    .addComponent(btnDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(37, Short.MAX_VALUE)))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnLunes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMartes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Sabado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnJueves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnViernes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(14, Short.MAX_VALUE)))
        );

        btnAtras.setBorderPainted(false);
        btnAtras.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/leftSelected.png"))); // NOI18N
        btnAtras.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/left.png"))); // NOI18N
        btnAtras.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/leftSelected.png"))); // NOI18N

        btnSiguiente.setBorderPainted(false);
        btnSiguiente.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/rightSelected.png"))); // NOI18N
        btnSiguiente.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/right.png"))); // NOI18N

        btnDetalles.setBorderPainted(false);
        btnDetalles.setIconoSeleccionado(new javax.swing.ImageIcon(getClass().getResource("/imagenes/infroPeli.png"))); // NOI18N
        btnDetalles.setIconoSimple(new javax.swing.ImageIcon(getClass().getResource("/imagenes/infroPeli.png"))); // NOI18N
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(36, 44, 99));

        panelConFondoHora3.setOpaque(false);

        btnFuncion.setBackground(new java.awt.Color(255, 51, 51));
        btnFuncion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFuncion.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncion.setText("00:00");
        btnFuncion.setBorderPainted(false);
        btnFuncion.setContentAreaFilled(false);
        btnFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConFondoHora3Layout = new javax.swing.GroupLayout(panelConFondoHora3);
        panelConFondoHora3.setLayout(panelConFondoHora3Layout);
        panelConFondoHora3Layout.setHorizontalGroup(
            panelConFondoHora3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora3Layout.createSequentialGroup()
                .addComponent(btnFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelConFondoHora3Layout.setVerticalGroup(
            panelConFondoHora3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora3Layout.createSequentialGroup()
                .addComponent(btnFuncion, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.add(panelConFondoHora3);

        panelConFondoHora1.setOpaque(false);

        btnFuncion1.setBackground(new java.awt.Color(255, 51, 51));
        btnFuncion1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFuncion1.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncion1.setText("00:00");
        btnFuncion1.setBorderPainted(false);
        btnFuncion1.setContentAreaFilled(false);
        btnFuncion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncion1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConFondoHora1Layout = new javax.swing.GroupLayout(panelConFondoHora1);
        panelConFondoHora1.setLayout(panelConFondoHora1Layout);
        panelConFondoHora1Layout.setHorizontalGroup(
            panelConFondoHora1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora1Layout.createSequentialGroup()
                .addComponent(btnFuncion1, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelConFondoHora1Layout.setVerticalGroup(
            panelConFondoHora1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora1Layout.createSequentialGroup()
                .addComponent(btnFuncion1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.add(panelConFondoHora1);

        panelConFondoHora10.setOpaque(false);

        btnFuncion2.setBackground(new java.awt.Color(255, 51, 51));
        btnFuncion2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFuncion2.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncion2.setText("00:00");
        btnFuncion2.setBorderPainted(false);
        btnFuncion2.setContentAreaFilled(false);
        btnFuncion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncion2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConFondoHora10Layout = new javax.swing.GroupLayout(panelConFondoHora10);
        panelConFondoHora10.setLayout(panelConFondoHora10Layout);
        panelConFondoHora10Layout.setHorizontalGroup(
            panelConFondoHora10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora10Layout.createSequentialGroup()
                .addComponent(btnFuncion2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelConFondoHora10Layout.setVerticalGroup(
            panelConFondoHora10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora10Layout.createSequentialGroup()
                .addComponent(btnFuncion2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.add(panelConFondoHora10);

        panelConFondoHora2.setOpaque(false);

        btnFuncion3.setBackground(new java.awt.Color(255, 51, 51));
        btnFuncion3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFuncion3.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncion3.setText("00:00");
        btnFuncion3.setBorderPainted(false);
        btnFuncion3.setContentAreaFilled(false);
        btnFuncion3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncion3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConFondoHora2Layout = new javax.swing.GroupLayout(panelConFondoHora2);
        panelConFondoHora2.setLayout(panelConFondoHora2Layout);
        panelConFondoHora2Layout.setHorizontalGroup(
            panelConFondoHora2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora2Layout.createSequentialGroup()
                .addComponent(btnFuncion3, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelConFondoHora2Layout.setVerticalGroup(
            panelConFondoHora2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora2Layout.createSequentialGroup()
                .addComponent(btnFuncion3, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.add(panelConFondoHora2);

        panelConFondoHora6.setOpaque(false);

        btnFuncion4.setBackground(new java.awt.Color(255, 51, 51));
        btnFuncion4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFuncion4.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncion4.setText("00:00");
        btnFuncion4.setBorderPainted(false);
        btnFuncion4.setContentAreaFilled(false);
        btnFuncion4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncion4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConFondoHora6Layout = new javax.swing.GroupLayout(panelConFondoHora6);
        panelConFondoHora6.setLayout(panelConFondoHora6Layout);
        panelConFondoHora6Layout.setHorizontalGroup(
            panelConFondoHora6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora6Layout.createSequentialGroup()
                .addComponent(btnFuncion4, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelConFondoHora6Layout.setVerticalGroup(
            panelConFondoHora6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora6Layout.createSequentialGroup()
                .addComponent(btnFuncion4, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.add(panelConFondoHora6);

        panelConFondoHora5.setOpaque(false);

        btnFuncion5.setBackground(new java.awt.Color(255, 51, 51));
        btnFuncion5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFuncion5.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncion5.setText("00:00");
        btnFuncion5.setBorderPainted(false);
        btnFuncion5.setContentAreaFilled(false);
        btnFuncion5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncion5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConFondoHora5Layout = new javax.swing.GroupLayout(panelConFondoHora5);
        panelConFondoHora5.setLayout(panelConFondoHora5Layout);
        panelConFondoHora5Layout.setHorizontalGroup(
            panelConFondoHora5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora5Layout.createSequentialGroup()
                .addComponent(btnFuncion5, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelConFondoHora5Layout.setVerticalGroup(
            panelConFondoHora5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora5Layout.createSequentialGroup()
                .addComponent(btnFuncion5, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.add(panelConFondoHora5);

        panelConFondoHora8.setOpaque(false);

        btnFuncion6.setBackground(new java.awt.Color(255, 51, 51));
        btnFuncion6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFuncion6.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncion6.setText("00:00");
        btnFuncion6.setBorderPainted(false);
        btnFuncion6.setContentAreaFilled(false);
        btnFuncion6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncion6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConFondoHora8Layout = new javax.swing.GroupLayout(panelConFondoHora8);
        panelConFondoHora8.setLayout(panelConFondoHora8Layout);
        panelConFondoHora8Layout.setHorizontalGroup(
            panelConFondoHora8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora8Layout.createSequentialGroup()
                .addComponent(btnFuncion6, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelConFondoHora8Layout.setVerticalGroup(
            panelConFondoHora8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora8Layout.createSequentialGroup()
                .addComponent(btnFuncion6, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.add(panelConFondoHora8);

        panelConFondoHora11.setOpaque(false);

        btnFuncion7.setBackground(new java.awt.Color(255, 51, 51));
        btnFuncion7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFuncion7.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncion7.setText("00:00");
        btnFuncion7.setBorderPainted(false);
        btnFuncion7.setContentAreaFilled(false);
        btnFuncion7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncion7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConFondoHora11Layout = new javax.swing.GroupLayout(panelConFondoHora11);
        panelConFondoHora11.setLayout(panelConFondoHora11Layout);
        panelConFondoHora11Layout.setHorizontalGroup(
            panelConFondoHora11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora11Layout.createSequentialGroup()
                .addComponent(btnFuncion7, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelConFondoHora11Layout.setVerticalGroup(
            panelConFondoHora11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora11Layout.createSequentialGroup()
                .addComponent(btnFuncion7, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.add(panelConFondoHora11);

        panelConFondoHora12.setOpaque(false);

        btnFuncion8.setBackground(new java.awt.Color(255, 51, 51));
        btnFuncion8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnFuncion8.setForeground(new java.awt.Color(255, 255, 255));
        btnFuncion8.setText("00:00");
        btnFuncion8.setBorderPainted(false);
        btnFuncion8.setContentAreaFilled(false);
        btnFuncion8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFuncion8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConFondoHora12Layout = new javax.swing.GroupLayout(panelConFondoHora12);
        panelConFondoHora12.setLayout(panelConFondoHora12Layout);
        panelConFondoHora12Layout.setHorizontalGroup(
            panelConFondoHora12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora12Layout.createSequentialGroup()
                .addComponent(btnFuncion8, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelConFondoHora12Layout.setVerticalGroup(
            panelConFondoHora12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConFondoHora12Layout.createSequentialGroup()
                .addComponent(btnFuncion8, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.add(panelConFondoHora12);

        lblPagina.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPagina.setForeground(new java.awt.Color(255, 255, 255));
        lblPagina.setText("Pagina 1");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(btnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(254, 254, 254))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(lblPagina)
                        .addGap(372, 372, 372))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(lblPagina)
                .addGap(18, 18, 18)
                .addComponent(btnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(38, 38, 38))
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1312, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLunesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLunesActionPerformed
        consulta.setDia("Lunes");
        pagina = 1;
        this.cargarFunciones();

    }//GEN-LAST:event_btnLunesActionPerformed

    private void btnMartesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMartesActionPerformed
        consulta.setDia("Martes");
        pagina = 1;
        this.cargarFunciones();


    }//GEN-LAST:event_btnMartesActionPerformed

    private void btnMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiercolesActionPerformed
        consulta.setDia("Miércoles");
        pagina = 1;
        this.cargarFunciones();


    }//GEN-LAST:event_btnMiercolesActionPerformed

    private void btnJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJuevesActionPerformed
        consulta.setDia("Jueves");
        pagina = 1;
        this.cargarFunciones();

    }//GEN-LAST:event_btnJuevesActionPerformed

    private void btnViernesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViernesActionPerformed
        consulta.setDia("Viernes");
        pagina = 1;
        this.cargarFunciones();

    }//GEN-LAST:event_btnViernesActionPerformed

    private void SabadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SabadoActionPerformed
        consulta.setDia("Sábado");
        pagina = 1;
        this.cargarFunciones();

    }//GEN-LAST:event_SabadoActionPerformed

    private void btnDomingoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDomingoActionPerformed
        consulta.setDia("Domingo");
        pagina = 1;
        this.cargarFunciones();
    }//GEN-LAST:event_btnDomingoActionPerformed

    private void btnSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSucursalesActionPerformed
        IConexionBD conexion = new ConexionBD();
        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        ISucursalDAO sucursalDAO = new SucursalDAO(conexion);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        FrmSucursales sucursales = new FrmSucursales(ciudadBO, sucursalBO, sucursal, cliente);
        sucursales.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSucursalesActionPerformed

    private void btnBoletosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoletosActionPerformed
        IConexionBD conexionBD = new ConexionBD();
        ITicketDAO ticketDAO = new TicketDAO(conexionBD);
        ITicketBO ticketBO = new TicketBO(ticketDAO);
        FrmBoletos boletos = new FrmBoletos(ticketBO, cliente, sucursal);
        boletos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBoletosActionPerformed

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

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        IConexionBD conexion = new ConexionBD();
        ICiudadDAO ciudadDAO = new CiudadDAO(conexion);
        ISucursalDAO sucursalDAO = new SucursalDAO(conexion);
        IPeliculaDAO peliculaDAO = new PeliculaDAO(conexion);
        IPeliculaBO peliculaBO = new PeliculaBO(peliculaDAO);
        ISucursalBO sucursalBO = new SucursalBO(sucursalDAO);
        ICiudadBO ciudadBO = new CiudadBO(ciudadDAO);
        FrmCatalogoSucursal catalogo = new FrmCatalogoSucursal(peliculaBO, ciudadBO, sucursalBO, sucursal, cliente);
        catalogo.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncionActionPerformed
        FuncionDTO funcion = this.funcioneCargadas.get(0);
        this.comprarBoleto(funcion);
    }//GEN-LAST:event_btnFuncionActionPerformed

    private void btnFuncion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncion1ActionPerformed
        FuncionDTO funcion = this.funcioneCargadas.get(1);
        this.comprarBoleto(funcion);
    }//GEN-LAST:event_btnFuncion1ActionPerformed

    private void btnFuncion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncion2ActionPerformed
        FuncionDTO funcion = this.funcioneCargadas.get(2);
        this.comprarBoleto(funcion);

    }//GEN-LAST:event_btnFuncion2ActionPerformed

    private void btnFuncion3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncion3ActionPerformed
        FuncionDTO funcion = this.funcioneCargadas.get(3);
        this.comprarBoleto(funcion);
    }//GEN-LAST:event_btnFuncion3ActionPerformed

    private void btnFuncion4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncion4ActionPerformed
        FuncionDTO funcion = this.funcioneCargadas.get(4);
        this.comprarBoleto(funcion);
    }//GEN-LAST:event_btnFuncion4ActionPerformed

    private void btnFuncion5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncion5ActionPerformed
        FuncionDTO funcion = this.funcioneCargadas.get(5);
        this.comprarBoleto(funcion);
    }//GEN-LAST:event_btnFuncion5ActionPerformed

    private void btnFuncion6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncion6ActionPerformed
        FuncionDTO funcion = this.funcioneCargadas.get(6);
        this.comprarBoleto(funcion);
    }//GEN-LAST:event_btnFuncion6ActionPerformed

    private void btnFuncion7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncion7ActionPerformed
        FuncionDTO funcion = this.funcioneCargadas.get(7);
        this.comprarBoleto(funcion);
    }//GEN-LAST:event_btnFuncion7ActionPerformed

    private void btnFuncion8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFuncion8ActionPerformed
        FuncionDTO funcion = this.funcioneCargadas.get(8);
        this.comprarBoleto(funcion);
    }//GEN-LAST:event_btnFuncion8ActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        IConexionBD conexion = new ConexionBD();
        IGeneroDAO generoDAO = new GeneroDAO(conexion);
        IGeneroBO generoBO = new GeneroBO(generoDAO);
        FrmDetallePelicula detalles = new FrmDetallePelicula(generoBO, pelicula, cliente, sucursal, ciudad);
        detalles.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDetallesActionPerformed

    private void comprarBoleto(FuncionDTO funcion) {
        IConexionBD conexion = new ConexionBD();
        IVentaDAO ventaDAO = new VentaDAO(conexion);
        IVentaBO ventaBO = new VentaBO(ventaDAO);
        ISalaDAO salaDAO = new SalaDAO(conexion);
        ISalaBO salaBO = new SalaBO(salaDAO);

        SalaDTO sala;
        try {
            sala = salaBO.leerPorId(funcion.getId());

            FrmCompraBoleto comprar = new FrmCompraBoleto(ventaBO, pelicula, cliente, funcion, sucursal, sala, ciudad);
            comprar.setVisible(true);
            this.dispose();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmFuncionesPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String obtenerNombreDiaDeHoy() {
        // Obtener el día de hoy
        LocalDate hoy = LocalDate.now();

        // Obtener el nombre del día en español
        return hoy.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilerias.MenuButton Sabado;
    private utilerias.MenuButton btnAtras;
    private utilerias.MenuButton btnBoletos;
    private utilerias.MenuButton btnDetalles;
    private utilerias.MenuButton btnDomingo;
    private javax.swing.JButton btnFuncion;
    private javax.swing.JButton btnFuncion1;
    private javax.swing.JButton btnFuncion2;
    private javax.swing.JButton btnFuncion3;
    private javax.swing.JButton btnFuncion4;
    private javax.swing.JButton btnFuncion5;
    private javax.swing.JButton btnFuncion6;
    private javax.swing.JButton btnFuncion7;
    private javax.swing.JButton btnFuncion8;
    private javax.swing.JButton btnImage;
    private utilerias.MenuButton btnInbox;
    private utilerias.MenuButton btnInicio;
    private utilerias.MenuButton btnJueves;
    private utilerias.MenuButton btnLunes;
    private utilerias.MenuButton btnMartes;
    private utilerias.MenuButton btnMiercoles;
    private utilerias.MenuButton btnPerfil;
    private utilerias.MenuButton btnSiguiente;
    private utilerias.MenuButton btnSucursales;
    private utilerias.MenuButton btnViernes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JLabel lblPelicula;
    private utilerias.PanelConFondoHora panelConFondoHora1;
    private utilerias.PanelConFondoHora panelConFondoHora10;
    private utilerias.PanelConFondoHora panelConFondoHora11;
    private utilerias.PanelConFondoHora panelConFondoHora12;
    private utilerias.PanelConFondoHora panelConFondoHora2;
    private utilerias.PanelConFondoHora panelConFondoHora3;
    private utilerias.PanelConFondoHora panelConFondoHora5;
    private utilerias.PanelConFondoHora panelConFondoHora6;
    private utilerias.PanelConFondoHora panelConFondoHora8;
    // End of variables declaration//GEN-END:variables
}
