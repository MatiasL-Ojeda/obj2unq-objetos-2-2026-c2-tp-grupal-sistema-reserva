package ar.edu.unq.po2.tp.sistemareservas;

public class Cancelada extends Reserva{
    @Override
    void confirmar(Reserva r, Catalogo c){
        throw new IllegalStateException("La reserva fue cancelada.");
    }

    @Override
    void checkear(Reserva r, Catalogo c) {
        throw new IllegalStateException("La reserva fue cancelada");
    }
    @Override
    void comenzar(Reserva r, Catalogo c){
        throw new IllegalStateException("La reserva fue cancelada.");
    }
    @Override
    void finalizar(Reserva r, Catalogo c){
        throw new IllegalStateException("La reserva fue cancelada.");
    }
    @Override
    void cancelar(Reserva r, Catalogo c){
        throw new IllegalStateException("La reserva ya fue cancelada");
    }
}
