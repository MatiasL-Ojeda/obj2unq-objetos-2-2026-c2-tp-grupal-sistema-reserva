package ar.edu.unq.po2.tp.sistemareservas;

import java.time.LocalDate;

public class RegistroOcupacion {
    private Categoria categoria;
    private int cuposTotales;
    private int cuposReservados;
    private LocalDate fecha;

    public RegistroOcupacion(Categoria categoria, int cuposTotales, int cuposReservados, LocalDate fecha) {
        this.categoria = categoria;
        this.cuposTotales = cuposTotales;
        this.cuposReservados = cuposReservados;
        this.fecha = fecha;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getCuposTotales() {
        return cuposTotales;
    }

    public int getCuposReservados() {
        return cuposReservados;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
