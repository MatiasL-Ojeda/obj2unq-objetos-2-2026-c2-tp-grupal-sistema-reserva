package ar.edu.unq.po2.tp.sistemareservas;

public class Notificador extends Subsistemas{
    private final Object adjunto;

    public Notificador(MailSender remitente, Object adjunto) {
        this.remitente = remitente;
        this.adjunto = adjunto;
    }

    @Override
    public void upDate(String direccionDestino, Reserva estadoAnterior, Reserva nuevoEstado){
        if (this.SonDistintos(estadoAnterior, nuevoEstado)) {
            if (nuevoEstado instanceof Confirmada){ // confirmo si el nuevoEstado es una instancia de Confirmada.
                remitente.enviarMail(
                        direccionDestino,
                        "Su reserva fue confirmada",
                        "Usted a confirmado su reserva, lo estaremos esperando.",
                        adjunto);
            }
            if (nuevoEstado instanceof Finalizada){ // confirmo si el nuevoEstado es una instancia de Finalizada.
                remitente.enviarMail(
                        direccionDestino,
                        "Finalización de reserva",
                        "Gracias por reservar con nosotros, lo esperamos nuevamente.",
                        adjunto);
            }
            if (nuevoEstado instanceof EnCurso){ // confirmo si el nuevoEstado es una instancia de EnCurso.
                remitente.enviarMail(
                        direccionDestino,
                        "Reserva en curso",
                        "Esperamos que este disfrutando de su reserva.",
                        adjunto);
            }
        }
    }
}
