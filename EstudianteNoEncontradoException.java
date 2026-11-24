package excepciones;

/**
 * Excepción que se genera cuando no se encuentra
 * un estudiante en el sistema universitario.
 */
public class EstudianteNoEncontradoException extends Exception {

    public EstudianteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}