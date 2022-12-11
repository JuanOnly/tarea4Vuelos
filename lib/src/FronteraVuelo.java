package lib.src;

import java.util.Scanner;

import lib.src.Model.ControlVuelo;
import lib.src.Model.Vuelo;

public class FronteraVuelo {

    public static void main(String[] args) throws Exception {

        ControlVuelo control = new ControlVuelo();

        Scanner sc = new Scanner(System.in);
        System.out.print("¿Cual es el origen de tu viaje?: ");
        String origen = sc.nextLine().toLowerCase();
        System.out.print("¿Cual es el destino de tu viaje?: ");
        String destino = sc.nextLine().toLowerCase();
        sc.close();

        System.out.println();
        System.out.println("Rutas disponibles de " + origen + " hasta " + destino);
        System.out.println("*************************************************");
        int nruta = 0;

        for (Vuelo vuelo : control.mostrarVuelos(origen, destino, control.cargarRutas("lib/src/JSON/Rutas.json"))) {
            nruta = nruta + 1;
            System.out.println(
                    "Ruta No: " + nruta + "\n" +
                            "Origen: " + vuelo.getOrigen() + "\n" + "Destino: " + vuelo.getDestino() + "\n"
                            + "duracionTotal: "
                            + vuelo.getDuracionTotal() + "\n" + "precioTotal: " + vuelo.getPrecioTotal() + "\n"
                            + "tipoVuelo: "
                            + vuelo.getTipoVuelo());
            System.out.println("*************************************************");

        }
    }
}
