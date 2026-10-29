package ar.edu.unq.po2.tp.sistemareservas;

public abstract class Reserva {
    abstract void confirmar(Reserva r, Catalogo c);
    abstract void checkear(Reserva r , Catalogo c);  // Deberia llevar un method cada uno o aplicar otra forma?
    abstract void comenzar(Reserva r , Catalogo c);
    abstract void finalizar(Reserva r, Catalogo c);
    abstract void cancelar(Reserva r , Catalogo c);
}
