
package Modelo;

import java.util.LinkedList;
import java.util.Queue;

import excepciones.CupoLlenoException;
import excepciones.PreRequisitoNoAprobadoException;

/**
 * Representa una materia del sistema universitario.
 */
public class Materia {

    private String codigo;
    private String nombre;
    private int cuposMaximos;
    private int creditos;

    private LinkedList<Materia> prerequisitos;
    private LinkedList<Estudiante> estudiantesInscritos;
    private Queue<Estudiante> colaEspera;

    public Materia(String codigo, String nombre,
                   int cuposMaximos, int creditos) {

        if (codigo == null || nombre == null ||
            codigo.trim().isEmpty() || nombre.trim().isEmpty() ||
            cuposMaximos <= 0 || creditos <= 0) {
            throw new IllegalArgumentException(
                "Los datos de la materia no son válidos."
            );
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.cuposMaximos = cuposMaximos;
        this.creditos = creditos;

        prerequisitos = new LinkedList<>();
        estudiantesInscritos = new LinkedList<>();
        colaEspera = new LinkedList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCuposMaximos() {
        return cuposMaximos;
    }

    public int getCreditos() {
        return creditos;
    }

    public LinkedList<Materia> getPrerequisitos() {
        return prerequisitos;
    }

    public LinkedList<Estudiante> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    public Queue<Estudiante> getColaEspera() {
        return colaEspera;
    }

    public void agregarPrerequisito(Materia materia) {
        if (materia != null &&
            !materia.equals(this) &&
            !prerequisitos.contains(materia)) {
            prerequisitos.add(materia);
        }
    }

    public boolean cumplePrerequisitos(Estudiante estudiante) {
        for (Materia requisito : prerequisitos) {
            if (!estudiante.aproboMateria(requisito.getCodigo())) {
                return false;
            }
        }
        return true;
    }

    public void agregarEstudiante(Estudiante estudiante)
            throws CupoLlenoException,
                   PreRequisitoNoAprobadoException {

        if (estudiante == null) {
            throw new IllegalArgumentException(
                "El estudiante no puede ser nulo."
            );
        }

        if (estudiantesInscritos.contains(estudiante)) {
            return;
        }

        if (!cumplePrerequisitos(estudiante)) {
            throw new PreRequisitoNoAprobadoException(
                "El estudiante no cumple los prerrequisitos."
            );
        }

        if (estudiantesInscritos.size() >= cuposMaximos) {
            throw new CupoLlenoException(
                "No hay cupos disponibles en esta materia."
            );
        }

        estudiantesInscritos.add(estudiante);
    }

    public void agregarAColaEspera(Estudiante estudiante) {
        if (estudiante != null &&
            !estudiantesInscritos.contains(estudiante) &&
            !colaEspera.contains(estudiante)) {
            colaEspera.offer(estudiante);
        }
    }

    /**
     * Cancela la inscripción y busca el siguiente estudiante
     * de la cola que cumpla los prerrequisitos.
     */
    public boolean cancelarInscripcion(Estudiante estudiante) {

        boolean eliminado = estudiantesInscritos.remove(estudiante);

        if (eliminado) {
            int cantidad = colaEspera.size();

            for (int i = 0; i < cantidad; i++) {
                Estudiante siguiente = colaEspera.poll();

                if (siguiente == null) {
                    break;
                }

                if (cumplePrerequisitos(siguiente)) {
                    estudiantesInscritos.add(siguiente);
                    break;
                } else {
                    colaEspera.offer(siguiente);
                }
            }
        }

        return eliminado;
    }

    public Estudiante retirarPrimeroDeCola() {
        return colaEspera.poll();
    }

    public void mostrarInscritos() {
        System.out.println("Estudiantes inscritos en " + nombre + ":");

        if (estudiantesInscritos.isEmpty()) {
            System.out.println("No hay estudiantes inscritos.");
            return;
        }

        for (Estudiante estudiante : estudiantesInscritos) {
            System.out.println(
                estudiante.getId() + " - " + estudiante.getNombre()
            );
        }
    }

    public void mostrarColaEspera() {
        System.out.println("Cola de espera de " + nombre + ":");

        if (colaEspera.isEmpty()) {
            System.out.println("No hay estudiantes en espera.");
            return;
        }

        for (Estudiante estudiante : colaEspera) {
            System.out.println(
                estudiante.getId() + " - " + estudiante.getNombre()
            );
        }
    }
}
