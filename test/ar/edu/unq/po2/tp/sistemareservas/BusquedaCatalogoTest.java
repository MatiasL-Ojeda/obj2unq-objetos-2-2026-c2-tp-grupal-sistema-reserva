package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BusquedaCatalogoTest {

    private Servicio hotelCentral;
    private Servicio resortPlaya;
    private Servicio excursionMuseo;
    private Servicio vueloBariloche;
    private Servicio hotelOferta;
    private PaqueteTuristico packEscapada;
    private BuscadorDeCatalogo buscador;

    @BeforeEach
    void setUp() {
        hotelCentral = new Servicio("Hotel Central", "Central SA", Categoria.ALOJAMIENTO, 15000, 0);
        resortPlaya = new Servicio("Resort Paraiso", "Playas SA", Categoria.ALOJAMIENTO, 22000, 0);
        excursionMuseo = new Servicio("Excursion Museo Historico", "Guías SA", Categoria.EXCURSION, 5000, 0);
        vueloBariloche = new Servicio("Vuelo a Bariloche", "Aerolineas", Categoria.VUELO, 30000, 0);
        hotelOferta = new Servicio("Hotel Oferta Especial", "Posadas SA", Categoria.ALOJAMIENTO, 12000, 0);

        packEscapada = new PaqueteTuristico("Pack Escapada Urbana", "Agencia", Categoria.PAQUETE, 10);
        packEscapada.addCatalogo(hotelCentral);
        packEscapada.addCatalogo(excursionMuseo);

        buscador = new BuscadorDeCatalogo();
        buscador.agregarItem(hotelCentral);
        buscador.agregarItem(resortPlaya);
        buscador.agregarItem(excursionMuseo);
        buscador.agregarItem(vueloBariloche);
        buscador.agregarItem(hotelOferta);
        buscador.agregarItem(packEscapada);
    }

    @Test
    void criterioPorNombreSinDistincionDeMayusculas() {
        Criterio criterio = new CriterioPorNombre("hotel");

        List<Catalogo> resultados = buscador.buscar(criterio);

        assertEquals(2, resultados.size());
        assertTrue(resultados.contains(hotelCentral));
        assertTrue(resultados.contains(hotelOferta));
        assertFalse(resultados.contains(resortPlaya));
    }

    @Test
    void criterioPorPrecioMaximo() {
        Criterio criterio = new CriterioPorPrecioMaximo(15000);

        List<Catalogo> resultados = buscador.buscar(criterio);

        assertEquals(3, resultados.size());
        assertTrue(resultados.contains(hotelCentral));
        assertTrue(resultados.contains(excursionMuseo));
        assertTrue(resultados.contains(hotelOferta));
        assertFalse(resultados.contains(resortPlaya));
        assertFalse(resultados.contains(packEscapada));
    }

    @Test
    void criterioPorCategoria() {
        Criterio criterio = new CriterioPorCategoria(Categoria.EXCURSION);

        List<Catalogo> resultados = buscador.buscar(criterio);

        assertEquals(1, resultados.size());
        assertTrue(resultados.contains(excursionMuseo));
    }

    @Test
    void criterioPorDisponibilidad() {
        vueloBariloche.setDisponibilidad(false);

        Criterio criterio = new CriterioPorDisponibilidad();

        List<Catalogo> resultados = buscador.buscar(criterio);

        assertEquals(5, resultados.size());
        assertFalse(resultados.contains(vueloBariloche));
        assertTrue(resultados.contains(hotelCentral));
    }

    @Test
    void criterioAndSimple() {
        resortPlaya.setDisponibilidad(false);

        Criterio criterio = new CriterioAnd(List.of(
                new CriterioPorCategoria(Categoria.ALOJAMIENTO),
                new CriterioPorPrecioMaximo(20000),
                new CriterioPorDisponibilidad()
        ));

        List<Catalogo> resultados = buscador.buscar(criterio);

        assertEquals(2, resultados.size());
        assertTrue(resultados.contains(hotelCentral));
        assertTrue(resultados.contains(hotelOferta));
        assertFalse(resultados.contains(resortPlaya));
    }

    @Test
    void criterioOrSimple() {
        Criterio criterio = new CriterioOr(List.of(
                new CriterioPorNombre("hotel"),
                new CriterioPorNombre("resort")
        ));

        List<Catalogo> resultados = buscador.buscar(criterio);

        assertEquals(3, resultados.size());
        assertTrue(resultados.contains(hotelCentral));
        assertTrue(resultados.contains(hotelOferta));
        assertTrue(resultados.contains(resortPlaya));
    }

    @Test
    void criterioNotSimple() {
        Criterio criterio = new CriterioNot(new CriterioPorCategoria(Categoria.EXCURSION));

        List<Catalogo> resultados = buscador.buscar(criterio);

        assertEquals(5, resultados.size());
        assertFalse(resultados.contains(excursionMuseo));
    }

    @Test
    void consultaAnidada1_AlojamientoYDisponible_O_NombreContieneOferta() {
        hotelCentral.setDisponibilidad(false);

        Criterio criterio = new CriterioOr(List.of(
                new CriterioAnd(List.of(
                        new CriterioPorCategoria(Categoria.ALOJAMIENTO),
                        new CriterioPorDisponibilidad()
                )),
                new CriterioPorNombre("oferta")
        ));

        List<Catalogo> resultados = buscador.buscar(criterio);

        assertEquals(2, resultados.size());
        assertTrue(resultados.contains(resortPlaya));
        assertTrue(resultados.contains(hotelOferta));
        assertFalse(resultados.contains(hotelCentral));
    }

    @Test
    void consultaAnidada2_Not_PrecioMayor15000_Y_AlojamientoOExcursion() {
        Criterio precioMayor15000 = new CriterioNot(new CriterioPorPrecioMaximo(15000));
        Criterio alojamientoOExcursion = new CriterioOr(List.of(
                new CriterioPorCategoria(Categoria.ALOJAMIENTO),
                new CriterioPorCategoria(Categoria.EXCURSION)
        ));

        Criterio criterio = new CriterioNot(
                new CriterioAnd(List.of(precioMayor15000, alojamientoOExcursion))
        );

        List<Catalogo> resultados = buscador.buscar(criterio);

        assertFalse(resultados.contains(resortPlaya));
        assertTrue(resultados.contains(hotelCentral));
        assertTrue(resultados.contains(excursionMuseo));
        assertTrue(resultados.contains(vueloBariloche));
        assertTrue(resultados.contains(hotelOferta));
        assertTrue(resultados.contains(packEscapada));
    }

    @Test
    void consultaAnidada3_CompuestaConListas() {
        Criterio criterio = new CriterioAnd(List.of(
                new CriterioPorNombre("pack"),
                new CriterioNot(new CriterioPorCategoria(Categoria.EXCURSION)),
                new CriterioPorPrecioMaximo(25000)
        ));

        List<Catalogo> resultados = buscador.buscar(criterio);

        assertEquals(1, resultados.size());
        assertTrue(resultados.contains(packEscapada));
    }
}
