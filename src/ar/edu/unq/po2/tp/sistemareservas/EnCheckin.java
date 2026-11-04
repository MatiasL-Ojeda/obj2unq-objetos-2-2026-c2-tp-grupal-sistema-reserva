package ar.edu.unq.po2.tp.sistemareservas;

public class EnCheckin extends Reserva{
    @Override
    public void comenzar(Reserva r, Cliente c){
        c.setReserva(new EnCurso());
    }
    @Override
    public void cancelar(Reserva r, Cliente c){
        c.setReserva(new Cancelada());
    }
}
