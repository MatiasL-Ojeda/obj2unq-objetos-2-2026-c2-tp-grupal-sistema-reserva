package ar.edu.unq.po2.tp.sistemareservas;

public class TrasladoEstandar implements MetodoTraslado{
    private int cantPasajeros; // cantidad de pasajeros del traslado.
    private Direccion dir;     // Direccion a la cual llevarlos.

    public TrasladoEstandar(int i, Direccion direccion) {
        this.cantPasajeros = i;
        this.dir = direccion;
    }

    @Override // calculo el costo del viaje.
    public float calcularCosto() {
        return PlataformaTransporte.estimarTraslado(this.cantPasajeros, this.dir);
    }
}