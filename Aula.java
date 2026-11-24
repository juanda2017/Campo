
package Modelo;

/**
 * Representa un aula y administra sus horarios.
 *
 * Días:
 * 0 = Domingo
 * 1 = Lunes
 * 2 = Martes
 * 3 = Miércoles
 * 4 = Jueves
 * 5 = Viernes
 * 6 = Sábado
 */
public class Aula {

    private String codigo;
    private String nombre;
    private int capacidad;

    // Matriz de disponibilidad: 7 días y 24 horas.
    // false = disponible, true = reservado.
    private boolean[][] disponibilidad;

    public Aula(String codigo, String nombre, int capacidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.disponibilidad = new boolean[7][24];
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Consulta si un horario está disponible.
     */
    public boolean consultarDisponibilidad(int dia, int hora) {

        validarHorario(dia, hora);

        return !disponibilidad[dia][hora];
    }

    /**
     * Reserva un horario del aula.
     */
    public void reservarHorario(int dia, int hora) {

        validarHorario(dia, hora);

        if (disponibilidad[dia][hora]) {
            throw new IllegalStateException(
                "El horario ya está reservado."
            );
        }

        disponibilidad[dia][hora] = true;
    }

    /**
     * Libera un horario reservado.
     */
    public void liberarHorario(int dia, int hora) {

        validarHorario(dia, hora);

        if (!disponibilidad[dia][hora]) {
            throw new IllegalStateException(
                "El horario ya se encuentra disponible."
            );
        }

        disponibilidad[dia][hora] = false;
    }

    /**
     * Valida los días y las horas permitidas.
     */
    private void validarHorario(int dia, int hora) {

        if (dia < 0 || dia > 6) {
            throw new IllegalArgumentException(
                "El día debe estar entre 0 y 6."
            );
        }

        if (hora < 0 || hora > 23) {
            throw new IllegalArgumentException(
                "La hora debe estar entre 0 y 23."
            );
        }
    }

    /**
     * Muestra la disponibilidad de un día.
     */
    public void mostrarDisponibilidad(int dia) {

        if (dia < 0 || dia > 6) {
            throw new IllegalArgumentException(
                "El día debe estar entre 0 y 6."
            );
        }

        String[] dias = {
            "Domingo", "Lunes", "Martes", "Miércoles",
            "Jueves", "Viernes", "Sábado"
        };

        System.out.println(
            "Disponibilidad del aula " + nombre +
            " - " + dias[dia]
        );

        for (int hora = 0; hora < 24; hora++) {

            String estado = disponibilidad[dia][hora]
                ? "Reservado"
                : "Disponible";

            System.out.println(hora + ":00 - " + estado);
        }
    }
}

