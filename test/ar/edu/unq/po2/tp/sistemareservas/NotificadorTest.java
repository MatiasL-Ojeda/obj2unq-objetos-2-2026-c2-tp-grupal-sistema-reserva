package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class NotificadorTest {

    private MailSender remitente;
    private Notificador notificador;
    private Object adjunto;

    @BeforeEach
    void setUp() {
        remitente   = mock(MailSender.class);
        adjunto     = new Object();
        notificador = new Notificador(remitente, adjunto);
    }

    @Test
    void enviaMailCuandoLaReservaEsConfirmada() {
        notificador.upDate("cliente@gmail.com",
                            new SolicitudPendiente(),
                            new Confirmada());

        verify(remitente).enviarMail(eq("cliente@gmail.com"),
                                     eq("Su reserva fue confirmada"),
                                     anyString(),
                                     same(adjunto));
    }

    @Test
    void enviaMailCuandoLaReservaEstaEnCurso() {
        notificador.upDate("cliente@gmail.com",
                            new Confirmada(),
                            new EnCurso());

        verify(remitente).enviarMail(eq("cliente@gmail.com"),
                                     eq("Reserva en curso"),
                                     anyString(),
                                     same(adjunto));
    }

    @Test
    void enviaMailCuandoLaReservaFinaliza() {
        notificador.upDate("cliente@gmail.com",
                            new EnCurso(),
                            new Finalizada());

        verify(remitente).enviarMail(eq("cliente@gmail.com"),
                                     eq("Finalización de reserva"),
                                     anyString(),
                                     same(adjunto));
    }

    @Test
    void noEnviaMailCuandoLaReservaEsCancelada() {
        notificador.upDate("cliente@gmail.com",
                            new Confirmada(),
                            new Cancelada());

        verifyNoInteractions(remitente);
    }

    @Test
    void noEnviaMailCuandoLosEstadosSonElMismoObjeto() {
        Reserva estado = new Confirmada();

        notificador.upDate("cliente@gmail.com", estado, estado);

        verifyNoInteractions(remitente);
    }
}