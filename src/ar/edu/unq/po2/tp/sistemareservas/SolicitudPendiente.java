package ar.edu.unq.po2.tp.sistemareservas;

public class SolicitudPendiente extends Reserva{
    @Override
    public void confirmar(Reserva r, Cliente c){
        c.setReserva(new Confirmada());
    }
    @Override
    public void cancelar(Reserva r, Cliente c){
        c.setReserva(new Cancelada());
    }
    @Override
    public void agregarServicio(Catalogo catalogoCliente, Catalogo servicioAAgregar){
        catalogoCliente.addCatalogo(servicioAAgregar);
    }
}