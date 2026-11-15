package ar.edu.unq.po2.tp.sistemareservas;

public class Guia {
    private boolean disponible;
    private PuntoDeRecepcion puntoAsignado;

    public Guia(boolean b, PuntoDeRecepcion p1) {
        this.disponible = b;
        this.puntoAsignado = p1;
    }

    public boolean isDisponible() { return disponible; } // pregunto si el guia esta disponible.

    public void setDisponible(boolean disponibilidad) { this.disponible = disponibilidad; } // Cambio la disponibilidad al guia.

    public PuntoDeRecepcion getPuntoAsignado() { return puntoAsignado; } // le pregunto al guia a que punto esta asignado.
}