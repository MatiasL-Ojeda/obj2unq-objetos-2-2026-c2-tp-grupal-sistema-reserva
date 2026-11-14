package ar.edu.unq.po2.tp.sistemareservas;

import java.util.Locale;

public class ExportadorCSV implements ReporteVisitor {

    @Override
    public String visitar(ReporteServiciosMasReservados r) {
        StringBuilder sb = new StringBuilder();
        sb.append("item,unidades_vendidas,precio_promedio_cobrado\n");
        for (var f : r.getFilas()) {
            sb.append(String.format(Locale.US, "\"%s\",%d,%.2f\n", escapeCsv(f.nombreItem()), f.unidadesVendidas(), f.precioPromedioCobrado()));
        }
        return sb.toString();
    }

    @Override
    public String visitar(ReporteOcupacionCupos r) {
        StringBuilder sb = new StringBuilder();
        sb.append("categoria,cupos_totales,cupos_reservados,cupos_libres\n");
        for (var f : r.getFilas()) {
            sb.append(String.format(Locale.US, "%s,%d,%d,%d\n", f.categoria().name(), f.cuposTotales(), f.cuposReservados(), f.cuposLibres()));
        }
        return sb.toString();
    }

    @Override
    public String visitar(ReporteExposicionOperador r) {
        StringBuilder sb = new StringBuilder();
        sb.append("operador,cantidad_items,porcentaje_catalogo\n");
        for (var f : r.getFilas()) {
            sb.append(String.format(Locale.US, "\"%s\",%d,%.2f\n", escapeCsv(f.operador()), f.cantidadItems(), f.porcentajeCatalogo()));
        }
        return sb.toString();
    }

    private String escapeCsv(String texto) {
        if (texto == null) return "";
        return texto.replace("\"", "\"\"");
    }
}
