package lib.src.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que hace las operaciones del proyecto
 */
public class ControlVuelo {

    private LectorArchivo LectorArchivo;
    private List<Vuelo> vuelos;

    public ControlVuelo() {
        vuelos = new ArrayList<>();
        LectorArchivo = new LectorArchivo();
    }

    /**
     * Muestra la lista de vuelos que pueden ser tomados, y se tiene en cuenta las
     * siguientes restricciones:
     * El archivo de rutas sólo debe de ser de formato JSON
     * El origen y el destino ingresado debe coincidir con alguna ruta existente
     * 
     * @param origen  cuidad donde el vuelo empieza
     * @param destino ciudad a la cual llega el vuelo
     * @param rutas   lista de rutas por las cuales pasa el vuelo
     * @return vuelo con todos los datos añadidos
     * @throws Exception ejucuta cuando no hay rutas o vuelos
     */
    public List<Vuelo> mostrarVuelos(String origen, String destino, List<String[]> rutas) throws Exception {

        if (!validarVuelo(origen, destino, rutas)) {
            throw new VueloNotFoundException("¡Caramba!, el vuelo no tiene rutas, intenta de nuevo");
        }

        adicionarVuelo(origen, destino, rutas);

        if (vuelos.size() == 0) {
            throw new VueloNotFoundException("Ojo, No hay vuelos disponibles");
        }
        return vuelos;
    }

    public List<String[]> cargarRutas(String ruta) throws Exception {
        return this.LectorArchivo.leer(ruta);
    }

    /**
     * Valida que el origen y destino existan en la ruta.
     * 
     * @param origen  cuidad donde el vuelo empieza
     * @param destino ciudad a la cual llega el vuelo
     * @param rutas   ingresa las rutas en la lista
     * @return boolean, true cuando existe origen y destino, false cuando no.
     * @throws VueloNotFoundException se lanza cuando hay error con la estructura
     *                                del json
     */
    private boolean validarVuelo(String origen, String destino, List<String[]> rutas) throws VueloNotFoundException {

        boolean existeDestino = false;
        boolean existeOrigen = false;

        for (String[] ruta : rutas) {

            if (!esTipoJSON(ruta)) {
                throw new VueloNotFoundException("hay un problema con el JSON");
            }
            if (origen.equals(ruta[0])) {
                existeOrigen = true;
            }
            if (destino.equals(ruta[1])) {
                existeDestino = true;
            }
        }

        if (existeOrigen && existeDestino) {
            return true;
        }

        return false;
    }

    /**
     * Añade un vuelo a la lista de vuelos, ya sea de tipo directo
     * o de tipo escala que coincidan con el origen y el destino
     * 
     * @param origen
     * @param destino
     * @param rutas
     */
    private void adicionarVuelo(String origen, String destino, List<String[]> rutas) {

        for (String[] ruta : rutas) {
            if (ruta[0].equals(origen) && ruta[1].equals(destino)) {
                Vuelo vuelo = new Vuelo(origen, destino, Double.parseDouble(ruta[2]), Double.parseDouble(ruta[3]),
                        "Un vuelo directo");
                vuelos.add(vuelo);
            }

            if (ruta[0].equals(origen) && !destino.equals(ruta[1])) {

                for (String[] escala : rutas) {

                    if (destino.equals(escala[1]) && ruta[1].equals(escala[0])) {
                        double duracionTotal = Double.parseDouble(ruta[2]) + Double.parseDouble(escala[2]);
                        double precioTotal = Double.parseDouble(ruta[3]) + Double.parseDouble(escala[3]);

                        Vuelo vuelo = new Vuelo(origen, destino, duracionTotal, precioTotal, "El vuelo es de escala");
                        vuelos.add(vuelo);
                    }
                }
            }
        }
    }

    /**
     * Método que verifica la estructura de Rutas.json y que la rutas ingresadas
     * quesiguen una estructura predeterminadas
     * 
     * @param tipo
     * @return true si cumple con la estructura basica del json; false si no
     */
    private boolean esTipoJSON(String[] tipo) {

        if (tipo.length != 4) {
            return false;
        }
        return true;
    }
}
