package ar.edu.unq.po2.tp.sistemareservas;

public interface CreditCardPayable {
    public abstract void validarDatos();
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion();
    public abstract void notificarResultado();
}
