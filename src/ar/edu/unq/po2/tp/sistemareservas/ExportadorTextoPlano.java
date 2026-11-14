package ar.edu.unq.po2.tp.sistemareservas;

import java.util.Locale;

public class ExportadorTextoPlano implements ReporteVisitor {

    @Override
    public String visitar(ReporteServiciosMasReservados r) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE DE SERVICIOS MAS RESERVADOS ===\n");
        sb.append(String.format(Locale.US, "%-35s | %-17s | %-22s\n", "Servicio / Paquete", "Unidades Vendidas", "Precio Promedio Cobrado"));
        sb.append("--------------------------------------------------------------------------------\n");
        for (var f : r.getFilas()) {
            sb.append(String.format(Locale.US, "%-35s | %-17d | $%-21.2f\n", f.nombreItem(), f.unidadesVendidas(), f.precioPromedioCobrado()));
        }
        return sb.toString();
    }

    @Override
    public String visitar(ReporteOcupacionCupos r) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE DE OCUPACION Y CUPOS ===\n");
        sb.append(String.format(Locale.US, "%-20s | %-13s | %-16s | %-12s\n", "Categoria", "Cupos Totales", "Cupos Reservados", "Cupos Libres"));
        sb.append("-------------------------------------------------------------------------\n");
        for (var f : r.getFilas()) {
            sb.append(String.format(Locale.US, "%-20s | %-13d | %-16d | %-12d\n", f.categoria().name(), f.cuposTotales(), f.cuposReservados(), f.cuposLibres()));
        }
        return sb.toString();
    }

    @Override
    public String visitar(ReporteExposicionOperador r) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE DE EXPOSICION POR OPERADOR ===\n");
        sb.append(String.format(Locale.US, "%-30s | %-14s | %-23s\n", "Operador / Proveedor", "Cantidad Items", "Porcentaje del Catalogo"));
        sb.append("-------------------------------------------------------------------------\n");
        for (var f : r.getFilas()) {
            sb.append(String.format(Locale.US, "%-30s | %-14d | %6.2f%%\n", f.operador(), f.cantidadItems(), f.porcentajeCatalogo()));
        }
        return sb.toString();
    }
}
