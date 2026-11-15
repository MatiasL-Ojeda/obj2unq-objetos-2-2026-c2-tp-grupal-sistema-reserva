package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.Mockito.*;

class SistemasPerifericosTest {

    private Cliente cliente;
    private Subsistemas sub1;
    private Subsistemas sub2;
    private SistemasPerifericos sistema;

    @BeforeEach
    void setUp() {
        MetodoPago metodoPago = mock(MetodoPago.class);
        cliente = new Cliente(metodoPago, "cliente@gmail.com");

        sub1 = mock(Subsistemas.class);
        sub2 = mock(Subsistemas.class);

        Set<Subsistemas> subsistemas = new HashSet<>();
        subsistemas.add(sub1);
        subsistemas.add(sub2);

        sistema = new SistemasPerifericos(subsistemas, cliente);
    }

    @Test
    void notificaATodosLosSubsistemas() {
        Reserva anterior = mock(Reserva.class);
        Reserva nuevo = mock(Reserva.class);

        sistema.notificar(anterior, nuevo);

        verify(sub1).upDate("cliente@gmail.com", anterior, nuevo);
        verify(sub2).upDate("cliente@gmail.com", anterior, nuevo);
    }

    @Test
    void agregaUnSubsistema() {
        Subsistemas sub3 = mock(Subsistemas.class);

        sistema.add(sub3);
        sistema.notificar(mock(Reserva.class), mock(Reserva.class));

        verify(sub3).upDate(anyString(), any(), any());
    }

    @Test
    void eliminaUnSubsistema() {
        sistema.delete(sub1);

        sistema.notificar(mock(Reserva.class), mock(Reserva.class));

        verify(sub1, never()).upDate(anyString(), any(), any());
    }
}