package ar.edu.unq.po2.tp.sistemareservas;

public class GeneradorFactura extends Subsistemas{
    private final VoucherGenerator generator;

    public GeneradorFactura(MailSender remitente, VoucherGenerator generador) {
        this.remitente = remitente;
        this.generator = generador;
    }

    @Override
    public void upDate(String direccionDestino, Reserva estadoAnterior, Reserva nuevoEstado){
        if (this.SonDistintos(estadoAnterior, nuevoEstado) && nuevoEstado instanceof Finalizada){ // confirmo si el nuevoEstado es una instancia de Finalizada.
            Comprobante comprobante = generator.generarComprobante();
            remitente.enviarMail(direccionDestino,
                                "Finalización de reserva",
                                "Gracias por reservar con nosotros, lo esperamos nuevamente.",
                                comprobante);
        }
    }
}