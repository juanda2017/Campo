package exepciones;

public class ColaDeEsperaVaciaException {
    /**
 * Excepción que se genera cuando se intenta retirar
 * un estudiante de una cola de espera vacía.
 */
public class ColaDeEsperaVaciaException extends Exception {

    public ColaDeEsperaVaciaException(String mensaje) {
        super(mensaje);
    }
}
}
