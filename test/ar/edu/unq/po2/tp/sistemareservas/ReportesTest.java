package ar.edu.unq.po2.tp.sistemareservas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportesTest {

    private Servicio hotelCentral;
    private Servicio excursionMuseo;
    private Servicio trasladoAeropuerto;
    private PaqueteTuristico packEscapada;

    private ReporteVisitor exportadorTexto;
    private ReporteVisitor exportadorCsv;
    private ReporteVisitor exportadorHtml;

    @BeforeEach
    void setUp() {
        hotelCentral = new Servicio("Noche de Hotel Central", "Hotel Central SA", Categoria.ALOJAMIENTO, 15000, 0);
        excursionMuseo = new Servicio("Excursión Guiada Museo", "Guías SA", Categoria.EXCURSION, 4000, 0);
        trasladoAeropuerto = new Servicio("Traslado Aeropuerto-Hotel", "Transfers VIP", Categoria.ALQUILERAUTO, 2000, 0);

        packEscapada = new PaqueteTuristico("Pack Escapada Urbana", "Agencia Central", Categoria.PAQUETE, 10);
        packEscapada.addCatalogo(hotelCentral);
        packEscapada.addCatalogo(excursionMuseo);

        exportadorTexto = new ExportadorTextoPlano();
        exportadorCsv = new ExportadorCSV();
        exportadorHtml = new ExportadorHTML();
    }

    @Test
    void reporteServiciosMasReservadosCalculaCantidadesYPrecioPromedio() {
        LocalDate hoy = LocalDate.of(2026, 7, 10);

        RegistroVenta v1 = new RegistroVenta(hotelCentral, 15000.0, hoy);
        RegistroVenta v2 = new RegistroVenta(hotelCentral, 13500.0, hoy);

        RegistroVenta v3 = new RegistroVenta(excursionMuseo, 4000.0, hoy);

        RegistroVenta v4 = new RegistroVenta(packEscapada, 18900.0, hoy);
        RegistroVenta v5 = new RegistroVenta(packEscapada, 18900.0, hoy);
        RegistroVenta v6 = new RegistroVenta(packEscapada, 18900.0, hoy);

        RegistroVenta vFuera = new RegistroVenta(trasladoAeropuerto, 2000.0, hoy.minusDays(30));

        List<RegistroVenta> ventas = List.of(v1, v2, v3, v4, v5, v6, vFuera);

        ReporteServiciosMasReservados reporte = new ReporteServiciosMasReservados(
                ventas,
                LocalDate.of(2026, 7, 1),
                LocalDate.of(2026, 7, 31)
        );

        List<ReporteServiciosMasReservados.FilaServicioMasReservado> filas = reporte.getFilas();

        assertEquals(3, filas.size(), "Debe haber 3 ítems dentro del período");

        assertEquals("Pack Escapada Urbana", filas.get(0).nombreItem());
        assertEquals(3, filas.get(0).unidadesVendidas());
        assertEquals(18900.0, filas.get(0).precioPromedioCobrado(), 0.01);

        assertEquals("Noche de Hotel Central", filas.get(1).nombreItem());
        assertEquals(2, filas.get(1).unidadesVendidas());
        assertEquals(14250.0, filas.get(1).precioPromedioCobrado(), 0.01);

        assertEquals("Excursión Guiada Museo", filas.get(2).nombreItem());
        assertEquals(1, filas.get(2).unidadesVendidas());
        assertEquals(4000.0, filas.get(2).precioPromedioCobrado(), 0.01);
    }

    @Test
    void reporteServiciosMasReservadosExportacionEnFormatos() {
        Reporte reporte = new ReporteServiciosMasReservados(List.of(
                new ReporteServiciosMasReservados.FilaServicioMasReservado("Noche de Hotel Central", 5, 14000.0)
        ));

        String txt = reporte.exportar(exportadorTexto);
        assertTrue(txt.contains("=== REPORTE DE SERVICIOS MAS RESERVADOS ==="));
        assertTrue(txt.contains("Noche de Hotel Central"));
        assertTrue(txt.contains("5"));
        assertTrue(txt.contains("14000.00"));

        String csv = reporte.exportar(exportadorCsv);
        assertTrue(csv.startsWith("item,unidades_vendidas,precio_promedio_cobrado\n"));
        assertTrue(csv.contains("\"Noche de Hotel Central\",5,14000.00"));

        String html = reporte.exportar(exportadorHtml);
        assertTrue(html.contains("<table class=\"reporte reporte-servicios\">"));
        assertTrue(html.contains("<td>Noche de Hotel Central</td>"));
        assertTrue(html.contains("<td>5</td>"));
        assertTrue(html.contains("<td>$14000.00</td>"));
        assertTrue(html.contains("</table>"));
    }

    @Test
    void reporteServiciosMasReservadosVentasVacias() {
        ReporteServiciosMasReservados reporte = new ReporteServiciosMasReservados(
                Collections.emptyList(),
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 31)
        );

        assertTrue(reporte.getFilas().isEmpty());
        String csv = reporte.exportar(exportadorCsv);
        assertEquals("item,unidades_vendidas,precio_promedio_cobrado\n", csv);
    }

    @Test
    void reporteOcupacionCuposCalculaLibresYTotalesPorCategoria() {
        LocalDate fecha = LocalDate.of(2026, 7, 15);

        RegistroOcupacion r1 = new RegistroOcupacion(Categoria.ALOJAMIENTO, 50, 30, fecha);
        RegistroOcupacion r2 = new RegistroOcupacion(Categoria.ALOJAMIENTO, 50, 10, fecha);
        RegistroOcupacion r3 = new RegistroOcupacion(Categoria.VUELO, 120, 100, fecha);
        RegistroOcupacion rFuera = new RegistroOcupacion(Categoria.EXCURSION, 20, 10, fecha.minusMonths(2));

        List<RegistroOcupacion> registros = List.of(r1, r2, r3, rFuera);

        ReporteOcupacionCupos reporte = new ReporteOcupacionCupos(
                registros,
                LocalDate.of(2026, 7, 1),
                LocalDate.of(2026, 7, 31)
        );

        List<ReporteOcupacionCupos.FilaOcupacion> filas = reporte.getFilas();

        assertEquals(2, filas.size(), "Solo ALOJAMIENTO y VUELO estan en el período");

        ReporteOcupacionCupos.FilaOcupacion fAlojamiento = filas.get(0);
        assertEquals(Categoria.ALOJAMIENTO, fAlojamiento.categoria());
        assertEquals(100, fAlojamiento.cuposTotales());
        assertEquals(40, fAlojamiento.cuposReservados());
        assertEquals(60, fAlojamiento.cuposLibres());

        ReporteOcupacionCupos.FilaOcupacion fVuelo = filas.get(1);
        assertEquals(Categoria.VUELO, fVuelo.categoria());
        assertEquals(120, fVuelo.cuposTotales());
        assertEquals(100, fVuelo.cuposReservados());
        assertEquals(20, fVuelo.cuposLibres());
    }

    @Test
    void reporteOcupacionCuposExportacionEnFormatos() {
        Reporte reporte = new ReporteOcupacionCupos(List.of(
                new ReporteOcupacionCupos.FilaOcupacion(Categoria.ALOJAMIENTO, 100, 35, 65)
        ));

        String txt = reporte.exportar(exportadorTexto);
        assertTrue(txt.contains("=== REPORTE DE OCUPACION Y CUPOS ==="));
        assertTrue(txt.contains("ALOJAMIENTO"));
        assertTrue(txt.contains("100"));
        assertTrue(txt.contains("35"));
        assertTrue(txt.contains("65"));

        String csv = reporte.exportar(exportadorCsv);
        assertTrue(csv.startsWith("categoria,cupos_totales,cupos_reservados,cupos_libres\n"));
        assertTrue(csv.contains("ALOJAMIENTO,100,35,65"));

        String html = reporte.exportar(exportadorHtml);
        assertTrue(html.contains("<table class=\"reporte reporte-ocupacion\">"));
        assertTrue(html.contains("<td>ALOJAMIENTO</td>"));
        assertTrue(html.contains("<td>100</td>"));
        assertTrue(html.contains("<td>35</td>"));
        assertTrue(html.contains("<td>65</td>"));
    }

    @Test
    void reporteExposicionOperadorCalculaPorcentajesYConcentracion() {
        Servicio s1 = new Servicio("Hotel 1", "Operador A", Categoria.ALOJAMIENTO, 1000, 0);
        Servicio s2 = new Servicio("Hotel 2", "Operador A", Categoria.ALOJAMIENTO, 1200, 0);
        Servicio s3 = new Servicio("Excursion 1", "Operador A", Categoria.EXCURSION, 500, 0);
        Servicio s4 = new Servicio("Vuelo 1", "Operador B", Categoria.VUELO, 5000, 0);

        List<Catalogo> catalogo = List.of(s1, s2, s3, s4);

        ReporteExposicionOperador reporte = new ReporteExposicionOperador(catalogo);
        List<ReporteExposicionOperador.FilaExposicion> filas = reporte.getFilas();

        assertEquals(2, filas.size());
        assertEquals("Operador A", filas.get(0).operador());
        assertEquals(3, filas.get(0).cantidadItems());
        assertEquals(75.0, filas.get(0).porcentajeCatalogo(), 0.01);

        assertEquals("Operador B", filas.get(1).operador());
        assertEquals(1, filas.get(1).cantidadItems());
        assertEquals(25.0, filas.get(1).porcentajeCatalogo(), 0.01);
    }

    @Test
    void reporteExposicionOperadorExportacionEnFormatos() {
        Servicio s1 = new Servicio("Hotel 1", "Central SA", Categoria.ALOJAMIENTO, 1000, 0);
        Servicio s2 = new Servicio("Vuelo 1", "Aerolineas", Categoria.VUELO, 5000, 0);

        Reporte reporte = new ReporteExposicionOperador(List.of(s1, s2));

        String txt = reporte.exportar(exportadorTexto);
        assertTrue(txt.contains("=== REPORTE DE EXPOSICION POR OPERADOR ==="));
        assertTrue(txt.contains("Central SA"));
        assertTrue(txt.contains("50.00%"));

        String csv = reporte.exportar(exportadorCsv);
        assertTrue(csv.startsWith("operador,cantidad_items,porcentaje_catalogo\n"));
        assertTrue(csv.contains("\"Central SA\",1,50.00"));
        assertTrue(csv.contains("\"Aerolineas\",1,50.00"));

        String html = reporte.exportar(exportadorHtml);
        assertTrue(html.contains("<table class=\"reporte reporte-exposicion\">"));
        assertTrue(html.contains("<td>Central SA</td>"));
        assertTrue(html.contains("<td>50.00%</td>"));
    }

    @Test
    void reporteExposicionCatalogoVacio() {
        ReporteExposicionOperador reporte = new ReporteExposicionOperador(Collections.emptyList());
        assertTrue(reporte.getFilas().isEmpty());

        String csv = reporte.exportar(exportadorCsv);
        assertEquals("operador,cantidad_items,porcentaje_catalogo\n", csv);

        ReporteExposicionOperador reporteNull = new ReporteExposicionOperador(null);
        assertTrue(reporteNull.getFilas().isEmpty());
    }

    @Test
    void reporteExposicionConProveedorNuloUsaSinProveedor() {
        Servicio s1 = new Servicio("Tour Sin Operador", null, Categoria.EXCURSION, 1000, 0);
        ReporteExposicionOperador reporte = new ReporteExposicionOperador(List.of(s1));

        assertEquals(1, reporte.getFilas().size());
        assertEquals("Sin Proveedor", reporte.getFilas().get(0).operador());
    }

    @Test
    void reporteOcupacionCuposCasosBordeFechasYRegistrosNulos() {
        LocalDate hoy = LocalDate.of(2026, 7, 10);
        RegistroOcupacion reg = new RegistroOcupacion(Categoria.ALOJAMIENTO, 10, 5, hoy);

        ReporteOcupacionCupos r1 = new ReporteOcupacionCupos(null, hoy, hoy);
        assertTrue(r1.getFilas().isEmpty());

        ReporteOcupacionCupos r2 = new ReporteOcupacionCupos(List.of(reg), null, hoy);
        assertTrue(r2.getFilas().isEmpty());

        ReporteOcupacionCupos r3 = new ReporteOcupacionCupos(List.of(reg), hoy, null);
        assertTrue(r3.getFilas().isEmpty());

        ReporteOcupacionCupos r4 = new ReporteOcupacionCupos(Collections.emptyList(), hoy, hoy);
        assertTrue(r4.getFilas().isEmpty());

        ReporteOcupacionCupos.FilaOcupacion fila = new ReporteOcupacionCupos.FilaOcupacion(Categoria.ALOJAMIENTO, 10, 4, 6);
        ReporteOcupacionCupos r5 = new ReporteOcupacionCupos(List.of(fila));
        assertEquals(1, r5.getFilas().size());
        assertEquals(fila, r5.getFilas().get(0));
    }

    @Test
    void exportadoresManejanTextosNulos() {
        ReporteServiciosMasReservados.FilaServicioMasReservado filaConNombreNulo =
                new ReporteServiciosMasReservados.FilaServicioMasReservado(null, 1, 50.0);
        ReporteServiciosMasReservados reporte = new ReporteServiciosMasReservados(List.of(filaConNombreNulo));

        String html = exportadorHtml.visitar(reporte);
        assertTrue(html.contains("<td></td>"));

        String csv = exportadorCsv.visitar(reporte);
        assertTrue(csv.contains(",1,50.00"));
    }
}
