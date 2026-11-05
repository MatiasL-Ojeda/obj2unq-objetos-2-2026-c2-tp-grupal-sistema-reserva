package ar.edu.unq.po2.tp.sistemareservas;

public class Guia {
    private boolean disponible;
    private final PuntoDeRecepcion puntoAsignado;

    public Guia(boolean disponibilidad, PuntoDeRecepcion punto) {
        this.disponible = disponibilidad;
        this.puntoAsignado = punto;
    }

    public boolean isDisponible() { return disponible; }

    public void setDisponible(boolean disponibilidad) { this.disponible = disponibilidad; }

    public PuntoDeRecepcion getPuntoAsignado() { return puntoAsignado; }
}