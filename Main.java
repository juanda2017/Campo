package estructuras;

import modelo.*;
import excepciones.*;
import java.util.Scanner;

/** Menú de consola con las 22 opciones del sistema. */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final SistemaAcademico sistema = new SistemaAcademico();

    public static void main(String[] args) {
        cargarDatosIniciales();

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            try {
                ejecutar(opcion);
            } catch (Exception ex) {
                System.out.println(
                    "Error: " + ex.getClass().getSimpleName()
                    + " - " + ex.getMessage()
                );
            }

        } while (opcion != 22);

        System.out.println("Programa finalizado.");
    }

    private static void mostrarMenu() {
        System.out.println("\n============================================================");
        System.out.println("       PLANIFICACIÓN ACADÉMICA - SISTEMA UNIVERSITARIO");
        System.out.println("============================================================");

        System.out.println("=== GESTION DE ESTUDIANTES ===");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Buscar estudiante por ID");
        System.out.println("3. Listar todos los estudiantes");
        System.out.println("4. Eliminar estudiante");

        System.out.println("=== GESTION DE MATERIAS ===");
        System.out.println("5. Crear materia");
        System.out.println("6. Agregar pre-requisito");
        System.out.println("7. Mostrar pre-requisitos");
        System.out.println("8. Inscribir estudiante");
        System.out.println("9. Cancelar inscripcion");
        System.out.println("10. Mostrar cola de espera");

        System.out.println("=== GESTION DE HORARIOS ===");
        System.out.println("11. Reservar horario en aula");
        System.out.println("12. Liberar horario");
        System.out.println("13. Consultar disponibilidad");

        System.out.println("=== RUTAS ENTRE EDIFICIOS ===");
        System.out.println("14. Agregar conexion entre edificios");
        System.out.println("15. Calcular ruta mas corta");

        System.out.println("=== REPORTES ACADEMICOS ===");
        System.out.println("16. Registrar nota");
        System.out.println("17. Ver reporte academico");
        System.out.println("18. Navegador de reportes (atras/adelante)");

        System.out.println("=== SISTEMA DESHACER/REHACER ===");
        System.out.println("19. Deshacer ultima operacion");
        System.out.println("20. Rehacer ultima operacion");

        System.out.println("=== PROCESAMIENTO POR LOTES ===");
        System.out.println("21. Procesar archivo CSV");

        System.out.println("=== SALIR ===");
        System.out.println("22. Salir");
    }

    private static void ejecutar(int op) throws Exception {
        switch (op) {

            case 1 -> {
                String id = leerTexto("ID: ");
                String nombre = leerTexto("Nombre: ");
                String email = leerTexto("Email: ");
                int semestre = leerEntero("Semestre actual: ");

                sistema.registrarEstudiante(
                    new Estudiante(nombre, id, email, semestre)
                );

                System.out.println("Estudiante registrado exitosamente.");
            }

            case 2 -> {
                sistema.buscarEstudiante(
                    leerTexto("ID: ")
                ).mostrarInformacion();
            }

            case 3 -> {
                if (sistema.listarEstudiantes().isEmpty()) {
                    System.out.println("No hay estudiantes registrados.");
                }

                for (Estudiante e : sistema.listarEstudiantes()) {
                    e.mostrarInformacion();
                }
            }

            case 4 -> {
                sistema.eliminarEstudiante(
                    leerTexto("ID del estudiante: ")
                );

                System.out.println(
                    "Estudiante eliminado. Puede deshacer la operación."
                );
            }

            case 5 -> {
                sistema.crearMateria(
                    new Materia(
                        leerTexto("Código: "),
                        leerTexto("Nombre: "),
                        leerEntero("Cupos máximos: "),
                        leerEntero("Créditos: ")
                    )
                );

                System.out.println("Materia creada.");
            }

            case 6 -> {
                sistema.agregarPrerequisito(
                    leerTexto("Código de materia: "),
                    leerTexto("Código del prerrequisito: ")
                );
            }

            case 7 -> {
                Materia m = sistema.buscarMateria(
                    leerTexto("Código de materia: ")
                );

                if (m.getPrerrequisitos().isEmpty()) {
                    System.out.println("No tiene prerrequisitos.");
                } else {
                    m.getPrerrequisitos().forEach(System.out::println);
                }
            }

            case 8 -> {
                sistema.inscribir(
                    leerTexto("ID estudiante: "),
                    leerTexto("Código materia: ")
                );

                System.out.println("Inscripción exitosa.");
            }

            case 9 -> {
                sistema.cancelarInscripcion(
                    leerTexto("ID estudiante: "),
                    leerTexto("Código materia: ")
                );
            }

            case 10 -> {
                sistema.mostrarCola(
                    leerTexto("Código materia: ")
                );
            }

            case 11 -> {
                String aula = leerTexto("Nombre del aula: ");
                int dia = leerEntero("Día (0=Domingo, 1=Lunes...6=Sábado): ");
                int hora = leerEntero("Hora (0-23): ");
                int duracion = leerEntero("Duración en horas: ");

                sistema.reservarHorario(aula, dia, hora, duracion);

                System.out.println("Reserva exitosa.");
            }

            case 12 -> {
                String aula = leerTexto("Nombre del aula: ");
                int dia = leerEntero("Día (0-6): ");
                int hora = leerEntero("Hora (0-23): ");
                int duracion = leerEntero("Duración: ");

                sistema.liberarHorario(aula, dia, hora, duracion);

                System.out.println("Horario liberado.");
            }

            case 13 -> {
                Aula aula = sistema.buscarAula(
                    leerTexto("Nombre del aula: ")
                );

                int dia = leerEntero("Día (0-6): ");
                int hora = leerEntero("Hora (0-23): ");

                System.out.println(
                    aula.consultarDisponibilidad(dia, hora)
                    ? "DISPONIBLE"
                    : "OCUPADO"
                );
            }

            case 14 -> {
                sistema.mostrarEdificios();

                int origen = leerEntero("Índice edificio origen: ");
                int destino = leerEntero("Índice edificio destino: ");
                int metros = leerEntero("Distancia en metros: ");

                sistema.agregarConexion(origen, destino, metros);

                System.out.println("Conexión agregada en ambos sentidos.");
            }

            case 15 -> {
                sistema.mostrarEdificios();

                int origen = leerEntero("Origen (índice): ");
                int destino = leerEntero("Destino (índice): ");

                sistema.calcularRuta(origen, destino);
            }

            case 16 -> {
                String id = leerTexto("ID estudiante: ");
                int semestre = leerEntero("Semestre (1-10): ");
                int posicion = leerEntero("Posición de materia (1-20): ");
                double nota = leerDouble("Nota (0-5): ");

                sistema.registrarNota(
                    id, semestre, posicion - 1, nota
                );

                System.out.println("Nota registrada.");
            }

            case 17 -> {
                sistema.mostrarReporte(
                    leerTexto("ID estudiante: ")
                );
            }

            case 18 -> {
                System.out.println("1. Atrás");
                System.out.println("2. Adelante");

                int opcion = leerEntero("Seleccione: ");

                if (opcion == 1) {
                    sistema.atrasReporte();
                } else if (opcion == 2) {
                    sistema.adelanteReporte();
                } else {
                    System.out.println("Opción inválida.");
                }
            }

            case 19 -> sistema.deshacer();

            case 20 -> sistema.rehacer();

            case 21 -> {
                sistema.procesarCSV(
                    leerTexto("Ruta del archivo CSV: ")
                );
            }

            case 22 -> {
                System.out.println("Saliendo del sistema...");
            }

            default -> {
                System.out.println("Seleccione una opción entre 1 y 22.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje));
            } catch (NumberFormatException ex) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    private static void cargarDatosIniciales() {
        try {
            sistema.crearAula("101", 30);
            sistema.crearAula("102", 25);
            sistema.crearAula("Laboratorio 1", 20);

            sistema.agregarConexion(0, 1, 100);
            sistema.agregarConexion(1, 2, 120);
            sistema.agregarConexion(2, 3, 180);
            sistema.agregarConexion(0, 2, 150);
            sistema.agregarConexion(2, 4, 90);
            sistema.agregarConexion(4, 3, 110);

        } catch (Exception ex) {
            System.out.println(
                "Aviso al cargar datos iniciales: " + ex.getMessage()
            );
        }
    }
}