package ar.edu.unq.po2.tp.sistemareservas;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReporteServiciosMasReservados implements Reporte {

    public record FilaServicioMasReservado(String nombreItem, int unidadesVendidas, double precioPromedioCobrado) {}

    private final List<FilaServicioMasReservado> filas;

    public ReporteServiciosMasReservados(List<RegistroVenta> ventas, LocalDate desde, LocalDate hasta) {
        this.filas = procesarVentas(ventas, desde, hasta);
    }

    public ReporteServiciosMasReservados(List<FilaServicioMasReservado> filas) {
        this.filas = new ArrayList<>(Objects.requireNonNull(filas, "Las filas no pueden ser nulas"));
    }

    private List<FilaServicioMasReservado> procesarVentas(List<RegistroVenta> ventas, LocalDate desde, LocalDate hasta) {
        if (ventas == null || ventas.isEmpty() || desde == null || hasta == null) {
            return Collections.emptyList();
        }

        List<RegistroVenta> ventasEnPeriodo = ventas.stream()
                .filter(v -> !v.getFecha().isBefore(desde) && !v.getFecha().isAfter(hasta))
                .toList();

        Map<String, List<RegistroVenta>> ventasPorItem = ventasEnPeriodo.stream()
                .collect(Collectors.groupingBy(v -> v.getItem().getNombre()));

        List<FilaServicioMasReservado> resultado = new ArrayList<>();
        for (Map.Entry<String, List<RegistroVenta>> entry : ventasPorItem.entrySet()) {
            String nombre = entry.getKey();
            List<RegistroVenta> lista = entry.getValue();
            int unidades = lista.size();
            double totalCobrado = lista.stream().mapToDouble(RegistroVenta::getPrecioCobrado).sum();
            double promedio = totalCobrado / unidades;
            resultado.add(new FilaServicioMasReservado(nombre, unidades, promedio));
        }

        resultado.sort(Comparator.comparingInt(FilaServicioMasReservado::unidadesVendidas).reversed()
                .thenComparing(FilaServicioMasReservado::nombreItem));

        return resultado;
    }

    public List<FilaServicioMasReservado> getFilas() {
        return this.filas;
    }

    @Override
    public String exportar(ReporteVisitor visitor) {
        return visitor.visitar(this);
    }
}
