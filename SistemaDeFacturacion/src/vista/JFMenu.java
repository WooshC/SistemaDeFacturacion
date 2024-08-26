package vista;

import Interfaz.LabelAdapter;
import conexion.Login;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modUsuarios.Empleado;
import vista.JFIngresar;
import vista.JFInventario;

public class JFMenu extends javax.swing.JFrame {

    //Mouse
    int xMouse, yMouse;
    //Atributos
    private Connection connection;
    private String username;
    private Login login;
    private Empleado empleado;
    private JPanel[] clickedPanels = new JPanel[6];
    private CardLayout contenido;
    private JFrame activeForm = null;

    private JLabel activeMenu; // Para rastrear el menú activo

    // Colores predefinidos
    private static final Color DEFAULT_COLOR = Color.decode("#292728");
    private static final Color HOVER_COLOR = Color.decode("#333333");
    private static final Color CLICKED_COLOR = Color.decode("#494848");

    public JFMenu(String userRole, Connection connection, String username) {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/iconos/AjustesBest.png")).getImage());
        this.connection = connection;
        this.username = username;
        this.login = new Login(connection);

        setLocationRelativeTo(null);
        initializeUserDisplay();
        initializePaneles();
        initializeMenuListeners();
        displayCurrentDate();
    }

    private JFMenu() {
        initComponents();
    }

    // Inicializar visualización de usuario
    private void initializeUserDisplay() {
        try {
            String[] nombreApellido = login.obtenerNombreApellido(username);
            txtID.setText(String.format("Usuario: %s %s", nombreApellido[0], nombreApellido[1]));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener los datos del usuario: " + e.getMessage());
        }
    }

    // Inicializar paneles
    private void initializePaneles() {
        contenido = (CardLayout) panelContent.getLayout();
        contenido.show(panelContent, "card1");

        clickedPanels[0] = Clicked1;
        clickedPanels[1] = Clicked2;
        clickedPanels[2] = Clicked3;
        clickedPanels[3] = Clicked4;
        clickedPanels[4] = Clicked5;
        clickedPanels[5] = Clicked6;

        for (JPanel panel : clickedPanels) {
            panel.setVisible(false);
        }
    }

    // Configurar eventos de los menús
    private void initializeMenuListeners() {
        menuInventario.addMouseListener(new LabelAdapter(menuInventario, Clicked1, clickedPanels, DEFAULT_COLOR, HOVER_COLOR, CLICKED_COLOR, this::abrirInventario));
        menuClientes.addMouseListener(new LabelAdapter(menuClientes, Clicked2, clickedPanels, DEFAULT_COLOR, HOVER_COLOR, CLICKED_COLOR));
        menuProveedores.addMouseListener(new LabelAdapter(menuProveedores, Clicked3, clickedPanels, DEFAULT_COLOR, HOVER_COLOR, CLICKED_COLOR));
        menuFacturacionYVenta.addMouseListener(new LabelAdapter(menuFacturacionYVenta, Clicked4, clickedPanels, DEFAULT_COLOR, HOVER_COLOR, CLICKED_COLOR));
        menuAdministracion.addMouseListener(new LabelAdapter(menuAdministracion, Clicked5, clickedPanels, DEFAULT_COLOR, HOVER_COLOR, CLICKED_COLOR));
        menuLogout.addMouseListener(new LabelAdapter(menuLogout, Clicked6, clickedPanels, DEFAULT_COLOR, HOVER_COLOR, CLICKED_COLOR, this::logoutAction));
    }

    // Mostrar la fecha actual
    private void displayCurrentDate() {
        String fecha = "dd-MM-yyyy";
        Locale localM = null;
        Date fechaYHora = new Date();
        txtDateLog.setText("Fecha  : " + mostrarFechaHora(fechaYHora, fecha, localM));
    }

    // Método estático para mostrar la fecha y hora
    private static String mostrarFechaHora(Date fechaYHora, String formato, Locale local) {
        SimpleDateFormat formateador = local == null ? new SimpleDateFormat(formato) : new SimpleDateFormat(formato, local);
        return formateador.format(fechaYHora);
    }

