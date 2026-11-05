package ar.edu.unq.po2.tp.sistemareservas;

import java.util.HashSet;
import java.util.Set;

public class GestorDeGuias {
    private final Set<Guia> guias = new HashSet<>();

    public void agregarGuia (Guia guia) {
        this.guias.add(guia);
    }

    public boolean hayDisponibilidad(PuntoDeRecepcion punto) {
        return guias.stream().anyMatch(guia ->
                guia.isDisponible() && guia.getPuntoAsignado().equals(punto));
    }
}