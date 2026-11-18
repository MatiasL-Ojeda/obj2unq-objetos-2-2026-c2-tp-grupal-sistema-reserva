package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AtributosTest {

    private void setField(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void tipoDeAtributoEnum() {
        assertEquals(5, TipoDeAtributo.values().length);
        assertEquals(TipoDeAtributo.TEXTO, TipoDeAtributo.valueOf("TEXTO"));
        assertEquals(TipoDeAtributo.NUMERICO, TipoDeAtributo.valueOf("NUMERICO"));
        assertEquals(TipoDeAtributo.BOOLEANO, TipoDeAtributo.valueOf("BOOLEANO"));
        assertEquals(TipoDeAtributo.FECHA, TipoDeAtributo.valueOf("FECHA"));
        assertEquals(TipoDeAtributo.TRANSPORTE, TipoDeAtributo.valueOf("TRANSPORTE"));
    }

    @Test
    void atributoDinamico() {
        AtributoDinamico ad = new AtributoDinamico("Desayuno incluido", true, TipoDeAtributo.BOOLEANO);

        assertEquals("Desayuno incluido", ad.getNombreAtributo());
        assertTrue(ad.getObligatoriedad());
        assertEquals(TipoDeAtributo.BOOLEANO, ad.getTipo());

        ad.setObligatoriedad(false);
        assertFalse(ad.getObligatoriedad());
    }

    @Test
    void valoresAtributosValidez() {
        ValorAtributoBooleano vab = new ValorAtributoBooleano();
        assertTrue(vab.validez(TipoDeAtributo.BOOLEANO));
        assertFalse(vab.validez(TipoDeAtributo.TEXTO));

        ValorAtributoFecha vaf = new ValorAtributoFecha();
        assertTrue(vaf.validez(TipoDeAtributo.FECHA));
        assertFalse(vaf.validez(TipoDeAtributo.NUMERICO));

        ValorAtributoNumerico van = new ValorAtributoNumerico();
        assertTrue(van.validez(TipoDeAtributo.NUMERICO));
        assertFalse(van.validez(TipoDeAtributo.BOOLEANO));

        ValorAtributoTexto vat = new ValorAtributoTexto();
        assertTrue(vat.validez(TipoDeAtributo.TEXTO));
        assertFalse(vat.validez(TipoDeAtributo.FECHA));

        ValorAtributoTransporte vatr = new ValorAtributoTransporte();
        assertTrue(vatr.validez(TipoDeAtributo.TRANSPORTE));
        assertFalse(vatr.validez(TipoDeAtributo.TEXTO));
    }

    @Test
    void atributoDefinidoObligatorioYValorNulo() {
        AtributoDefinido adef = new AtributoDefinido();
        AtributoDinamico ad = new AtributoDinamico("Nombre", true, TipoDeAtributo.TEXTO);
        setField(adef, "ad", ad);
        setField(adef, "va", null);

        assertFalse(adef.esValido());
    }

    @Test
    void atributoDefinidoObligatorioYValorVacio() {
        AtributoDefinido adef = new AtributoDefinido();
        AtributoDinamico ad = new AtributoDinamico("Nombre", true, TipoDeAtributo.TEXTO);
        ValorAtributoTexto vat = new ValorAtributoTexto() {
            @Override
            public String toString() {
                return "";
            }
        };
        setField(adef, "ad", ad);
        setField(adef, "va", vat);

        assertFalse(adef.esValido());
    }

    @Test
    void atributoDefinidoObligatorioValido() {
        AtributoDefinido adef = new AtributoDefinido();
        AtributoDinamico ad = new AtributoDinamico("Edad", true, TipoDeAtributo.NUMERICO);
        ValorAtributoNumerico van = new ValorAtributoNumerico() {
            @Override
            public String toString() {
                return "25";
            }
        };
        setField(adef, "ad", ad);
        setField(adef, "va", van);

        assertTrue(adef.esValido());
    }

    @Test
    void atributoDefinidoObligatorioTipoInconsistente() {
        AtributoDefinido adef = new AtributoDefinido();
        AtributoDinamico ad = new AtributoDinamico("Edad", true, TipoDeAtributo.NUMERICO);
        ValorAtributoTexto vat = new ValorAtributoTexto() {
            @Override
            public String toString() {
                return "veinticinco";
            }
        };
        setField(adef, "ad", ad);
        setField(adef, "va", vat);

        assertFalse(adef.esValido());
    }

    @Test
    void atributoDefinidoNoObligatorioYValorNulo() {
        AtributoDefinido adef = new AtributoDefinido();
        AtributoDinamico ad = new AtributoDinamico("Opcional", false, TipoDeAtributo.BOOLEANO);
        setField(adef, "ad", ad);
        setField(adef, "va", null);

        assertTrue(adef.esValido());
    }

    @Test
    void atributoDefinidoNoObligatorioConValorValido() {
        AtributoDefinido adef = new AtributoDefinido();
        AtributoDinamico ad = new AtributoDinamico("Opcional", false, TipoDeAtributo.BOOLEANO);
        ValorAtributoBooleano vab = new ValorAtributoBooleano();
        setField(adef, "ad", ad);
        setField(adef, "va", vab);

        assertTrue(adef.esValido());
    }
}
