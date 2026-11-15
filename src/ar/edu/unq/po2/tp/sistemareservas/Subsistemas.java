package ar.edu.unq.po2.tp.sistemareservas;

// interfaz la cual deben implementar los subsistemas.
public abstract class Subsistemas {
    MailSender remitente;

    Boolean SonDistintos (Reserva re1, Reserva re2) { return re1 != re2; }
    public void upDate(String direccionDestino, Reserva estadoAnterior, Reserva nuevoEstado){}
}
