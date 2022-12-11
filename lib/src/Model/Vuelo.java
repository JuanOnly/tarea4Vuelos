package lib.src.Model;

/**
 * 
 * El vuelo puede ser de 2 o menos rutas y esta representada con un origien y un
 * destino
 */
public class Vuelo {

    private String origen;
    private String destino;
    private double duracionTotal;
    private double precioTotal;
    private String tipoVuelo;

    public Vuelo(String origen, String destino, double duracionTotal, double precioTotal, String tipoVuelo) {
        this.origen = origen;
        this.destino = destino;
        this.duracionTotal = duracionTotal;
        this.precioTotal = precioTotal;
        this.tipoVuelo = tipoVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public double getDuracionTotal() {
        return duracionTotal;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public String getTipoVuelo() {
        return tipoVuelo;
    }

}