package ar.edu.unq.po2.tp.sistemareservas;

public class TrasladoChauffeur implements MetodoTraslado{
    private float precioReserva;

    public TrasladoChauffeur (float costo) {
        this.precioReserva = costo;
    }

    @Override
    public float calcularCosto(){
        return TrasladoVIP.calcularCosto(this.precioReserva);
    }
}
