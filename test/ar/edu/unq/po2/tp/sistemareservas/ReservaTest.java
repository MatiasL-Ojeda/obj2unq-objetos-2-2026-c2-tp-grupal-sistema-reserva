package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaTest {

    private SistemaCatalogo mockSistema;
    private Catalogo mockCatalogoCliente;
    private Catalogo mockServicio;

    @BeforeEach
    void setUp() {
        mockSistema = mock(SistemaCatalogo.class);
        mockCatalogoCliente = mock(Catalogo.class);
        mockServicio = mock(Catalogo.class);
    }

    @Test
    void reservaBaseLanzaExcepcionesEnTodosSusMetodos() {
        Reserva base = new Reserva() {};

        assertThrows(IllegalStateException.class, () -> base.confirmar(base, mockSistema));
        assertThrows(IllegalStateException.class, () -> base.checkear(base, mockSistema));
        assertThrows(IllegalStateException.class, () -> base.comenzar(base, mockSistema));
        assertThrows(IllegalStateException.class, () -> base.finalizar(base, mockSistema));
        assertThrows(IllegalStateException.class, () -> base.cancelar(base, mockSistema));
        assertThrows(IllegalStateException.class, () -> base.agregarServicio(mockCatalogoCliente, mockServicio));
    }

    @Test
    void solicitudPendienteTransicionesValidas() {
        SolicitudPendiente sp = new SolicitudPendiente();

        sp.confirmar(sp, mockSistema);
        verify(mockSistema).setReserva(any(Confirmada.class));

        sp.cancelar(sp, mockSistema);
        verify(mockSistema).setReserva(any(Cancelada.class));

        sp.agregarServicio(mockCatalogoCliente, mockServicio);
        verify(mockCatalogoCliente).addCatalogo(mockServicio);
    }

    @Test
    void solicitudPendienteTransicionesInvalidas() {
        SolicitudPendiente sp = new SolicitudPendiente();

        assertThrows(IllegalStateException.class, () -> sp.checkear(sp, mockSistema));
        assertThrows(IllegalStateException.class, () -> sp.comenzar(sp, mockSistema));
        assertThrows(IllegalStateException.class, () -> sp.finalizar(sp, mockSistema));
    }

    @Test
    void confirmadaTransicionesValidas() {
        Confirmada c = new Confirmada();

        c.checkear(c, mockSistema);
        verify(mockSistema).setReserva(any(EnCheckin.class));

        c.cancelar(c, mockSistema);
        verify(mockSistema).setReserva(any(Cancelada.class));
    }

    @Test
    void confirmadaTransicionesInvalidas() {
        Confirmada c = new Confirmada();

        assertThrows(IllegalStateException.class, () -> c.confirmar(c, mockSistema));
        assertThrows(IllegalStateException.class, () -> c.comenzar(c, mockSistema));
        assertThrows(IllegalStateException.class, () -> c.finalizar(c, mockSistema));
        assertThrows(IllegalStateException.class, () -> c.agregarServicio(mockCatalogoCliente, mockServicio));
    }

    @Test
    void enCheckinTransicionesValidas() {
        EnCheckin ec = new EnCheckin();

        ec.comenzar(ec, mockSistema);
        verify(mockSistema).setReserva(any(EnCurso.class));

        ec.cancelar(ec, mockSistema);
        verify(mockSistema).setReserva(any(Cancelada.class));
    }

    @Test
    void enCheckinTransicionesInvalidas() {
        EnCheckin ec = new EnCheckin();

        assertThrows(IllegalStateException.class, () -> ec.confirmar(ec, mockSistema));
        assertThrows(IllegalStateException.class, () -> ec.checkear(ec, mockSistema));
        assertThrows(IllegalStateException.class, () -> ec.finalizar(ec, mockSistema));
        assertThrows(IllegalStateException.class, () -> ec.agregarServicio(mockCatalogoCliente, mockServicio));
    }

    @Test
    void enCursoTransicionesValidas() {
        EnCurso ec = new EnCurso();

        ec.finalizar(ec, mockSistema);
        verify(mockSistema).setReserva(any(Finalizada.class));

        ec.cancelar(ec, mockSistema);
        verify(mockSistema).setReserva(any(Cancelada.class));
    }

    @Test
    void enCursoTransicionesInvalidas() {
        EnCurso ec = new EnCurso();

        assertThrows(IllegalStateException.class, () -> ec.confirmar(ec, mockSistema));
        assertThrows(IllegalStateException.class, () -> ec.checkear(ec, mockSistema));
        assertThrows(IllegalStateException.class, () -> ec.comenzar(ec, mockSistema));
        assertThrows(IllegalStateException.class, () -> ec.agregarServicio(mockCatalogoCliente, mockServicio));
    }

    @Test
    void finalizadaTransicionesInvalidas() {
        Finalizada f = new Finalizada();

        assertThrows(IllegalStateException.class, () -> f.confirmar(f, mockSistema));
        assertThrows(IllegalStateException.class, () -> f.checkear(f, mockSistema));
        assertThrows(IllegalStateException.class, () -> f.comenzar(f, mockSistema));
        assertThrows(IllegalStateException.class, () -> f.finalizar(f, mockSistema));
        assertThrows(IllegalStateException.class, () -> f.cancelar(f, mockSistema));
        assertThrows(IllegalStateException.class, () -> f.agregarServicio(mockCatalogoCliente, mockServicio));
    }

    @Test
    void canceladaTransicionesInvalidas() {
        Cancelada c = new Cancelada();

        assertThrows(IllegalStateException.class, () -> c.confirmar(c, mockSistema));
        assertThrows(IllegalStateException.class, () -> c.checkear(c, mockSistema));
        assertThrows(IllegalStateException.class, () -> c.comenzar(c, mockSistema));
        assertThrows(IllegalStateException.class, () -> c.finalizar(c, mockSistema));
        assertThrows(IllegalStateException.class, () -> c.cancelar(c, mockSistema));
        assertThrows(IllegalStateException.class, () -> c.agregarServicio(mockCatalogoCliente, mockServicio));
    }
}
