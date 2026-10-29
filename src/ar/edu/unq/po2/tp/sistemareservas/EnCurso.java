package ar.edu.unq.po2.tp.sistemareservas;

public class EnCurso extends Reserva{
    @Override
    void confirmar(Reserva r, Catalogo c){
        throw new IllegalStateException("Ya se ha pasado la etapa de confirmacion.");
    }

    @Override
    void checkear(Reserva r, Catalogo c) {
        throw new IllegalStateException("Ya ha finalizado el checkeo.");
    }
    @Override
    void comenzar(Reserva r, Catalogo c){
        throw new IllegalStateException("La reserva ya ha comenzado.");
    }
    @Override
    void finalizar(Reserva r, Catalogo c){
        c.setReserva(new Finalizada());
    }
    @Override
    void cancelar(Reserva r, Catalogo c){
        c.setReserva(new Cancelada());
    }
}
