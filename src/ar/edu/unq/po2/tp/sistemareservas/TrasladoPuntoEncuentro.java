package ar.edu.unq.po2.tp.sistemareservas;

public class TrasladoPuntoEncuentro implements MetodoTraslado {
    private final PuntoDeRecepcion punto;
    private final GestorDeGuias guias;

    public TrasladoPuntoEncuentro(PuntoDeRecepcion punto, GestorDeGuias guias) {
        this.punto = punto;
        this.guias = guias;
    }

    @Override
    public float calcularCosto() {
        return 0.0f;
    }

    public boolean hayDisponibilidad() {
        return this.guias.hayDisponibilidad(this.punto);
    }
}