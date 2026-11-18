package ar.edu.unq.po2.tp.sistemareservas;

public abstract class MetodoPago {
    private float montoAPagar;
    public MetodoPago(float montoAPagar){
        this.montoAPagar = montoAPagar;
    }
    public abstract void validarDatos();
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion();
    public void notificarResultado(SistemaCatalogo c){

        c.confirmarReserva();
    }
}
