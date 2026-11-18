package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SistemaCatalogoTest {

    private SistemasPerifericos perifericos;
    private Catalogo catalogo;
    private SistemaCatalogo sistema;

    @BeforeEach
    void setUp() {
        perifericos = mock(SistemasPerifericos.class);
        catalogo    = mock(Catalogo.class);
        sistema     = new SistemaCatalogo(catalogo, perifericos);
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

    @Test
    void confirmaReservaActualizandoEstadoAConfirmada() {
        sistema.confirmarReserva();

        assertTrue(sistema.getEstadoReserva() instanceof Confirmada);
        verify(perifericos).notificar(any(SolicitudPendiente.class), any(Confirmada.class));
    }

    @Test
    void agregaServicioCuandoEstadoEsSolicitudPendiente() {
        Catalogo nuevoServicio = mock(Catalogo.class);

        sistema.agregarServicicio(nuevoServicio);

        verify(catalogo).addCatalogo(nuevoServicio);
    }

    @Test
    void lanzarExcepcionAlAgregarServicioEnEstadoConfirmada() {
        sistema.confirmarReserva();
        Catalogo nuevoServicio = mock(Catalogo.class);

        assertThrows(IllegalStateException.class, () -> sistema.agregarServicicio(nuevoServicio));
    }
}