    // Acción de logout
    private void logoutAction() {
        getToolkit().beep();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "¿Estás seguro/a que quieres salir de esta cuenta?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            JFIngresar ingresarFrame = new JFIngresar();
            ingresarFrame.setVisible(true);
            dispose();
        } else {
            resetMenu(menuLogout, Clicked6);
        }
    }

    // Abrir formulario de inventario
    private void abrirInventario() {
        abrirFormHijo(new JFInventario());
    }

    // Abrir un formulario hijo en el panel contenedor
     private void abrirFormHijo(JFrame formHijo) {

        // Verifica si hay un formulario activo y ocúltalo si es necesario
        if (activeForm != null) {
            if (activeForm.getClass() == formHijo.getClass()) {
                return;
            }
            activeForm.setVisible(false);
        }

        // Asigna el nuevo formulario activo
        activeForm = formHijo;
        // Configura el JFrame antes de hacerlo visible
        // Esto debe hacerse antes de llamar a setVisible(true)
        if (!formHijo.isUndecorated()) {
            // Usa un Frame de configuración para configurar el JFrame
            // Nota: Necesitamos un JFrame temporal para aplicar la configuración
            JFrame tempFrame = new JFrame();
            tempFrame.setUndecorated(true);
            tempFrame.dispose(); // Destruye el marco temporal
        }

        // Ajusta el tamaño y la ubicación del formulario hijo
        formHijo.setSize(JPGPanelContenedor.getSize());
        formHijo.setLocation(JPGPanelContenedor.getLocation());
        JPGPanelContenedor.setLayout(null);
        // Configura el tamaño y la posición del formulario hijo antes de agregarlo
        formHijo.setBounds(0, 0, JPGPanelContenedor.getWidth(), JPGPanelContenedor.getHeight());
        // Añade el contenido al panel contenedor
        JPGPanelContenedor.removeAll();

        JPGPanelContenedor.add(formHijo.getContentPane());
        JPGPanelContenedor.revalidate();
        JPGPanelContenedor.repaint();

    }

    // Restablecer un menú al color y estado predeterminado
    private void resetMenu(JLabel menuLabel, JPanel panel) {
        menuLabel.setBackground(DEFAULT_COLOR);
        panel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Home = new javax.swing.JPanel();
        PanelHome = new javax.swing.JPanel();
        jPBarraDeInicio = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        menuProveedores = new javax.swing.JLabel();
        menuClientes = new javax.swing.JLabel();
        menuInventario = new javax.swing.JLabel();
        menuFacturacionYVenta = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        menuLogout = new javax.swing.JLabel();
        Clicked1 = new javax.swing.JPanel();
        Clicked2 = new javax.swing.JPanel();
        Clicked3 = new javax.swing.JPanel();
        Clicked4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        menuAdministracion = new javax.swing.JLabel();
        Clicked5 = new javax.swing.JPanel();
        Clicked6 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        panelContent = new javax.swing.JPanel();
        JPGPanelContenedor = new javax.swing.JPanel();
        jLInicio = new javax.swing.JLabel();
        txtID = new javax.swing.JLabel();
        txtDateLog = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        Home.setLayout(new java.awt.CardLayout());

        PanelHome.setBackground(new java.awt.Color(245, 245, 245));
        PanelHome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        PanelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPBarraDeInicio.setBackground(new java.awt.Color(146, 10, 48));
        jPBarraDeInicio.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPBarraDeInicioMouseDragged(evt);
            }
        });
        jPBarraDeInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPBarraDeInicioMousePressed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("SF");

        javax.swing.GroupLayout jPBarraDeInicioLayout = new javax.swing.GroupLayout(jPBarraDeInicio);
        jPBarraDeInicio.setLayout(jPBarraDeInicioLayout);
        jPBarraDeInicioLayout.setHorizontalGroup(
            jPBarraDeInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBarraDeInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69)
                .addContainerGap(1381, Short.MAX_VALUE))
        );
        jPBarraDeInicioLayout.setVerticalGroup(
            jPBarraDeInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPBarraDeInicioLayout.createSequentialGroup()
                .addComponent(jLabel69)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        PanelHome.add(jPBarraDeInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 20));

        jPanel1.setBackground(new java.awt.Color(41, 39, 40));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menuProveedores.setBackground(new java.awt.Color(41, 39, 40));
        menuProveedores.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuProveedores.setForeground(new java.awt.Color(255, 255, 255));
        menuProveedores.setText("Conductores");
        menuProveedores.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(menuProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 210, 50));

        menuClientes.setBackground(new java.awt.Color(41, 39, 40));
        menuClientes.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuClientes.setForeground(new java.awt.Color(255, 255, 255));
        menuClientes.setText("Clientes");
        menuClientes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(menuClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 210, 52));

        menuInventario.setBackground(new java.awt.Color(41, 39, 40));
        menuInventario.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuInventario.setForeground(new java.awt.Color(255, 255, 255));
        menuInventario.setText("Inventario");
        menuInventario.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(menuInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 210, 52));

        menuFacturacionYVenta.setBackground(new java.awt.Color(41, 39, 40));
        menuFacturacionYVenta.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuFacturacionYVenta.setForeground(new java.awt.Color(255, 255, 255));
        menuFacturacionYVenta.setText("Facturación");
        menuFacturacionYVenta.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuFacturacionYVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(menuFacturacionYVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 210, 52));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Version 0.1");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 740, -1, -1));

        menuLogout.setBackground(new java.awt.Color(41, 39, 40));
        menuLogout.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuLogout.setForeground(new java.awt.Color(255, 255, 255));
        menuLogout.setText("     Cerrar sesión");
        menuLogout.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(menuLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 210, 52));

        Clicked1.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked1Layout = new javax.swing.GroupLayout(Clicked1);
        Clicked1.setLayout(Clicked1Layout);
        Clicked1Layout.setHorizontalGroup(
            Clicked1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked1Layout.setVerticalGroup(
            Clicked1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 10, 52));

        Clicked2.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked2Layout = new javax.swing.GroupLayout(Clicked2);
        Clicked2.setLayout(Clicked2Layout);
        Clicked2Layout.setHorizontalGroup(
            Clicked2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked2Layout.setVerticalGroup(
            Clicked2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 10, 52));

        Clicked3.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked3Layout = new javax.swing.GroupLayout(Clicked3);
        Clicked3.setLayout(Clicked3Layout);
        Clicked3Layout.setHorizontalGroup(
            Clicked3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked3Layout.setVerticalGroup(
            Clicked3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 10, 52));

        Clicked4.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked4Layout = new javax.swing.GroupLayout(Clicked4);
        Clicked4.setLayout(Clicked4Layout);
        Clicked4Layout.setHorizontalGroup(
            Clicked4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked4Layout.setVerticalGroup(
            Clicked4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 10, 52));

        jPanel13.setBackground(new java.awt.Color(146, 10, 48));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(178, 8, 55));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Facturación");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel13.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 150, -1));

        jLabel2.setBackground(new java.awt.Color(178, 8, 55));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("2024");
        jPanel13.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 120, -1));

        jPanel1.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 220, 60));

        menuAdministracion.setBackground(new java.awt.Color(41, 39, 40));
        menuAdministracion.setFont(new java.awt.Font("Leelawadee UI Semilight", 0, 16)); // NOI18N
        menuAdministracion.setForeground(new java.awt.Color(255, 255, 255));
        menuAdministracion.setText("Administración");
        menuAdministracion.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        menuAdministracion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(menuAdministracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 210, 52));

        Clicked5.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked5Layout = new javax.swing.GroupLayout(Clicked5);
        Clicked5.setLayout(Clicked5Layout);
        Clicked5Layout.setHorizontalGroup(
            Clicked5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked5Layout.setVerticalGroup(
            Clicked5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 10, 52));

        Clicked6.setBackground(new java.awt.Color(178, 8, 55));

        javax.swing.GroupLayout Clicked6Layout = new javax.swing.GroupLayout(Clicked6);
        Clicked6.setLayout(Clicked6Layout);
        Clicked6Layout.setHorizontalGroup(
            Clicked6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        Clicked6Layout.setVerticalGroup(
            Clicked6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(Clicked6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, -1, 50));

        PanelHome.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 770));

        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8_Delete_32px.png"))); // NOI18N
        btnExit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnExit.setContentAreaFilled(false);
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        PanelHome.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 20, 35, 35));

        panelContent.setLayout(new java.awt.CardLayout());

        JPGPanelContenedor.setBackground(new java.awt.Color(245, 245, 245));
        JPGPanelContenedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLInicio.setFont(new java.awt.Font("Tahoma", 0, 64)); // NOI18N
        jLInicio.setForeground(new java.awt.Color(102, 102, 102));
        jLInicio.setText("Facturación");
        JPGPanelContenedor.add(jLInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, -1, -1));

        panelContent.add(JPGPanelContenedor, "card1");

        PanelHome.add(panelContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 1180, 690));

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(51, 51, 51));
        txtID.setText("Logged  :");
        PanelHome.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 290, -1));

        txtDateLog.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDateLog.setForeground(new java.awt.Color(51, 51, 51));
        txtDateLog.setText("Date      :");
        PanelHome.add(txtDateLog, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 310, -1));

        Home.add(PanelHome, "home");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPBarraDeInicioMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBarraDeInicioMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPBarraDeInicioMouseDragged

    private void jPBarraDeInicioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPBarraDeInicioMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPBarraDeInicioMousePressed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        getToolkit().beep();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Estas seguro de cerrar la aplicacion?", "Warning",
                dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Clicked1;
    private javax.swing.JPanel Clicked2;
    private javax.swing.JPanel Clicked3;
    private javax.swing.JPanel Clicked4;
    private javax.swing.JPanel Clicked5;
    private javax.swing.JPanel Clicked6;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel JPGPanelContenedor;
    private javax.swing.JPanel PanelHome;
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel jLInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JPanel jPBarraDeInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JLabel menuAdministracion;
    private javax.swing.JLabel menuClientes;
    private javax.swing.JLabel menuFacturacionYVenta;
    private javax.swing.JLabel menuInventario;
    private javax.swing.JLabel menuLogout;
    private javax.swing.JLabel menuProveedores;
    private javax.swing.JPanel panelContent;
    private javax.swing.JLabel txtDateLog;
    private javax.swing.JLabel txtID;
    // End of variables declaration//GEN-END:variables
}
