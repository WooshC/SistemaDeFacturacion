/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modUsuarios;

/**
 *
 * @author Woosh
 * 
 */

public class Administrador extends Empleado {

    public Administrador(String nombre, String apellido, String identificacion, String direccion
            , String telefono) {
        super(nombre, apellido, identificacion, direccion, telefono, "Administrador");
    }

    public void gestionarSistema() {
        System.out.println("El administrador está gestionando el sistema.");
    }

    @Override
    public void registrarUsuario() {
        // Lógica para registrar un nuevo usuario
        System.out.println("Administrador registrando un nuevo usuario.");
    }
}
