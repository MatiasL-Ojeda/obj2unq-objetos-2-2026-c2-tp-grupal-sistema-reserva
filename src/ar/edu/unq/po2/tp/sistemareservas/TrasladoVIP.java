package ar.edu.unq.po2.tp.sistemareservas;

public class TrasladoVIP extends Traslado{
    private float costoReserva;

    @Override
    public float costoTraslado(){
        return 0; //TrasladoVIP.calcularCosto(this.costoReserva); //Creo que seria algo asi?x2
    }
}
