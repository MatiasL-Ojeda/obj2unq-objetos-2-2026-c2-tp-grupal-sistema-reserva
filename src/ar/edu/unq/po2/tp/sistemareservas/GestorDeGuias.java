package ar.edu.unq.po2.tp.sistemareservas;

import java.util.HashSet;
import java.util.Set; // Uso el set para no tener gias repetidos.

public class GestorDeGuias {
    private final Set<Guia> guias = new HashSet<>();

    public void agregarGuia (Guia guia) {
        this.guias.add(guia);
    } // agrego un nuevo guia.

    public boolean hayDisponibilidad(PuntoDeRecepcion punto) {
        return guias.stream().anyMatch(guia ->                                  // Le pregunto a cada guia.
                guia.isDisponible() && guia.getPuntoAsignado().equals(punto));  // si estan disponibles en dicho punto.
    }
}