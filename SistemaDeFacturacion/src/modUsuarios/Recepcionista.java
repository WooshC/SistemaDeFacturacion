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

public class Recepcionista extends Empleado {

    public Recepcionista(String nombre, String apellido, String identificacion, String direccion, String telefono) {
        super(nombre, apellido, identificacion, direccion, telefono, "Recepcionista");
    }

    @Override
    public void registrarUsuario() {
        // Implementación específica para registrar un recepcionista
        System.out.println("Registrando recepcionista: " + getNombre() + " " + getApellido());
    }

    public void atenderCliente() {
        System.out.println("El recepcionista está atendiendo a un cliente.");
    }
}