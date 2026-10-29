package ar.edu.unq.po2.tp.sistemareservas;

public interface BankTransferPayable {
    public abstract void validarDatos(String cvu, String alias);
    public abstract void reservarFondos();
    public abstract void ejecutarTransaccion();
    public abstract void notificarResultado();
}
