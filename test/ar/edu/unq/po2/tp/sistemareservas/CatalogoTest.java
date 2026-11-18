package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatalogoTest {

    private void setField(Object target, String fieldName, Object value) {
        try {
            Field field = Catalogo.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void gettersYPrecioFinal() {
        Servicio s = new Servicio("Excursion Cataratas", "Iguazu Viajes", Categoria.EXCURSION, 10000.0, 20.0);

        assertEquals("Excursion Cataratas", s.getNombre());
        assertEquals("Iguazu Viajes", s.getProveedor());
        assertEquals(Categoria.EXCURSION, s.getCategoria());
        assertEquals(10000.0, s.getPrecioBase());
        assertEquals(8000.0, s.precioFinal());
        assertTrue(s.tieneDisponibilidad());

        s.setDisponibilidad(false);
        assertFalse(s.tieneDisponibilidad());

        // Test método por defecto addCatalogo que no hace nada en Servicio
        s.addCatalogo(mock(Catalogo.class));
    }

    @Test
    void validarExitoso() {
        Servicio s = new Servicio("Hotel Sol", "Hoteles SA", Categoria.ALOJAMIENTO, 5000.0, 0.0);
        assertTrue(s.validar());
    }

    @Test
    void validarFallaPorCodigoCero() {
        Servicio s = new Servicio("Hotel Sol", "Hoteles SA", Categoria.ALOJAMIENTO, 5000.0, 0.0);
        setField(s, "codigo", 0);
        assertFalse(s.validar());
    }

    @Test
    void validarFallaPorNombreNulo() {
        Servicio s = new Servicio(null, "Hoteles SA", Categoria.ALOJAMIENTO, 5000.0, 0.0);
        assertFalse(s.validar());
    }

    @Test
    void validarFallaPorNombreVacio() {
        Servicio s = new Servicio("   ", "Hoteles SA", Categoria.ALOJAMIENTO, 5000.0, 0.0);
        assertFalse(s.validar());
    }

    @Test
    @SuppressWarnings("unchecked")
    void validarConAtributosDefinidos() throws Exception {
        Servicio s = new Servicio("Hotel Sol", "Hoteles SA", Categoria.ALOJAMIENTO, 5000.0, 0.0);

        Field field = Catalogo.class.getDeclaredField("atributosDefinidos");
        field.setAccessible(true);
        List<AtributoDefinido> lista = (List<AtributoDefinido>) field.get(s);

        AtributoDefinido attrValido = mock(AtributoDefinido.class);
        when(attrValido.esValido()).thenReturn(true);
        lista.add(attrValido);

        assertTrue(s.validar());

        AtributoDefinido attrInvalido = mock(AtributoDefinido.class);
        when(attrInvalido.esValido()).thenReturn(false);
        lista.add(attrInvalido);

        assertFalse(s.validar());
    }
}
