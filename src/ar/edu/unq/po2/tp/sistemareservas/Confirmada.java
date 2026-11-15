package ar.edu.unq.po2.tp.sistemareservas;

public class Confirmada extends Reserva{

    @Override
    public void checkear(Reserva r, SistemaCatalogo c) {
        c.setReserva(new EnCheckin());
    }

    @Override
    public void cancelar(Reserva r, SistemaCatalogo c){
        c.setReserva(new Cancelada());
    }
}
