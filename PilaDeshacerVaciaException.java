package exepciones;

public class PilaDeshacerVaciaException {
   /**
 * Excepción cuando se intenta deshacer una operación
 * y la pila de deshacer está vacía.
 */
public class PilaDeshacerVaciaException extends Exception {

    public PilaDeshacerVaciaException(String mensaje) {
        super(mensaje);
    }
} 
}
