package exepciones;

public class ArchivoInvalidoException {
  /**
 * Excepción que se genera cuando un archivo
 * no tiene el formato esperado o no puede procesarse.
 */
public class ArchivoInvalidoException extends Exception {

    public ArchivoInvalidoException(String mensaje) {
        super(mensaje);
    }
}  
}
