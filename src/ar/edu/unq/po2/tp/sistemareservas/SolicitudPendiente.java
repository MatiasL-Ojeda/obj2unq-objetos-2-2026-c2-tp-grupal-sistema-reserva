package ar.edu.unq.po2.tp.sistemareservas;

public class SolicitudPendiente extends Reserva{
    @Override
    void confirmar(Reserva r, Catalogo c){
        c.setReserva(new Confirmada());
    }

    @Override
    void checkear(Reserva r, Catalogo c) {
        throw new IllegalStateException("No se puede chequear en este momento.");
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