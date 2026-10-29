package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatalogoTest {
    @Test
    void testCatalogo(){
        PaqueteTuristico peu = new PaqueteTuristico("Pack Escapada Urbana", "Charlie Chaplin",    Categoria.PAQUETE, 10);
        Catalogo nhc = new Servicio("Noche de Hotel Central",    "Hotel Central",         Categoria.ALOJAMIENTO,  15000, 0);
        Catalogo egm = new Servicio("Excursion Guiada Museo",    "Una Noche en el Museo", Categoria.EXCURSION,    4000,  0);
        Catalogo tah = new Servicio("Traslado Aeropuerto-Hotel", "Jason Statham",         Categoria.ALQUILERAUTO, 2000,  0);
        peu.agregarServicio(nhc);
        peu.agregarServicio(egm);
        peu.agregarServicio(tah);
        double precioFinal = peu.precioFinal();

        assertEquals(18900, precioFinal, "El precio final deberia ser 18.900");
    }
}
