package modUsuarios;

/**
 *
 * @author Woosh
 * 
 */

public class Cliente {
    private String nombre;
    private String apellido;
    private String identificacion;
    private String direccion;
    private String telefono;

    public Cliente(String nombre, String apellido, String identificacion, String direccion, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Identificación: " + identificacion);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
    }
}
