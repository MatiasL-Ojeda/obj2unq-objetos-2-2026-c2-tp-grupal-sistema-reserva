package ar.edu.unq.po2.tp.sistemareservas;

import java.util.HashMap;

public class Cliente {

    Catalogo c; // El catalogo de los servicios elegidos por el cliente.

    private Reserva estadoReserva = new SolicitudPendiente(); // El estado de la reserva inicial del cliente.

    public void agregarServicicio(Catalogo servicioAAgregar){
        estadoReserva.agregarServicio(c, servicioAAgregar);
    }
    public void confirmarReserva(){
        estadoReserva.confirmar(this.estadoReserva, this);
    }
    public void setReserva(Reserva r){
        this.estadoReserva = r;
    }
}
