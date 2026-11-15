package ar.edu.unq.po2.tp.sistemareservas;

public class Fidelizacion extends Subsistemas{
    private final CuponGenerator generador;
    private final int descuento;

    public Fidelizacion(MailSender remitente, CuponGenerator generador) {
        this.remitente = remitente;
        this.generador = generador;
        this.descuento = 5;
    }

    @Override
    public void upDate(String direccionDestino, Reserva estadoAnterior, Reserva nuevoEstado){
        if (this.SonDistintos(estadoAnterior, nuevoEstado) && nuevoEstado instanceof Cancelada ){ // confirmo si el nuevoEstado es una instancia de Cancelada.
            remitente.enviarMail(direccionDestino,
                                "Cancelación",
                                "Su reserva fue cancelada, le pfrecemos un cupon de descuento del 5% en su proxima reserva.",
                                generador.generarCupon(descuento));
        }
    }
}
