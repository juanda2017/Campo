package Modelo;


/**
 * Clase abstracta que representa los datos generales
 * de las personas del sistema universitario.
 */
public abstract class Persona {

    private String nombre;
    private String id;
    private String correo;

    public Persona(String nombre, String id, String correo) {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Método abstracto que implementarán las clases hijas.
     */
    public abstract void mostrarInformacion();
}