package ar.edu.unq.po2.tp.sistemareservas;

// Interfaz dada por lo profersores
public interface MailSender {
    void enviarMail(String direccionDestino, String titulo, String mensaje, Object adjunto);
}
