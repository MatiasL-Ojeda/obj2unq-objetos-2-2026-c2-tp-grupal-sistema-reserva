package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrasladoPuntoEncuentroTest {
    private PuntoDeRecepcion p1 = new PuntoDeRecepcion("Aeropuerto");
    private PuntoDeRecepcion p2 = new PuntoDeRecepcion("Terminal");
    private GestorDeGuias gG1 = new GestorDeGuias();
    private Guia g1 = new Guia(true, p1);
    private Guia g2 = new Guia(false, p2);

    @BeforeEach
    void setUp(){
        this.gG1.agregarGuia(g1);
        this.gG1.agregarGuia(g2);
    }

    @Test
    void elCostoDelTrasladoSiempreEsCero() {
        TrasladoPuntoEncuentro traslado = new TrasladoPuntoEncuentro(p1, gG1);

        assertEquals(0.0f, traslado.calcularCosto());
    }

    @Test
    void hayDisponibilidadCuandoHayUnGuiaDisponibleEnElPunto() {
        TrasladoPuntoEncuentro traslado = new TrasladoPuntoEncuentro(p1, gG1);

        assertTrue(traslado.hayDisponibilidad());
    }

    @Test
    void noHayDisponibilidadCuandoElGuiaNoEstaDisponible() {
        TrasladoPuntoEncuentro traslado = new TrasladoPuntoEncuentro(p2, gG1);

        assertFalse(traslado.hayDisponibilidad());
    }
}