package ar.edu.unq.po2.tp.sistemareservas;

import java.util.*;
import java.util.stream.Collectors;

public class ReporteExposicionOperador implements Reporte {

    public record FilaExposicion(String operador, int cantidadItems, double porcentajeCatalogo) {}

    private List<FilaExposicion> filas;

    public ReporteExposicionOperador(List<Catalogo> itemsCatalogo) {
        this.filas = procesarItems(itemsCatalogo);
    }

    private List<FilaExposicion> procesarItems(List<Catalogo> items) {
        if (items == null || items.isEmpty()) {
            return Collections.emptyList();
        }

        int totalItems = items.size();
        Map<String, List<Catalogo>> porOperador = items.stream()
                .collect(Collectors.groupingBy(item -> item.getProveedor() != null ? item.getProveedor() : "Sin Proveedor"));

        List<FilaExposicion> resultado = new ArrayList<>();
        for (Map.Entry<String, List<Catalogo>> entry : porOperador.entrySet()) {
            String op = entry.getKey();
            int cant = entry.getValue().size();
            double porcentaje = (cant * 100.0) / totalItems;
            resultado.add(new FilaExposicion(op, cant, porcentaje));
        }

        resultado.sort(Comparator.comparingDouble(FilaExposicion::porcentajeCatalogo).reversed()
                .thenComparing(FilaExposicion::operador));

        return resultado;
    }

    public List<FilaExposicion> getFilas() {
        return this.filas;
    }

    @Override
    public String exportar(ReporteVisitor visitor) {
        return visitor.visitar(this);
    }
}
