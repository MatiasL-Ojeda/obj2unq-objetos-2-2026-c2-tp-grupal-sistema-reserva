package ar.edu.unq.po2.tp.sistemareservas;

public class TrasladoEstandar implements MetodoTraslado{
    private int cantPasajeros;
    private Direccion dir;

    public TrasladoEstandar (int cantPasajeros, Direccion dir) {
        this.dir = dir;
        this.cantPasajeros = cantPasajeros;
    }

    @Override
    public float calcularCosto() {
        return PlataformaTransporte.estimarTraslado(this.cantPasajeros, this.dir);
    }
}