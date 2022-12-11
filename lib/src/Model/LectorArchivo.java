package lib.src.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Esta clase contiene los métodos requeridos para leerArchivo el archivo.json
 * que es
 * enviada por parámetro
 */
public class LectorArchivo {
    /**
     * Método encargado de leer el archivo y validar su existencia y tipo
     * 
     * @param ruta
     * @return si existe, la lista, si no, null
     */
    public List<String[]> leerArchivo(String ruta) {

        try {
            List<String[]> rutas = new ArrayList<>();
            JSONParser jparser = new JSONParser();
            FileReader fReader;

            File file = new File(ruta);
            fReader = new FileReader(file);

            JSONArray jarray = (JSONArray) jparser.parse(fReader);

            for (int i = 0; i < jarray.size(); i++) {
                JSONObject obj = (JSONObject) jarray.get(i);
                parseJSONObject(obj, rutas);
            }
            return rutas;

        } catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace() + " :No se encuentra el archivo");
        } catch (ParseException e) {
            System.out.println(e.getStackTrace() + " :No es posible parsear");
        } catch (IOException e) {
            System.out.println(e.getStackTrace() + " :IOException");
        }
        return null;
    }

    public String tipoArchivo(String ruta) {
        String tipo = "";
        int i = ruta.lastIndexOf('.');
        if (i > 0) {
            tipo = ruta.substring(i + 1).toLowerCase();
        }
        return tipo;
    }

    /**
     * Método que organiza el archivo.json en una lista de strings llamada rutas
     * 
     * @param obj
     * @param rutas
     */
    public static void parseJSONObject(JSONObject obj, List<String[]> rutas) {
        String origen = (String) obj.get("origen");
        String destino = (String) obj.get("destino");
        double duracionTotal = (double) obj.get("duracionTotal");
        double precioTotal = (double) obj.get("precioTotal");

        String[] ruta = new String[4];
        ruta[0] = origen;
        ruta[1] = destino;
        ruta[2] = String.valueOf(duracionTotal);
        ruta[3] = String.valueOf(precioTotal);

        rutas.add(ruta);

    }
}