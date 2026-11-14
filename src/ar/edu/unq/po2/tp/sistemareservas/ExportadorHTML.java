package ar.edu.unq.po2.tp.sistemareservas;

import java.util.Locale;

public class ExportadorHTML implements ReporteVisitor {

    @Override
    public String visitar(ReporteServiciosMasReservados r) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"reporte reporte-servicios\">\n");
        sb.append("  <thead>\n");
        sb.append("    <tr>\n");
        sb.append("      <th>Servicio / Paquete</th>\n");
        sb.append("      <th>Unidades Vendidas</th>\n");
        sb.append("      <th>Precio Promedio Cobrado</th>\n");
        sb.append("    </tr>\n");
        sb.append("  </thead>\n");
        sb.append("  <tbody>\n");
        for (var f : r.getFilas()) {
            sb.append("    <tr>\n");
            sb.append(String.format(Locale.US, "      <td>%s</td>\n", escapeHtml(f.nombreItem())));
            sb.append(String.format(Locale.US, "      <td>%d</td>\n", f.unidadesVendidas()));
            sb.append(String.format(Locale.US, "      <td>$%.2f</td>\n", f.precioPromedioCobrado()));
            sb.append("    </tr>\n");
        }
        sb.append("  </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }

    @Override
    public String visitar(ReporteOcupacionCupos r) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"reporte reporte-ocupacion\">\n");
        sb.append("  <thead>\n");
        sb.append("    <tr>\n");
        sb.append("      <th>Categoría</th>\n");
        sb.append("      <th>Cupos Totales</th>\n");
        sb.append("      <th>Cupos Reservados</th>\n");
        sb.append("      <th>Cupos Libres</th>\n");
        sb.append("    </tr>\n");
        sb.append("  </thead>\n");
        sb.append("  <tbody>\n");
        for (var f : r.getFilas()) {
            sb.append("    <tr>\n");
            sb.append(String.format(Locale.US, "      <td>%s</td>\n", escapeHtml(f.categoria().name())));
            sb.append(String.format(Locale.US, "      <td>%d</td>\n", f.cuposTotales()));
            sb.append(String.format(Locale.US, "      <td>%d</td>\n", f.cuposReservados()));
            sb.append(String.format(Locale.US, "      <td>%d</td>\n", f.cuposLibres()));
            sb.append("    </tr>\n");
        }
        sb.append("  </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }

    @Override
    public String visitar(ReporteExposicionOperador r) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table class=\"reporte reporte-exposicion\">\n");
        sb.append("  <thead>\n");
        sb.append("    <tr>\n");
        sb.append("      <th>Operador / Proveedor</th>\n");
        sb.append("      <th>Cantidad Ítems</th>\n");
        sb.append("      <th>Porcentaje del Catálogo</th>\n");
        sb.append("    </tr>\n");
        sb.append("  </thead>\n");
        sb.append("  <tbody>\n");
        for (var f : r.getFilas()) {
            sb.append("    <tr>\n");
            sb.append(String.format(Locale.US, "      <td>%s</td>\n", escapeHtml(f.operador())));
            sb.append(String.format(Locale.US, "      <td>%d</td>\n", f.cantidadItems()));
            sb.append(String.format(Locale.US, "      <td>%.2f%%</td>\n", f.porcentajeCatalogo()));
            sb.append("    </tr>\n");
        }
        sb.append("  </tbody>\n");
        sb.append("</table>");
        return sb.toString();
    }

    private String escapeHtml(String texto) {
        if (texto == null) return "";
        return texto.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;");
    }
}
