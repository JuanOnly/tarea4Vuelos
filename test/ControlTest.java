package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.src.Model.ControlVuelo;
import lib.src.Model.Vuelo;
import lib.src.Model.VueloNotFoundException;

public class ControlTest {
        @Test
        void testVueloTipoDirecto() throws Exception {

                ControlVuelo control = new ControlVuelo();
                List<String[]> rutas = control.cargarRutas(
                                "C:/Users/ASUS/Documents/Juan/Ingeneria de Sistemas/22-2/Ingenieria de Software/thirdQuarter/tarea4Final/tarea4_Vuelos/lib/src/JSON/Rutas.json");

                String origen = "manizales";
                String destino = "medellin";
                double duracionTotalEsperada = 1.0;
                double precioTotalEsperado = 95000.0;

                List<Vuelo> vuelos = control.mostrarVuelos(origen, destino, rutas);

                assertAll("Un vuelo directo",
                                () -> assertEquals(origen, vuelos.get(0).getOrigen()),
                                () -> assertEquals(destino, vuelos.get(0).getDestino()),
                                () -> assertEquals(duracionTotalEsperada, vuelos.get(0).getDuracionTotal()),
                                () -> assertEquals(precioTotalEsperado, vuelos.get(0).getPrecioTotal()));
        }

        @Test
        void testVueloTipoEscala() throws Exception {

                ControlVuelo control = new ControlVuelo();
                List<String[]> rutas = control.cargarRutas(
                                "C:/Users/ASUS/Documents/Juan/Ingeneria de Sistemas/22-2/Ingenieria de Software/thirdQuarter/tarea4Final/tarea4_Vuelos/lib/src/JSON/Rutas.json");

                String origen = "manizales";
                String destino = "cartagena";
                double duracionTotalEsperada = 5.0;
                double precioTotalEsperado = 315000.0;

                List<Vuelo> vuelos = control.mostrarVuelos(origen, destino, rutas);

                assertAll("Vuelo en escala",
                                () -> assertEquals(origen, vuelos.get(0).getOrigen()),
                                () -> assertEquals(destino, vuelos.get(0).getDestino()),
                                () -> assertEquals(duracionTotalEsperada, vuelos.get(0).getDuracionTotal()),
                                () -> assertEquals(precioTotalEsperado, vuelos.get(0).getPrecioTotal()));
        }

        @Test
        void testOrigenExisteDestinoNoExiste() throws Exception {

                ControlVuelo control = new ControlVuelo();
                List<String[]> rutas = control.cargarRutas(
                                "C:/Users/ASUS/Documents/Juan/Ingeneria de Sistemas/22-2/Ingenieria de Software/thirdQuarter/tarea4Final/tarea4_Vuelos/lib/src/JSON/Rutas.json");

                String origen = "manizales";
                String destino = "barrancabermeja";

                assertThrows(VueloNotFoundException.class,
                                () -> control.mostrarVuelos(origen, destino, rutas));
        }

        @Test
        void testOrigenNoExisteDestinoExiste() throws Exception {

                ControlVuelo control = new ControlVuelo();
                List<String[]> rutas = control.cargarRutas(
                                "C:/Users/ASUS/Documents/Juan/Ingeneria de Sistemas/22-2/Ingenieria de Software/thirdQuarter/tarea4Final/tarea4_Vuelos/lib/src/JSON/Rutas.json");

                String origen = "barrancabermeja";
                String destino = "cartagena";

                assertThrows(VueloNotFoundException.class,
                                () -> control.mostrarVuelos(origen, destino, rutas));
        }

        @Test
        void testVueloNoExistente() throws Exception {

                ControlVuelo control = new ControlVuelo();
                List<String[]> rutas = control.cargarRutas(
                                "C:/Users/ASUS/Documents/Juan/Ingeneria de Sistemas/22-2/Ingenieria de Software/thirdQuarter/tarea4Final/tarea4_Vuelos/lib/src/JSON/Rutas.json");

                String origen = "vichada";
                String destino = "barrancabermeja";

                assertThrows(VueloNotFoundException.class,
                                () -> control.mostrarVuelos(origen, destino, rutas));
        }
}
