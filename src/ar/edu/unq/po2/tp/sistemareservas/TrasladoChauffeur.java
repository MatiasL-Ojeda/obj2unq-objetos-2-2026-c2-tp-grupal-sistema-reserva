package ar.edu.unq.po2.tp.sistemareservas;

public class TrasladoChauffeur implements MetodoTraslado{
    private float precioReserva; // Precio de la reserva

    public TrasladoChauffeur(float v) { this.precioReserva = v; }

    @Override
    public float calcularCosto(){
        return TrasladoVIP.calcularCosto(this.precioReserva);
    } // calculo el costo del viaje
}
