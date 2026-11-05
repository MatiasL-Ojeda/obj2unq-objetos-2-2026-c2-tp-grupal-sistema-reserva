package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrasladoEstandarTest {

    @Test
    void delegaElCalculoDelCostoEnPlataformaTransporte() {
        Direccion direccion = mock(Direccion.class);

        TrasladoEstandar traslado = new TrasladoEstandar(3, direccion);

        try ( MockedStatic<PlataformaTransporte> plataforma = mockStatic(PlataformaTransporte.class) ) {

            plataforma.when( () -> PlataformaTransporte.estimarTraslado(3, direccion)
            ).thenReturn(1500.0f);

            assertEquals(1500.0f, traslado.calcularCosto(), 0.001f);

            plataforma.verify( () -> PlataformaTransporte.estimarTraslado(3, direccion) );
        }
    }
}