package ar.edu.unq.po2.tp.sistemareservas;

public class EnCheckin extends Reserva{
    @Override
    void confirmar(Reserva r, Catalogo c){
        throw new IllegalStateException("Ya se ha pasado la etapa de confirmacion.");
    }

    @Override
    void checkear(Reserva r, Catalogo c) {
        throw new IllegalStateException("Ya se encuentra en etapa de checkeo.");
    }
    @Override
    void comenzar(Reserva r, Catalogo c){
        c.setReserva(new EnCurso());
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
