package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrasladoChauffeurTest {

    @Test
    void delegaElCalculoDelCostoEnTrasladoVIP() {
        TrasladoChauffeur traslado = new TrasladoChauffeur(10000.0f);

        try (MockedStatic<TrasladoVIP> vip = mockStatic(TrasladoVIP.class) ) {

            vip.when( () -> TrasladoVIP.calcularCosto(10000.0f) ).thenReturn(1500.0f);

            assertEquals(1500.0f, traslado.calcularCosto(), 0.001f);

            vip.verify( () -> TrasladoVIP.calcularCosto(10000.0f) );
        }
    }
}