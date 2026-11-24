
package Modelo;

import java.util.LinkedList;

/**
 * Representa a un estudiante del sistema universitario.
 * Hereda los datos generales de la clase Persona.
 */
public class Estudiante extends Persona {

    private int semestre;

    // Matriz para guardar las notas de hasta 10 semestres
    // y 20 materias por semestre.
    private Double[][] notas;

    // Matriz para guardar los códigos de las materias.
    private String[][] codigosMaterias;

    // Lista enlazada con el historial académico.
    private LinkedList<Materia> historialMaterias;

    public Estudiante(String nombre, String id, String correo, int semestre) {
        super(nombre, id, correo);

        this.semestre = semestre;
        this.notas = new Double[10][20];
        this.codigosMaterias = new String[10][20];
        this.historialMaterias = new LinkedList<>();
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        if (semestre >= 1 && semestre <= 10) {
            this.semestre = semestre;
        }
    }

    public Double[][] getNotas() {
        return notas;
    }

    public LinkedList<Materia> getHistorialMaterias() {
        return historialMaterias;
    }

    /**
     * Registra la nota de una materia en un semestre.
     */
    public void registrarNota(int semestre, int posicion,
                              String codigoMateria, double nota) {

        if (semestre < 1 || semestre > 10) {
            throw new IllegalArgumentException(
                "El semestre debe estar entre 1 y 10."
            );
        }

        if (posicion < 0 || posicion >= 20) {
            throw new IllegalArgumentException(
                "La posición debe estar entre 0 y 19."
            );
        }

        if (nota < 0 || nota > 5) {
            throw new IllegalArgumentException(
                "La nota debe estar entre 0 y 5."
            );
        }

        notas[semestre - 1][posicion] = nota;
        codigosMaterias[semestre - 1][posicion] = codigoMateria;
    }

    /**
     * Calcula el promedio de un semestre.
     */
    public double calcularPromedioSemestre(int semestre) {

        if (semestre < 1 || semestre > 10) {
            return 0;
        }

        double suma = 0;
        int cantidad = 0;

        for (int i = 0; i < 20; i++) {
            Double nota = notas[semestre - 1][i];

            if (nota != null) {
                suma += nota;
                cantidad++;
            }
        }

        if (cantidad == 0) {
            return 0;
        }

        return suma / cantidad;
    }

    /**
     * Calcula el promedio acumulado de todos los semestres.
     */
    public double calcularPromedioAcumulado() {

        double suma = 0;
        int cantidad = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {

                if (notas[i][j] != null) {
                    suma += notas[i][j];
                    cantidad++;
                }
            }
        }

        if (cantidad == 0) {
            return 0;
        }

        return suma / cantidad;
    }

    /**
     * Comprueba si el estudiante aprobó una materia.
     * La nota mínima para aprobar es 3.0.
     */
    public boolean aproboMateria(String codigoMateria) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {

                if (codigoMateria.equals(codigosMaterias[i][j])) {

                    Double nota = notas[i][j];

                    if (nota != null && nota >= 3.0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Agrega una materia al historial académico.
     */
    public void agregarAlHistorial(Materia materia) {

        if (materia != null &&
            !historialMaterias.contains(materia)) {

            historialMaterias.add(materia);
        }
    }

    /**
     * Muestra la información del estudiante.
     */
    @Override
    public void mostrarInformacion() {

        System.out.println("----- DATOS DEL ESTUDIANTE -----");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Identificación: " + getId());
        System.out.println("Correo: " + getCorreo());
        System.out.println("Semestre: " + semestre);
        System.out.printf(
            "Promedio acumulado: %.2f%n",
            calcularPromedioAcumulado()
        );
    }
}
