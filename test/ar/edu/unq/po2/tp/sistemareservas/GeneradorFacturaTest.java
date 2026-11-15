package ar.edu.unq.po2.tp.sistemareservas;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class GeneradorFacturaTest {

    private MailSender remitente;
    private VoucherGenerator generador;
    private GeneradorFactura generadorFactura;

    @BeforeEach
    void setUp() {
        remitente = mock(MailSender.class);
        generador = mock(VoucherGenerator.class);
        generadorFactura = new GeneradorFactura(remitente, generador);
    }

    @Test
    void generaComprobanteCuandoLaReservaFinaliza() {
        Comprobante comprobante = new Comprobante();

        when(generador.generarComprobante()).thenReturn(comprobante);

        generadorFactura.upDate("cliente@gmail.com",
                                new EnCurso(),
                                new Finalizada());

        verify(generador).generarComprobante();

        verify(remitente).enviarMail(eq("cliente@gmail.com"),
                                     eq("Finalización de reserva"),
                                     anyString(),
                                     same(comprobante));
    }

    @Test
    void noGeneraComprobanteSiLaReservaNoFinaliza() {
        generadorFactura.upDate("cliente@gmail.com",
                                new Confirmada(),
                                new EnCurso());

        verifyNoInteractions(generador);
        verifyNoInteractions(remitente);
    }

    @Test
    void noGeneraComprobanteSiNoCambiaElEstado() {
        Reserva estado = new Finalizada();

        generadorFactura.upDate("cliente@gmail.com", estado, estado);

        verifyNoInteractions(generador);
        verifyNoInteractions(remitente);
    }
}