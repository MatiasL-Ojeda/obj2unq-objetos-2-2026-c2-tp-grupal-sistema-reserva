package ar.edu.unq.po2.tp.sistemareservas;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ReporteOcupacionCupos implements Reporte {

    public record FilaOcupacion(Categoria categoria, int cuposTotales, int cuposReservados, int cuposLibres) {}

    private List<FilaOcupacion> filas;

    public ReporteOcupacionCupos(List<RegistroOcupacion> registros, LocalDate desde, LocalDate hasta) {
        this.filas = procesarRegistros(registros, desde, hasta);
    }

    public ReporteOcupacionCupos(List<FilaOcupacion> filas) {
        this.filas = new ArrayList<>(Objects.requireNonNull(filas, "Las filas no pueden ser nulas"));
    }

    private List<FilaOcupacion> procesarRegistros(List<RegistroOcupacion> registros, LocalDate desde, LocalDate hasta) {
        if (registros == null || registros.isEmpty() || desde == null || hasta == null) {
            return Collections.emptyList();
        }

        List<RegistroOcupacion> enPeriodo = registros.stream()
                .filter(r -> !r.getFecha().isBefore(desde) && !r.getFecha().isAfter(hasta))
                .toList();

        Map<Categoria, List<RegistroOcupacion>> porCategoria = enPeriodo.stream()
                .collect(Collectors.groupingBy(RegistroOcupacion::getCategoria));

        List<FilaOcupacion> resultado = new ArrayList<>();
        for (Map.Entry<Categoria, List<RegistroOcupacion>> entry : porCategoria.entrySet()) {
            Categoria cat = entry.getKey();
            int totales = entry.getValue().stream().mapToInt(RegistroOcupacion::getCuposTotales).sum();
            int reservados = entry.getValue().stream().mapToInt(RegistroOcupacion::getCuposReservados).sum();
            int libres = Math.max(0, totales - reservados);
            resultado.add(new FilaOcupacion(cat, totales, reservados, libres));
        }

        resultado.sort(Comparator.comparing(f -> f.categoria().name()));
        return resultado;
    }

    public List<FilaOcupacion> getFilas() {
        return this.filas;
    }

    @Override
    public String exportar(ReporteVisitor visitor) {
        return visitor.visitar(this);
    }
}
