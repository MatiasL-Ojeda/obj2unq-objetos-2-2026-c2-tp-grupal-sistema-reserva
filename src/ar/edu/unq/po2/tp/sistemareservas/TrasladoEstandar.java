package ar.edu.unq.po2.tp.sistemareservas;

public class TrasladoEstandar extends Traslado{
    private int pasajeros;
    //private Direccion dir;
    @Override
    public float costoTraslado(){
        return 0; //PlataformaTransporte.estimarTraslado(this.pasajeros, this.dir); //Creo que seria algo asi?
    }
}
