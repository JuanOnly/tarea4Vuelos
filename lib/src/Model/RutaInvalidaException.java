package lib.src.Model;

/**
 * Se ejecuta esta excepcion cuando alguna ruta del JSON es invalida
 */
public class RutaInvalidaException extends Exception {

    public RutaInvalidaException(String mensaje) {
        super(mensaje);
    }

}
