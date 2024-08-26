/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login {

    private Connection connection;

    public Login(Connection connection) {
        this.connection = connection;
    }
    
     public String[] obtenerNombreApellido(String username) throws SQLException {
        String query = "SELECT E.Nombre, E.Apellido FROM Usuario U JOIN Empleado E ON U.idEmpleado = E.Cedula WHERE U.nombreUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new String[]{rs.getString("Nombre"), rs.getString("Apellido")};
            }
        }
        return new String[]{"", ""}; // Devuelve valores vacíos si no se encuentra el usuario
    }

    // Verificar si el usuario está bloqueado
    public boolean usuarioBloqueado(String username) throws SQLException {
        String query = "SELECT estadoUsuario FROM Usuario WHERE nombreUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String estado = rs.getString("estadoUsuario");
                return "Bloqueado".equals(estado);
            }
        }
        return false;
    }

    // Verificar si el usuario existe y si la contraseña es correcta
   public boolean verificarUsuario(String username, String password) throws SQLException {
    String query = "SELECT clave FROM Usuario WHERE nombreUsuario = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String storedPassword = rs.getString("clave");
            return password.equals(storedPassword);
        }
    }
    return false;
}

    // Obtener el rol del usuario
    public String obtenerRolUsuario(String username) throws SQLException {
        String query = "SELECT E.rol FROM Usuario U JOIN Empleado E ON U.idEmpleado = E.Cedula WHERE U.nombreUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("rol");
            }
        }
        return null;
    }
    
    // Bloquear usuario
    public void bloquearUsuario(String username) throws SQLException {
        String query = "UPDATE Usuario SET estadoUsuario = 'Bloqueado' WHERE nombreUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        }
    }
}
