
package exepciones;

/**
 * Representa a un profesor del sistema universitario.
 * Hereda los datos generales de Persona.
 */
public class Profesor extends Persona {

    private String departamento;
    private double salario;

    public Profesor(String nombre, String id, String correo,
                    String departamento, double salario) {

        super(nombre, id, correo);
        this.departamento = departamento;
        this.salario = salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario >= 0) {
            this.salario = salario;
        }
    }

    /**
     * Muestra la información del profesor.
     */
    @Override
    public void mostrarInformacion() {

        System.out.println("----- DATOS DEL PROFESOR -----");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Identificación: " + getId());
        System.out.println("Correo: " + getCorreo());
        System.out.println("Departamento: " + departamento);
        System.out.println("Salario: " + salario);
    }
}
