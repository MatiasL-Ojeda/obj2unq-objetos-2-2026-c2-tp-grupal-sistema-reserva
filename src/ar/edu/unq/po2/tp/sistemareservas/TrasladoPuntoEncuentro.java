package ar.edu.unq.po2.tp.sistemareservas;

public class TrasladoPuntoEncuentro implements MetodoTraslado {
    private final PuntoDeRecepcion punto;   // Punto de reunion.
    private final GestorDeGuias guias;      // Guias con los que cuenta este traslado.

    public TrasladoPuntoEncuentro(PuntoDeRecepcion p1, GestorDeGuias gG1) {
        this.guias = gG1;
        this.punto = p1;
    }

    @Override
    public float calcularCosto() {
        return 0.0f;
    }   // se calcula el costo del traslado.

    public boolean hayDisponibilidad() {
        return this.guias.hayDisponibilidad(this.punto);
    }
    // pregunto si hay algún guia disponible en el punto de reunion.
}