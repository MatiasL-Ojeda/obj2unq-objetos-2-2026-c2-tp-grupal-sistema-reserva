package ar.edu.unq.po2.tp.sistemareservas;

import java.util.Set; // Uso el set para no tener subsistemas repetidos.

public class SistemaCatalogo  {
    private Catalogo c; // El catalogo de los servicios elegidos por el cliente.
    private Reserva estadoReserva = new SolicitudPendiente(); // El estado de la reserva inicial del cliente.
    private final SistemasPerifericos subsistemas;

    public SistemaCatalogo (Catalogo catalogo, SistemasPerifericos subsistemas){
        this.subsistemas = subsistemas;
        this.c = catalogo;
    }
    public void agregarServicicio(Catalogo servicioAAgregar){
        estadoReserva.agregarServicio(c, servicioAAgregar);
    }
    public void confirmarReserva(){
        estadoReserva.confirmar(this.estadoReserva, this);
    }
    public void setReserva(Reserva r){
        subsistemas.notificar(estadoReserva, r);
        this.estadoReserva = r;
    }
    public Reserva getEstadoReserva() {
        return estadoReserva;
    }
}
