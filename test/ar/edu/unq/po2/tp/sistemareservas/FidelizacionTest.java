package ar.edu.unq.po2.tp.sistemareservas;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class FidelizacionTest {

    private MailSender remitente;
    private CuponGenerator generador;
    private Fidelizacion fidelizacion;

    @BeforeEach
    void setUp() {
        remitente = mock(MailSender.class);
        generador = mock(CuponGenerator.class);
        fidelizacion = new Fidelizacion(remitente, generador);
    }

    @Test
    void generaCuponDelCincoPorCientoCuandoSeCancelaLaReserva() {
        Cupon cupon = new Cupon();

        when(generador.generarCupon(5)).thenReturn(cupon);

        fidelizacion.upDate("cliente@gmail.com",
                            new Confirmada(),
                            new Cancelada());

        verify(generador).generarCupon(5);

        verify(remitente).enviarMail(eq("cliente@gmail.com"),
                                     eq("Cancelación"),
                                     anyString(),
                                     same(cupon));
    }

    @Test
    void noGeneraCuponCuandoLaReservaNoSeCancela() {
        fidelizacion.upDate("cliente@gmail.com",
                            new Confirmada(),
                            new EnCurso());

        verifyNoInteractions(generador);
        verifyNoInteractions(remitente);
    }

    @Test
    void noGeneraCuponCuandoNoCambiaElEstado() {
        Reserva estado = new Cancelada();

        fidelizacion.upDate("cliente@gmail.com", estado, estado);

        verifyNoInteractions(generador);
        verifyNoInteractions(remitente);
    }
}