package lib.src.Model;

/**
 * Se ejecuta esta excepcion cuando no hay un vuelo
 */
public class VueloNotFoundException extends Exception {

    public VueloNotFoundException(String mensaje) {
        super(mensaje);
    }

}
