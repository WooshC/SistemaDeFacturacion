package conexion;

/**
 *
 * @author Woosh
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexionBD {
    
    private final String HOST = "localhost";
    private final String PUERTO = "5432";
    private final String DB = "sistemafacturacion";
    private final String USER = "admin";
    private final String PASSWORD = "1234";
    
    public Connection getConexion(){
        Connection conexion = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            String url ="jdbc:postgresql://"+HOST+":"+PUERTO+"/"+DB;
            conexion = DriverManager.getConnection(url, USER, PASSWORD);
            //JOptionPane.showMessageDialog(null, "conexion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return conexion;
    }
}