package exepciones;

public class reRequisitoNoAprobadoException {
    

/**
 * Excepción cuando un estudiante no ha aprobado
 * las materias que son prerrequisitos.
 */
public class PreRequisitoNoAprobadoException extends Exception {

    public PreRequisitoNoAprobadoException(String mensaje) {
        super(mensaje);
    }
}
}
