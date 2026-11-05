package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ServicioTuristicoTest {
    private Servicio s1;
    private Servicio s2;
    private Servicio s3;
    private PaqueteTuristico peu;
    @BeforeEach
    public void setUp(){
        // SetUp
        //DOC: doubles
        this.s1 = mock(Servicio.class);//("Noche de Hotel Central",    "Hotel Central",         Categoria.ALOJAMIENTO,  15000, 0);
        this.s2 = mock(Servicio.class);//("Excursion Guiada Museo",    "Una Noche en el Museo", Categoria.EXCURSION,    4000,  0);
        this.s3 = mock(Servicio.class);//("Traslado Aeropuerto-Hotel", "Jason Statham",         Categoria.ALQUILERAUTO, 2000,  0);
        this.peu = new PaqueteTuristico("Pack Escapada Urbana", "Charlie Chaplin",    Categoria.PAQUETE, 10);
    }
    @Test
    void testunPaqueteTuristicoSumaLosPreciosDeVariosServicios(){
        when(s1.getPrecioBase()).thenReturn(15000.0);
        when(s2.getPrecioBase()).thenReturn(4000.0);
        when(s3.getPrecioBase()).thenReturn(2000.0);

        this.peu.addCatalogo(s1);
        this.peu.addCatalogo(s2);
        this.peu.addCatalogo(s3);

        double precioFinal = this.peu.precioFinal();

        assertEquals(18900, precioFinal, "El precio final deberia ser 18.900");

    }
}
