package vista;

import conexion.ConexionBD;
import conexion.Login;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;

/**
 *
 * @author Woosh
 */
public class JFIngresar extends javax.swing.JFrame {

    String database = "sistemafacturacion"; //Ingresa el nombre de tu base de dat
    int xMouse, yMouse;
    private Connection connection;
    private Login login;
    private Map<String, Integer> intentosFallidos = new HashMap<>();

    public JFIngresar(){
    initComponents();
    setIconImage(new ImageIcon(getClass().getResource("/Iconos/AjustesBest.png")).getImage());
    this.setLocationRelativeTo(null);
    setTitle("Login Form");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Font originalFont = olvidasteContra.getFont();
    Font boldFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize());
    Color originalColor = olvidasteContra.getForeground();
    Color hoverColor = new Color(0, 0, 255);

    olvidasteContra.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            olvidasteContra.setFont(boldFont);
            olvidasteContra.setForeground(hoverColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            olvidasteContra.setFont(originalFont);
            olvidasteContra.setForeground(originalColor);
        }
    });
       
}
    
    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void mostrarMenu(String userRole, Connection connection, String username) {
        if ("Administrador".equals(userRole)) {
            JFMenu menu = new JFMenu(userRole, connection, username);
            this.setVisible(false);
            menu.setVisible(true);
        } else {
            // Aquí puedes implementar la lógica para otros roles si es necesario
        }
    }

    private void registrarIntentoFallido(String username) {
        int intentos = intentosFallidos.getOrDefault(username, 0) + 1;
        intentosFallidos.put(username, intentos);

        if (intentos >= 4) { // Bloquear después de 4 intentos fallidos
            try {
                login.bloquearUsuario(username);
                mostrarMensajeError("Usuario bloqueado por intentos fallidos.");
            } catch (SQLException e) {
                mostrarMensajeError("Error al bloquear el usuario: " + e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        exitP = new javax.swing.JPanel();
        exitTXT = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTFUser = new javax.swing.JTextField();
        jTFPassword = new javax.swing.JPasswordField();
        jBIngresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jBMostrarC = new javax.swing.JButton();
        olvidasteContra = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(146, 10, 48));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(460, 24));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        exitP.setBackground(new java.awt.Color(146, 10, 48));
        exitP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        exitTXT.setFont(new java.awt.Font("Roboto Black", 0, 12)); // NOI18N
        exitTXT.setForeground(new java.awt.Color(255, 255, 255));
        exitTXT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitTXT.setText("X");
        exitTXT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitTXTMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitTXTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitTXTMouseExited(evt);
            }
        });

        javax.swing.GroupLayout exitPLayout = new javax.swing.GroupLayout(exitP);
        exitP.setLayout(exitPLayout);
        exitPLayout.setHorizontalGroup(
            exitPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitPLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        exitPLayout.setVerticalGroup(
            exitPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitPLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Inicio de sesión");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                .addComponent(exitP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(exitP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, -1));

        jTFUser.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTFUser.setForeground(new java.awt.Color(204, 204, 204));
        jTFUser.setText("Ingresa tu nombre de usuario");
        jTFUser.setBorder(null);
        jTFUser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFUserFocusGained(evt);
            }
        });
        jTFUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTFUserKeyTyped(evt);
            }
        });
        bg.add(jTFUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 340, 20));

        jTFPassword.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTFPassword.setForeground(new java.awt.Color(204, 204, 204));
        jTFPassword.setText("Ingresa tu contraseña");
        jTFPassword.setBorder(null);
        jTFPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTFPasswordFocusGained(evt);
            }
        });
        jTFPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFPasswordKeyPressed(evt);
            }
        });
        bg.add(jTFPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 340, 20));

        jBIngresar.setBackground(new java.awt.Color(255, 250, 243));
        jBIngresar.setText("Ingresar");
        jBIngresar.setBorder(null);
        jBIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBIngresarMouseExited(evt);
            }
        });
        jBIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBIngresarActionPerformed(evt);
            }
        });
        bg.add(jBIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 101, 30));

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel1.setText("Contraseña");
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jBMostrarC.setBackground(new java.awt.Color(255, 250, 243));
        jBMostrarC.setText("Mostar Contraseña");
        jBMostrarC.setBorder(null);
        jBMostrarC.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBMostrarC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBMostrarCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBMostrarCMouseExited(evt);
            }
        });
        jBMostrarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMostrarCActionPerformed(evt);
            }
        });
        bg.add(jBMostrarC, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, 30));

        olvidasteContra.setForeground(new java.awt.Color(102, 153, 255));
        olvidasteContra.setText("¿Olvidaste tu contraseña o usuario?");
        olvidasteContra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        olvidasteContra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                olvidasteContraMouseClicked(evt);
            }
        });
        bg.add(olvidasteContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/gestionDePaquetes.png"))); // NOI18N
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 460, 120));

        jLabel4.setFont(new java.awt.Font("Roboto Medium", 1, 24)); // NOI18N
        jLabel4.setText("Iniciar sesión");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 340, 10));

        jLabel5.setFont(new java.awt.Font("Roboto Medium", 0, 12)); // NOI18N
        jLabel5.setText("Usuario");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 340, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBIngresarActionPerformed
         ConexionBD conexionBD = new ConexionBD(); // Aquí podrías agregar más lógica para manejar el error de conexión.
        connection = conexionBD.getConexion();
        login = new Login(connection); // Inicialización correcta
        String username = jTFUser.getText();
        String password = new String(jTFPassword.getPassword());      

        if (username.isEmpty() && password.isEmpty()) {
            mostrarMensajeError("Ingrese un usuario y contraseña");
        } else if (username.isEmpty()) {
            mostrarMensajeError("Ingrese un usuario");
        } else if (password.isEmpty()) {
            mostrarMensajeError("Ingrese una contraseña");
        } else {
            try {
                // Verificar si el usuario está bloqueado
                if (login.usuarioBloqueado(username)) {
                    mostrarMensajeError("Usuario bloqueado. Intentos excedidos.");
                    return;
                }

                // Verificar si el usuario existe y si la contraseña es correcta
                if (!login.verificarUsuario(username, password)) {
                    mostrarMensajeError("Usuario o contraseña incorrectos");
                    registrarIntentoFallido(username);
                    return;
                }

                // Obtener el rol del usuario
                String userRole = login.obtenerRolUsuario(username);

                if (userRole == null) {
                    mostrarMensajeError("Usuario Incorrecto");
                } else {
                    mostrarMenu(userRole, connection, username);
                }
            } catch (SQLException e) {
                Logger.getLogger(JFIngresar.class.getName()).log(Level.SEVERE, null, e);
                mostrarMensajeError("Error al verificar el usuario.");
            }
        }
    }//GEN-LAST:event_jBIngresarActionPerformed

    private void jBMostrarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMostrarCActionPerformed
        if (jTFPassword.echoCharIsSet()) {
            jTFPassword.setEchoChar((char) 0);
        } else {
            jTFPassword.setEchoChar('*');
        }
    }//GEN-LAST:event_jBMostrarCActionPerformed

    private void olvidasteContraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_olvidasteContraMouseClicked
        this.dispose();
        JFRecuperar recuperar = new JFRecuperar();
        recuperar.setVisible(true);
    }//GEN-LAST:event_olvidasteContraMouseClicked

    private void jTFUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFUserKeyTyped
        char c = evt.getKeyChar();
        if (c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
            if (!((Character.isLetter(c) && Character.isLowerCase(c))
                    || (Character.isLetter(c) && Character.isUpperCase(c))
                    || Character.isDigit(c) || c == 'ñ' || c == 'Ñ')) {
                evt.consume(); // No permite ingresar el carácter

                // Mostrar mensaje de advertencia
                JOptionPane.showMessageDialog(this, "Solo se permiten letras y números.");
            }
        }
    }//GEN-LAST:event_jTFUserKeyTyped

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_jPanel1MouseDragged

    private void exitTXTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTXTMouseClicked
        int dialogResult = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres cerrar el sistema?",
                "Confirmación de cierre", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitTXTMouseClicked

    private void exitTXTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTXTMouseEntered
        exitP.setBackground(Color.red);
    }//GEN-LAST:event_exitTXTMouseEntered

    private void exitTXTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitTXTMouseExited
        exitP.setBackground(new Color(146, 10, 48));
    }//GEN-LAST:event_exitTXTMouseExited

    private void jBMostrarCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBMostrarCMouseEntered
        jBMostrarC.setBackground(new Color(255, 51, 133));
        //[255,51,133]
    }//GEN-LAST:event_jBMostrarCMouseEntered

    private void jBMostrarCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBMostrarCMouseExited
        // [255,250,243]
        jBMostrarC.setBackground(new Color(255, 250, 243));
    }//GEN-LAST:event_jBMostrarCMouseExited

    private void jBIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBIngresarMouseEntered
        jBIngresar.setBackground(new Color(255, 51, 133));
    }//GEN-LAST:event_jBIngresarMouseEntered

    private void jBIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBIngresarMouseExited
        jBIngresar.setBackground(new Color(255, 250, 243));
    }//GEN-LAST:event_jBIngresarMouseExited

    private void jTFPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFPasswordKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFPasswordKeyPressed

    private void jTFUserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFUserFocusGained
        String password = new String(jTFPassword.getPassword());
        if (jTFUser.getText().equals("Ingresa tu nombre de usuario")) {
            jTFUser.setText("");
            jTFUser.setForeground(Color.black);
        }
        if (password.isEmpty()) {
            jTFPassword.setText("Ingresa tu contraseña");
            jTFPassword.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_jTFUserFocusGained

    private void jTFPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTFPasswordFocusGained
        String password = new String(jTFPassword.getPassword());
        if (password.equals("Ingresa tu contraseña")) {
            jTFPassword.setText("");
            jTFPassword.setForeground(Color.black);
        }
        if (jTFUser.getText().isEmpty()) {
            jTFUser.setText("Ingresa tu nombre de usuario");
            jTFUser.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_jTFPasswordFocusGained

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
            java.util.logging.Logger.getLogger(JFIngresar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFIngresar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFIngresar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFIngresar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFIngresar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JPanel exitP;
    private javax.swing.JLabel exitTXT;
    private javax.swing.JButton jBIngresar;
    private javax.swing.JButton jBMostrarC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField jTFPassword;
    private javax.swing.JTextField jTFUser;
    private javax.swing.JLabel olvidasteContra;
    // End of variables declaration//GEN-END:variables
}
