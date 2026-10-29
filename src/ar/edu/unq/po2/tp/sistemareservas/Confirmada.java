package ar.edu.unq.po2.tp.sistemareservas;

public class Confirmada extends Reserva{
    @Override
    void confirmar(Reserva r, Catalogo c){
        throw new IllegalStateException("La reserva ya se encuentra confirmada.");
    }

    @Override
    void checkear(Reserva r, Catalogo c) {
        c.setReserva(new EnCheckin());
    }
    @Override
    void comenzar(Reserva r, Catalogo c){
        throw new IllegalStateException("No se puede comenzar en este momento.");
    }
    @Override
    void finalizar(Reserva r, Catalogo c){
        throw new IllegalStateException("No se puede finalizar en este momento.");
    }
    @Override
    void cancelar(Reserva r, Catalogo c){
        c.setReserva(new Cancelada());
    }
}
