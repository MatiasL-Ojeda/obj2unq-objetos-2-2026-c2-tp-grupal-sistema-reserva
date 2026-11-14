package ar.edu.unq.po2.tp.sistemareservas;

public interface ReporteVisitor {
    String visitar(ReporteServiciosMasReservados reporte);
    String visitar(ReporteOcupacionCupos reporte);
    String visitar(ReporteExposicionOperador reporte);
}
