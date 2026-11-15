package ar.edu.unq.po2.tp.sistemareservas;

import java.util.Set;

public class SistemasPerifericos {
    private final Set<Subsistemas> subsistemas;
    private final Cliente cliente;

    public SistemasPerifericos (Set<Subsistemas> sub, Cliente cli) {
        this.subsistemas = sub;
        this.cliente = cli;
    }
    public void notificar (Reserva estadoAnterior, Reserva nuevoEstado) {
        this.subsistemas.forEach( sub -> sub.upDate(cliente.Correo(), estadoAnterior, nuevoEstado));
    } // Se le notifica a cada subsistema que hubo un cambio.

    public void add (Subsistemas sub) { this.subsistemas.add(sub); } // agrega un subsistema.

    public void delete (Subsistemas sub) { this.subsistemas.remove(sub); } // elimina un subsistema.

    public Set<Subsistemas> getSubsistemas () { return this.subsistemas; }
}
