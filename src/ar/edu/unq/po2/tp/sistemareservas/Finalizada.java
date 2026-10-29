package ar.edu.unq.po2.tp.sistemareservas;

public class Finalizada extends Reserva{
    @Override
    void confirmar(Reserva r, Catalogo c){
        throw new IllegalStateException("La reserva ya finalizo.");
    }

    @Override
    void checkear(Reserva r, Catalogo c) {
        throw new IllegalStateException("La reserva ya finalizo.");
    }
    @Override
    void comenzar(Reserva r, Catalogo c){
        throw new IllegalStateException("La reserva ya finalizo.");
    }
    @Override
    void finalizar(Reserva r, Catalogo c){
        throw new IllegalStateException("La reserva ya finalizo.");
    }
    @Override
    void cancelar(Reserva r, Catalogo c){
        throw new IllegalStateException("La reserva ya finalizo.");
    }
}
