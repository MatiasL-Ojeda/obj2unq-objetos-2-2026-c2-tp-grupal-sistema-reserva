package ar.edu.unq.po2.tp.sistemareservas;

import java.time.LocalDate;

public class RegistroVenta {
    private Catalogo item;
    private double precioCobrado;
    private LocalDate fecha;

    public RegistroVenta(Catalogo item, double precioCobrado, LocalDate fecha) {
        this.item = item;
        this.precioCobrado = precioCobrado;
        this.fecha = fecha;
    }

    public Catalogo getItem() {
        return item;
    }

    public double getPrecioCobrado() {
        return precioCobrado;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
