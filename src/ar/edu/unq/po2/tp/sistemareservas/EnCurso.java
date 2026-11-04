package ar.edu.unq.po2.tp.sistemareservas;

public class EnCurso extends Reserva{

    @Override
    public void finalizar(Reserva r, Cliente c){
        c.setReserva(new Finalizada());
    }
    @Override
    public void cancelar(Reserva r, Cliente c){
        c.setReserva(new Cancelada());
    }
}
