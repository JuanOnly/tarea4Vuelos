package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import lib.src.Model.ControlVuelo;

public class PruebaUnitaria {

	@Test
	void testAdicionarVuelo() throws Exception {

			ControlVuelo control = new ControlVuelo();
			List<String[]> rutas = control.cargarRutas(
							"C:/Users/ASUS/Documents/Juan/Ingeneria de Sistemas/22-2/Ingenieria de Software/thirdQuarter/tarea4Final/tarea4_Vuelos/lib/src/JSON/Rutas.json");

			String origen = "manizales";
			String destino = "medellin";

			control.adicionarVuelo(origen, destino, rutas);
			assertEquals(1, control.getVuelos().size(), "Se ha agregado un vuelo exitosamente");
	}
	
}
