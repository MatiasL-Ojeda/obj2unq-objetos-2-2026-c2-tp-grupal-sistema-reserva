package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AtributoDinamicoTest {

    // Declaramos una instancia base que usaremos en varios tests
    private AtributoDinamico<String> atributoTexto;

    @BeforeEach void setUp() {
        // Generamos un atributo que vamor a utilizar en la mayoria de tests
        atributoTexto = new AtributoDinamico<>(
                "Email", true, TipoDeAtributo.TEXTO, "test@correo.com"
        );
    }

    @Test
    void creacionExitosaConValorValido() {
        // Usamos el objeto inicializado en el @BeforeEach
        assertEquals("Email", atributoTexto.getNombreAtributo());
        assertTrue(atributoTexto.getObligatoriedad());
        assertEquals(TipoDeAtributo.TEXTO, atributoTexto.getTipo());
        assertEquals("test@correo.com", atributoTexto.getValor());
    }

    @Test
    void permiteValorNuloSiNoEsObligatorio() {
        // En este caso necesitamos un atributo diferente al inicial, asi que los creamos aca
        AtributoDinamico<String> atributoOpcional = new AtributoDinamico<>(
                "Comentarios", false, TipoDeAtributo.TEXTO, null
        );
        // Comprobacion
        assertNull(atributoOpcional.getValor());
        assertFalse(atributoOpcional.getObligatoriedad());
    }

    @Test
    void actualizarValorExitosamenteConSetValor() {
        // Modificamos el valor del atributo inicial para comprobar que se pueda actualizar
        atributoTexto.setValor("nuevo_correo@correo.com");

        // Comprobacion
        assertEquals("nuevo_correo@correo.com", atributoTexto.getValor());
    }

    @Test
    void lanzaExcepcionAlModificarConSetValorInvalido() {
        // De cualquier otra forma no funcionaba bien el testeo, por lo que utilizams
        // una referencia comodín (?) para asignarle una instancia con tipo abierto
        // y evitar errores de compilación.
        AtributoDinamico<?> atributoGenerico = new AtributoDinamico<>(
                "Edad", true, TipoDeAtributo.NUMERO, 25
        );

        // Intentamos asignarle un String a un atributo que espera un Integer (según TipoDeAtributo.NUMERO)
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    @SuppressWarnings("unchecked")
                    AtributoDinamico<Object> attr = (AtributoDinamico<Object>) atributoGenerico;
                    attr.setValor("Esto es un texto y no un número");
                }
        );
    }
}
