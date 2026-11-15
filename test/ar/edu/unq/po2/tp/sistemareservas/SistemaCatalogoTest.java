package ar.edu.unq.po2.tp.sistemareservas;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SistemaCatalogoTest {

    private SistemasPerifericos perifericos;
    private SistemaCatalogo sistema;

    @BeforeEach
    void setUp() {
        perifericos = mock(SistemasPerifericos.class);
        sistema     = new SistemaCatalogo(mock(Catalogo.class), perifericos);
    }

    @Test
    void notificaCuandoCambiaElEstado() {
        Reserva nuevoEstado = new Confirmada();

        sistema.setReserva(nuevoEstado);

        verify(perifericos).notificar(any(SolicitudPendiente.class), same(nuevoEstado));
    }

    @Test
    void actualizaElEstadoDeLaReserva() {
        Reserva nuevoEstado = new Confirmada();

        sistema.setReserva(nuevoEstado);

        assertSame(nuevoEstado, sistema.getEstadoReserva());
    }
}
